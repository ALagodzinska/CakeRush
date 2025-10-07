package model;

public final class CakeElements {
    private CakeElements() {    // prevents from creating an instance of the class
    }

    public enum CakeColor { 
        WHITE, PINK, BLUE, GREEN, YELLOW; 

        public static int length() {
            return values().length;
        }

        public static String listAllOptions() {
            String summary = "";

            for (int i = 1; i <= length(); i++) {
                summary += i + ": " + values()[ i - 1 ] + " ";
            }

            return summary;
        }
    }
    
    public enum Glaze { 
        NONE, PINK, PURPLE, GREEN, BLUE; 
    
        public static int length() {
            return values().length;
        }

        public static String listAllOptions() {
            String summary = "";

            for (int i = 1; i <= length(); i++) {
                summary += i + ": " + values()[ i - 1 ] + " ";
            }

            return summary;
        }
    }

    public enum Topping { 
        NONE, SPRINKLES, FRUIT, CANDIES, CREAM; 
        
        public static int length() {
            return values().length;
        }

        public static String listAllOptions() {
            String summary = "";

            for (int i = 1; i <= length(); i++) {
                summary += i + ": " + values()[ i - 1 ] + " ";
            }

            return summary;
        }
    }

    public enum Decoration { 
        NONE, CANDLE, FLOWER, BALLOON, CARD; 
        
        public static int length() {
            return values().length;
        }

        public static String listAllOptions() {
            String summary = "";

            for (int i = 1; i <= length(); i++) {
                summary += i + ": " + values()[ i - 1 ] + " ";
            }

            return summary;
        }
    }
}
