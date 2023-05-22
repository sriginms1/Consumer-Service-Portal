package com.inow.csp.config;


import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.ClientRequest;
import org.springframework.web.reactive.function.client.ClientResponse;

import org.springframework.web.reactive.function.client.WebClient;

import com.inow.csp.controller.ClientController;
import com.inow.csp.exception.CustomHttpClientException;
import com.inow.csp.exception.CustomHttpServerException;
import com.inow.csp.exception.TokenExpiredException;
import com.inow.csp.output.client.ClientResponseBean;




import reactor.core.publisher.Mono;

@Component
public class AuthTokenAspects {
    
    private WebClient webClient;
    private final TokenStorage tokenStorage;
    private ClientController clientController;
    
    @Autowired
    public AuthTokenAspects(WebClient.Builder webClientBuilder, TokenStorage tokenStorage, ClientController clientController) {
        this.webClient = webClientBuilder.baseUrl(Constants.SERVER_HOST).build();
        this.tokenStorage = tokenStorage;
        this.clientController = clientController;
    }
    
    @Before("@annotation(com.inow.csp.annotation.AuthTokenRequired)")
    public void addAuthToken(ClientRequest request) throws Exception {
        // Generate and add the JWT token to the request headers
        String authToken = generateAuthToken();
        request.headers().add("Authorization", "Bearer " + authToken);
    }
    
    @AfterReturning(pointcut = "@annotation(com.inow.csp.annotation.AuthTokenRequired)", returning = "response")
    public <T> Mono<T> handleResponse(Mono<T> response) {
        // Handle the response if needed
        return response;
    }
    
    private String generateAuthToken() throws Exception {
    	String jwtTokenString = null;
    	try {
			ClientResponseBean token = tokenStorage.retrieveToken();
			jwtTokenString = token.getJWTToken();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new Exception("Something went wrong while retrieving JWT token from cache");
		}
        return jwtTokenString;
    }
    
    public <T> Mono<T> makeGetRequest(String uri, Class<T> responseType) {
        return webClient.get()
                .uri(uri)
                .retrieve()
                .bodyToMono(responseType);
    }
    
    public <T> Mono<T> makePostRequest(String uri, Object requestBody, Class<T> responseType) {

        return webClient.post()
                .uri(uri)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(requestBody))
                .retrieve()
                .onStatus(status -> status.is4xxClientError(), response -> handleClientError(response, uri, requestBody, responseType))
                .onStatus(status -> status.is5xxServerError(), response -> handleServerError(response, uri, requestBody, responseType))
                .bodyToMono(responseType);
        
    }
    
    
    private Mono<? extends Throwable> handleClientError(ClientResponse response, String uri, Object requestBody, Class<?> responseType) {
        return response.bodyToMono(String.class)
                .flatMap(errorBody -> {
                    if (errorBody.contains("TokenExpiredException")) {
                        // Handle TokenExpiredException error here
                        String errorMessage = "Token has been expired";

                        // Make the call to the "/generateToken" controller method
                        return Mono.fromSupplier(() -> {
                            try {
                                return this.clientController.generateToken();
                            } catch (Exception e) {
                                throw new RuntimeException(e);
                            }
                        }).flatMap(responseEntity -> {
                            ClientResponseBean retrievedToken = responseEntity.getBody();
                            if (retrievedToken != null && retrievedToken.getJWTToken() != null) {
                                // Retry the makePostRequest method
                                return makePostRequestWithRetry(uri, requestBody, responseType, errorMessage)
                                        .then(Mono.<Throwable>empty());
                            } else {
                                // Return an error
                                return Mono.error(new CustomHttpClientException("Token retrieval error"));
                            }
                       });

                    } else {
                        String errorMessage = "Client error: " + response.statusCode() + " - " + errorBody;
                        return Mono.error(new CustomHttpClientException(errorMessage));
                    }
                });
    }

    private <T> Mono<? extends T> makePostRequestWithRetry(String uri, Object requestBody, Class<T> responseType, String errorMessage) {
        return makePostRequest(uri, requestBody, responseType)
                .onErrorResume(throwable -> {
                    if (throwable instanceof TokenExpiredException) {
                        // Retry the makePostRequest method
                        return makePostRequestWithRetry(uri, requestBody, responseType, errorMessage);
                    } else {
                        // Return the original error
                        return Mono.error(throwable);
                    }
                })
                .flatMap(response -> Mono.error(new TokenExpiredException(errorMessage)));
    }


	
    private Mono<? extends Throwable> handleServerError(ClientResponse response, String uri, Object requestBody, Class<?> responseType) {
        return response.bodyToMono(String.class)
                .flatMap(errorBody -> {
                    if (errorBody.contains("TokenExpiredException")) {
                        // Handle TokenExpiredException error here
                        String errorMessage = "Token has been expired";

                        // Make the call to the "/generateToken" controller method
                        return Mono.fromSupplier(() -> {
                            try {
                                return this.clientController.generateToken();
                            } catch (Exception e) {
                                throw new RuntimeException(e);
                            }
                        }).flatMap(responseEntity -> {
                            ClientResponseBean retrievedToken = responseEntity.getBody();
                            if (retrievedToken != null && retrievedToken.getJWTToken() != null) {
                                // Retry the makePostRequest method
                                return makePostRequestWithRetry(uri, requestBody, responseType, errorMessage)
                                        .then(Mono.<Throwable>empty());
                            } else {
                                // Return an error
                                return Mono.error(new CustomHttpServerException("Token retrieval error"));
                            }
                       });

                    } else {
                        String errorMessage = "Server error: " + response.statusCode() + " - " + errorBody;
                        return Mono.error(new CustomHttpServerException(errorMessage));
                    }
                });
    }

}
