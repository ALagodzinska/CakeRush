package model;

import java.util.Comparator;

// Adapted from: 
    //   StackOverflow: How to sort Objects in an Arraylist with an Object parameter
    //   Author: Wundwin Born
    //   URL: https://stackoverflow.com/questions/25311557/how-to-sort-objects-in-an-arraylist-with-an-object-parameter

// Allows to compare GameSession objects by total score value. Will be used for sorting GameSessions.
public class GameScoreComparator implements Comparator<GameSession> {

    // EFFECTS: Compares first game to a second game by score. If firstGame has higher score than secondGame 
    // returns one, else if firstGame score equals to secondGame score returns zero, otherwise returns -1.
    @Override
    public int compare(GameSession firstGame, GameSession secondGame) {
        if (firstGame.getTotalScore() == secondGame.getTotalScore()) {
            return 0;
        } else if (firstGame.getTotalScore() > secondGame.getTotalScore()) {
            return 1;
        } else {
            return -1;
        }
    }
    
}
