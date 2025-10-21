package persistence;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.*;

import model.Cake;
import model.GameLibrary;
import model.GameRound;
import model.GameSession;

public class TestJsonWriter extends TestJson {
    @Test
    public void testWriterInvalidFile() {
        try {
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    public void testWriterEmptyGameLibrary() {
        try {
            GameLibrary gameLibrary = new GameLibrary();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyGameLibrary.json");
            writer.open();
            writer.write(gameLibrary);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyGameLibrary.json");
            gameLibrary = reader.read();
            assertEquals(1, gameLibrary.getNextID());
            assertEquals(0, gameLibrary.getGames().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    public void testWriterEmptyRoundList() {
        try {
            GameLibrary gameLibrary = new GameLibrary();
            gameLibrary.addExistingGame(new GameSession(1));
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyRoundList.json");
            writer.open();
            writer.write(gameLibrary);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyRoundList.json");
            gameLibrary = reader.read();
            assertEquals(2, gameLibrary.getNextID());
            assertEquals(1, gameLibrary.getGames().size());
            checkGame(1, false, 0, 3, 0, gameLibrary.getGames().get(0));
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    public void testWriterGeneralGameLibrary() {
        try {
            GameLibrary gameLibrary = setupGeneralGameLibrary();
            Cake targetCake = new Cake();
            Cake userCake = new Cake();
            targetCake.setNumberOfTiers(2);
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralWorkroom.json");
            writer.open();
            writer.write(gameLibrary);
            writer.close();
            JsonReader reader = new JsonReader("./data/testWriterGeneralWorkroom.json");
            gameLibrary = reader.read();
            assertEquals(3, gameLibrary.getNextID());
            assertEquals(2, gameLibrary.getGames().size());
            GameSession gameOne = gameLibrary.getGames().get(0);
            GameSession gameTwo = gameLibrary.getGames().get(1);
            checkGame(1, false, 0, 2, 2, gameOne);
            checkGame(2, false, 0, 1, 1, gameTwo);
            checkRound(targetCake, userCake, false, gameOne.getRounds().get(0));
            checkRound(userCake, userCake, true, gameOne.getRounds().get(1));
            checkRound(targetCake, userCake, false, gameTwo.getRounds().get(0));
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    private GameLibrary setupGeneralGameLibrary() {
        GameLibrary gameLibrary = new GameLibrary();
        GameSession gameOne = new GameSession(1, false, 0, 2);
        GameSession gameTwo = new GameSession(2, false, 0, 1);
        Cake targetCake = new Cake();
        Cake userCake = new Cake();
        targetCake.setNumberOfTiers(2);
        gameOne.addPlayedRound(new GameRound(targetCake, userCake, false));
        gameOne.addPlayedRound(new GameRound(userCake, userCake, true));
        gameTwo.addPlayedRound(new GameRound(targetCake, userCake, false));
        gameLibrary.addExistingGame(gameOne);
        gameLibrary.addExistingGame(gameTwo);

        return gameLibrary;
    }
}
