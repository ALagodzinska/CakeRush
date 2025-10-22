package ui.screens;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import model.GameLibrary;
import model.GameSession;
import persistence.JsonReader;
import persistence.JsonWriter;
import ui.Constants;
import ui.InputValidator;
import ui.MenuOptions.MainMenuOptions;

import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;

@ExcludeFromJacocoGeneratedReport
// Manages the main screen and all existing games, allows to create a new game instance.
public class MainScreenHandler {
    private static final String GAME_STORAGE = "./data/gameLibrary.json";

    private GameLibrary gameLibrary;                 // Library that manages all games
    private Scanner scanner;                         // Scanner for reading user input
    private GameScreenHandler gameSessionHandler;    // handler that manages the game session    
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // EFFECTS: Sets up the main screen handler, initializes a new instance of Scanner for user input,  
    // and new instance of GameSessionHandler to manage games. Creates an empty list of all games played.
    public MainScreenHandler() {
        Random random = new Random();
        this.scanner = new Scanner(System.in);
        this.gameLibrary = new GameLibrary();
        this.gameSessionHandler = new GameScreenHandler(scanner, new RoundScreenHandler(scanner, random));
        jsonWriter = new JsonWriter(GAME_STORAGE);
        jsonReader = new JsonReader(GAME_STORAGE);
    }

    // EFFECTS: Displays the main menu and based on user input, either creates the new game or shows 
    // the list of all games. Exits main menu when the user chooses to exit.
    public void runMainMenu() {
        loadGameLibrary();
        String menuPrompt = Constants.INSTRUCTIONS_FOR_INPUT + "\n" +  MainMenuOptions.listAllOptions();
        MainMenuOptions selectedOption;
        int selectedIndex;

        System.out.println(Constants.GAME_TITLE);

        do {
            System.out.println(Constants.SEPARATOR);  
            selectedIndex = InputValidator.getValidUserChoice(scanner, 
                menuPrompt, 1, MainMenuOptions.values().length) - 1;
            selectedOption = MainMenuOptions.values()[selectedIndex];            
            switch (selectedOption) {
                case NEW_GAME:     
                    startNewGame();                                        
                    break;
                case SHOW_GAMES:
                    printPlayedGames();
                    break;
                case EXIT:
                    saveGameLibrary();                    
                    break;
            }
        } while (selectedOption != MainMenuOptions.EXIT);
    }

    // MODIFIES: this
    // EFFECTS: Creates a new game and adds it to the list of played games and opens a game menu.
    private void startNewGame() {
        System.out.println("Creating a new game...");
        GameSession newGame = gameLibrary.createGame();
        System.out.println();     
        gameSessionHandler.runGameMenu(newGame);
    }

    // EFFECTS: Displays a list of all played games. Allows for user to select one of the played games to play. 
    // If no games were played, shows a message to the user. 
    private void printPlayedGames() {    
        ArrayList<GameSession> games = gameLibrary.getGames();    
        if (games.isEmpty()) {
            System.out.println("No games played\n");
            return;
        }

        System.out.println("List of played games:");
        for (int i = 0; i < games.size(); i++) {
            gameSessionHandler.printGameSummary(games.get(i));
        }
        System.out.println();
        startOldGame();
    }

    // EFFECTS: Allows to open game menu for the in-progress game, shows a message if the selected game is completed.
    private void startOldGame() {
        int userSelection = InputValidator.getValidUserChoice(scanner, Constants.SELECT_OLD_GAME_PROMPT, 
                1, gameLibrary.getGames().size(), true);
                
        if (userSelection != -1) {
            GameSession game = gameLibrary.getGameByID(userSelection);
            if (game != null) {
                gameSessionHandler.runGameMenu(game);
            }            
        } else {
            System.out.println(Constants.MESSAGE_GO_BACK_TO_MAIN_MENU);
        }
    }

    // Adapted from: 
    //   Project Title: JsonSerializationDemo
    //   Author: CPSC210
    //   Type: source code
    //   URL: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

    // MODIFIES: this
    // EFFECTS: Loads existing game library from the file into the current game library.
    private void loadGameLibrary() {
        int userSelection = InputValidator.getValidUserChoice(scanner, Constants.LOAD_GAMES_PROMPT, 
                1, 2);
        if (userSelection == 1) {
            try {
                gameLibrary = jsonReader.read();
                System.out.println("Loaded game library with " + gameLibrary.getGames().size() 
                        + " games from " + GAME_STORAGE);
            } catch (IOException e) {
                System.out.println("Unable to read from file: " + GAME_STORAGE);
            }                  
        }        
    }

    // Adapted from: 
    //   Project Title: JsonSerializationDemo
    //   Author: CPSC210
    //   Type: source code
    //   URL: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

    // EFFECTS: Saves game library to JSON file.
    private void saveGameLibrary() {
        int userSelection = InputValidator.getValidUserChoice(scanner, Constants.LOAD_GAMES_PROMPT, 
                1, 2);
        if (userSelection == 1) {
            try {
                jsonWriter.open();
                jsonWriter.write(gameLibrary);
                jsonWriter.close();
                String getPlural = gameLibrary.getGames().size() == 1 ? "" : "s";
                System.out.println("Saved current game library with " + gameLibrary.getGames().size()
                         + " game" + getPlural + " to " + GAME_STORAGE);
            } catch (FileNotFoundException e) {
                System.out.println("Unable to write to file: " + GAME_STORAGE);
            }
        }        

        System.out.println("Thank you for playing! See you next time!");
    }
}
