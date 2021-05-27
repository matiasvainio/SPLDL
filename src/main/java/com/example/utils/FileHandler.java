package com.example.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import com.google.gson.Gson;

public class FileHandler {
    private final Gson gson;

    public FileHandler() {
        gson = new Gson();
    }

    public Credentials read() {
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

        return deserialize(sb.toString());
    }

    public Credentials deserialize(String s) {
        return gson.fromJson(s, Credentials.class);
    }
}
