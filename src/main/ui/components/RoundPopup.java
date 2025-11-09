package ui.components;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import model.GameRound;

// Represents a popup that is shown to user before the start of the round.
public class RoundPopup extends JPanel {
    private String title;
    private String statistics;
    private ActionListener onExit;
    private ActionListener onContinue;
    private boolean canContinue;

    // EFFECTS: Initializes a popup with acess to previous round data and total score.
    public RoundPopup(String title, String stats, ActionListener onExit, 
            ActionListener onContinue, boolean canContinue) {
        super();
        this.title = title;
        this.statistics = stats;
        this.onExit = onExit;
        this.onContinue = onContinue;
        this.canContinue = canContinue;

        init(); 
    }

    private void init() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setSize(500, 200);
        this.setPreferredSize(new Dimension(500,150));
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        JLabel popupTitle = new JLabel(this.title);
        popupTitle.setFont(new Font("Arial", Font.BOLD, 20));
        popupTitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel stats = new JLabel(this.statistics);
        stats.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel buttonPanel = createButtonPanel();

        this.add(Box.createRigidArea(new Dimension(0, 10)));
        this.add(popupTitle);
        this.add(Box.createRigidArea(new Dimension(0, 20)));
        this.add(stats);
        this.add(Box.createRigidArea(new Dimension(0, 40)));
        this.add(buttonPanel);
    }

    private JPanel createButtonPanel() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));

        JButton exitButton = new JButton("EXIT");
        exitButton.addActionListener(onExit);

        JButton continueButton = new JButton("CONTINUE");
        continueButton.addActionListener(onContinue);
        continueButton.setEnabled(canContinue);

        buttonPanel.add(exitButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(50, 0)));
        buttonPanel.add(continueButton);

        return buttonPanel;
    }
}
