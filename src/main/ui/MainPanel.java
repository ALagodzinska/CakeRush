package ui;

import javax.swing.JFrame;
import javax.swing.JPanel;

// The main GUI container for the application. Serves as a parent for all screens.
public class MainPanel extends JFrame {
    // EFFECTS: Constructs the main panel with the specified size and centers it on the screen.
    public MainPanel() {
        super();
        this.setSize(1000, 800);
        this.setLocationRelativeTo(null);
    }

    // MODIFIES: this
    // EFFECTS: Clears the main panel and displays given panel.
    public void displayScreen(JPanel panel) {
        this.getContentPane().removeAll();
        this.add(panel);
        this.revalidate();
        this.repaint();
    }
}
