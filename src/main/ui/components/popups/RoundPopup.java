package ui.components.popups;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.GameRound;
import model.GameSession;
import ui.MainPanel;
import ui.screens.GameMenu;
import ui.screens.RoundList;
import ui.screens.RoundScreen;

import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;

@ExcludeFromJacocoGeneratedReport
// Represents a popup displayed to user in between rounds.
public class RoundPopup extends PopupBase {
    private GameSession gameSession;
    private MainPanel mainPanel;
    private RoundScreen roundScreen;    

    // EFFECTS: Creates a popup that prompts user to play next round or exit back to game menu.
    public RoundPopup(GameSession currentGame, MainPanel mainPanel, RoundScreen roundScreen) {
        super(mainPanel, "PLAY NEXT ROUND?","", "EXIT", "CONTINUE");

        this.gameSession = currentGame;
        this.mainPanel = mainPanel;
        this.roundScreen = roundScreen;      
        
        this.addActionToCancelButton(exitAction());
        this.addActionToContinueButton(continueAction());        
    }

    // EFFECTS: creates a round results panel that contains a message and
    // played round statistics and returns it.
    private JPanel createResultsPanel() {
        JPanel resultsPanel = new JPanel();

        resultsPanel.setLayout(new BoxLayout(resultsPanel, BoxLayout.Y_AXIS));

        GameRound completedRound = roundScreen.getCurrentRound();
        String titleText = completedRound.isVictory() ? "CONGRATULATIONS! YOU WON!" : "YOU LOST...";
        if (completedRound.isVictory()) {
            resultsPanel.setBackground(new Color(152, 251, 152));
        } else {
            resultsPanel.setBackground(new Color(255, 128, 128));
        }

        JLabel resultsTitle = new JLabel(titleText);
        resultsTitle.setFont(new Font("Arial", Font.BOLD, 20));
        resultsTitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel score = new JLabel("+" + completedRound.getScore());
        score.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel time = new JLabel(RoundList.formatTime(completedRound.getRoundTime()));
        time.setAlignmentX(Component.CENTER_ALIGNMENT);

        resultsPanel.add(resultsTitle);
        resultsPanel.add(score);
        resultsPanel.add(time);

        return resultsPanel;
    }

    // EFFECTS: Initializes new action listener that will send user back to the game menu and that is
    // triggered by pressing exit button on popup.
    private ActionListener exitAction() {
        return new ActionListener() {
            public void actionPerformed(ActionEvent e) {     
                close();
            }
        };
    }

    // MODIFIES: this
    // EFFECTS: Initializes new action listener that will get triggered by pressing exit button on popup.
    private ActionListener continueAction() {
        return new ActionListener() {
            public void actionPerformed(ActionEvent evt) { 
                RoundPopup.this.setVisible(false);
                RoundPopup.this.roundScreen.clearScreen();
                RoundPopup.this.roundScreen.startRound();
            }
        };
    }

    private void updatePopup() {
        this.addResultsPanel(createResultsPanel());

        this.addActionToCancelButton(exitAction());
        this.addActionToContinueButton(continueAction()); 
    }

    // MODIFIES: this
    // EFFECTS: Checks whether the continue button should be active, updates description label and displays popup.
    @Override
    public void open() {
        this.setEnabledContinueBtn(!this.gameSession.isFinished());
        // String stats = "Total score: " + this.gameSession.getTotalScore() + "            Lives left: " 
        //         + this.gameSession.getLivesLeft();
        // this.setDescription(stats);
        updatePopup();
        this.setVisible(true);           
        
    }

    // MODIFIES: this
    // EFFECTS: Closes the popup window and displays Game Menu screen.
    @Override
    public void close() {
        this.setVisible(false);           
        this.mainPanel.displayScreen(new GameMenu(this.gameSession, mainPanel));
    }
    
}
