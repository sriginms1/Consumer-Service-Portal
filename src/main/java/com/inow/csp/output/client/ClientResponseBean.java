package com.inow.csp.output.client;

public class ClientResponseBean {

	private String JWTToken;

    public ClientResponseBean(String token) {
        this.JWTToken = token;
    }

    public String getJWTToken() {
        return JWTToken;
    }

}
