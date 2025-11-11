package ui;

import javax.swing.JFrame;
import javax.swing.JPanel;

import model.GameLibrary;
import ui.components.popups.LoadGamesPopup;
import ui.components.popups.PopupBase;

import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;

@ExcludeFromJacocoGeneratedReport
// The main GUI container for the application. Serves as a parent for all screens.
public class MainPanel extends JFrame {
    public static final String GAME_STORAGE = "./data/gameLibrary.json";
    private GameLibrary library;    

    // EFFECTS: Constructs the main panel with the specified size and centers it on the screen.
    // Creates new GameLibrary instance.
    public MainPanel() {
        super();
        library = new GameLibrary();        
        this.setSize(1000, 875);
        this.setLocationRelativeTo(null);
    }

    // EFFECTS: Starts the application by openning the popup asking user to load previously 
    // played games.
    public void startGame() {
        PopupBase loadGamesPopup = new LoadGamesPopup(this, library);
        loadGamesPopup.open();
    }

    // MODIFIES: this
    // EFFECTS: Clears the main panel and displays given panel.
    public void displayScreen(JPanel panel) {
        this.getContentPane().removeAll();
        this.add(panel);
        this.revalidate();
        this.repaint();
    }

    // EFFECTS: returns current game library. 
    public GameLibrary getGameLibrary() {
        return this.library;
    }

    // MODIFIES: this
    // EFFECTS: sets new game library to the current game library.
    public void setGameLibrary(GameLibrary library) {
        this.library = library;
    }
}
