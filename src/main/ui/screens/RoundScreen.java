package ui.screens;

import java.awt.Component;
import java.awt.Dialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.Timer;

import model.GameRound;
import model.GameSession;
import ui.CakeDisplay;
import ui.MainPanel;
import ui.components.InputSelection;
import ui.components.RoundPopup;
import ui.components.TimerDisplay;
import ui.components.Title;

// Represents a round screen.
public class RoundScreen extends JPanel {
    private GameSession game;
    private MainPanel mainPanel;

    // Visual elements
    private CakeDisplay targetCakeDisplay;
    private CakeDisplay userCakeDisplay;
    private TimerDisplay timerDisplay;
    private Title title;    
    private Timer timer;
    private InputSelection inputSelection;
    private JDialog dialog;

    private int remainingTime;
    private GameRound currentRound;

    // EFFECTS: Constructs a round session screen for specified game.
    public RoundScreen(GameSession game, MainPanel mainPanel) {        
        super();
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.game = game;
        this.mainPanel = mainPanel;      
        this.title = new Title("MEMORIZE THE CAKE");
        this.timerDisplay = new TimerDisplay();
        this.add(title);
        this.add(timerDisplay);

        startRound();
    }

    // MODIFIES: this
    // EFFECTS: starts new round of the game, initializes all cake components on the screen and activates the game.
    private void startRound() {
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
        showPopup();
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

    // EFFECTS: Display popup window that displays game statistics and provides a choice to user to 
    // play next round or exit to the game menu.
    private void showPopup() {      
        String stats = "Total score: " + this.game.getTotalScore() + "            Lives left: " 
                + this.game.getLivesLeft();

        boolean isPlayable = this.game.getLivesLeft() > 0;

        RoundPopup popup = new RoundPopup("PLAY ROUND?", stats, exitAction(), continueAction(), isPlayable);
        this.dialog = new JDialog(this.mainPanel, "Popup", Dialog.ModalityType.APPLICATION_MODAL);
        dialog.setUndecorated(true);
        dialog.setAlwaysOnTop(true); 
        dialog.setContentPane(popup);

        dialog.pack();
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }

    // EFFECTS: Returns a result message based on whether round is won or lost.
    private String getRoundResultsMessage() {
        if (this.currentRound.isVictory()) {
            return "CONGRATULATIONS! ROUND WON!";
        } else {
            return "ROUND OVER!";
        }
    }

    // EFFECTS: initializes new action listener that will get triggered by pressing exit button on popup.
    private ActionListener exitAction() {
        return new ActionListener() {
            public void actionPerformed(ActionEvent evt) {     
                RoundScreen.this.dialog.setVisible(false);           
                RoundScreen.this.mainPanel.displayScreen(new GameMenu(game, mainPanel));
            }
        };
    }

    // EFFECTS: initializes new action listener that will get triggered by pressing exit button on popup.
    private ActionListener continueAction() {
        return new ActionListener() {
            public void actionPerformed(ActionEvent evt) { 
                RoundScreen.this.dialog.setVisible(false);
                clearScreen();
                RoundScreen.this.startRound();
            }
        };
    }

    private void clearScreen() {
        this.title.setText("MEMORIZE THE CAKE");
        timerDisplay.setTime(remainingTime);
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
