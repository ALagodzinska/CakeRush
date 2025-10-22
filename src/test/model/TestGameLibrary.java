package model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;

@ExcludeFromJacocoGeneratedReport
public class TestGameLibrary {
    private GameLibrary gameLibrary;

    @BeforeEach
    void setup() {
        gameLibrary = new GameLibrary();
    }

    @Test
    void testConstructor() {
        assertEquals(0, gameLibrary.getGames().size());
    }

    @Test
    void testCounstructorWithParams() {
        GameLibrary newGameLibrary = new GameLibrary(2);
        assertEquals(2, newGameLibrary.getNextID());
        assertEquals(0, newGameLibrary.getGames().size());
    }

    @Test
    void testCreateGameOne() {
        GameSession game = gameLibrary.createGame();
        assertEquals(1, gameLibrary.getGames().size());
        assertEquals(game, gameLibrary.getGames().get(0));
        assertEquals(2, gameLibrary.getNextID());
    }

    @Test
    void testCreateGameMultiple() {
        GameSession game1 = gameLibrary.createGame();
        GameSession game2 = gameLibrary.createGame();


        assertEquals(2, gameLibrary.getGames().size());
        assertEquals(game1, gameLibrary.getGames().get(0));
        assertEquals(game2, gameLibrary.getGames().get(1));
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
        int id = gameLibrary.getGames().get(0).getGameID();
        assertEquals(gameLibrary.getGames().get(0), gameLibrary.getGameByID(id));
    }

    @Test
    void testAddExistingGame() {
        GameSession newGame = new GameSession(2);
        gameLibrary.addExistingGame(newGame);
        assertEquals(3, gameLibrary.getNextID());
        assertEquals(1, gameLibrary.getGames().size());
        assertEquals(newGame, gameLibrary.getGames().get(0));
    }

    @Test
    void testToJsonEmptyLibrary() {
        JSONObject json = gameLibrary.toJson();

        assertEquals(1, json.getInt("nextID"));
        assertEquals(0, json.getJSONArray("games").length());
    }

    @Test
    void testToJson() {
        GameSession newGame = new GameSession(2);
        gameLibrary.addExistingGame(newGame);
        JSONObject json = gameLibrary.toJson();

        assertEquals(3, json.getInt("nextID"));
        assertEquals(1, json.getJSONArray("games").length());
    }
}
