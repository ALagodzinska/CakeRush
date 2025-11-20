package ui.components.buttons;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.border.LineBorder;

import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;

// Represents a return button.
@ExcludeFromJacocoGeneratedReport
public class ReturnButton extends JButton {
    private static final Dimension btnDimension = new Dimension(100, 60);

    // Creates a styled button with specified text.
    public ReturnButton() {
        super();
        ImageIcon icon = new ImageIcon("./images/return.PNG");
        Image scaledIcon = icon.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT);
        this.setIcon(new ImageIcon(scaledIcon));
        this.setBackground(new Color(129,199,132));
        this.setBorder(new LineBorder(Color.BLACK, 3, true));
        this.setPreferredSize(btnDimension);
        this.setMaximumSize(btnDimension);
        this.setMinimumSize(btnDimension);
        this.setFont(new Font("Arial", Font.BOLD, 20));
        this.setAlignmentX(Component.RIGHT_ALIGNMENT);
    }
}
