package persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import org.junit.jupiter.api.Test;

import model.Cake;
import model.GameLibrary;
import model.GameRound;
import model.GameSession;

// Adapted from: 
//   Project Title: JsonSerializationDemo
//   Author: CPSC210
//   Type: source code
//   URL: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

public class TestJsonReader extends TestJson {
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
        JsonReader reader = new JsonReader("./data/testReaderEmptyGameLibrary.json");
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
            assertEquals(1, gameLibrary.getPlayedGames().size());
            GameSession game = gameLibrary.getPlayedGames().get(0);          
            checkGame(1, false, 0, 3, 0, game);
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    // TODO: debug
    @Test
    void testReaderGeneralWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralGameLibrary.json");
        try {
            GameLibrary gameLibrary = reader.read();
            ArrayList<GameSession> games = gameLibrary.getPlayedGames();
            assertEquals(2, games.size());
            GameSession gameOne = games.get(0);
            GameSession gameTwo = games.get(1);

            checkGame(1, false, 0, 2, 2, gameOne);
            checkGame(2, false, 0, 1, 1, gameTwo);
            
            Cake targetCake = new Cake();
            Cake userCake = new Cake();
            targetCake.setNumberOfTiers(2);

            checkRound(targetCake, userCake, false, gameOne.getRounds().get(0));
            checkRound(userCake, userCake, true, gameOne.getRounds().get(1));
            checkRound(targetCake, userCake, false, gameTwo.getRounds().get(0));
            
            
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
