package com.example.server;

import com.example.SpotifyDataHandler;
import com.example.utils.Credentials;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CallbackController {

    @GetMapping("/callback")
    public Callback callback(@RequestParam(value = "code", defaultValue = "foobar") String code) {
        Credentials.getInstance().setAccessCode(code);
        SpotifyDataHandler spotifyDataHandler = new SpotifyDataHandler();
        spotifyDataHandler.getTokens();
        return new Callback(code);
    }
}
