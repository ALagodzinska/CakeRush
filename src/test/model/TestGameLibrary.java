package model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

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
        GameSession game = gameLibrary.createGame();
        assertEquals(1, gameLibrary.getPlayedGames().size());
        assertEquals(game, gameLibrary.getPlayedGames().get(0));
        assertEquals(2, gameLibrary.getNextID());
    }

    @Test
    void testCreateGameMultiple() {
        GameSession game1 = gameLibrary.createGame();
        GameSession game2 = gameLibrary.createGame();


        assertEquals(2, gameLibrary.getPlayedGames().size());
        assertEquals(game1, gameLibrary.getPlayedGames().get(0));
        assertEquals(game2, gameLibrary.getPlayedGames().get(1));
        assertEquals(game2.getGameID() + 1, gameLibrary.getNextID());
    }

    @Test
    void testGetGameByIDNotInTheList() {
        gameLibrary.createGame();
        assertNull(gameLibrary.getGameByID(34));
    }

    @Test
    void testGetGameBy() {
        gameLibrary.createGame();
        int id = gameLibrary.getPlayedGames().get(0).getGameID();
        assertEquals(gameLibrary.getPlayedGames().get(0), gameLibrary.getGameByID(id));
    }
}
