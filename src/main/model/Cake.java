package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.json.JSONObject;

import model.CakeElements.CakeColor;
import model.CakeElements.Decoration;
import model.CakeElements.Glaze;
import model.CakeElements.Topping;
import persistence.Writable;

// Represents a cake that consists of different elements: number of tiers, cake color, glaze, topping and decoration.
public class Cake implements Writable {
    public static final int MIN_NUM_OF_TIERS = 1; 
    public static final int MAX_NUM_OF_TIERS = 3;
    public static final int NUM_ELEMENTS = 5;

    private int numberOfTiers;         // number of cake tiers (min: 1, max: maxNumberOfTiers)
    private CakeColor cakeColor;       // cake color (must be from the list of CAKE_COLORS)
    private Glaze glaze;               // glaze type (must be from the list of GLAZES)
    private Topping topping;           // topping (must be from the list of TOPPINGS)
    private Decoration decoration;     // decoration (must be from the list of DECORATIONS)

    // EFFECTS: Creates one tier white color cake, with no glaze, topping and
    // decoration.
    public Cake() {        
        this.numberOfTiers = MIN_NUM_OF_TIERS;
        this.cakeColor = CakeColor.WHITE;
        this.glaze = Glaze.NONE;
        this.topping = Topping.NONE;
        this.decoration = Decoration.NONE;
    }

    // EFFECTS: Creates a cake with random selection from possible number of tiers, cake colors, glaze, 
    // toppings and decorations.
    public Cake(Random random) {
        this.numberOfTiers = random.nextInt(MAX_NUM_OF_TIERS) + 1;
        this.cakeColor = CakeColor.values()[random.nextInt(CakeColor.length())];
        this.glaze = Glaze.values()[random.nextInt(Glaze.length())];
        this.topping = Topping.values()[random.nextInt(Topping.length())];
        this.decoration = Decoration.values()[random.nextInt(Decoration.length())];
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

    // EFFECTS: returns a String that is a fraction of matching elements out of all elements.
    public String getComparisonAccuracy(Cake anotherCake) {
        int correctCount = 0;        
        if (this.numberOfTiers == anotherCake.numberOfTiers) {
            correctCount++;
        }
        if (this.cakeColor.equals(anotherCake.cakeColor)) {
            correctCount++;
        }
        if (this.glaze.equals(anotherCake.glaze)) {
            correctCount++;
        }
        if (this.topping.equals(anotherCake.topping)) {
            correctCount++;
        }
        if (this.decoration.equals(anotherCake.decoration)) {
            correctCount++;
        }

        return correctCount + "/" + NUM_ELEMENTS;
    }

    public int getNumberOfTiers() {
        return this.numberOfTiers;
    }
    
    // REQUIRES: Number of tiers must be betweeen 1 and maxNumberOfTiers(inclusive).
    // MODIFIES: this
    // EFFECTS: Sets the number of tiers to the given parameter value.
    public void setNumberOfTiers(int numberOfTiers) {
        this.numberOfTiers = numberOfTiers;
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

    // EFFECTS: Generates and returns a string representing information about all cake elements and their values.
    @Override
    public String toString() {
        return "NUMBER OF TIERS: " + this.numberOfTiers
            + " ;  COLOR: " + this.cakeColor
            + " ;  GLAZE: " + this.glaze
            + " ;  TOPPING: " + this.topping 
            + " ;  DECORATION: " + this.decoration;
    }

    // EFFECTS: Generates and returns a string representing information about all cake elements and their 
    // values in random order.
    public String getReorderedSummary() {
        String result = "";        
        List<String> listOfFieldsSummary = new ArrayList<String>() {{
                        add("NUMBER OF TIERS: " + numberOfTiers);
                        add("COLOR: " + cakeColor);
                        add("GLAZE: " + glaze);
                        add("TOPPING: " + topping);
                        add("DECORATION: " + decoration);
                    }
                };

        Collections.shuffle(listOfFieldsSummary);

        for (String fieldSummary: listOfFieldsSummary) {
            result += fieldSummary + "  |  ";
        }
        
        return result;
    }

    // EFFECTS: Returns cake as a JSON object.
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("numberOfTiers", numberOfTiers);
        json.put("cakeColor", cakeColor);
        json.put("topping", topping);
        json.put("decoration", decoration);
        json.put("glaze", glaze);
        return json;
    }
}
