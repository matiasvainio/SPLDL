package com.example;

import com.example.utils.Credentials;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class SpotifyDataHandler {
    private final Credentials credentials;

    public SpotifyDataHandler(Credentials credentials) {
        this.credentials = credentials;
    }

    public void get() {
        try {
            URL url = new URL("https://google.com");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            System.out.println(getUri());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getUri() {
        return "https://accounts.spotify.com/authorize?client_id=" +
                credentials.getClientId() +
                "&response_type=code&" +
                "redirect_uri=https%3A%2F%2Flocalhost:8080%2Fcallback";
    }
}
