package model;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;


public class TestCakeElements {
    @Test
    void testCakeColorListAllOptions() {
        assertEquals("1: WHITE 2: PINK 3: BLUE 4: GREEN 5: YELLOW ", CakeElements.CakeColor.listAllOptions());
    }

    @Test
    void testGlazeListAllOptions() {
        assertEquals("1: NONE 2: PINK 3: PURPLE 4: GREEN 5: BLUE ", CakeElements.Glaze.listAllOptions());
    }

    @Test
    void testToppingListAllOptions() {
        assertEquals("1: NONE 2: SPRINKLES 3: FRUIT 4: CANDIES 5: CREAM ", CakeElements.Topping.listAllOptions());
    }

    @Test
    void testDecorationListAllOptions() {
        assertEquals("1: NONE 2: CANDLE 3: FLOWER 4: BALLOON 5: CARD ", CakeElements.Decoration.listAllOptions());
    }
}
