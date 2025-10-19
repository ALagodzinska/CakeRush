package model;

import java.util.ArrayList;

// Represents a library of all played games. Contains a list of played games.
public class GameLibrary {    
    private static int nextGameID = 1;          // id for the next game
    private ArrayList<GameSession> playedGames; // list of all played games


    // EFFECTS: Creates a game library with empty list of games.
    public GameLibrary() {
        playedGames = new ArrayList<>();
    }

    // EFFECTS: Creates a game library with specified next game id and an empty list of games.
    public GameLibrary(int nextID) {
        nextGameID = nextID;
        playedGames = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: Creates a new game and adds game to the list of games, inreases the next ID by one.
    public GameSession createGame() {
        GameSession game = new GameSession(nextGameID);
        nextGameID++;

        playedGames.add(game);

        return game;
    }

    // MODIFIES: this
    // EFFECTS: Creates a game with specified parameters and adds it to the list of games, inreases the next ID by one.
    public void addExistingGame(GameSession game) {
        nextGameID++;

        playedGames.add(game);
    }

    // EFFECTS: Returns a game from the list of all games based on the passed id. 
    // If the game with such id is not present in the list returns null.
    public GameSession getGameByID(int id) {
        for (GameSession game: playedGames) {
            if (game.getGameID() == id) {
                return game;
            }
        }

        return null;
    }

    public ArrayList<GameSession> getPlayedGames() {
        return this.playedGames;
    }

    public int getNextID() {
        return nextGameID;
    }
}
