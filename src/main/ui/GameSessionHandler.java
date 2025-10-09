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
        String menuPrompt = Constants.INSTRUCTIONS_FOR_INPUT + "\n" +  GameMenuOptions.listAllOptions();
        GameMenuOptions selectedOption;
        int selectedIndex;

        do {
            selectedIndex = InputValidator.getValidUserChoice(scanner, 
                menuPrompt, 1, 4) - 1;
            selectedOption = GameMenuOptions.values()[selectedIndex];

            switch (selectedOption) {
                case PLAY:     
                    startGame(game);          
                    break;
                case SHOW_ROUNDS:
                    printPlayedRounds(game.getRounds());
                    break;
                case GET_SCORE:
                    displayGameScore(game.getTotalScore());
                    break;
                case EXIT:
                    System.out.println("Going back to the main menu...");
                    break;
            }
        } while (selectedOption != GameMenuOptions.EXIT); 
    }

    // MODIFIES: GameSession
    // EFFECTS: Starts the game, and before each round asks the user whether to continue. Continues playing 
    // until the game is lost or user chooses to stop.
    private void startGame(GameSession game) {
        if (!game.isFinished()) {
            int userSelection;
            do {
                userSelection = InputValidator.getValidUserChoice(scanner, "Start the round?\n1.YES 2.NO", 
            1, 2);
                if (userSelection == 1) {
                    game.addRound(roundHandler.playRound());  
                    System.out.println("Number of lives left: " + game.getLivesLeft() + "\n"); 
                } else {                    
                    break;
                }            
            } while (!game.isFinished());
        } else {
            System.out.println("This game is over!\n Create a new game to start playing.");
        }
        
        System.out.println("Going back to the game screen...");
    }

    // EFFECTS: Displays a list of all played rounds in current game. If no rounds were played, 
    // shows a message to the user.
    private void printPlayedRounds(ArrayList<GameRound> rounds) {
        if (rounds.size() == 0) {
            System.out.println("No rounds in this game");
            return;
        }

        for (int i = 0; i < rounds.size(); i++) {
            roundHandler.printRoundSummary(rounds.get(i), i + 1);
        }
        System.out.println();
    }
 
    // EFFECTS: Displays the total score earned in the current game.
    private void displayGameScore(int totalScore) {
        System.out.println("The total score: " + totalScore);
        System.out.println();
    }
}
