package model;

import java.util.Random;

// Represents a cake that consists of different elements: number of tiers, cake color, glaze, topping and decoration.
public class Cake {
    // Ask TA about using enums
    public static final int maxNumberOfTiers = 3;
    public static final  String[] CAKE_COLORS =  {"white", "pink", "blue", "green", "yellow"};
    public static final  String[] GLAZES =  {"none", "pink", "purple", "green", "blue"};
    public static final  String[] TOPPINGS =  {"none", "sprinkles", "fruit", "candies", "cream"};
    public static final  String[] DECORATIONS =  {"none", "candle", "flower", "balloon", "card"};

    private int numberOfTiers;      // number of cake tiers (min: 1, max: maxNumberOfTiers)
    private String cakeColor;       // cake color (must be from the list of CAKE_COLORS)
    private String glaze;           // glaze type (must be from the list of GLAZES)
    private String topping;         // topping (must be from the list of TOPPINGS)
    private String decoration;      // decoration (must be from the list of DECORATIONS)

    // EFFECTS: Creates one tier white color cake, with no glaze, topping and
    // decoration.
    public Cake() {        
        this.numberOfTiers = 1;
        this.cakeColor = CAKE_COLORS[0];
        this.glaze = GLAZES[0];
        this.topping = TOPPINGS[0];
        this.decoration = DECORATIONS[0];
    }

    // EFFECTS: Creates a cake with random selection from possible number of tiers, cake colors, glaze, 
    // toppings and decorations.
    public Cake(Random random) {
        this.numberOfTiers = random.nextInt(maxNumberOfTiers) + 1;
        this.cakeColor = CAKE_COLORS[random.nextInt(CAKE_COLORS.length)];
        this.glaze = GLAZES[random.nextInt(GLAZES.length)];
        this.topping = TOPPINGS[random.nextInt(TOPPINGS.length)];
        this.decoration = DECORATIONS[random.nextInt(DECORATIONS.length)];
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
        if (numberOfTiers >= 1 && numberOfTiers <= maxNumberOfTiers) {
            this.numberOfTiers = numberOfTiers;
        }
    }

    public String getCakeColor() {
        return this.cakeColor;
    }

    // REQUIRES: The cake color must be an element from the CAKE_COLORS array.
    public void setCakeColor(String cakeColor) {
        this.cakeColor = cakeColor;
    }

    public String getGlaze() {
        return this.glaze;
    }
    
    // REQUIRES: The glaze must be an element from the GLAZES array.
    public void setGlaze(String glaze) {
        this.glaze = glaze;
    }

    public String getTopping() {
        return this.topping;
    }
    
    // REQUIRES: The topping must be an element from the TOPPINGS array.
    public void setTopping(String topping) {
        this.topping = topping;
    }

    public String getDecoration() {
        return this.decoration;
    }
    
    // REQUIRES: The decoration must be an element from the DECORATIONS array.
    public void setDecoration(String decoration) {
        this.decoration = decoration;
    }
}
