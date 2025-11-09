package ui.components;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JButton;

public class ReturnButton extends JButton {
    private static final Dimension btnDimension = new Dimension(150, 60);

    // Creates a styled button with specified text.
    public ReturnButton(String text) {
        super(text);
        this.setBackground(Color.PINK);
        this.setPreferredSize(btnDimension);
        this.setMaximumSize(btnDimension);
        this.setMinimumSize(btnDimension);
        this.setFont(new Font("Arial", Font.BOLD, 20));
        this.setAlignmentX(Component.RIGHT_ALIGNMENT);
    }
}
