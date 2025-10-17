package persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.Test;

import model.Cake;
import model.GameLibrary;
import model.GameRound;
import model.GameSession;

// Adapted from: 
//   Project Title: JsonSerializationDemo
//   Author: CPSC210
//   Type: source code
//   URL: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

public class TestJsonReader extends TestJson{
    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            GameLibrary gameLibrary = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyGameLibrary() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyGamesList.json");
        try {
            GameLibrary gameLibrary = reader.read();
            assertEquals(1, gameLibrary.getNextID());
            assertEquals(0, gameLibrary.getPlayedGames().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderEmptyRounds() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyRoundList.json");
        try {
            GameLibrary gameLibrary = reader.read();
            assertEquals(2, gameLibrary.getNextID());
            assertEquals(1, gameLibrary.getPlayedGames().size());
            GameSession game = gameLibrary.getPlayedGames().get(0);
            checkGame(1, false, 0, 3, new ArrayList<GameRound>(), game);
            assertEquals(0, game.getRounds().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralGameLibrary.json");
        try {
            GameLibrary gameLibrary = reader.read();
            assertEquals(3, gameLibrary.getNextID());
            assertEquals(1, gameLibrary.getPlayedGames().size());
            ArrayList<GameSession> games = gameLibrary.getPlayedGames();
            assertEquals(2, games.size());
            ArrayList<GameRound> gameOneRounds = games.get(0).getRounds();
            ArrayList<GameRound> gameTwoRounds = games.get(1).getRounds();
            Cake targetCake = new Cake();
            Cake userCake = new Cake();
            userCake.setNumberOfTiers(2);
            checkRound(targetCake, userCake, false, gameOneRounds.get(0));
            checkRound(targetCake, targetCake, true, gameOneRounds.get(1));
            checkRound(targetCake, userCake, false, gameTwoRounds.get(0));
            
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
