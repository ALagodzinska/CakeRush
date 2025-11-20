package ui.components.popups;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import model.GameLibrary;
import persistence.JsonReader;
import ui.MainPanel;
import ui.screens.MainMenu;

import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;

@ExcludeFromJacocoGeneratedReport
// Represents a popup that prompts user to load previously played games into game library.
public class LoadGamesPopup extends PopupBase {
    private JsonReader jsonReader;
    private MainPanel mainPanel;

    // EFFECTS: Creates a popup base with specified title and labels, adds action to the popup buttons.
    // Initiazlizes a JsonReader instance.
    public LoadGamesPopup(MainPanel mainPanel, GameLibrary library) {
        super(mainPanel, "LOAD PLAYED GAMES?", "", "NO", "YES");
        this.addActionToCancelButton(new ExitActionListener());
        this.addActionToContinueButton(new LoadActionListener());
        this.jsonReader = new JsonReader(MainPanel.GAME_STORAGE);        
        this.mainPanel = mainPanel;
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

    // Action listener that handles load button clicks.
    @ExcludeFromJacocoGeneratedReport
    private class LoadActionListener implements ActionListener {
        //EFFECTS: Reads from file and assigns game library from a file to a 
        // current game library in the main panel. After action is done closes the popup.
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                GameLibrary library = jsonReader.read();
                LoadGamesPopup.this.mainPanel.setGameLibrary(library);

            } catch (IOException ex) {
                System.out.println("Couldn't read from the file");
            }

            close();
        }
    }

    // Action listener that handles exit button clicks.
    @ExcludeFromJacocoGeneratedReport
    private class ExitActionListener implements ActionListener {
        // EFFECTS: Closes popup.
        @Override
        public void actionPerformed(ActionEvent e) {
            close();
        }
    }
}
