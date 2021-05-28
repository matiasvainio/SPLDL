package com.example;

import com.example.utils.FileHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class App {
    public static void main(String[] args) {
        FileHandler fileHandler = new FileHandler();
        fileHandler.read();
        SpotifyDataHandler spotifyDataHandler = new SpotifyDataHandler();
        System.out.println(spotifyDataHandler.getUri());
        SpringApplication.run(App.class);

        UI ui = new UI();
        Thread thread = new Thread(ui);
        thread.start();
    }
}
