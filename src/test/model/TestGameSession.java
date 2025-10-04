package model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Random;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestGameSession {
    private GameSession gameSession;
    private GameRound victoryRound;
    private GameRound lossRound;
    private Random random = new Random();

    @BeforeEach
    void runBefore() {
        gameSession = new GameSession();

        victoryRound = new GameRound(random);
        Cake userCake = victoryRound.getUserCake();
        Cake targetCake = victoryRound.getTargetCake();
        userCake.setNumberOfTiers(targetCake.getNumberOfTiers());
        userCake.setCakeColor(targetCake.getCakeColor());
        userCake.setGlaze(targetCake.getGlaze());
        userCake.setTopping(targetCake.getTopping());
        userCake.setDecoration(targetCake.getDecoration());
        victoryRound.finishRound(10);

        lossRound = new GameRound(random);
        lossRound.getTargetCake().setNumberOfTiers(1);
        lossRound.getUserCake().setNumberOfTiers(3);
        lossRound.finishRound(20);
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

        gameSession.addRound(victoryRound);

        assertEquals(1, gameSession.getRounds().size());
        assertEquals(victoryRound, gameSession.getRounds().get(0));   
        assertEquals(10, gameSession.getTotalScore());
        assertEquals(10, gameSession.getTotalTimePlayed());
        assertFalse(gameSession.isFinished());
    }

    @Test
    void testAddRoundMultipleAfterRoundVictory() {
        assertEquals(0, gameSession.getRounds().size());  

        gameSession.addRound(victoryRound);
        gameSession.addRound(victoryRound);

        assertEquals(2, gameSession.getRounds().size());
        assertEquals(20, gameSession.getTotalScore());
        assertEquals(20, gameSession.getTotalTimePlayed());
        assertFalse(gameSession.isFinished());
    }

    @Test
    void testAddRoundMultipleAfterRoundLoss() {
        assertEquals(0, gameSession.getRounds().size());  

        gameSession.addRound(victoryRound);
        gameSession.addRound(lossRound);
        gameSession.addRound(victoryRound);

        assertEquals(2, gameSession.getRounds().size());
        assertEquals(10, gameSession.getTotalScore());
        assertEquals(30, gameSession.getTotalTimePlayed());
        assertTrue(gameSession.isFinished());
    }
}
