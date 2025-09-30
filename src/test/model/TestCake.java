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
        assertEquals(Cake.CAKE_COLORS[0], defaultCake.getCakeColor());
        assertEquals(Cake.GLAZES[0], defaultCake.getGlaze());
        assertEquals(Cake.TOPPINGS[0], defaultCake.getTopping());
        assertEquals(Cake.DECORATIONS[0], defaultCake.getDecoration());
    }

    @Test
    void testRandomConstructor() {
        // Check with TA how to test random ctor
        Random random = new Random();
        Cake randomCake = new Cake(random);
        assertNotNull(randomCake);
    }

    @Test
    void testCompareSame() {
        defaultCake.setNumberOfTiers(2);
        defaultCake.setCakeColor(Cake.CAKE_COLORS[2]);
        defaultCake.setGlaze(Cake.GLAZES[1]);
        defaultCake.setTopping(Cake.TOPPINGS[3]);
        defaultCake.setDecoration(Cake.DECORATIONS[4]);

        assertEquals(2, defaultCake.getNumberOfTiers());
        assertEquals(Cake.CAKE_COLORS[2], defaultCake.getCakeColor());
        assertEquals(Cake.GLAZES[1], defaultCake.getGlaze());
        assertEquals(Cake.TOPPINGS[3], defaultCake.getTopping());
        assertEquals(Cake.DECORATIONS[4], defaultCake.getDecoration());

        Cake secondCake = new Cake();
        secondCake.setNumberOfTiers(2);
        secondCake.setCakeColor(Cake.CAKE_COLORS[2]);
        secondCake.setGlaze(Cake.GLAZES[1]);
        secondCake.setTopping(Cake.TOPPINGS[3]);
        secondCake.setDecoration(Cake.DECORATIONS[4]);

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
        secondCake.setCakeColor(Cake.CAKE_COLORS[2]);

        assertFalse(defaultCake.compare(secondCake));
    }

    @Test
    void testCompareDifferentGlaze() {
        Cake secondCake = new Cake();
        secondCake.setGlaze(Cake.GLAZES[2]);

        assertFalse(defaultCake.compare(secondCake));
    }

    @Test
    void testCompareDifferentTopping() {
        Cake secondCake = new Cake();
        secondCake.setTopping(Cake.TOPPINGS[2]);

        assertFalse(defaultCake.compare(secondCake));
    }

    @Test
    void testCompareDifferentDecoration() {
        Cake secondCake = new Cake();
        secondCake.setDecoration(Cake.DECORATIONS[2]);

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
