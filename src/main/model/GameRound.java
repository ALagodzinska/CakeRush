package model;

import java.util.Random;

// Represents a single round of the game. A round stores the target cake, tracks the user's attempt to recreate a cake
// and the round outcome.
public class GameRound {
    public static final int MAX_ROUND_TIME = 30;

    private int roundNumber;
    private Cake targetCake;
    private Cake userCake;
    private int completionTime;
    private boolean isVictory;

    // REQUIRES: Round number must be one greater than the previous round.
    // EFFECTS: Creates a game round with assigned round number, random target cake, default user cake, 
    // zero time, and victory status set to false.
    public GameRound(int roundNumber, Random random) {
        this.roundNumber = roundNumber;
        this.targetCake = new Cake(random);
        this.userCake = new Cake(); 
        this.completionTime = 0;
        this.isVictory = false;
    }

    public int getRoundNumber() {
        return this.roundNumber;
    }

    public boolean getIsVictory() {
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
    
    // REQUIRES: The time value between 0 and the maximum allowed round time.
    // MODIFIES: this
    // EFFECTS: Sets round victory state to true if the target cake and user cake are same
    // otherwise sets to false. Records the time spent to finish the round.
    public void finishRound(int timePlayed) {
        if (timePlayed >= 0 && timePlayed <= MAX_ROUND_TIME) {
            this.isVictory = targetCake.compare(userCake);
            this.completionTime = timePlayed;
        }
    }
}
