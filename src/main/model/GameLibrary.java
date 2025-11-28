package model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.json.JSONArray;
import org.json.JSONObject;

import persistence.Writable;

// Represents a library of all played games. Contains a list of played games.
public class GameLibrary implements Writable {    
    private int nextGameID = 1;          // id for the next game
    private ArrayList<GameSession> games; // list of all played games


    // EFFECTS: Creates a game library with empty list of games.
    public GameLibrary() {
        EventLog.getInstance().logEvent(new Event("Starting empty game library"));
        games = new ArrayList<>();
    }

    // EFFECTS: Creates a game library with specified next game id and an empty list of games.
    public GameLibrary(int nextID) {
        EventLog.getInstance().logEvent(new Event("Loading game library with next id " + nextID));
        nextGameID = nextID;
        games = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: Creates a new game and adds game to the list of games, inreases the next ID by one.
    public GameSession createGame() {        
        GameSession game = new GameSession(nextGameID);
        EventLog.getInstance().logEvent(new Event("Created new game with id " + game.getGameID()));

        nextGameID++;       

        games.add(game);       

        return game;
    }

    // MODIFIES: this
    // EFFECTS: Creates a game with specified parameters and adds it to the list of games, inreases the next ID by one.
    public void addExistingGame(GameSession game) {
        EventLog.getInstance().logEvent(new Event("Loaded existing game with id " + game.getGameID()));
        nextGameID = game.getGameID() + 1;
        games.add(game);        
    }

    // EFFECTS: Returns a game from the list of all games based on the passed id. 
    // If the game with such id is not present in the list returns null.
    public GameSession getGameByID(int id) {
        for (GameSession game: games) {
            if (game.getGameID() == id) {
                return game;
            }
        }

        return null;
    }

    public ArrayList<GameSession> getGames() {
        return this.games;
    }

    public int getNextID() {
        return this.nextGameID;
    }

    // Adapted from: 
    //   Java Code Geeks: Filter a List by Any Matching Field
    //   Author: Yatin Batra
    //   URL: https://www.javacodegeeks.com/filter-a-list-by-any-matching-field.html#google_vignette
    
    // EFFECTS: Returns the list of games that are not finished.
    public List<GameSession> getPlayableGames() {
        return this.games.stream()
                    .filter(game -> game.isFinished() == false)
                    .collect(Collectors.toList());
    }

    // EFFECTS: Returns game library as a json object.
    @Override
    public JSONObject toJson() {
        EventLog.getInstance().logEvent(new Event("Saving game library with next id " + nextGameID 
                + " and " + games.size() + " games"));
        JSONObject json = new JSONObject();
        json.put("nextID", nextGameID);
        json.put("games", gamesToJson());
        return json;
    }

    // EFFECTS: Converts and returns a list of games as a json array.
    private JSONArray gamesToJson() {
        JSONArray jsonArray = new JSONArray();

        for (GameSession game : games) {
            jsonArray.put(game.toJson());
        }

        return jsonArray;
    }
}
