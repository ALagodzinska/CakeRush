package ui.screens;

import java.awt.Component;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.GameSession;
import ui.MainPanel;
import ui.components.MenuButton;
import ui.components.Title;

// Represents a game menu screen.
public class GameMenu extends JPanel {
    private GameSession game;
    private MainPanel mainPanel;

    // EFFECTS: Constructs and displays game menu for the given game on the given panel.
    public GameMenu(GameSession game, MainPanel mainPanel) {
        super();
        this.game = game;
        this.mainPanel = mainPanel;
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS)); // arranges in a column
        init();
    }

    // MODIFIES: this
    // EFFECTS: Initializes and adds GUI components to this panel.
    private void init() {
        JLabel title = new Title("Game " + game.getGameID());        
        this.add(title);        
        JLabel totalScore = new JLabel("Total score: " + game.getTotalScore());   
        totalScore.setAlignmentX(Component.CENTER_ALIGNMENT);     
        this.add(totalScore);
        JLabel livesLeft = new JLabel("Lives left: " + game.getLivesLeft());  
        livesLeft.setAlignmentX(Component.CENTER_ALIGNMENT);          
        this.add(livesLeft);
        this.add(Box.createVerticalStrut(40));
        
        addPlayGameBtn();
        addRoundHistoryBtn();
        addExitBtn();
    }

    // MODIFIES: this
    // EFFECTS: Creates the play game button, assigns the action event to it and adds it to this panel.
    private void addPlayGameBtn() {
        String text = this.game.getRounds().size() == 0 ? "START" : "CONTINUE";
        JButton playGame = new MenuButton(text);
        playGame.addActionListener(e -> mainPanel.displayScreen(new RoundScreen(game)));

        if (game.isFinished()) {
            playGame.setEnabled(false);
        }

        this.add(playGame);
        this.add(Box.createVerticalStrut(40));
    }

    // MODIFIES: this
    // EFFECTS: Creates the play round history button, assigns the action event to it and adds it to this panel.
    private void addRoundHistoryBtn() {
        JButton roundHistory = new MenuButton("VIEW ROUNDS");
        roundHistory.addActionListener(e -> System.out.println("LIST OF ROUNDS")); // TODO: add action on click

        if (game.getRounds().size() == 0) {
            roundHistory.setEnabled(false);
        }

        this.add(roundHistory);
        this.add(Box.createVerticalStrut(40)); 
    }

    // MODIFIES: this
    // EFFECTS: Creates the exit button, assigns the action event to it and adds it to this panel.
    private void addExitBtn() {
        JButton exit = new MenuButton("EXIT");
        exit.addActionListener(e -> System.out.println("EXIT")); // TODO: add action on click
        this.add(exit); 
    }
}
