package com.inow.csp.config;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class TokenStorage {
    private static final String SECRET_KEY = "XnoYisKh99KdWNS5vNtaEnmiCcmE1CbO7OYa+TPPkXU=";
    private static final String ENCRYPTION_ALGORITHM = "AES";

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

    public String retrieveToken() {
        try {
            // Retrieve the encrypted token from the secure storage
            String encryptedToken = tokenCache.get("encryptedToken");

            Cipher cipher = Cipher.getInstance(ENCRYPTION_ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encryptedToken));
            String decryptedToken = new String(decryptedBytes, StandardCharsets.UTF_8);
            return decryptedToken;
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException e) {
            // Handle decryption error
            return null;
        }
    }

}

