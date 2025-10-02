package model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Random;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestGameRound {
    private GameRound gameRound;
    private Random random = new Random();

    @BeforeEach
    void runBefore() {
        gameRound = new GameRound(1, random);
    }

    
    @Test
    void testConstructor() {
        assertEquals(1, gameRound.getRoundNumber());
        assertNotNull(gameRound.getTargetCake());
        // Check that in the beginning of the round the user have a default cake.
        assertEquals(1, gameRound.getUserCake().getNumberOfTiers());
        assertEquals(Cake.CAKE_COLORS[0], gameRound.getUserCake().getCakeColor());
        assertEquals(Cake.GLAZES[0], gameRound.getUserCake().getGlaze());
        assertEquals(Cake.TOPPINGS[0], gameRound.getUserCake().getTopping());
        assertEquals(Cake.DECORATIONS[0], gameRound.getUserCake().getDecoration());

        assertEquals(0, gameRound.getCompletionTime());
        assertEquals(false, gameRound.getIsVictory());
    }

    @Test
    void testFinishRoundSuccessfulRound() {
        Cake targetCake = gameRound.getTargetCake();
        Cake userCake = gameRound.getUserCake();
        // Match cake
        userCake.setNumberOfTiers(targetCake.getNumberOfTiers());
        userCake.setCakeColor(targetCake.getCakeColor());
        userCake.setGlaze(targetCake.getGlaze());
        userCake.setTopping(targetCake.getTopping());
        userCake.setDecoration(targetCake.getDecoration());

        assertFalse(gameRound.getIsVictory());

        gameRound.finishRound(20);

        assertEquals(20, gameRound.getCompletionTime());
        assertTrue(gameRound.getIsVictory());
    }

    @Test
    void testFinishRoundFailedRound() {
        Cake targetCake = gameRound.getTargetCake();
        Cake userCake = gameRound.getUserCake();
        // Make different cakes
        targetCake.setNumberOfTiers(2);
        userCake.setNumberOfTiers(3);

        assertFalse(gameRound.getIsVictory());

        gameRound.finishRound(20);

        assertEquals(20, gameRound.getCompletionTime());
        assertFalse(gameRound.getIsVictory());
    }

    @Test
    void testFinishRoundTimeZero() {
        assertFalse(gameRound.getIsVictory());

        gameRound.finishRound(0);

        assertEquals(0, gameRound.getCompletionTime());
        assertFalse(gameRound.getIsVictory());
    }

    @Test
    void testFinishRoundTimeMaxAllowed() {
        assertFalse(gameRound.getIsVictory());

        gameRound.finishRound(GameRound.MAX_ROUND_TIME);

        assertEquals(GameRound.MAX_ROUND_TIME, gameRound.getCompletionTime());
        assertFalse(gameRound.getIsVictory());
    }

    @Test
    void testFinishRoundTimeUnderLowerBoundary() {
        assertFalse(gameRound.getIsVictory());

        gameRound.finishRound(-1);

        assertEquals(0, gameRound.getCompletionTime());
        assertFalse(gameRound.getIsVictory());
    }

    @Test
    void testFinishRoundTimeOverUpperBoundary() {
        assertFalse(gameRound.getIsVictory());

        gameRound.finishRound(GameRound.MAX_ROUND_TIME + 1);

        assertEquals(0, gameRound.getCompletionTime());
        assertFalse(gameRound.getIsVictory());
    }
}
