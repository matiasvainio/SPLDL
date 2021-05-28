package com.example;

import com.example.utils.Credentials;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class SpotifyDataHandler {

    public void get() {
        try {
            URL url = new URL(
                    "https://api.spotify.com/v1/artists/1vCWHaC5f2uS3yhpwWbIA6/albums?album_type=SINGLE&offset=20&limit=10");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            System.out.println(getUri());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getUri() {
        return "https://accounts.spotify.com/authorize?client_id="
                + Credentials.getInstance().getClientId() + "&response_type=code&"
                + "redirect_uri=http%3A%2F%2Flocalhost:8080%2Fcallback";
    }

    public Map<String, Object> getTokens() {
        Map<String, Object> map = new HashMap<>();
        if (Credentials.getInstance().getRefreshToken() == null) {
            String code = Credentials.getInstance().getAccessCode();
            String redirectUri = "http://localhost:8080/callback";
            String urlParams = "&grant_type=authorization_code" + "&code=" + code + "&redirect_uri="
                    + redirectUri;

            map = getToken(urlParams);
            Credentials.getInstance().setAccessToken(map.get("access_token").toString());
            Credentials.getInstance().setRefreshToken(map.get("refresh_token").toString());

        } else {
            String token = Credentials.getInstance().getRefreshToken();
            String urlParams = "grant_type=refresh_token&refresh_token=" + token;
            map = getToken(urlParams);
        }

        return map;
    }

    private Map<String, Object> getToken(String urlParams) {
        Map<String, Object> map = new HashMap<>();
        var objectMapper = new ObjectMapper();
        try {
            URL url = new URL("https://accounts.spotify.com/api/token");
            byte[] postData = urlParams.getBytes(StandardCharsets.UTF_8);

            HttpURLConnection con = (HttpURLConnection) url.openConnection();

            con.setDoOutput(true);
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-type", "application/x-www-form-urlencoded");
            con.setRequestProperty("Authorization",
                    Credentials.getInstance().getAuthorizationCode());

            try (var wr = new DataOutputStream(con.getOutputStream())) {
                wr.write(postData);
            }

            StringBuilder content;

            try (var br = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
                String line;
                content = new StringBuilder();

                while ((line = br.readLine()) != null) {
                    content.append(line);
                    content.append(System.lineSeparator());
                }
            }

            map = objectMapper.readValue(content.toString(),
                    new TypeReference<Map<String, Object>>() {});


            System.out.println(map);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return map;
    }
}
