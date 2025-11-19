package ui.screens;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.Timer;
import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;

import ui.components.InputSelection;
import ui.components.LivesDisplay;
import ui.components.TimerDisplay;
import ui.components.Title;
import ui.components.popups.PopupBase;
import ui.components.popups.RoundPopup;
import ui.CakeDisplay;
import ui.MainPanel;

import model.GameRound;
import model.GameSession;

@ExcludeFromJacocoGeneratedReport
// Represents a round screen.
public class RoundScreen extends JPanel {
    private static final int TIMER_DELAY = 1000;
    // Visual elements
    private CakeDisplay targetCakeDisplay;
    private CakeDisplay userCakeDisplay;
    private TimerDisplay timerDisplay;
    private Title title;    
    private InputSelection inputSelection;
    private PopupBase popup;
    private JPanel statsPanel;

    private int remainingTime;
    private Timer timer;
    private GameRound currentRound;
    private GameSession game;

    // EFFECTS: Constructs a round session screen for specified game and starts a game round.
    public RoundScreen(GameSession game, MainPanel mainPanel) {        
        super();
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));            
        this.title = new Title("MEMORIZE THE CAKE");        
        this.add(title);
        statsPanel = createStatisticsPanel();
        this.add(statsPanel);
        this.popup = new RoundPopup(game, mainPanel, this);

        this.game = game; 

        startRound();
    }

    // EFFECTS: Creates and returns a panel that stores game lives and round timer.
    public JPanel createStatisticsPanel() {
        JPanel statsPanel = new JPanel();
        statsPanel.setLayout(new BoxLayout(statsPanel, BoxLayout.X_AXIS));

        updateStatisticsPanel();

        return statsPanel;
    }

    // MODIFIES: this
    // EFFECTS: Updates statistics panel, redraws number of lives and updates timer display.
    public void updateStatisticsPanel() {
        statsPanel.removeAll();
        statsPanel.add(Box.createRigidArea(new Dimension(70, 0)));
        statsPanel.add(new LivesDisplay(game.getLivesLeft()));
        statsPanel.add(Box.createRigidArea(new Dimension(350, 0)));
        timerDisplay = new TimerDisplay();
        statsPanel.add(timerDisplay);

        statsPanel.revalidate();
        statsPanel.repaint();
    }

    // MODIFIES: this
    // EFFECTS: Starts new round of the game, initializes all cake components on the screen and activates the game.
    public void startRound() {
        this.currentRound = new GameRound(new Random());        

        targetCakeDisplay = new CakeDisplay(currentRound.getTargetCake());
        targetCakeDisplay.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(targetCakeDisplay);
        

        userCakeDisplay = new CakeDisplay(currentRound.getUserCake());
        userCakeDisplay.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        inputSelection = new InputSelection(userCakeDisplay);
        inputSelection.setActionOnSumbit(submitAction());        

        remainingTime = GameRound.MAX_TIME + GameRound.PREVIEW_TIME;
        timer = new javax.swing.Timer(TIMER_DELAY, roundAction());

        targetCakeDisplay.setVisible(true);
        timer.start();        
    }

    // MODIFIES: this
    // EFFECTS: shows user the target cake and allows user within round time 
    // to customize user's cake to match target cake.
    private ActionListener roundAction() {
        ActionListener action = new ActionListener() {
            public void actionPerformed(ActionEvent evt) {                                             
                if (remainingTime <= 0) {                    
                    endRound();                    
                } else if (remainingTime == GameRound.MAX_TIME) {               
                    changeFromPreviewToGame();         
                } 
                timerDisplay.setTime(remainingTime);   
                              
                remainingTime--;                          
            }
        };

        return action;
    }

    // EFFECTS: initializes new action listener that will get triggered by pressing button.
    private ActionListener submitAction() {
        return new ActionListener() {
            public void actionPerformed(ActionEvent evt) {               
                endRound();
            }
        };
    }

    // MODIFIES: this
    // EFFECTS: when the round ends stop the active timer, disable all input buttons and change screen title.
    private void endRound() {
        this.timer.stop();               
        this.currentRound.finishRound(GameRound.MAX_TIME - this.remainingTime);
        this.game.addPlayedRound(currentRound);
        this.inputSelection.setEnabled(false);
        updateStatisticsPanel();
        this.popup.open();
    }

    // MODIFIES: this
    // EFFECTS: Changes screen fro preview to the game screen with user inputs.
    private void changeFromPreviewToGame() {
        this.remove(targetCakeDisplay);   
        this.add(userCakeDisplay);    
        this.add(inputSelection, Component.BOTTOM_ALIGNMENT);
        this.title.setText("RECREATE THE CAKE");
        this.revalidate(); 
        this.repaint();   
    }

    // MODIFIES: this
    // EFFECTS: Creates default round screen. Removes cake display and input buttons.
    public void clearScreen() {
        this.title.setText("MEMORIZE THE CAKE");
        this.remove(userCakeDisplay); 
        this.remove(inputSelection); 
        this.revalidate();
        this.repaint(); 
    }

    // EFFECTS: Returns the current round data.
    public GameRound getCurrentRound() {
        return this.currentRound;
    }    
}
