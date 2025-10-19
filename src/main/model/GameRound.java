package model;

import java.util.Random;

// Represents a single round of the game. A round stores the target cake, tracks the user's attempt to recreate a cake
// and the round outcome.
public class GameRound {
    public static final int ROUND_SCORE = 10;

    private Cake targetCake;        // the cake that user has to replicate
    private Cake userCake;          // the cake user modifies to match target cake
    private boolean isVictory;      // tracks whether the round is succesfully completed (user cake matches the 
                                    // target cake at the end of the round)

    // EFFECTS: Creates a game round with random target cake, default user cake, 
    // and victory status set to false.
    public GameRound(Random random) {
        this.targetCake = new Cake(random);
        this.userCake = new Cake();
        this.isVictory = false;
    }

    // REQUIRES: Target cake and user cake not null.
    // EFFECTS: Creates a game round with defined targetCake, userCake and isVictory state.
    public GameRound(Cake targetCake, Cake userCake, boolean isVictory) {
        this.targetCake = targetCake;
        this.userCake = userCake;
        this.isVictory = isVictory;
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

    // MODIFIES: this
    // EFFECTS: Sets round victory state to true if the target cake and user cake are same otherwise sets to false.
    public void finishRound() {
        this.isVictory = targetCake.compare(userCake);
    }
}
