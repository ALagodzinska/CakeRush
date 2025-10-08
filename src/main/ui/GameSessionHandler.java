package ui;

import java.util.ArrayList;
import java.util.Scanner;

import model.GameRound;
import model.GameSession;

// Handler that manages one Game Session.
public class GameSessionHandler {
    // stores menu options for the game
    enum GameMenuOptions {
        PLAY("Play the game"),
        SHOW_ROUNDS("Show the played rounds"),
        GET_SCORE("Total Score"),
        EXIT("Exit");

        private String text;

        GameMenuOptions(String text) {
            this.text = text;
        }

        public String getText() {
            return text;
        }

        public static String listAllOptions() {
            String summary = "";

            for (int i = 1; i <= values().length; i++) {
                summary += i + ": " + values()[ i - 1 ].getText() + " ";
            }

            return summary;
        }
    }

    private Scanner scanner;
    private RoundHandler roundHandler;

    // EFFECTS: creates game handler that uses the given Scanner for user input 
    // and the provided RoundHandler to manage rounds within a game.
    public GameSessionHandler(Scanner scanner, RoundHandler roundHandler) {
        this.scanner = scanner;
        this.roundHandler = roundHandler;
    }

    // EFFECTS: Displays the game menu and based on user input, either starts the game, shows 
    // the list of played rounds, or displays the total score. Exits game menu when the user chooses to exit.
    public void runGameMenu(GameSession game) {
        // stub
    }

    // MODIFIES: GameSession
    // EFFECTS: Starts the game, and before each round asks the user whether to continue. Continues playing 
    // until the game is lost or user chooses to stop.
    private void startGame(GameSession game) {
        // stub
    }

    // EFFECTS: Displays a list of all played rounds in current game. If no rounds were played, shows a message to the user.
    private void printPlayedRounds(ArrayList<GameRound> rounds){
        // stub
    }
 
    // EFFECTS: Displays the total score earned in the current game.
    private void displayGameScore(int totalScore) {
        // stub
    }

    // EFFECTS: Exits the current game.
    private void exitGame() {
        // stub
    }
}
