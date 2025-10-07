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
        // Check that in the beginning of the round the user have a default cake.
        assertEquals(1, gameRound.getUserCake().getNumberOfTiers());
        assertEquals(CakeColor.WHITE, gameRound.getUserCake().getCakeColor());
        assertEquals(Glaze.NONE, gameRound.getUserCake().getGlaze());
        assertEquals(Topping.NONE, gameRound.getUserCake().getTopping());
        assertEquals(Decoration.NONE, gameRound.getUserCake().getDecoration());

        assertEquals(0, gameRound.getCompletionTime());
        assertEquals(0, gameRound.getScore());
        assertFalse(gameRound.isVictory());
    }

    @Test
    void testFinishRoundSuccessfulRoundMaxScore() {
        Cake targetCake = gameRound.getTargetCake();
        Cake userCake = gameRound.getUserCake();
        // Match cake
        userCake.setNumberOfTiers(targetCake.getNumberOfTiers());
        userCake.setCakeColor(targetCake.getCakeColor());
        userCake.setGlaze(targetCake.getGlaze());
        userCake.setTopping(targetCake.getTopping());
        userCake.setDecoration(targetCake.getDecoration());

        assertFalse(gameRound.isVictory());

        gameRound.finishRound(10);

        assertEquals(GameRound.MAX_SCORE_TIME_LIMIT, gameRound.getCompletionTime());
        assertTrue(gameRound.isVictory());
        assertEquals(GameRound.MAX_SCORE, gameRound.getScore());
    }

    @Test
    void testFinishRoundSuccessfulRoundMediumScore() {
        Cake targetCake = gameRound.getTargetCake();
        Cake userCake = gameRound.getUserCake();
        // Match cake
        userCake.setNumberOfTiers(targetCake.getNumberOfTiers());
        userCake.setCakeColor(targetCake.getCakeColor());
        userCake.setGlaze(targetCake.getGlaze());
        userCake.setTopping(targetCake.getTopping());
        userCake.setDecoration(targetCake.getDecoration());

        assertFalse(gameRound.isVictory());

        gameRound.finishRound(20);

        assertEquals(GameRound.MEDIUM_SCORE_TIME_LIMIT, gameRound.getCompletionTime());
        assertTrue(gameRound.isVictory());
        assertEquals(GameRound.MEDIUM_SCORE, gameRound.getScore());
    }

    @Test
    void testFinishRoundTimeSuccessfulMaxAllowedTime() {
        Cake targetCake = gameRound.getTargetCake();
        Cake userCake = gameRound.getUserCake();
        // Match cake
        userCake.setNumberOfTiers(targetCake.getNumberOfTiers());
        userCake.setCakeColor(targetCake.getCakeColor());
        userCake.setGlaze(targetCake.getGlaze());
        userCake.setTopping(targetCake.getTopping());
        userCake.setDecoration(targetCake.getDecoration());

        assertFalse(gameRound.isVictory());

        gameRound.finishRound(GameRound.MAX_ROUND_TIME);

        assertEquals(GameRound.MAX_ROUND_TIME, gameRound.getCompletionTime());
        assertTrue(gameRound.isVictory());
        assertEquals(GameRound.MIN_SCORE, gameRound.getScore());
    }


    @Test
    void testFinishRoundFailedRound() {
        Cake targetCake = gameRound.getTargetCake();
        Cake userCake = gameRound.getUserCake();
        // Make different cakes
        targetCake.setNumberOfTiers(2);
        userCake.setNumberOfTiers(3);

        assertFalse(gameRound.isVictory());

        gameRound.finishRound(20);

        assertEquals(20, gameRound.getCompletionTime());
        assertFalse(gameRound.isVictory());
        assertEquals(0, gameRound.getScore());
    } 

    @Test
    void testFinishRoundTimeUnderLowerBoundary() {
        assertFalse(gameRound.isVictory());

        gameRound.finishRound(-1);

        assertEquals(0, gameRound.getCompletionTime());
        assertFalse(gameRound.isVictory());
        assertEquals(0, gameRound.getScore());
    }

    @Test
    void testFinishRoundTimeOverUpperBoundary() {
        assertFalse(gameRound.isVictory());

        gameRound.finishRound(GameRound.MAX_ROUND_TIME + 1);

        assertEquals(0, gameRound.getCompletionTime());
        assertFalse(gameRound.isVictory());
        assertEquals(0, gameRound.getScore());
    }
}
