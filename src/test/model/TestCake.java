package model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Random;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
        assertEquals(1, defaultCake.getNumberOfTiers());
        assertEquals(Cake.CakeColor.WHITE, defaultCake.getCakeColor());
        assertEquals(Cake.Glaze.NONE, defaultCake.getGlaze());
        assertEquals(Cake.Topping.NONE, defaultCake.getTopping());
        assertEquals(Cake.Decoration.NONE, defaultCake.getDecoration());
    }

    @Test
    void testRandomConstructor() {
        int[] randomNumbersFixedSeed = randomFixedValues();
        
        Random random = new Random(SEED);
        Cake randomCake = new Cake(random);

        assertEquals(randomNumbersFixedSeed[0] + 1, randomCake.getNumberOfTiers());
        assertEquals(Cake.CakeColor.values()[randomNumbersFixedSeed[1]], randomCake.getCakeColor());
        assertEquals(Cake.Glaze.values()[randomNumbersFixedSeed[2]], randomCake.getGlaze());
        assertEquals(Cake.Topping.values()[randomNumbersFixedSeed[3]], randomCake.getTopping());
        assertEquals(Cake.Decoration.values()[randomNumbersFixedSeed[4]], randomCake.getDecoration());
    }

    @Test
    void testCompareSame() {
        defaultCake.setNumberOfTiers(2);
        defaultCake.setCakeColor(Cake.CakeColor.BLUE);
        defaultCake.setGlaze(Cake.Glaze.PINK);
        defaultCake.setTopping(Cake.Topping.CREAM);
        defaultCake.setDecoration(Cake.Decoration.CANDLE);

        assertEquals(2, defaultCake.getNumberOfTiers());
        assertEquals(Cake.CakeColor.BLUE, defaultCake.getCakeColor());
        assertEquals(Cake.Glaze.PINK, defaultCake.getGlaze());
        assertEquals(Cake.Topping.CREAM, defaultCake.getTopping());
        assertEquals(Cake.Decoration.CANDLE, defaultCake.getDecoration());

        Cake secondCake = new Cake();
        secondCake.setNumberOfTiers(2);
        secondCake.setCakeColor(Cake.CakeColor.BLUE);
        secondCake.setGlaze(Cake.Glaze.PINK);
        secondCake.setTopping(Cake.Topping.CREAM);
        secondCake.setDecoration(Cake.Decoration.CANDLE);

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
        secondCake.setCakeColor(Cake.CakeColor.GREEN);

        assertFalse(defaultCake.compare(secondCake));
    }

    @Test
    void testCompareDifferentGlaze() {
        Cake secondCake = new Cake();
        secondCake.setGlaze(Cake.Glaze.PURPLE);

        assertFalse(defaultCake.compare(secondCake));
    }

    @Test
    void testCompareDifferentTopping() {
        Cake secondCake = new Cake();
        secondCake.setTopping(Cake.Topping.FRUIT);

        assertFalse(defaultCake.compare(secondCake));
    }

    @Test
    void testCompareDifferentDecoration() {
        Cake secondCake = new Cake();
        secondCake.setDecoration(Cake.Decoration.CARD);

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
    void testSetNumberOfTiersNumberOverUpperBoundary() {
        assertEquals(1, defaultCake.getNumberOfTiers());
        defaultCake.setNumberOfTiers(10);
        assertEquals(1, defaultCake.getNumberOfTiers());
    }

    @Test
    void testSetNumberOfTiersNumberUnderLowerBoundary() {
        assertEquals(1, defaultCake.getNumberOfTiers());
        defaultCake.setNumberOfTiers(0);
        assertEquals(1, defaultCake.getNumberOfTiers());
    }

    private int[] randomFixedValues() {
        int[] fiveRandomValues = new int[NUM_OF_ELEMENTS];
        int[] optionsForAllElements = getNumberOfOptionsInOrder();
        Random random = new Random(SEED);

        for (int i = 0; i < fiveRandomValues.length; i++) {
            fiveRandomValues[i] = random.nextInt(optionsForAllElements[i]);
        }

        return fiveRandomValues;
    }

    private int[] getNumberOfOptionsInOrder() {
        int[] numbersOfOptionsForAllElements = new int[NUM_OF_ELEMENTS];

        numbersOfOptionsForAllElements[0] = Cake.MAX_NUM_OF_TIERS;
        numbersOfOptionsForAllElements[1] = Cake.CakeColor.values().length;
        numbersOfOptionsForAllElements[2] = Cake.Glaze.values().length;
        numbersOfOptionsForAllElements[3] = Cake.Topping.values().length;
        numbersOfOptionsForAllElements[4] = Cake.Decoration.values().length;

        return numbersOfOptionsForAllElements;
    }
}
