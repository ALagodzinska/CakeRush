package ui;

import java.io.File;
import java.io.IOException;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import model.Cake;
import model.CakeElements.Decoration;
import model.CakeElements.Glaze;
import model.CakeElements.Topping;

// Represents a panel where the cake is depicted to user.
public class CakeDisplay extends JPanel {
    Cake cake;
    BufferedImage cakeBaseImage;
    BufferedImage glazeImage;
    BufferedImage toppingImage;
    BufferedImage decorationImage;

    // EFFECTS: Constructs cake panel and loads images for the specified cake    
    public CakeDisplay(Cake cake) {
        super();
        this.cake = cake;
        this.setPreferredSize(new Dimension(500, 900));        
    }

    // EFFECTS: Loads images for all cake elements.
    private void loadImages() {    
        clearImageValues();    
        try {
            cakeBaseImage = ImageIO.read(new File("./images/cakeColor/" 
                + cake.getCakeColor().toString().toLowerCase() + ".PNG"));
            if (cake.getGlaze() != Glaze.NONE) {
                glazeImage = ImageIO.read(new File("./images/glaze/" 
                + cake.getGlaze().toString().toLowerCase() + ".PNG"));
            }
            if (cake.getTopping() != Topping.NONE) {
                toppingImage = ImageIO.read(new File("./images/topping/" 
                + cake.getTopping().toString().toLowerCase() + ".PNG"));
            }
            if (cake.getDecoration() != Decoration.NONE) {
                decorationImage = ImageIO.read(new File("./images/decoration/" 
                + cake.getDecoration().toString().toLowerCase() + ".PNG"));
            }
        } catch (IOException e) {
            // TODO: Handle image loading error
            System.out.println("ERROR" + e);
        }
    }

    private void clearImageValues() { 
        cakeBaseImage = null;
        glazeImage = null;
        toppingImage = null;
        decorationImage = null;
    }

    // EFFECTS: Draws each cake tier sequentially, adding decorations only to top tier.
    @Override
    protected void paintComponent(Graphics g) {
        loadImages();
        super.paintComponent(g);
        System.out.println("HERE");
        drawFirstTier(g);

        int numOfTiers = this.cake.getNumberOfTiers();
        if (numOfTiers > 1) {
            drawSecondTire(g);

            if (numOfTiers > 2) {
                drawThirdTire(g);
            }
        }

        drawDecorations(g);
    }

    // MODIFIES: this
    // EFFECTS: Draws all elements on the first tier of the cake.
    private void drawFirstTier(Graphics g) {
        g.drawImage(cakeBaseImage, 250, 70, 500, 500, this);

        if (glazeImage != null) {
            g.drawImage(glazeImage, 250, 70, 500, 500, this);
        }

        if (toppingImage != null) {
            g.drawImage(toppingImage, 250, 70, 500, 500, this);
        }
    }

    // MODIFIES: this
    // EFFECTS: Draws all elements on the second tier of the cake.
    private void drawSecondTire(Graphics g) {
        g.drawImage(cakeBaseImage, 325, 20, 350, 350, this);

        if (glazeImage != null) {
            g.drawImage(glazeImage, 325, 20, 350, 350, this);
        }

        if (toppingImage != null) {
            g.drawImage(toppingImage, 325, 20, 350, 350, this);
        }
    }

    // MODIFIES: this
    // EFFECTS: Draws all elements on the third tier of the cake.
    private void drawThirdTire(Graphics g) {
        g.drawImage(cakeBaseImage, 385, 0, 230, 230, this);

        if (glazeImage != null) {
            g.drawImage(glazeImage, 385, 0, 230, 230, this);
        }

        if (toppingImage != null) {
            g.drawImage(toppingImage, 385, 0, 230, 230, this);
        }
    }

    // EFFECTS: Draws decorations on top tier of the cake.
    private void drawDecorations(Graphics g) {
        if (decorationImage == null) {
            return;
        }

        int numOfTiers = this.cake.getNumberOfTiers();
        switch (numOfTiers) {
            case 1:  
                g.drawImage(decorationImage, 250, 70, 500, 500, this);          
                break;        
            case 2:
                g.drawImage(decorationImage, 325, 20, 350, 350, this);
                break;
            case 3:
                g.drawImage(decorationImage, 385, 0, 230, 230, this);
                break;
            
        }
    }

    public Cake getDisplayedCake() {
        return this.cake;
    }
}
