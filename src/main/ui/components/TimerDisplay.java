package ui.components;

import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;

@ExcludeFromJacocoGeneratedReport
// Represents displed timer on th screen.
public class TimerDisplay extends JPanel {
    private JLabel timeDisplay;

    // EFFECTS: Constructs timer panel adds text label to it and applies styling.
    public TimerDisplay() {
        super();  
        this.timeDisplay = new JLabel(" ");
        this.add(timeDisplay);
        this.setBounds(200,200, 50,50);
        this.setBorder(BorderFactory.createLineBorder(Color.RED));
    }

    // REQUIRES: time >= 0
    // MODIFIES: this
    // EFFECTS: Updates the time on the timer if the time is larger than round time, 
    // sets to the label the modulus of time.
    public void setTime(int time) {
        if (time == 0) {
            this.timeDisplay.setText("");
            return;
        }

        // TODO: add constants
        if (time >= 30) {
            time %= 10;
        }

        this.timeDisplay.setText(String.valueOf(time));
        this.revalidate(); 
        this.repaint();
    }
}
