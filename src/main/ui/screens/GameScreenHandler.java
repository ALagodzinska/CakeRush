package ui.screens;

import java.util.ArrayList;
import java.util.Scanner;

import model.GameRound;
import model.GameSession;
import ui.Constants;
import ui.InputValidator;
import ui.MenuOptions.GameMenuOptions;

// Handler that manages one Game Session.
public class GameScreenHandler { 
    private Scanner scanner;                    // Scanner for reading user input
    private RoundScreenHandler roundHandler;    // handler that manages the game round

    // EFFECTS: creates game handler that uses the given Scanner for user input 
    // and the provided RoundHandler to manage rounds within a game.
    public GameScreenHandler(Scanner scanner, RoundScreenHandler roundHandler) {
        this.scanner = scanner;
        this.roundHandler = roundHandler;
    }

    // EFFECTS: Displays the game menu and based on user input, either starts the game, shows 
    // the list of played rounds, or displays the total score. Exits game menu when the user chooses to exit.
    public void runGameMenu(GameSession game) {
        System.out.println(Constants.SEPARATOR);
        System.out.println("Game Nr." + game.getGameID());
        GameMenuOptions selectedOption;
        int selectedIndex;

        do {
            selectedIndex = InputValidator.getValidUserChoice(scanner, 
                Constants.GAME_MENU_PROMPT, 1, GameMenuOptions.values().length) - 1;
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
                    System.out.println(Constants.MESSAGE_GO_BACK_TO_MAIN_MENU);
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
                userSelection = InputValidator.getValidUserChoice(scanner, Constants.START_ROUND_PROMPT, 
            1, 2);
                if (userSelection == 1) {
                    try {
                        game.addRound(roundHandler.playRound());  
                        System.out.println("Number of lives left: " + game.getLivesLeft() + "\n"); 
                    } catch (InterruptedException ex) {
                        System.out.println(Constants.UNEXPECTED_ERROR_MESSAGE);
                    }                    
                } else {                    
                    break;
                }            
            } while (!game.isFinished());
        } else {
            System.out.println(Constants.GAME_OVER_MESSAGE);
            System.out.println("Total score: " + game.getTotalScore() + "\n");
        }
        
        System.out.println(Constants.MESSAGE_GO_BACK_TO_GAME_MENU);
    }

    // EFFECTS: Displays a list of all played rounds in current game. If no rounds were played, 
    // shows a message to the user.
    private void printPlayedRounds(ArrayList<GameRound> rounds) {        
        if (rounds.size() == 0) {
            System.out.println(Constants.EMPTY_ROUNDS_MESSAGE);
            return;
        }

        System.out.println(Constants.ROUNDS_MESSAGE);
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

    // EFFECTS: Prints a summary for the round
    public void printGameSummary(GameSession game) {
        String gameState = game.isFinished() ? "Completed" : "In-Progress";
        System.out.println("\t" + game.getGameID() + ": " + gameState + " Game");
        System.out.println("\t\tTotal score: " + game.getTotalScore());
    }
}
