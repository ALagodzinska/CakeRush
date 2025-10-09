package ui;

import java.util.Random;
import java.util.Scanner;

import model.Cake;
import model.GameRound;
import model.CakeElements.CakeColor;
import model.CakeElements.Decoration;
import model.CakeElements.Glaze;
import model.CakeElements.Topping;

// Handler that manages one round.
public class RoundHandler {

    private Scanner scanner;
    private Random random;

    // EFFECTS: creates round handler that uses passed Scanner for user input 
    // and Random for generating random game round.
    public RoundHandler(Scanner scanner, Random random) {
        this.scanner = scanner;
        this.random = random;
    }

    // MODIFIES: the created GameRound.
    // EFFECTS: Creates a new GameRound, plays the game: displays the target cake and matches it with user input 
    // values, updates and displays round status and returns the completed GameRound.
    public GameRound playRound() {
        GameRound round = new GameRound(random); 

        displayRoundStart(round);
        hideCakeWithVisualNoise();

        playUserTurn(round);        

        displayRoundEnd(round.isVictory());

        return round;
    }

    // EFFECTS: Displays an introduction message and the target cake to the user for 5 seconds.
    private void displayRoundStart(GameRound round) {
        System.out.println(Constants.ROUND_INTRO_MESSAGE);
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
        String line = String.valueOf("~").repeat(100);

        for (int i = 0; i < 50; i++) {
            System.out.println(line);
        }
    }

    // MODIFIES: the Game round passed as a parameter
    // EFFECTS: Gets user input, updates the cake, and completes the round.
    private void playUserTurn(GameRound round) {
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
            System.out.println(Constants.VICTORY_MESSAGE);
        } else {            
            System.out.println(Constants.LOSS_MESSAGE);
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
