package ui.components;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;

@ExcludeFromJacocoGeneratedReport
// Represents a panel that displays the image indicating the number of lives remaining.
public class LivesDisplay extends JPanel {
    private int lives;
    private BufferedImage livesImage;

    // REQUIRES: lives >= 0 and lives <= MAX_LIVES
    // MODIFIED: this
    // EFFECTS: Constructs lives image panel and loads the appropriate image based on the number of lives provided.
    public LivesDisplay(int lives) {
        super();
        this.lives = lives;
        if (lives > 0) {
            loadImages(lives);
        }        
    }

    // REQUIRES: lives > 0 and lives <= MAX_LIVES
    // MODIFIES: this
    // EFFECTS: Loads appropriate image for the specified number of lives.
    private void loadImages(int lives) {
        try {
            livesImage = ImageIO.read(new File("./images/lives/" + lives + ".PNG"));
        } catch (IOException e) {
            // Create empty image if load fails.
            livesImage = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
            System.out.println("Lives image loading error: " + e);
        }
    }

    // EFFECTS: Draws the lives image.
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (lives > 0) {
            g.drawImage(livesImage, 0, 0, 150, 50, this);
        }        
    }
}
