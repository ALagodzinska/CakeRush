package model;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import persistence.Writable;

// Represents a single game session. Contains a list of completed rounds and ends the game session when a round is lost.
public class GameSession implements Writable {
    public static final int MAX_LIVES = 3;

    private int gameID;                     // unique identifier for the game
    private ArrayList<GameRound> rounds;    // all rounds played in the game
    private boolean isFinished;             // true if the game was lost
    private int totalScore;                 // combined score from all played rounds
    private int livesLeft;                  // the number of lives remaining, when 0 game ends
    private int totalTimePlayed;

    // EFFECTS: Creates a game session with an empty list of rounds, sets the game finished state to false, 
    // assigns total score to zero and livesLeft to MAX_LIVES. 
    public GameSession(int gameID) {
        this.gameID = gameID;
        this.rounds = new ArrayList<GameRound>();
        this.isFinished = false;
        this.totalScore = 0;
        this.livesLeft = MAX_LIVES;
    }

    // REQUIRES: totalScore >= 0; livesLeft >= && <= MAX_LIVES
    // MODIFIES: this
    // EFFECTS: Creates a game session with fields defined by parameters passed to the method - 
    // gameID, isFinised state, total score and  
    // lives left. Sets rounds to the empty list of rounds. 
    public GameSession(int gameID, boolean isFinished, int totalScore, int livesLeft) {
        this.gameID = gameID;
        this.isFinished = isFinished;
        this.totalScore = totalScore;
        this.livesLeft = livesLeft;
        this.rounds = new ArrayList<GameRound>();
    }


    // REQUIRES: Game not finished
    // MODIFIES: this
    // EFFECTS: Adds a new round to the list of rounds and updates the game statistics.
    public void addPlayedRound(GameRound round) {
        rounds.add(round);
        updateAfterRound(round);
    }

    public void addSavedRound(GameRound round) {
        rounds.add(round);
    }
    
    public int getGameID() {
        return this.gameID;
    }

    public ArrayList<GameRound> getRounds() {
        return this.rounds;
    }

    public boolean isFinished() {
        return this.isFinished;
    }

    public int getTotalScore() {
        return this.totalScore;
    }

    public int getLivesLeft() {
        return this.livesLeft;
    }

    // MODIFIES: this
    // EFFECTS: Updates the total score. If round was lost, deducts one live, if the live number is zero
    // the game state is changed to finished.
    private void updateAfterRound(GameRound round) {
        boolean isVictory = round.isVictory();
        if (!isVictory) {
            livesLeft--;
            if (livesLeft <= 0) {
                this.isFinished = true;
            }
        }

        calculateStatistics();
    }

    // MODIFIES: this
    // EFFECTS: Update statistics of the total score and total time played.
    private void calculateStatistics() {
        // stub
    }

    // EFFECTS: 
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("gameID", gameID);
        json.put("rounds", roundsToJson());
        json.put("isFinished", isFinished);
        json.put("totalScore", totalScore);
        json.put("livesLeft", livesLeft);
        return json;
    }

    private JSONArray roundsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (GameRound round : rounds) {
            jsonArray.put(round.toJson());
        }

        return jsonArray;
    }
}
