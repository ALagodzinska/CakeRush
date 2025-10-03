package model;

import java.util.Random;

// Represents a cake that consists of different elements: number of tiers, cake color, glaze, topping and decoration.
public class Cake {
    public static final int MAX_NUM_OF_TIERS = 3;

    enum CakeColor { WHITE, PINK, BLUE, GREEN, YELLOW; }
    
    enum Glaze { NONE, PINK, PURPLE, GREEN, BLUE; }

    enum Topping { NONE, SPRINKLES, FRUIT, CANDIES, CREAM; }

    enum Decoration { NONE, CANDLE, FLOWER, BALLOON, CARD; }

    private int numberOfTiers;      // number of cake tiers (min: 1, max: maxNumberOfTiers)
    private CakeColor cakeColor;       // cake color (must be from the list of CAKE_COLORS)
    private Glaze glaze;           // glaze type (must be from the list of GLAZES)
    private Topping topping;         // topping (must be from the list of TOPPINGS)
    private Decoration decoration;      // decoration (must be from the list of DECORATIONS)

    // EFFECTS: Creates one tier white color cake, with no glaze, topping and
    // decoration.
    public Cake() {        
        this.numberOfTiers = 1;
        this.cakeColor = CakeColor.WHITE;
        this.glaze = Glaze.NONE;
        this.topping = Topping.NONE;
        this.decoration = Decoration.NONE;
    }

    // EFFECTS: Creates a cake with random selection from possible number of tiers, cake colors, glaze, 
    // toppings and decorations.
    public Cake(Random random) {
        CakeColor[] cakeColors = CakeColor.values();
        Glaze[] glazes = Glaze.values();
        Topping[] toppings = Topping.values();
        Decoration[] decorations = Decoration.values();

        this.numberOfTiers = random.nextInt(MAX_NUM_OF_TIERS) + 1;
        this.cakeColor = cakeColors[random.nextInt(cakeColors.length)];
        this.glaze = glazes[random.nextInt(glazes.length)];
        this.topping = toppings[random.nextInt(toppings.length)];
        this.decoration = decorations[random.nextInt(decorations.length)];
    }
    
    // EFFECTS: Compares every element of the cake. If cake have all elements the same returns true, else returns false.
    public boolean compare(Cake anotherCake) {
        boolean isSame = this.numberOfTiers == anotherCake.numberOfTiers 
                && this.cakeColor.equals(anotherCake.cakeColor)
                && this.glaze.equals(anotherCake.glaze)
                && this.topping.equals(anotherCake.topping)
                && this.decoration.equals(anotherCake.decoration);

        return isSame;
    }

    public int getNumberOfTiers() {
        return this.numberOfTiers;
    }

    // REQUIRES: number of tiers must be betweeen 1 and maxNumberOfTiers(inclusive).
    public void setNumberOfTiers(int numberOfTiers) {
        if (numberOfTiers >= 1 && numberOfTiers <= MAX_NUM_OF_TIERS) {
            this.numberOfTiers = numberOfTiers;
        }
    }

    public CakeColor getCakeColor() {
        return this.cakeColor;
    }

    public void setCakeColor(CakeColor cakeColor) {
        this.cakeColor = cakeColor;
    }

    public Glaze getGlaze() {
        return this.glaze;
    }
    
    public void setGlaze(Glaze glaze) {
        this.glaze = glaze;
    }

    public Topping getTopping() {
        return this.topping;
    }

    public void setTopping(Topping topping) {
        this.topping = topping;
    }

    public Decoration getDecoration() {
        return this.decoration;
    }
    
    public void setDecoration(Decoration decoration) {
        this.decoration = decoration;
    }
}
