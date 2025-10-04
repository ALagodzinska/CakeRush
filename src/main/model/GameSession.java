package model;

import java.util.ArrayList;

// Represents a single game session. Contains a list of completed rounds and ends the game session when a round is lost.
public class GameSession {

    // EFFECTS: Creates a game session with an empty list of rounds, sets the game finished state to false, 
    // assigns total score and total time played to zero. 
    public GameSession() {
        // stub
    }

    // EFFECTS: Adds a new round to the list of rounds and updates the game statistics.
    public void addRound(GameRound round) {
        // stub
    }    

    public ArrayList<GameRound> getRounds() {
        // stub
        return null;
    }

    public boolean isFinished() {
        // stub
        return false;
    }

    public int getTotalScore() {
        // stub
        return 0;
    }

    public int getTotalTimePlayed() {
        // stub
        return 0;
    }

    // MODIFIES: this
    // EFFECTS: Updates the total time played, total score. If round was lost, updates the game state to finished.
    private void updateAfterRound(GameRound round) {
        // stub
    }
}
