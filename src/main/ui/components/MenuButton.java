package ui.components;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JButton;

// Styled button used in the menus.
public class MenuButton extends JButton {
    private static final Dimension btnDimension = new Dimension(300, 40);

    // Creates a styled button with specified text.
    public MenuButton(String text) {
        super(text);
        this.setBackground(Color.PINK);
        this.setPreferredSize(btnDimension);
        this.setMaximumSize(btnDimension);
        this.setMinimumSize(btnDimension);
        this.setFont(new Font("Arial", Font.BOLD, 20));
        this.setAlignmentX(Component.CENTER_ALIGNMENT);
    }
}
