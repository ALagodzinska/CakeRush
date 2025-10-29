package ui.screens;

import javax.swing.JLabel;
import javax.swing.JPanel;

import model.GameSession;

// Represents a round screen.
public class RoundScreen extends JPanel {
    private GameSession game;

    // EFFECTS: Constructs a round session screen for specified game.
    public RoundScreen(GameSession game) {
        super();
        this.game = game;
        this.add(new JLabel("new round for game " + game.getGameID()));
    }
}
