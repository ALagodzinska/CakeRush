package model;

// Represents a single round of the game. A round stores the target cake, tracks the user's attempt to recreate a cake
// and the round outcome.
public class GameRound {
    public static final int MAX_ROUND_TIME = 30;

    // EFFECTS: Creates a game round with random target cake, default user cake, 
    // zero time, and victory status set to false.
    public GameRound() {
        // stub
    }

    public int getRoundNumber() {
        return 0;
    }

    public boolean getIsVictory() {
        // stub
        return false;
    }    

    public Cake getUserCake() {
        // stub
        return null;
    }

    public Cake getTargetCake() {
        // stub
        return null;
    }

    public int getCompletionTime() {
        // stub
        return 0;
    }
    
    // REQUIRES: The time value between 0 and the maximum allowed round time.
    // MODIFIES: this
    // EFFECTS: Sets round victory state to true if the target cake and user cake are same
    // otherwise sets to false. Records the time spent to finish the round.
    public void finishRound(int timePlayed) {
        // stub
    }
}
