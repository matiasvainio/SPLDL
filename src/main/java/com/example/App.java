package com.example;

import com.example.utils.Credentials;
import com.example.utils.FileHandler;

public class App {
    public static void main(String[] args) {
        FileHandler fileHandler = new FileHandler();
        Credentials credentials = fileHandler.read();

        System.out.println(credentials);

    }
}
