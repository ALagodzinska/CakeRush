package ui.screens;

import java.awt.Component;
import java.awt.Dialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.Timer;

import model.GameRound;
import model.GameSession;
import ui.CakeDisplay;
import ui.MainPanel;
import ui.components.InputSelection;
import ui.components.TimerDisplay;
import ui.components.Title;
import ui.components.popups.PopupBase;
import ui.components.popups.RoundPopup;

import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;

@ExcludeFromJacocoGeneratedReport
// Represents a round screen.
public class RoundScreen extends JPanel {
    private GameSession game;

    // Visual elements
    private CakeDisplay targetCakeDisplay;
    private CakeDisplay userCakeDisplay;
    private TimerDisplay timerDisplay;
    private Title title;    
    private Timer timer;
    private InputSelection inputSelection;
    private PopupBase popup;

    private int remainingTime;
    private GameRound currentRound;

    // EFFECTS: Constructs a round session screen for specified game.
    public RoundScreen(GameSession game, MainPanel mainPanel) {        
        super();
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.game = game;     
        this.title = new Title("MEMORIZE THE CAKE");
        this.timerDisplay = new TimerDisplay();
        this.popup = new RoundPopup(game, mainPanel, this);
        this.add(title);
        this.add(timerDisplay);

        startRound();
    }

    // MODIFIES: this
    // EFFECTS: starts new round of the game, initializes all cake components on the screen and activates the game.
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
        int delay = 1000;
        timer = new javax.swing.Timer(delay, roundAction());

        targetCakeDisplay.setVisible(true);
        timer.start();        
    }

    // MODIFIES: this
    // EFFECTS: shows user the target cake and allows user within round time 
    // to customize user's cake to match target cake.
    private ActionListener roundAction() {
        ActionListener action = new ActionListener() {
            public void actionPerformed(ActionEvent evt) {   
                timerDisplay.setTime(remainingTime);             
                if (remainingTime <= 0) {
                    endRound();                    
                } else if (remainingTime == GameRound.MAX_TIME) {                    
                    changeFromPreviewToGame();         
                }                 
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
        this.timerDisplay.setTime(0);       
        this.currentRound.finishRound(GameRound.MAX_TIME - this.remainingTime);
        this.game.addPlayedRound(currentRound);
        this.inputSelection.setEnabled(false);
        this.title.setText(getRoundResultsMessage());
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

    // EFFECTS: Returns a result message based on whether round is won or lost.
    private String getRoundResultsMessage() {
        if (this.currentRound.isVictory()) {
            return "CONGRATULATIONS! ROUND WON!";
        } else {
            return "ROUND OVER!";
        }
    }

    // MODIFIES: this
    // EFFECTS: Creates default round screen. Removes cake display and input buttons.
    public void clearScreen() {
        this.title.setText("MEMORIZE THE CAKE");
        this.timerDisplay.setTime(0);
        this.remove(userCakeDisplay); 
        this.remove(inputSelection); 
        this.revalidate();
        this.repaint(); 
    }

    // TODO: fix statistics how they are displayed
    
    // TODO: figure out how to show two at the same time
    // private void showComparisonPanel() {
    //     JPanel comparisonPanel = new JPanel();
    //     comparisonPanel.setLayout(new BoxLayout(comparisonPanel, BoxLayout.X_AXIS));
    //     comparisonPanel.add(targetCakeDisplay);
    //     comparisonPanel.add(userCakeDisplay);
    //     this.add(comparisonPanel);
    // }
}
