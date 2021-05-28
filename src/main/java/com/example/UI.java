package com.example;

import java.util.Scanner;
import com.example.utils.Credentials;

public class UI implements Runnable {

    @Override
    public void run() {
        SpotifyDataHandler spotifyDataHandler = new SpotifyDataHandler();
        Scanner scanner = new Scanner(System.in);

        String selection = "";
        System.out.println("Program running");
        System.out.println("Select:");
        System.out.println("1. print accesscode");
        System.out.println("2. get something");

        while (!selection.equals("0")) {
            selection = scanner.nextLine();

            switch (selection) {
                case "1":
                    System.out.println(Credentials.getInstance());
                case "2":
                    spotifyDataHandler.getTokens();
                    System.out.println(Credentials.getInstance());
            }
        }

        scanner.close();
    }
}
