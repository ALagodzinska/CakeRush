package ui;

import model.Cake;
import model.CakeElements.CakeColor;
import model.CakeElements.Decoration;
import model.CakeElements.Glaze;
import model.CakeElements.Topping;

public class Constants {
    public static final String INVALID_INPUT_MESSAGE = "Invalid input value. Try again.";
    public static final String NUMBER_OF_TIERS_PROMPT = "What was the number of tiers of the cake?\n" 
            + "Choose a number from " + Cake.MIN_NUM_OF_TIERS + "to "
            + Cake.MAX_NUM_OF_TIERS;
    public static final String CAKE_COLOR_PROMPT = "What was the color of the cake?\n" 
            + "Choose a number from the list of options\n"
            + CakeColor.listAllOptions();
    public static final String GLAZE_PROMPT = "What was the glaze of the cake?\n" 
            + "Choose a number from the list of options\n"
            + Glaze.listAllOptions();
    public static final String TOPPING_PROMPT = "What was the topping of the cake?\n" 
            + "Choose a number from the list of options\n"
            + Topping.listAllOptions();
    public static final String DECORATION_PROMPT = "What was the decoration of the cake?\n" 
            + "Choose a number from the list of options\n"
            + Decoration.listAllOptions();
}
