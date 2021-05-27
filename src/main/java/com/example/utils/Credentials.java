package com.example.utils;

public class Credentials {
    private String clientId;
    private String clientSecret;
    private String accessCode;

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

    @Override
    public String toString() {
        return "Credentials [clientId=" + clientId + ", clientSecret=" + clientSecret + ", accessCode=" + accessCode + "]";
    }
}