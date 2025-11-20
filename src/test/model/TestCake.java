package model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Random;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.CakeElements.CakeColor;
import model.CakeElements.Decoration;
import model.CakeElements.Glaze;
import model.CakeElements.Topping;

import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;

@ExcludeFromJacocoGeneratedReport
public class TestCake {
    private static final int SEED = 12;
    private static final int NUM_OF_ELEMENTS = 5;

    private Cake defaultCake;

    @BeforeEach
    void runBefore() {
        defaultCake = new Cake();
    }

    @Test
    void testDefaultConstructor() {
        assertEquals(Cake.MIN_NUM_OF_TIERS, defaultCake.getNumberOfTiers());
        assertEquals(CakeColor.WHITE, defaultCake.getCakeColor());
        assertEquals(Glaze.NONE, defaultCake.getGlaze());
        assertEquals(Topping.NONE, defaultCake.getTopping());
        assertEquals(Decoration.NONE, defaultCake.getDecoration());
    }

    @Test
    void testRandomConstructor() {
        int[] randomNumbersFixedSeed = randomFixedValues();
        
        Random random = new Random(SEED);
        Cake randomCake = new Cake(random);

        assertEquals(randomNumbersFixedSeed[0] + 1, randomCake.getNumberOfTiers());
        assertEquals(CakeColor.values()[randomNumbersFixedSeed[1]], randomCake.getCakeColor());
        assertEquals(Glaze.values()[randomNumbersFixedSeed[2]], randomCake.getGlaze());
        assertEquals(Topping.values()[randomNumbersFixedSeed[3]], randomCake.getTopping());
        assertEquals(Decoration.values()[randomNumbersFixedSeed[4]], randomCake.getDecoration());
    }

    @Test
    void testCompareSame() {
        defaultCake.setNumberOfTiers(2);
        defaultCake.setCakeColor(CakeColor.BLUE);
        defaultCake.setGlaze(Glaze.CARAMEL);
        defaultCake.setTopping(Topping.CREAM);
        defaultCake.setDecoration(Decoration.CANDLE);

        assertEquals(2, defaultCake.getNumberOfTiers());
        assertEquals(CakeColor.BLUE, defaultCake.getCakeColor());
        assertEquals(Glaze.CARAMEL, defaultCake.getGlaze());
        assertEquals(Topping.CREAM, defaultCake.getTopping());
        assertEquals(Decoration.CANDLE, defaultCake.getDecoration());

        Cake secondCake = new Cake();
        secondCake.setNumberOfTiers(2);
        secondCake.setCakeColor(CakeColor.BLUE);
        secondCake.setGlaze(Glaze.CARAMEL);
        secondCake.setTopping(Topping.CREAM);
        secondCake.setDecoration(Decoration.CANDLE);

        assertTrue(defaultCake.compare(secondCake));
    }

    @Test
    void testCompareDifferentNumberOfTiers() {
        Cake secondCake = new Cake();
        secondCake.setNumberOfTiers(2);

        assertFalse(defaultCake.compare(secondCake));
    }

    @Test
    void testCompareDifferentCakeColor() {
        Cake secondCake = new Cake();
        secondCake.setCakeColor(CakeColor.GREEN);

        assertFalse(defaultCake.compare(secondCake));
    }

    @Test
    void testCompareDifferentGlaze() {
        Cake secondCake = new Cake();
        secondCake.setGlaze(Glaze.CHOCOLATE);

        assertFalse(defaultCake.compare(secondCake));
    }

    @Test
    void testCompareDifferentTopping() {
        Cake secondCake = new Cake();
        secondCake.setTopping(Topping.FRUIT);

        assertFalse(defaultCake.compare(secondCake));
    }

    @Test
    void testCompareDifferentDecoration() {
        Cake secondCake = new Cake();
        secondCake.setDecoration(Decoration.CARD);

        assertFalse(defaultCake.compare(secondCake));
    }

    @Test
    void testSetNumberOfTiersLowerBoundary() {
        defaultCake.setNumberOfTiers(1);
        assertEquals(1, defaultCake.getNumberOfTiers());
    }

    @Test
    void testSetNumberOfTiersUpperBoundary() {
        defaultCake.setNumberOfTiers(Cake.MAX_NUM_OF_TIERS);
        assertEquals(Cake.MAX_NUM_OF_TIERS, defaultCake.getNumberOfTiers());
    }

    @Test
    void testToString() {
        assertEquals("NUMBER OF TIERS: 1"
                + " ;  COLOR: WHITE"
                + " ;  GLAZE: NONE"
                + " ;  TOPPING: NONE"
                + " ;  DECORATION: NONE", defaultCake.toString());
    }

    @Test
    void testGetReorderedSummary() {
        String reorderedSummary = defaultCake.getReorderedSummary();
        assertTrue(reorderedSummary.contains("NUMBER OF TIERS: 1"));
        assertTrue(reorderedSummary.contains("COLOR: WHITE"));
        assertTrue(reorderedSummary.contains("GLAZE: NONE"));
        assertTrue(reorderedSummary.contains("TOPPING: NONE"));
        assertTrue(reorderedSummary.contains("DECORATION: NONE"));
    }

    @Test
    void testToJson() {
        JSONObject json = defaultCake.toJson();
        assertEquals(1, json.getInt("numberOfTiers"));
        assertEquals("WHITE", json.get("cakeColor").toString());
        assertEquals("NONE", json.get("topping").toString());
        assertEquals("NONE", json.get("decoration").toString());
        assertEquals("NONE", json.get("glaze").toString());
    }

    @Test
    void testGetComparisonAccuracyDifferentCakes() {
        Cake secondCake = new Cake();
        secondCake.setDecoration(Decoration.CARD);
        secondCake.setNumberOfTiers(3);
        secondCake.setCakeColor(CakeColor.BLUE);
        secondCake.setGlaze(Glaze.STRAWBERRY);
        secondCake.setTopping(Topping.FRUIT);

        assertEquals("0/5", defaultCake.getComparisonAccuracy(secondCake));
    }

    @Test
    void testGetComparisonAccuracySameCakes() {
        assertEquals("5/5", defaultCake.getComparisonAccuracy(defaultCake));
    }

    // EFFECTS: Returns an array of five values generated using a fixed seed, based on the size of each 
    // cake element's options.
    private int[] randomFixedValues() {
        int[] fiveRandomValues = new int[NUM_OF_ELEMENTS];
        int[] optionsForAllElements = getNumberOfOptionsInOrder();
        Random random = new Random(SEED);

        for (int i = 0; i < fiveRandomValues.length; i++) {
            fiveRandomValues[i] = random.nextInt(optionsForAllElements[i]);
        }

        return fiveRandomValues;
    }

    // EFFECTS: Returns an array of 5 values that contains in order the number of each cake element's options.
    private int[] getNumberOfOptionsInOrder() {
        int[] numbersOfOptionsForAllElements = new int[NUM_OF_ELEMENTS];

        numbersOfOptionsForAllElements[0] = Cake.MAX_NUM_OF_TIERS;
        numbersOfOptionsForAllElements[1] = CakeColor.values().length;
        numbersOfOptionsForAllElements[2] = Glaze.values().length;
        numbersOfOptionsForAllElements[3] = Topping.values().length;
        numbersOfOptionsForAllElements[4] = Decoration.values().length;

        return numbersOfOptionsForAllElements;
    }    
}
