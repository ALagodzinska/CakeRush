package persistence;

import java.io.IOException;
import java.util.ArrayList;
import org.json.JSONObject;

import model.Cake;
import model.GameLibrary;
import model.GameRound;
import model.GameSession;

// Adapted from: 
//   Project Title: JsonSerializationDemo
//   Author: CPSC210
//   Type: source code
//   URL: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

// Represents a reader that reads workroom from JSON data stored in file.
public class JsonReader {
    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
    }

    // EFFECTS: reads list of played games from file and returns it;
    // throws IOException if an error occurs reading data from file
    public ArrayList<GameSession> read() throws IOException {
        // stub
        return null;
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        // stub 
        return null;
    }

    // EFFECTS: parses list of games from JSON object and returns it
    private GameLibrary parseGameLibrary(JSONObject jsonObject) {
        // stub
        return null;
    }

    // MODIFIES: GameLibrary
    // EFFECTS: parses list of games from JSON object and adds them to game library
    private void addGames(GameLibrary library, JSONObject jsonObject) {
        // stub
    }

    // MODIFIES: GameLibrary
    // EFFECTS: parses game from JSON object and adds it to game library list of games
    private void addGame(GameLibrary library, JSONObject jsonObject) {
        // stub
    }

    // MODIFIES: GameSession
    // EFFECTS: parses list of rounds from JSON object and adds them to game session
    private void addRounds(GameSession game, JSONObject jsonObject) {
        // stub
    }

    // MODIFIES: GameSession
    // EFFECTS: parses round from JSON object and adds it to game session list of rounds
    private void addRound(GameSession game, JSONObject jsonObject) {
        // stub
    }

    // MODIFIES: GameRound
    // EFFECTS: parses cake from JSON round object and adds it to round
    private void addCake(GameRound round, JSONObject jsonObject) {
        // stub
    }

    // MODIFIES: Cake
    // EFFECTS: parses all cake elements from JSON object and adds them to cake
    private void addCakeElements(Cake cake, JSONObject jsonObject) {
        // stub
    }
}
