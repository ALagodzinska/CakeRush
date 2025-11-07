package model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
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
        assertEquals(0, gameRound.getRoundTime());
        assertEquals(0, gameRound.getScore());
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

        gameRound.finishRound(30);

        assertTrue(gameRound.isVictory());
        assertEquals(30, gameRound.getRoundTime());
        assertEquals(10, gameRound.getScore());
    }

    @Test
    void testFinishRoundFailedRound() {
        Cake targetCake = gameRound.getTargetCake();
        Cake userCake = gameRound.getUserCake();
        // Make different cakes
        targetCake.setNumberOfTiers(2);
        userCake.setNumberOfTiers(3);

        assertFalse(gameRound.isVictory());

        gameRound.finishRound(10);

        assertFalse(gameRound.isVictory());
        assertEquals(10, gameRound.getRoundTime());
        assertEquals(0, gameRound.getScore());
    }

    @Test
    void testFinishRoundSuccesfulFast() {
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

        assertEquals(10, gameRound.getRoundTime());
        assertTrue(gameRound.isVictory());        
        assertEquals(30, gameRound.getScore());
    }

    @Test
    void testGameRoundParmeterConstructor() {
        Cake targetCake = new Cake(random);
        Cake userCake = new Cake();

        GameRound newRound = new GameRound(targetCake, userCake, false, 20, 10);
        
        assertEquals(targetCake, newRound.getTargetCake());
        assertEquals(userCake, newRound.getUserCake());
        assertEquals(false, newRound.isVictory());
        assertEquals(20, newRound.getRoundTime());
        assertEquals(10, newRound.getScore());
    }

    @Test
    void testToJson() {
        JSONObject json = gameRound.toJson();

        assertEquals(false, json.getBoolean("isVictory"));
        assertNotNull(json.getJSONObject("userCake"));
        assertNotNull(json.getJSONObject("targetCake"));
        assertEquals(0, json.getInt("roundTime"));
        assertEquals(0, json.getInt("score"));
    }
}
