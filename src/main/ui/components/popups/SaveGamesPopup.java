package ui.components.popups;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

import model.GameLibrary;
import persistence.JsonWriter;
import ui.MainPanel;

import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;

@ExcludeFromJacocoGeneratedReport
// Represents a popup that prompts user to save previously played games into game a file.
public class SaveGamesPopup extends PopupBase {
    private JsonWriter jsonWriter; 
    private GameLibrary gameLibrary;

    // EFFECTS: Creates a popup base with specified title and labels, adds action to the popup buttons.
    // Initiazlizes a JsonWriter instance.
    public SaveGamesPopup(MainPanel mainPanel, GameLibrary library) {
        super(mainPanel, "SAVE PLAYED GAMES?", library.getGames().size() + " GAMES TO SAVE", "NO", "YES");
        this.addActionToCancelButton(new ExitActionListener());
        this.addActionToContinueButton(new SaveActionListener());
        this.jsonWriter = new JsonWriter(MainPanel.GAME_STORAGE);
        this.gameLibrary = library;
    }    

    // MODIFIES: this
    // EFFECTS: Closes popup and exits application.
    @Override
    public void close() {
        this.setVisible(false);           
        System.exit(0);
    }
    
    @ExcludeFromJacocoGeneratedReport
    // Action listener that handles save button click.
    private class SaveActionListener implements ActionListener {
        // EFFECTS: Initializes and returns an action listener that tries to write to file existing game library. 
        // After action is done closes the popup.
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                jsonWriter.open();
                jsonWriter.write(gameLibrary);
                jsonWriter.close();                    
            } catch (FileNotFoundException ex) {
                System.out.println("Couldn't save file");
            }

            close();
        }
    }

    @ExcludeFromJacocoGeneratedReport
    // Action listener that handles exit button click.
    private class ExitActionListener implements ActionListener {
        // EFFECTS: Initializes new action listener that closes popup and ends the application.
        @Override
        public void actionPerformed(ActionEvent e) {
            close();
        }
    }
}
