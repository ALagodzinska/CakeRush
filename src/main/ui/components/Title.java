package ui.components;

import java.awt.Component;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;

@ExcludeFromJacocoGeneratedReport
// Styled label used as a title.
public class Title extends JLabel {
    // Creates a styled text label with specified text.
    public Title(String text) {
        super(text);
        this.setFont(new Font("Arial", Font.BOLD, 50));
        this.setHorizontalAlignment(SwingConstants.CENTER);
        this.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.setBorder(BorderFactory.createEmptyBorder(10, 10, 5, 10));
    }
}
