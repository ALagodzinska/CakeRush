package ui.screens;

import java.util.Random;
import java.util.Scanner;

import model.Cake;
import model.GameRound;
import model.CakeElements.CakeColor;
import model.CakeElements.Decoration;
import model.CakeElements.Glaze;
import model.CakeElements.Topping;
import ui.Constants;
import ui.InputValidator;

// Handler that manages one round.
public class RoundScreenHandler {

    private Scanner scanner;        // Scanner for reading user input
    private Random random;          // Random used to generate random rounds

    // EFFECTS: creates round handler that uses passed Scanner for user input 
    // and Random for generating random game round.
    public RoundScreenHandler(Scanner scanner, Random random) {
        this.scanner = scanner;
        this.random = random;
    }

    // MODIFIES: the created GameRound.
    // EFFECTS: Creates a new GameRound, plays the game: displays the target cake and matches it with user input 
    // values, updates and displays round status and returns the completed GameRound.
    public GameRound playRound() {
        GameRound round = new GameRound(random); 

        displayRoundStart(round);
        System.out.println(Constants.SEPARATOR);
        
        hideCakeWithVisualNoise();  
        System.out.println(Constants.SEPARATOR);      

        playUserTurn(round);        

        displayRoundEnd(round.isVictory());

        return round;
    }

    // EFFECTS: Displays an introduction message and the target cake to the user for 5 seconds.
    private void displayRoundStart(GameRound round) {
        System.out.println(Constants.ROUND_INTRO_MESSAGE);
        System.out.println(Constants.PURPLE_COLOR + "TARGET CAKE:" + Constants.DEFAULT_COLOR);
        System.out.println(round.getTargetCake());
        System.out.println();

        // Sleep for 5 seconds - time for user to remember the target cake
        try {            
            Thread.sleep(5000);
        } catch (Exception ex) {
            System.out.println("Something went wrong");
        }
        
    }

    // EFFECTS: Prints a long message to the console to hide the target cake from the user.
    private void hideCakeWithVisualNoise() {
        System.out.println(String.valueOf(Constants.VISUAL_NOISE).repeat(3));
    }

    // MODIFIES: the Game round passed as a parameter
    // EFFECTS: Gets user input, updates the cake, and completes the round.
    private void playUserTurn(GameRound round) {
        System.out.println("Your turn!");
        setUserInputs(round.getUserCake());
        round.finishRound();
    }

    // MODIFIES: the cake passed as a parameter
    // EFFECTS: Gets user input for all cake elements from console and updates the round user cake 
    // with the chosen values.
    private void setUserInputs(Cake cakeToModify) {
        int numOfTiers = InputValidator.getValidUserChoice(scanner, Constants.NUMBER_OF_TIERS_PROMPT, 
                Cake.MIN_NUM_OF_TIERS, Cake.MAX_NUM_OF_TIERS);
        cakeToModify.setNumberOfTiers(numOfTiers);

        System.out.println(Constants.INSTRUCTIONS_FOR_INPUT);
        int cakeColorIndex = InputValidator.getValidUserChoice(scanner, Constants.CAKE_COLOR_PROMPT, 
                1, CakeColor.length()) - 1;
        cakeToModify.setCakeColor(CakeColor.values()[cakeColorIndex]);

        int glazeIndex = InputValidator.getValidUserChoice(scanner, Constants.GLAZE_PROMPT, 1, Glaze.length()) - 1;
        cakeToModify.setGlaze(Glaze.values()[glazeIndex]);

        int toppingIndex = InputValidator.getValidUserChoice(scanner, Constants.TOPPING_PROMPT, 
                1, Topping.length()) - 1;
        cakeToModify.setTopping(Topping.values()[toppingIndex]);

        int decorationIndex = InputValidator.getValidUserChoice(scanner, Constants.DECORATION_PROMPT, 
                1, Decoration.length()) - 1;
        cakeToModify.setDecoration(Decoration.values()[decorationIndex]);
    }

    // EFFECTS: Dispalys a victory message if the round is won, otherwise, displays a loss message.
    private void displayRoundEnd(boolean isWon) {
        if (isWon) {
            System.out.println(Constants.GREEN_COLOR + Constants.VICTORY_MESSAGE + Constants.DEFAULT_COLOR);
        } else {            
            System.out.println(Constants.RED_COLOR + Constants.LOSS_MESSAGE + Constants.DEFAULT_COLOR);
        }
    }

    // EFFECTS: Prints a summary for the round
    public void printRoundSummary(GameRound round, int roundNumber) {
        if (round.isVictory()) {
            System.out.println(roundNumber + ": Victory round!");
            System.out.println("\tCreated cake: " + round.getTargetCake());            
        } else {
            System.out.println(roundNumber + ": Lost Round");
            System.out.println("\tTarget cake : " + round.getTargetCake());
            System.out.println("\tYour cake: " + round.getUserCake());
        }
    }
}
