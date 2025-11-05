package ui.screens;

import java.awt.Component;
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

        

        remainingTime = 35;
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
                if (remainingTime <= 0) {
                    timer.stop(); 
                    // RoundScreen.this.remove(userCakeDisplay);  
                    RoundScreen.this.title.setText("RESULTS");
                    System.out.println(RoundScreen.this.currentRound.getUserCake().getReorderedSummary());
                    inputSelection.setEnabled(false);
                    // showComparisonPanel();   // TODO: figure out how to show two at the same time
                    // RoundScreen.this.revalidate(); 
                    // RoundScreen.this.repaint();
                } else if (remainingTime == 30) {                    
                    RoundScreen.this.remove(targetCakeDisplay);   
                    RoundScreen.this.add(userCakeDisplay);    
                    RoundScreen.this.add(inputSelection, Component.BOTTOM_ALIGNMENT);
                    RoundScreen.this.title.setText("RECREATE THE CAKE");
                    RoundScreen.this.revalidate(); 
                    RoundScreen.this.repaint();                
                }
                timerDisplay.setTime(remainingTime);
                remainingTime--;             
            }
        };

        return action;
    }
    
    // TODO: figure out how to show two at the same time
    // private void showComparisonPanel() {
    //     JPanel comparisonPanel = new JPanel();
    //     comparisonPanel.setLayout(new BoxLayout(comparisonPanel, BoxLayout.X_AXIS));
    //     comparisonPanel.add(targetCakeDisplay);
    //     comparisonPanel.add(userCakeDisplay);
    //     this.add(comparisonPanel);
    // }
}
