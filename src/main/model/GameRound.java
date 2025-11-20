package model;

import java.util.Random;
import org.json.JSONObject;

import persistence.Writable;

// Represents a single round of the game. A round stores the target cake, tracks the user's attempt to recreate a cake
// and the round outcome.
public class GameRound implements Writable {
    public static final int MAX_TIME_POINTS = 30;
    public static final int ROUND_SCORE = 10;
    public static final int MAX_TIME = 30;
    public static final int PREVIEW_TIME = 7;

    private Cake targetCake;        // the cake that user has to replicate
    private Cake userCake;          // the cake user modifies to match target cake
    private boolean isVictory;      // tracks whether the round is succesfully completed (user cake matches the 
                                    // target cake at the end of the round)
    private int roundTime;
    private int score;

    // EFFECTS: Creates a game round with random target cake, default user cake, 
    // victory status set to false, round time and score set to zero.
    public GameRound(Random random) {
        this.targetCake = new Cake(random);
        this.userCake = new Cake();
        this.isVictory = false;
        this.roundTime = 0;
        this.score = 0;
    }

    // REQUIRES: Target cake and user cake not null.
    // EFFECTS: Creates a game round with defined targetCake, userCake, isVictory state, roundTime and score.
    public GameRound(Cake targetCake, Cake userCake, boolean isVictory, int roundTime, int score) {
        this.targetCake = targetCake;
        this.userCake = userCake;
        this.isVictory = isVictory;
        this.roundTime = roundTime;
        this.score = score;
    }

    public boolean isVictory() {
        return this.isVictory;
    }    

    public Cake getUserCake() {
        return this.userCake;
    }

    public Cake getTargetCake() {
        return this.targetCake;
    }

    public int getRoundTime() {
        return this.roundTime;
    }

    public int getScore() {
        return this.score;
    }

    // MODIFIES: this
    // EFFECTS: If the victory state for the round is true adds the difference between MAX_TIME_POINTS 
    // and round time to the ROUND_SCORE and saves value to score. Otherwise sets the score value to zero.
    private void calculateScore() {
        if (this.isVictory) {
            score = ROUND_SCORE + (MAX_TIME_POINTS - this.roundTime);
        } else {
            score = 0;
        }
    }

    // REQUIRES: roundTime >= 0
    // MODIFIES: this
    // EFFECTS: Sets round victory state to true if the target cake and user cake are same otherwise sets to false.
    // Calculates score for the round
    public void finishRound(int roundTime) {
        this.roundTime = roundTime;
        this.isVictory = targetCake.compare(userCake);        
        calculateScore();
    }

    // EFFECTS: Converts and returns round as a JSONobject.
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("targetCake", targetCake.toJson());
        json.put("userCake", userCake.toJson());
        json.put("isVictory", isVictory);
        json.put("score", score);
        json.put("roundTime", roundTime);
        return json;
    }
}
