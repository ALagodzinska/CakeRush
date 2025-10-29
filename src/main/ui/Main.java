package ui;

import javax.swing.JPanel;

import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;
import model.GameSession;
import ui.screens.GameMenu;

@ExcludeFromJacocoGeneratedReport
// Starts the game.
public class Main {
    public static void main(String[] args) {
        // Creating main frame.
        MainPanel frame = new MainPanel();
        
        JPanel panel = new GameMenu(new GameSession(2), frame);
        frame.add(panel);
         
        // making the frame visible
        frame.setVisible(true);
    }
}
