package model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Random;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;

@ExcludeFromJacocoGeneratedReport
public class TestGameSession {
    private GameSession gameSession;
    private GameRound wonRound;
    private GameRound lostRound;
    private Random random = new Random();

    @BeforeEach
    void runBefore() {
        gameSession = new GameSession(1);

        wonRound = new GameRound(random);
        Cake userCake = wonRound.getUserCake();
        Cake targetCake = wonRound.getTargetCake();
        userCake.setNumberOfTiers(targetCake.getNumberOfTiers());
        userCake.setCakeColor(targetCake.getCakeColor());
        userCake.setGlaze(targetCake.getGlaze());
        userCake.setTopping(targetCake.getTopping());
        userCake.setDecoration(targetCake.getDecoration());
        wonRound.finishRound(30);

        lostRound = new GameRound(random);
        lostRound.getTargetCake().setNumberOfTiers(1);
        lostRound.getUserCake().setNumberOfTiers(3);
        lostRound.finishRound(30);
    }

    @Test
    void testConstructor() {
        assertEquals(1, gameSession.getGameID());
        assertEquals(0, gameSession.getRounds().size());
        assertFalse(gameSession.isFinished());
        assertEquals(0, gameSession.getTotalScore());
        assertEquals(GameSession.MAX_LIVES, gameSession.getLivesLeft());
        assertEquals(0, gameSession.getTotalTimePLayed());
    }

    @Test
    void testAddRoundOne() {
        assertEquals(0, gameSession.getRounds().size());  

        gameSession.addPlayedRound(wonRound);

        assertEquals(1, gameSession.getRounds().size());
        assertEquals(wonRound, gameSession.getRounds().get(0));   
        assertEquals(10, gameSession.getTotalScore());
        assertEquals(GameSession.MAX_LIVES, gameSession.getLivesLeft());
        assertFalse(gameSession.isFinished());
        assertEquals(30, gameSession.getTotalTimePLayed());
    }

    @Test
    void testAddRoundMultipleAfterRoundVictory() {
        assertEquals(0, gameSession.getRounds().size());  

        gameSession.addPlayedRound(wonRound);
        gameSession.addPlayedRound(wonRound);

        assertEquals(2, gameSession.getRounds().size());
        assertEquals(wonRound, gameSession.getRounds().get(0));  
        assertEquals(wonRound, gameSession.getRounds().get(1));  
        assertEquals(20, gameSession.getTotalScore());
        assertEquals(GameSession.MAX_LIVES, gameSession.getLivesLeft());
        assertFalse(gameSession.isFinished());
        assertEquals(60, gameSession.getTotalTimePLayed());
    }

    @Test
    void testAddRoundMultipleAfterOneRoundLoss() {
        assertEquals(0, gameSession.getRounds().size());  

        gameSession.addPlayedRound(wonRound);
        gameSession.addPlayedRound(lostRound);
        gameSession.addPlayedRound(wonRound);

        assertEquals(3, gameSession.getRounds().size());
        assertEquals(wonRound, gameSession.getRounds().get(0));  
        assertEquals(lostRound, gameSession.getRounds().get(1)); 
        assertEquals(wonRound, gameSession.getRounds().get(2)); 
        assertEquals(20, gameSession.getTotalScore());
        assertEquals(2, gameSession.getLivesLeft());
        assertFalse(gameSession.isFinished());
        assertEquals(90, gameSession.getTotalTimePLayed());
    }

    @Test
    void testAddRoundMultipleGameOver() {
        assertEquals(0, gameSession.getRounds().size());  

        gameSession.addPlayedRound(lostRound);
        gameSession.addPlayedRound(lostRound);
        gameSession.addPlayedRound(lostRound);

        assertEquals(3, gameSession.getRounds().size());

        assertEquals(lostRound, gameSession.getRounds().get(0));  
        assertEquals(lostRound, gameSession.getRounds().get(1)); 
        assertEquals(lostRound, gameSession.getRounds().get(2)); 
        
        assertEquals(0, gameSession.getTotalScore());
        assertEquals(0, gameSession.getLivesLeft());
        assertTrue(gameSession.isFinished());
    }

    @Test
    void testToJson() {
        gameSession.addPlayedRound(lostRound);
        gameSession.addPlayedRound(lostRound);       

        JSONObject json = gameSession.toJson();

        assertEquals(1, json.getInt("gameID"));
        assertEquals(false, json.getBoolean("isFinished"));
        assertEquals(0, json.getInt("totalScore"));
        assertEquals(1, json.getInt("livesLeft"));
        assertEquals(2, json.getJSONArray("rounds").length());
        assertEquals(60, json.getInt("totalTimePlayed"));
    }

    @Test
    void testAddSavedRound() {
        gameSession.addSavedRound(lostRound);
        assertEquals(1, gameSession.getRounds().size());
        assertEquals(lostRound, gameSession.getRounds().get(0));
    }

    @Test
    void testConstructorWithParameters() {
        GameSession game = new GameSession(1, false, 100, 2, 30);

        assertEquals(1, game.getGameID());
        assertEquals(false, game.isFinished());
        assertEquals(100, game.getTotalScore());
        assertEquals(2, game.getLivesLeft());
        assertEquals(0, game.getRounds().size());
        assertEquals(30, game.getTotalTimePLayed());
    }
}
