package model;

import java.util.ArrayList;

// Represents a single game session. Contains a list of completed rounds and ends the game session when a round is lost.
public class GameSession {
    private static int nextID = 1;

    private int gameID;
    private ArrayList<GameRound> rounds;
    private boolean isFinished;
    private int totalScore;
    private int totalTimePlayed;    

    // EFFECTS: Creates a game session with an empty list of rounds, sets the game finished state to false, 
    // assigns total score and total time played to zero. 
    public GameSession() {
        this.gameID = nextID;
        nextID++;

        this.rounds = new ArrayList<GameRound>();
        this.isFinished = false;
        this.totalScore = 0;
        this.totalTimePlayed = 0;
    }

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

    public int getTotalTimePlayed() {
        return this.totalTimePlayed;
    }

    // MODIFIES: this
    // EFFECTS: Updates the total time played, total score. If round was lost, updates the game state to finished.
    private void updateAfterRound(GameRound round) {
        this.totalTimePlayed += round.getCompletionTime();
        this.totalScore += round.getScore();
        this.isFinished = !round.isVictory();
    }
}
