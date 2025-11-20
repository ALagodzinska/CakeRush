package ui.components;

import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JPanel;
import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;

import model.GameRound;

@ExcludeFromJacocoGeneratedReport
// Represents a panel that displays the amount of time remaining on the timer.
public class TimerDisplay extends JPanel {
    private JLabel timeDisplay;

    // EFFECTS: Constructs timer panel adds text label to it and applies styling.
    public TimerDisplay() {
        super();  
        this.timeDisplay = new JLabel(" ");
        this.timeDisplay.setFont(new Font("Arial", Font.BOLD, 40));
        JLabel label = new JLabel("TIME: ");
        label.setFont(new Font("Arial", Font.PLAIN, 30));

        this.add(label);
        this.add(timeDisplay);

        this.setBounds(200,200, 50,50);
        this.setPreferredSize(new Dimension(50, 50));
        this.setMinimumSize(new Dimension(50, 50));
    }

    // REQUIRES: time >= 0
    // MODIFIES: this
    // EFFECTS: Updates the time on the timer; if the time is larger than round time, 
    // sets to the label the modulus of time. Updates and repaints this panel.
    public void setTime(int time) {
        if (time == 0) {
            this.timeDisplay.setText("");
            
        } else if (time > GameRound.MAX_TIME) {
            time %= 10;
        }

        this.timeDisplay.setText(String.valueOf(time));
        this.revalidate(); 
        this.repaint();
    }
}
