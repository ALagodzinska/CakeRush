package ui.components.popups;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.GameSession;
import ui.MainPanel;
import ui.screens.GameMenu;
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
        super(mainPanel, "PLAY NEXT ROUND?", "", "EXIT", "CONTINUE");

        this.gameSession = currentGame;
        this.mainPanel = mainPanel;
        this.roundScreen = roundScreen;        
        this.addActionToCancelButton(exitAction());
        this.addActionToContinueButton(continueAction());
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

    // MODIFIES: this
    // EFFECTS: Checks whether the continue button should be active, updates description label and displays popup.
    @Override
    public void open() {
        this.setEnabledContinueBtn(!this.gameSession.isFinished());
        String stats = "Total score: " + this.gameSession.getTotalScore() + "            Lives left: " 
                + this.gameSession.getLivesLeft();
        this.setDescription(stats);
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
