package model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Random;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.CakeElements.CakeColor;
import model.CakeElements.Decoration;
import model.CakeElements.Glaze;
import model.CakeElements.Topping;

public class TestGameRound {
    private GameRound gameRound;
    private Random random = new Random();

    @BeforeEach
    void runBefore() {
        gameRound = new GameRound(random);
    }

    
    @Test
    void testConstructor() {
        assertNotNull(gameRound.getTargetCake());
        assertEquals(1, gameRound.getUserCake().getNumberOfTiers());
        assertEquals(CakeColor.WHITE, gameRound.getUserCake().getCakeColor());
        assertEquals(Glaze.NONE, gameRound.getUserCake().getGlaze());
        assertEquals(Topping.NONE, gameRound.getUserCake().getTopping());
        assertEquals(Decoration.NONE, gameRound.getUserCake().getDecoration());
        assertFalse(gameRound.isVictory());
    }

    @Test
    void testFinishRoundSuccessful() {
        Cake targetCake = gameRound.getTargetCake();
        Cake userCake = gameRound.getUserCake();
        // Match cake
        userCake.setNumberOfTiers(targetCake.getNumberOfTiers());
        userCake.setCakeColor(targetCake.getCakeColor());
        userCake.setGlaze(targetCake.getGlaze());
        userCake.setTopping(targetCake.getTopping());
        userCake.setDecoration(targetCake.getDecoration());

        assertFalse(gameRound.isVictory());

        gameRound.finishRound();

        assertTrue(gameRound.isVictory());
    }

    @Test
    void testFinishRoundFailedRound() {
        Cake targetCake = gameRound.getTargetCake();
        Cake userCake = gameRound.getUserCake();
        // Make different cakes
        targetCake.setNumberOfTiers(2);
        userCake.setNumberOfTiers(3);

        assertFalse(gameRound.isVictory());

        gameRound.finishRound();

        assertFalse(gameRound.isVictory());
    }
}
