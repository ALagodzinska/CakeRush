package ui;

import model.Cake;
import model.CakeElements.CakeColor;
import model.CakeElements.Decoration;
import model.CakeElements.Glaze;
import model.CakeElements.Topping;
import ui.MenuOptions.GameMenuOptions;

import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;

@ExcludeFromJacocoGeneratedReport
// Stores all constants for the UI used in interaction with the user.
public class Constants {
    // String colors 
    public static final String DEFAULT_COLOR = "\u001B[0m";
    public static final String RED_COLOR = "\u001B[31m";
    public static final String GREEN_COLOR = "\u001B[32m";
    public static final String PURPLE_COLOR = "\u001B[35m";

    public static final String INVALID_INPUT_MESSAGE = "Invalid input value. Try again.\n";
    public static final String INSTRUCTIONS_FOR_INPUT = "Select a number from the list of options.";

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
            + "You have 7 seconds to remember all the elements\n";
    public static final String UNEXPECTED_ERROR_MESSAGE = "Something went wrong. Try starting a new round.";

    // Game screen constants
    public static final String MESSAGE_GO_BACK_TO_MAIN_MENU = "Going back to the main menu...\n";
    public static final String MESSAGE_GO_BACK_TO_GAME_MENU = "Going back to the game screen...\n";
    public static final String START_ROUND_PROMPT = "Start a new round?\n1.YES 2.NO\n";
    public static final String GAME_MENU_PROMPT = Constants.INSTRUCTIONS_FOR_INPUT + "\n" 
            + GameMenuOptions.listAllOptions();
    public static final String GAME_OVER_MESSAGE = "This game is over!\nCreate a new game to start playing.";
    public static final String EMPTY_ROUNDS_MESSAGE = "This game doesn't have any rounds.\n";
    public static final String ROUNDS_MESSAGE = "List of played rounds:\n";

    // Main screen constants
    public static final String SELECT_OLD_GAME_PROMPT = "Select the number of the game you want to continue playing\n"
            + "or type 'exit' to go back to the main menu";
    public static final String LOAD_GAMES_PROMPT = "Do you want to load saved games?\n1.YES 2.NO\n";
    public static final String SAVE_GAMES_PROMPT = "Do you want to save played games?\n1.YES 2.NO\n";
    // Visual noise 
    public static final String VISUAL_NOISE = "                       )\\\r\n" 
                + "                      (__)\r\n" 
                + "                       /\\\r\n" 
                + "                      [[]]\r\n"  
                + "               @@@@@@@[[]]@@@@@@\r\n"  
                + "         @@@@@@@@@@@@@[[]]@@@@@@@@@@@@@@\r\n"  
                + "     @@@@@@@          [[]]          @@@@@@@\r\n"  
                + " @@@@@@@@@            [[]]            @@@@@@@@@\r\n"  
                + "@@@@@@@               [[]]               @@@@@@@\r\n"  
                + "!@@@@@@@@@@                          @@@@@@@@@@!\r\n"  
                + "!      @@@@@@@@@                @@@@@@@@@      !\r\n"  
                + "!        @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@        !\r\n"  
                + "!              @@@@@@@@@@@@@@@@@@@             !\r\n"  
                + "!                                              !\r\n"  
                + "!                                              !\r\n"  
                + "!                                              !\r\n" 
                + "!!!!!!!!!!!                          !!!!!!!!!!!\r\n" 
                + "     !!!!!!!!!!!                !!!!!!!!!!!\r\n"
                + "         !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!\r\n"; 

    public static final String GAME_TITLE = "   _____      _        _____           _     \r\n" 
                                            + "  / ____|    | |      |  __ \\         | |    \r\n" 
                                            + " | |     __ _| | _____| |__) |   _ ___| |__  \r\n" 
                                            + " | |    / _` | |/ / _ \\  _  / | | / __| '_ \\ \r\n"
                                            + " | |___| (_| |   <  __/ | \\ \\ |_| \\__ \\ | | |\r\n"
                                            + "  \\_____\\__,_|_|\\_\\___|_|  \\_\\__,_|___/_| |_|\r\n" 
                                            + "                                             \r\n";

    public static final String SEPARATOR = ".oOo.oOo.oOo.oOo.oOo.oOo.oOo.oOo.oOo.oOo.oOo.oOo.oOo.oOo.oOo."
                                + ".oOo.oOo.oOo.oOo.oOo.oOo.oOo.oOo.oOo.oOo.oOo.oOo.oOo.oOo.";
}
