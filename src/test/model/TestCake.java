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
        assertEquals("white", defaultCake.getCakeColor());
        assertEquals("none", defaultCake.getGlaze());
        assertEquals("none", defaultCake.getTopping());
        assertEquals("none", defaultCake.getDecoration());
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
        defaultCake.setCakeColor("blue");
        defaultCake.setGlaze("pink");
        defaultCake.setTopping("sprinkles");
        defaultCake.setDecoration("note");

        assertEquals(2, defaultCake.getNumberOfTiers());
        assertEquals("blue", defaultCake.getCakeColor());
        assertEquals("pink", defaultCake.getGlaze());
        assertEquals("sprinkles", defaultCake.getTopping());
        assertEquals("note", defaultCake.getDecoration());

        Cake secondCake = new Cake();
        secondCake.setNumberOfTiers(2);
        secondCake.setCakeColor("blue");
        secondCake.setGlaze("pink");
        secondCake.setTopping("sprinkles");
        secondCake.setDecoration("note");

        assertTrue(defaultCake.compare(secondCake));
    }

    @Test
    void testCompareDifferent() {
        defaultCake.setNumberOfTiers(2);
        defaultCake.setCakeColor("blue");
        defaultCake.setGlaze("pink");
        defaultCake.setTopping("sprinkles");
        defaultCake.setDecoration("note");

        Cake secondCake = new Cake();

        assertFalse(defaultCake.compare(secondCake));
    }
}
