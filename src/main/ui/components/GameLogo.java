package ui.components;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;

// Represents the game logo.
@ExcludeFromJacocoGeneratedReport
public class GameLogo extends JPanel {
    private BufferedImage gameLogo;

    // EFFECTS: Loads logog image, and adjusts panel size.
    public GameLogo() {
        super();
        loadImage();

        this.setPreferredSize(new Dimension(400,250));
        this.setMaximumSize(new Dimension(400,250));
    }

    // MODIFIES: this
    // EFFECTS: Loads logo image from a file.
    private void loadImage() {
        try {
            gameLogo = ImageIO.read(new File("./images/title.PNG"));
        } catch (IOException e) {
            // Create empty image if load fails.
            gameLogo = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
            System.out.println("Lives image loading error: " + e);
        }
    }

    // EFFECTS: Draws logo image.
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(gameLogo, 0, 0, 400, 250, this);
    }
}
