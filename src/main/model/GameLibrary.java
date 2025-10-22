package model;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import persistence.Writable;

// Represents a library of all played games. Contains a list of played games.
public class GameLibrary implements Writable {    
    private int nextGameID = 1;          // id for the next game
    private ArrayList<GameSession> games; // list of all played games


    // EFFECTS: Creates a game library with empty list of games.
    public GameLibrary() {
        games = new ArrayList<>();
    }

    // EFFECTS: Creates a game library with specified next game id and an empty list of games.
    public GameLibrary(int nextID) {
        nextGameID = nextID;
        games = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: Creates a new game and adds game to the list of games, inreases the next ID by one.
    public GameSession createGame() {
        GameSession game = new GameSession(nextGameID);
        nextGameID++;

        games.add(game);

        return game;
    }

    // MODIFIES: this
    // EFFECTS: Creates a game with specified parameters and adds it to the list of games, inreases the next ID by one.
    public void addExistingGame(GameSession game) {
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

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("nextID", nextGameID);
        json.put("games", gamesToJson());
        return json;
    }

    private JSONArray gamesToJson() {
        JSONArray jsonArray = new JSONArray();

        for (GameSession game : games) {
            jsonArray.put(game.toJson());
        }

        return jsonArray;
    }
}
