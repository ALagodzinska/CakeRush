package model;

// Represents various cake elements. Contains enums for each type of cake element, such as
// cake color, glaze, toppings and decorations.
public final class CakeElements {

    // EFFECTS: Prevents from creating an instance of this class.
    private CakeElements() {
    }

    // Represents the possible colors of a cake.
    public enum CakeColor { 
        WHITE, PINK, BLUE, GREEN, YELLOW; 

        // EFFECTS: Returns the number of possible color options.
        public static int length() {
            return values().length;
        }

        // EFFECTS: Generates and returns a string with all cake color options,
        // formatted as a numbered list.
        public static String listAllOptions() {
            String summary = "";

            for (int i = 1; i <= length(); i++) {
                summary += i + ": " + values()[ i - 1 ] + " ";
            }

            return summary;
        }
    }
    
    // Represents possible glaze options for a cake.
    public enum Glaze { 
        NONE, VANILLA, CHOCOLATE, STRAWBERRY, CARAMEL; 
    
        // EFFECTS: Returns the number of possible glaze options.
        public static int length() {
            return values().length;
        }

        // EFFECTS: Generates and returns a string with all glaze options,
        // formatted as a numbered list.
        public static String listAllOptions() {
            String summary = "";

            for (int i = 1; i <= length(); i++) {
                summary += i + ": " + values()[ i - 1 ] + " ";
            }

            return summary;
        }
    }

    // Represents possible topping options for a cake.
    public enum Topping { 
        NONE, SPRINKLES, FRUIT, CANDIES, CREAM; 
        
        // EFFECTS: Returns the number of possible topping options.
        public static int length() {
            return values().length;
        }

        // EFFECTS: Generates and returns a string with all topping options,
        // formatted as a numbered list.
        public static String listAllOptions() {
            String summary = "";

            for (int i = 1; i <= length(); i++) {
                summary += i + ": " + values()[ i - 1 ] + " ";
            }

            return summary;
        }
    }

    // Represents possible decoration options for a cake.
    public enum Decoration { 
        NONE, CANDLE, FLOWER, BALLOON, CARD; 
        
        // EFFECTS: Returns the number of possible decoration options.
        public static int length() {
            return values().length;
        }

        // EFFECTS: Generates and returns a string with all decoration options,
        // formatted as a numbered list.
        public static String listAllOptions() {
            String summary = "";

            for (int i = 1; i <= length(); i++) {
                summary += i + ": " + values()[ i - 1 ] + " ";
            }

            return summary;
        }
    }
}
