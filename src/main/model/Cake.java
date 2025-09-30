package model;

import java.util.Random;

// Represents a cake that consists of different elements: number of tiers, cake color, glaze, topping and decoration.
public class Cake {

    // EFFECTS: Creates one tier white color cake, with no glaze, topping and
    // decoration.
    public Cake() {
        // stub
    }

    // EFFECTS: Creates a cake with random selection from possible number of tiers, cake colors, glaze, 
    // toppings and decorations.
    public Cake(Random random) {
        // stub
    }
    
    // EFFECTS: Compares every element of the cake. If cake have all elements the same returns true, else returns false.
    public boolean compare(Cake anotherCake) {
        // stub
        return false;
    }

    public int getNumberOfTiers() {
        // stub
        return 0;
    }

    public void setNumberOfTiers(int numberOfTiers) {
        // stub
    }

    public String getCakeColor() {
        // stub
        return null;
    }

    public void setCakeColor(String cakeColor) {
        // stub
    }

    public String getGlaze() {
        // stub
        return null;
    }

    public void setGlaze(String glaze) {
        // stub
    }

    public String getTopping() {
        // stub
        return null;
    }

    public void setTopping(String topping) {
        // stub
    }

    public String getDecoration() {
        // stub
        return null;
    }

    public void setDecoration(String decoration) {
        // stub
    }
}
