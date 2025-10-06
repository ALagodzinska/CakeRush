package model;

import java.util.Random;

// Represents a single round of the game. A round stores the target cake, tracks the user's attempt to recreate a cake
// and the round outcome.
public class GameRound {
    public static final int MAX_ROUND_TIME = 30;    // the maximum time for one round,
                                                    // when this value is reached the round completes.
    public static final int MIN_SCORE = 5;
    public static final int MEDIUM_SCORE = 8;
    public static final int MAX_SCORE = 10; 

    public static final int MAX_SCORE_TIME_LIMIT = 10;
    public static final int MEDIUM_SCORE_TIME_LIMIT = 20;


    private Cake targetCake;        // the cake that user has to replicate
    private Cake userCake;          // the cake user modifies to match target cake
    private int completionTime;     // time it took to complete the round (in seconds)
    private int score;              // score calculated based on completion time after the round ends
    private boolean isVictory;      // tracks whether the round is succesfully completed (user cake matches the 
                                    // target cake at the end of the round)

    // REQUIRES: Round number must be one greater than the previous round.
    // EFFECTS: Creates a game round with assigned round number, random target cake, default user cake, 
    // zero time, zero score and victory status set to false.
    public GameRound(Random random) {
        this.targetCake = new Cake(random);
        this.userCake = new Cake(); 
        this.completionTime = 0;
        this.score = 0;
        this.isVictory = false;
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

    public int getCompletionTime() {
        return this.completionTime;
    }

    public int getScore() {
        return this.score;
    }    

    // REQUIRES: The time value between 0 and the maximum allowed round time.
    // MODIFIES: this
    // EFFECTS: Sets round victory state to true if the target cake and user cake are same otherwise sets to false. 
    // Records the round statistics.
    public void finishRound(int timePlayed) {
        if (timePlayed >= 0 && timePlayed <= MAX_ROUND_TIME) {
            this.isVictory = targetCake.compare(userCake);
            updateStatistics(timePlayed);
        }
    }

     // MODIFIES: this
    // EFFECTS: Records the round time and calculates the round score.
    private void updateStatistics(int timePlayed) {        
        this.completionTime = timePlayed;
        this.score = calculateScore();
    }

    // EFFECTS: Calculates score based on whether the round is won and how much time it took for 
    // the user to replicate a cake.
    private int calculateScore() {
        if (!this.isVictory) {
            return 0;
        } else if (this.completionTime <= MAX_SCORE_TIME_LIMIT) {
            return MAX_SCORE;
        } else if (this.completionTime <= MEDIUM_SCORE_TIME_LIMIT) {
            return MEDIUM_SCORE;
        } else {
            return MIN_SCORE;
        }
    }
}
