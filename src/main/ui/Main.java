package ui;

import javax.swing.JPanel;

import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;
import ui.components.popups.LoadGamesPopup;
import ui.components.popups.PopupBase;
import ui.screens.MainMenu;

@ExcludeFromJacocoGeneratedReport
// Starts the game.
public class Main {
    public static void main(String[] args) {
        MainPanel frame = new MainPanel();        
        
        frame.setVisible(true);
        frame.startGame();
    }
}
