package persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;
import static org.junit.Assert.assertEquals;
import org.junit.jupiter.api.Test;

import model.Cake;
import model.GameLibrary;
import model.GameRound;
import model.GameSession;
import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;

@ExcludeFromJacocoGeneratedReport
public class TestJsonWriter extends JsonTestBase {
    @Test
    void testWriterInvalidFile() {
        try {
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyGameLibrary() {
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
    void testWriterEmptyRoundList() {
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
            checkGame(1, false, 0, 3, 0, 0, gameLibrary.getGames().get(0));
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralGameLibrary() {
        try {
            GameLibrary gameLibrary = setupGeneralGameLibrary();
            Cake targetCake = new Cake();
            Cake userCake = new Cake();
            targetCake.setNumberOfTiers(2);
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralGameLibrary.json");
            writer.open();
            writer.write(gameLibrary);
            writer.close();
            JsonReader reader = new JsonReader("./data/testWriterGeneralGameLibrary.json");
            gameLibrary = reader.read();
            assertEquals(3, gameLibrary.getNextID());
            assertEquals(2, gameLibrary.getGames().size());
            GameSession gameOne = gameLibrary.getGames().get(0);
            GameSession gameTwo = gameLibrary.getGames().get(1);
            checkGame(1, false, 0, 2, 2, 30, gameOne);
            checkGame(2, false, 0, 2, 1, 30, gameTwo);
            checkRound(targetCake, userCake, false, 15, 0, gameOne.getRounds().get(0));
            checkRound(userCake, userCake, true, 15, 10, gameOne.getRounds().get(1));
            checkRound(targetCake, userCake, false, 30, 0, gameTwo.getRounds().get(0));
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    private GameLibrary setupGeneralGameLibrary() {
        GameLibrary gameLibrary = new GameLibrary();
        GameSession gameOne = new GameSession(1, false, 0, 2, 30);
        GameSession gameTwo = new GameSession(2, false, 0, 2, 30);
        Cake targetCake = new Cake();
        Cake userCake = new Cake();
        targetCake.setNumberOfTiers(2);
        gameOne.addSavedRound(new GameRound(targetCake, userCake, false, 15, 0));
        gameOne.addSavedRound(new GameRound(userCake, userCake, true, 15, 10));
        gameTwo.addSavedRound(new GameRound(targetCake, userCake, false, 30, 0));
        gameLibrary.addExistingGame(gameOne);
        gameLibrary.addExistingGame(gameTwo);

        return gameLibrary;
    }
}
