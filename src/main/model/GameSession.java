package model;

import java.util.ArrayList;

// Represents a single game session. Contains a list of completed rounds and ends the game session when a round is lost.
public class GameSession {
    public static final int MAX_LIVES = 3;

    private static int nextID = 1;

    private int gameID;                     // unique identifier for the game
    private ArrayList<GameRound> rounds;    // all rounds played in the game
    private boolean isFinished;             // true if the game was lost
    private int totalScore;                 // combined score from all played rounds
    private int livesLeft;                  // the number of lives remaining, when 0 game ends

    // EFFECTS: Creates a game session with an empty list of rounds, sets the game finished state to false, 
    // assigns total score to zero and livesLeft to MAX_LIVES. 
    public GameSession() {
        this.gameID = nextID;
        nextID++;

        this.rounds = new ArrayList<GameRound>();
        this.isFinished = false;
        this.totalScore = 0;
    }

    // REQUIRES: Game not finished
    // MODIFIES: this
    // EFFECTS: Adds a new round to the list of rounds and updates the game statistics.
    public void addRound(GameRound round) {
        if (!this.isFinished) {
            rounds.add(round);
            updateAfterRound(round);
        }       
    }   
    
    public int getGameID() {
        return this.gameID;
    }

    public int getNextID() {
        return nextID;
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
        // stub
        return 0;
    }

    // MODIFIES: this
    // EFFECTS: Updates the total score. If round was lost, deducts one live, if the live number is zero
    // the game state is changed to finished.
    private void updateAfterRound(GameRound round) {
        // stub
    }

    // EFFECTS: If the round is won adds ROUND_SCORE to the total score, otherwise subtracts ROUND_SCORE
    // from the total score, if the total score is less than ROUND_SCORE sets the total score zero.
    private void calculateScore(boolean isRoundWon) {
        // stub
    }
}
