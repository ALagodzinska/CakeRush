package ui;

import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;

@ExcludeFromJacocoGeneratedReport
// Starts the game.
public class Main {
    public static void main(String[] args) {
        MainPanel frame = new MainPanel();        
        
        frame.setVisible(true);
        frame.startGame();
    }
}
