package persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import model.Cake;
import model.CakeElements.CakeColor;
import model.CakeElements.Decoration;
import model.CakeElements.Glaze;
import model.CakeElements.Topping;
import model.GameRound;
import model.GameSession;

// Adapted from: 
//   Project Title: JsonSerializationDemo
//   Author: CPSC210
//   Type: source code
//   URL: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

public class TestJson {
    protected void checkGame(int id, boolean isFinished, int totalScore, int livesLeft, int roundsSize, 
            GameSession game) {
        assertEquals(id, game.getGameID());
        assertEquals(isFinished, game.isFinished());
        assertEquals(totalScore, game.getTotalScore());
        assertEquals(livesLeft, game.getLivesLeft());
        assertEquals(roundsSize, game.getRounds().size());
    }

    protected void checkRound(Cake targetCake, Cake userCake, boolean isVictory, GameRound round) {
        checkCake(targetCake.getNumberOfTiers(), targetCake.getCakeColor(), 
                targetCake.getGlaze(), targetCake.getTopping(), targetCake.getDecoration(), round.getTargetCake());
        checkCake(userCake.getNumberOfTiers(), userCake.getCakeColor(), 
                userCake.getGlaze(), userCake.getTopping(), userCake.getDecoration(), round.getUserCake());
        assertEquals(isVictory, round.isVictory());
    }

    private void checkCake(int numberOfTiers, CakeColor color, Glaze glaze, Topping topping, Decoration decoration,
            Cake cake) {
        assertEquals(numberOfTiers, cake.getNumberOfTiers());
        assertEquals(color, cake.getCakeColor());
        assertEquals(topping, cake.getTopping());
        assertEquals(glaze, cake.getGlaze());
        assertEquals(decoration, cake.getDecoration());
    }
}
