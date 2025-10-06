package model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Random;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestGameSession {
    private GameSession gameSession;
    private GameRound wonRound;
    private GameRound lostRound;
    private Random random = new Random();

    @BeforeEach
    void runBefore() {
        gameSession = new GameSession();

        wonRound = new GameRound(random);
        Cake userCake = wonRound.getUserCake();
        Cake targetCake = wonRound.getTargetCake();
        userCake.setNumberOfTiers(targetCake.getNumberOfTiers());
        userCake.setCakeColor(targetCake.getCakeColor());
        userCake.setGlaze(targetCake.getGlaze());
        userCake.setTopping(targetCake.getTopping());
        userCake.setDecoration(targetCake.getDecoration());
        wonRound.finishRound(10);

        lostRound = new GameRound(random);
        lostRound.getTargetCake().setNumberOfTiers(1);
        lostRound.getUserCake().setNumberOfTiers(3);
        lostRound.finishRound(20);
    }

    @Test
    void testConstructor() {
        assertEquals(gameSession.getNextID() - 1, gameSession.getGameID());
        assertEquals(0, gameSession.getRounds().size());
        assertFalse(gameSession.isFinished());
        assertEquals(0, gameSession.getTotalScore());
        assertEquals(0, gameSession.getTotalTimePlayed());
    }

    @Test
    void testAddRoundOneRound() {
        assertEquals(0, gameSession.getRounds().size());  

        gameSession.addRound(wonRound);

        assertEquals(1, gameSession.getRounds().size());
        assertEquals(wonRound, gameSession.getRounds().get(0));   
        assertEquals(10, gameSession.getTotalScore());
        assertEquals(10, gameSession.getTotalTimePlayed());
        assertFalse(gameSession.isFinished());
    }

    @Test
    void testAddRoundMultipleAfterRoundVictory() {
        assertEquals(0, gameSession.getRounds().size());  

        gameSession.addRound(wonRound);
        gameSession.addRound(wonRound);

        assertEquals(2, gameSession.getRounds().size());
        assertEquals(wonRound, gameSession.getRounds().get(0));  
        assertEquals(wonRound, gameSession.getRounds().get(1));  
        assertEquals(20, gameSession.getTotalScore());
        assertEquals(20, gameSession.getTotalTimePlayed());
        assertFalse(gameSession.isFinished());
    }

    @Test
    void testAddRoundMultipleAfterRoundLoss() {
        assertEquals(0, gameSession.getRounds().size());  

        gameSession.addRound(wonRound);
        gameSession.addRound(lostRound);
        gameSession.addRound(wonRound);

        assertEquals(2, gameSession.getRounds().size());
        assertEquals(wonRound, gameSession.getRounds().get(0));  
        assertEquals(lostRound, gameSession.getRounds().get(1)); 
        assertEquals(10, gameSession.getTotalScore());
        assertEquals(30, gameSession.getTotalTimePlayed());
        assertTrue(gameSession.isFinished());
    }
}
