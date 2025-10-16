package model;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestGameLibrary {
    private GameLibrary gameLibrary;

    @BeforeEach
    void setup() {
        gameLibrary = new GameLibrary();
    }

    @Test
    void testConstructor() {
        assertEquals(0, gameLibrary.getPlayedGames().size());
    }

    @Test
    void testCreateGameOne() {
        gameLibrary.createGame();
        assertEquals(1, gameLibrary.getPlayedGames().size());
        assertEquals(1, gameLibrary.getPlayedGames().get(0).getGameID());
        assertEquals(2, gameLibrary.getNextID());
    }

    @Test
    void testCreateGameMultiple() {
        int firstID = gameLibrary.getNextID();
        gameLibrary.createGame();
        int secondID = gameLibrary.getNextID();
        gameLibrary.createGame();


        assertEquals(2, gameLibrary.getPlayedGames().size());
        assertEquals(firstID, gameLibrary.getPlayedGames().get(0).getGameID());
        assertEquals(secondID, gameLibrary.getPlayedGames().get(1).getGameID());
        assertEquals(secondID + 1, gameLibrary.getNextID());
    }
}
