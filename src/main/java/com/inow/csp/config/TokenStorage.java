package com.inow.csp.config;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.stereotype.Component;

import com.inow.csp.output.client.ClientResponseBean;

import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class TokenStorage {
    private static final String SECRET_KEY = Constants.SECRET_KEY_FOR_TOKEN_CACHE;
    private static final String ENCRYPTION_ALGORITHM = Constants.ENCRYPTION_ALGORITHM_FOR_TOKEN_CACHE;

    private SecretKey secretKey;
    private Map<String, String> tokenCache;

    public TokenStorage() {
        secretKey = generateSecretKey();
        tokenCache = new ConcurrentHashMap<>();
    }

    private SecretKey generateSecretKey() {
        byte[] decodedKey = Base64.getDecoder().decode(SECRET_KEY);
        return new SecretKeySpec(decodedKey, ENCRYPTION_ALGORITHM);
    }

    public void storeToken(String token) {
        try {
            Cipher cipher = Cipher.getInstance(ENCRYPTION_ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            byte[] encryptedBytes = cipher.doFinal(token.getBytes(StandardCharsets.UTF_8));
            String encryptedToken = Base64.getEncoder().encodeToString(encryptedBytes);
            // Store the encrypted token securely in cache
            tokenCache.put("encryptedToken", encryptedToken);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException e) {
            // Handle encryption error
        }
    }

    public ClientResponseBean retrieveToken() throws Exception {
        try {
            // Retrieve the encrypted token from the secure storage
            String encryptedToken = tokenCache.get("encryptedToken");

            Cipher cipher = Cipher.getInstance(ENCRYPTION_ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encryptedToken));
            String decryptedToken = new String(decryptedBytes, StandardCharsets.UTF_8);
            ClientResponseBean clientResponseBean = new ClientResponseBean();
            clientResponseBean.setJWTToken(decryptedToken);
            return clientResponseBean;
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException e) {
           throw new Exception("Something went wrong while retrieving JWT token from cache");
        }
    }

}
//ClientResponseBean
