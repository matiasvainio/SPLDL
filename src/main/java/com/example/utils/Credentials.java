package com.example.utils;

import java.util.Base64;

public class Credentials {
    private static Credentials INSTANCE;

    private String clientId;
    private String clientSecret;
    private String accessCode;
    private String accessToken;
    private String refreshToken;


    private Credentials() { }

    public static Credentials getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Credentials();
        }
        return INSTANCE;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }
    
    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public String getAccessCode() {
        return accessCode;
    }

    public void setAccessCode(String accessCode) {
        this.accessCode = accessCode;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getAuthorizationCode() {
        String originalInput = getClientId() + ":" + getClientSecret();
        return "Basic " + Base64.getEncoder().encodeToString(originalInput.getBytes());
    }

    @Override
    public String toString() {
        return "Credentials [accessCode=" + accessCode + ", accessToken=" + accessToken
                + ", clientId=" + clientId + ", clientSecret=" + clientSecret + ", refreshToken="
                + refreshToken + "]";
    }

}