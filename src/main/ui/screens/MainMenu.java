package ui.screens;

import java.awt.Component;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.GameLibrary;
import model.GameSession;
import ui.MainPanel;
import ui.components.MenuButton;
import ui.components.Title;
import ui.components.popups.SaveGamesPopup;

// Represents a game menu screen.
public class MainMenu extends JPanel {
    private MainPanel mainPanel;
    private GameLibrary gameLibrary;

    // EFFECTS: Constructs and displays game menu for the given game on the given panel.
    public MainMenu(MainPanel mainPanel) {
        super();
        System.out.println("HERE IS LIBRARY");
        this.gameLibrary = mainPanel.getGameLibrary();
        this.mainPanel = mainPanel;
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS)); // arranges in a column
        init();
    }

    // MODIFIES: this
    // EFFECTS: Initializes and adds GUI components to this panel.
    private void init() {
        JLabel title = new Title("CAKE RUSH");        
        this.add(title);
        this.add(Box.createVerticalStrut(40));
        
        addStartNewGameBtn();
        addGameHistoryBtn();
        addExitBtn();
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
                mainPanel.displayScreen(new GameList(gameLibrary.getGames(), mainPanel)));

        System.out.println(gameLibrary.getGames().size());
        if (gameLibrary.getGames().size() == 0) {
            
            System.out.println("EMPTY");
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

