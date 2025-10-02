package model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Random;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestCake {
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
        // Check with TA how to test random ctor
        // seed number (test it) how to predict outcomes for the seed 
        // method to return values in order of the seed 
        
        Random random = new Random();
        Cake randomCake = new Cake(random);
        assertNotNull(randomCake);
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
        defaultCake.setNumberOfTiers(Cake.maxNumberOfTiers);
        assertEquals(Cake.maxNumberOfTiers, defaultCake.getNumberOfTiers());
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
}
