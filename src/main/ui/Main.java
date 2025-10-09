package ui;

import java.util.Random;
import java.util.Scanner;

import model.GameSession;

public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println("Welcome to my project!");
        Random random = new Random();
        Scanner scanner = new Scanner(System.in);
        RoundHandler rh = new RoundHandler(scanner, random);

        // rh.playRound();

        GameSessionHandler gh = new GameSessionHandler(scanner, rh);
        GameSession game = new GameSession(1);
        gh.runGameMenu(game);
    }
}
