package com.example.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class FileHandler {
    private ObjectMapper objectMapper;

    public FileHandler() {
        objectMapper = new ObjectMapper();
    }

    public void read() {
        InputStream is = getClass().getClassLoader().getResourceAsStream("credentials.json");

        StringBuilder sb = new StringBuilder();
        try (Reader reader = new BufferedReader(new InputStreamReader(is, Charset.forName(StandardCharsets.UTF_8.name())))) {
            int c = 0;
            while ((c = reader.read()) != -1) {
                sb.append((char) c);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            Credentials.getInstance().setClientId(deserialize(sb.toString()).getClientId());
            Credentials.getInstance().setClientSecret(deserialize(sb.toString()).getClientSecret());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    public Credentials deserialize(String s) throws JsonMappingException, JsonProcessingException {
        return objectMapper.readValue(s, Credentials.class);
    }
}
