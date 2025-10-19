package persistence;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.JSONArray;
import org.json.JSONObject;

import model.Cake;
import model.GameLibrary;
import model.GameRound;
import model.GameSession;
import model.CakeElements.CakeColor;
import model.CakeElements.Decoration;
import model.CakeElements.Glaze;
import model.CakeElements.Topping;

// Adapted from: 
//   Project Title: JsonSerializationDemo
//   Author: CPSC210
//   Type: source code
//   URL: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

// Represents a reader that reads workroom from JSON data stored in file.
public class JsonReader {
    private String source;      // Stores the source path to a file

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads game library from file and returns it;
    // throws IOException if an error occurs reading data from file
    public GameLibrary read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseGameLibrary(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses list of games from JSON object and returns it
    private GameLibrary parseGameLibrary(JSONObject jsonObject) {
        int nextGameID = jsonObject.getInt("nextID");
        System.out.println(nextGameID);
        GameLibrary gameLibrary = new GameLibrary(nextGameID);
        addGames(gameLibrary, jsonObject);
        return gameLibrary;
    }

    // MODIFIES: GameLibrary
    // EFFECTS: parses list of games from JSON object and adds them to game library
    private void addGames(GameLibrary library, JSONObject jsonObject) {
        JSONArray gamesArray = jsonObject.getJSONArray("games");
        for (Object json : gamesArray) {
            JSONObject gameObject = (JSONObject) json;
            addGame(library, gameObject);
        }
    }

    // MODIFIES: GameLibrary
    // EFFECTS: parses game from JSON object and adds it to game library list of games
    private void addGame(GameLibrary library, JSONObject jsonObject) {
        int gameID = jsonObject.getInt("gameID");
        boolean isFinished = jsonObject.getBoolean("isFinished");
        int totalScore = jsonObject.getInt("totalScore");
        int livesLeft = jsonObject.getInt("livesLeft");        
        GameSession game = new GameSession(gameID, isFinished, totalScore, livesLeft);        

        addRounds(game, jsonObject);
        
        library.addExistingGame(game);
    }

    // MODIFIES: GameSession
    // EFFECTS: parses list of rounds from JSON object and adds them to game session
    private void addRounds(GameSession game, JSONObject jsonObject) {
        JSONArray roundsArray = jsonObject.getJSONArray("rounds");
        for (Object json : roundsArray) {
            JSONObject roundObject = (JSONObject) json;
            addRound(game, roundObject);
        }
    }

    // MODIFIES: GameSession
    // EFFECTS: parses round from JSON object and adds it to game session list of rounds
    private void addRound(GameSession game, JSONObject jsonObject) {        
        boolean isVictory = jsonObject.getBoolean("isVictory"); 
        
        Cake userCake = getUserCake(jsonObject);
        Cake targetCake = getTargetCake(jsonObject);
        
        GameRound round = new GameRound(targetCake, userCake, isVictory);
        
        game.addSavedRound(round);
    }

    // MODIFIES: GameRound
    // EFFECTS: parses cake from JSON round object and adds it to round
    private Cake getUserCake(JSONObject jsonObject) {
        Cake userCake = new Cake();

        JSONObject jsonUserCake = jsonObject.getJSONObject("userCake");
        addCakeElements(userCake, jsonUserCake);

        return userCake;
    }

    // MODIFIES: GameRound
    // EFFECTS: parses cake from JSON round object and adds it to round
    private Cake getTargetCake(JSONObject jsonObject) {
        Cake targetCake = new Cake();

        JSONObject jsonTargetCake = jsonObject.getJSONObject("targetCake");
        addCakeElements(targetCake, jsonTargetCake);

        return targetCake;      
    }

    // MODIFIES: Cake
    // EFFECTS: parses all cake elements from JSON object and adds them to cake
    private void addCakeElements(Cake cake, JSONObject jsonObject) {
        int numberOfTiers = jsonObject.getInt("numberOfTiers");
        CakeColor cakeColor = CakeColor.valueOf(jsonObject.getString("cakeColor"));
        Glaze glaze = Glaze.valueOf(jsonObject.getString("glaze"));
        Topping topping = Topping.valueOf(jsonObject.getString("topping"));
        Decoration decoration = Decoration.valueOf(jsonObject.getString("decoration"));

        cake.setNumberOfTiers(numberOfTiers);
        cake.setCakeColor(cakeColor);
        cake.setTopping(topping);
        cake.setGlaze(glaze);
        cake.setDecoration(decoration);
    }
}
