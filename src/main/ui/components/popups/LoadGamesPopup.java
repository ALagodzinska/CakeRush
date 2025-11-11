package ui.components.popups;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import model.GameLibrary;
import persistence.JsonReader;
import ui.MainPanel;
import ui.screens.MainMenu;

// Represents a popup that prompts user to load previously played games into game library.
public class LoadGamesPopup extends PopupBase {
    private JsonReader jsonReader;
    private MainPanel mainPanel;

    // EFFECTS: Creates a popup base with specified title and labels, adds action to the popup buttons.
    // Initiazlizes a JsonReader instance.
    public LoadGamesPopup(MainPanel mainPanel, GameLibrary library) {
        super(mainPanel, "LOAD PLAYED GAMES?", "", "NO", "YES");
        this.addActionToCancelButton(exitAction());
        this.addActionToContinueButton(loadAction());
        this.jsonReader = new JsonReader(MainPanel.GAME_STORAGE);        
        this.mainPanel = mainPanel;
    }

    // EFFECTS: Initializes and returns an action listener that tries to read from file and assign game library from to a 
    // current game library in the main panel. After action is done closes the popup.
    private ActionListener loadAction() {
        return new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("CLICK");
                try {
                    GameLibrary library = jsonReader.read();
                    LoadGamesPopup.this.mainPanel.setGameLibrary(library);

                } catch (IOException ex) {
                    System.out.println("Couldn't read from the file");
                }

                close();
            }
        };
    }

    // EFFECTS: Initializes and returns an action listener that closes popup.
    private ActionListener exitAction() {
        return new ActionListener() {
            public void actionPerformed(ActionEvent e) {     
                close();
            }
        };
    }

    // MODIFIES: this
    // EFFECTS: Closes popup window and displays to user main menu.
    @Override
    public void close() {
        this.setVisible(false);   
        MainMenu mainMenu = new MainMenu(mainPanel);    
        this.mainPanel.add(mainMenu);
        this.mainPanel.displayScreen(mainMenu);
    }
}
