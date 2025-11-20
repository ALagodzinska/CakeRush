package model;

import static org.junit.Assert.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;

@ExcludeFromJacocoGeneratedReport
public class TestGameScoreComparator {
    private GameSession game1;
    private GameSession game2;
    private GameSession game3;
    private GameScoreComparator comparator;

    @BeforeEach
    void setup() {
        game1 = new GameSession(1, false, 200, 0, 0);
        game2 = new GameSession(1, false, 200, 0, 0);
        game3 = new GameSession(1, false, 600, 0, 0);

        comparator = new GameScoreComparator();
    }

    @Test
    void testComparatorEqual() {
        assertEquals(0, comparator.compare(game2, game1));
    }

    @Test
    void testComparatorGreater() {
        assertEquals(1, comparator.compare(game3, game1));
    }

    @Test
    void testComparatorLess() {
        assertEquals(-1, comparator.compare(game2, game3));
    }
}
