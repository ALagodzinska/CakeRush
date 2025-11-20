package ui.screens;

import java.awt.Component;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;

import ui.MainPanel;
import ui.components.GameLogo;
import ui.components.buttons.MenuButton;
import ui.components.popups.SaveGamesPopup;

import model.GameLibrary;
import model.GameSession;

@ExcludeFromJacocoGeneratedReport
// Represents a game menu screen.
public class MainMenu extends JPanel {
    private MainPanel mainPanel;
    private GameLibrary gameLibrary;

    // EFFECTS: Constructs and displays game menu for the given game on the given panel.
    public MainMenu(MainPanel mainPanel) {
        super();
        this.gameLibrary = mainPanel.getGameLibrary();
        this.mainPanel = mainPanel;
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS)); // arranges in a column
        init();
    }

    // MODIFIES: this
    // EFFECTS: Initializes and adds GUI components to this panel.
    private void init() {
        addLogo();
        
        addStartNewGameBtn();
        addGameHistoryBtn();
        addExitBtn();
    }

    private void addLogo() {
        JPanel logo = new GameLogo();   
        logo.setAlignmentX(Component.CENTER_ALIGNMENT);
        logo.setAlignmentY(Component.CENTER_ALIGNMENT);  

        this.add(Box.createVerticalStrut(30));
        this.add(logo);
        this.add(Box.createVerticalStrut(20));
    }

    // MODIFIES: this
    // EFFECTS: Creates the play game button, assigns the action event to it and adds it to this panel.
    private void addStartNewGameBtn() {
        JButton addNewGameBtn = new MenuButton("CREATE NEW GAME");
        
        addNewGameBtn.addActionListener(e -> {
            GameSession newGame = MainMenu.this.gameLibrary.createGame();
            mainPanel.displayScreen(new GameMenu(newGame, mainPanel)); 
        });

        this.add(addNewGameBtn);
        this.add(Box.createVerticalStrut(40));
    }

    // MODIFIES: this
    // EFFECTS: Creates the play game history button, assigns the action event to it and adds it to this panel.
    private void addGameHistoryBtn() {        
        JButton gameHistory = new MenuButton("VIEW GAMES");
        gameHistory.addActionListener(e -> 
                mainPanel.displayScreen(new GameList(mainPanel)));

        System.out.println(gameLibrary.getGames().size());
        if (gameLibrary.getGames().size() == 0) {            
            gameHistory.setEnabled(false);
        }

        this.add(gameHistory);
        this.add(Box.createVerticalStrut(40)); 
    }

    // MODIFIES: this
    // EFFECTS: Creates the exit button, assigns the action event to it and adds it to this panel.
    private void addExitBtn() {
        JButton exit = new MenuButton("EXIT");
        exit.addActionListener(e -> {
            SaveGamesPopup popup = new SaveGamesPopup(mainPanel, gameLibrary);
            popup.open();
            System.exit(0);
        });
        this.add(exit); 
    }
}

