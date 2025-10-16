package model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

public class TestGameLibrary {
    private GameLibrary gameLibrary;
    private GameSession game1;
    private GameSession game2;

    @BeforeEach
    void setup() {
        gameLibrary = new GameLibrary();
        game1 = new GameSession(1);
        game2 = new GameSession(2);
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
        gameLibrary.createGame();
        gameLibrary.createGame();
        assertEquals(2, gameLibrary.getPlayedGames().size());
        assertEquals(1, gameLibrary.getPlayedGames().get(0).getGameID());
        assertEquals(2, gameLibrary.getPlayedGames().get(0).getGameID());
        assertEquals(3, gameLibrary.getNextID());
    }
}
