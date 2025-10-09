package ui;

import java.util.ArrayList;
import java.util.Scanner;

import model.GameSession;

// Manages the main screen and all existing games, allows to create a new game instance.
public class MainScreenHandler {
    enum MainMenuOptions {
        NEW_GAME,
        SHOW_GAMES,
        EXIT;
    }

    private static int nextGameID = 1;
    private Scanner scanner;
    private GameSessionHandler gameSessionHandler;
    private ArrayList<GameSession> listOfGames;

    // EFFECTS: Sets up the main screen handler, initializes a new instance of Scanner for user input,  
    // and new instance of GameSessionHandler to manage games. Creates an empty list of all games played.
    public MainScreenHandler() {
        // stub
    }

    // EFFECTS: Displays the main menu and based on user input, either creates the new game or shows 
    // the list of all games. Exits main menu when the user chooses to exit.
    public void runMainMenu() {
    }

    // MODIFIES: this
    // EFFECTS: Creates a new game and adds it to the list of played games and opens a game menu.
    private void startNewGame() {
        // stub
    }

    // EFFECTS: Displays a list of all played games. Allows for user to select one of the played games to play. 
    // If no games were played, shows a message to the user. 
    private void printPlayedGames() {
        // stub
    }

    // EFFECTS: Allows to open game menu for the in-progress game, shows a message if the selected game is completed.
    private void startOldGame(GameSession game) {
        // stub
    }
}
