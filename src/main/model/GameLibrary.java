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

    // MODIFIES: this
    // EFFECTS: Creates a new game and adds game to the list of games, inreases the next ID by one.
    public void createGame() {
        GameSession game = new GameSession(nextGameID);
        nextGameID++;

        playedGames.add(game);
    }

    public ArrayList<GameSession> getPlayedGames() {
        return this.playedGames;
    }

    public int getNextID() {
        return nextGameID;
    }
}
