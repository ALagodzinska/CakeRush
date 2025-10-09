package ui;

import model.Cake;
import model.CakeElements.CakeColor;
import model.CakeElements.Decoration;
import model.CakeElements.Glaze;
import model.CakeElements.Topping;

public class Constants {
    public static final String INVALID_INPUT_MESSAGE = "Invalid input value. Try again.\n";
    public static final String INSTRUCTIONS_FOR_INPUT = "Choose a number from the list of options.";

    // Round constants
    public static final String NUMBER_OF_TIERS_PROMPT = "How many tiers did the cake have?”\n" 
            + "Choose a number from " + Cake.MIN_NUM_OF_TIERS + " to "
            + Cake.MAX_NUM_OF_TIERS;
    public static final String CAKE_COLOR_PROMPT = "What was the color of the cake?\n"
            + CakeColor.listAllOptions();
    public static final String GLAZE_PROMPT = "What was the glaze of the cake?\n" 
            + Glaze.listAllOptions();
    public static final String TOPPING_PROMPT = "What was the topping of the cake?\n"
            + Topping.listAllOptions();
    public static final String DECORATION_PROMPT = "What was the decoration of the cake?\n"
            + Decoration.listAllOptions();
    public static final String VICTORY_MESSAGE = "Congratulations! You won this round!\n";
    public static final String LOSS_MESSAGE = "Wrong cake! You've lost this round!\n";
    public static final String ROUND_INTRO_MESSAGE = "Generating random cake...\n"
            + "You have 5 seconds to remember all the elements\n";

    // Game constants

    public static final String GAME_MENU = "1. PLAY THE GAME\n"
                + "2. SEE THE LIST OF PLAYED ROUNDS\n"
                + "3. SEE TOTAL SCORE\n"
                + "4. EXIT";
}
