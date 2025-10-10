package ui;

// Represents ptions for various menus. Contains enums for game and main menu options.
public class MenuOptions {

    // EFFECTS: Prevents from creating an instance of this class.
    private MenuOptions() {
    }

    // Represents menu options for the game menu.
    public enum GameMenuOptions {
        PLAY("Play the game"),
        SHOW_ROUNDS("Show played rounds"),
        GET_SCORE("Total Score"),
        EXIT("Exit Current Game");

        private String text;        // text representation of the enum member

        // EFFECTS: Creates an enum member with specified text.
        GameMenuOptions(String text) {
            this.text = text;
        }

        public String getText() {
            return text;
        }

        // EFFECTS: Generates and returns a string with all menu options,
        // formatted as a numbered list.
        public static String listAllOptions() {
            String summary = "";

            for (int i = 1; i <= values().length; i++) {
                summary += "\t" + i + ": " + values()[ i - 1 ].getText() + "\n";
            }

            return summary;
        }
    }

    // Represents menu options for the main menu.
    public enum MainMenuOptions {
        NEW_GAME("Create a new game"),
        SHOW_GAMES("Show played games"),
        EXIT("Exit");

        private String text;        // text representation of the enum member

        // EFFECTS: Creates an enum member with specified text.
        MainMenuOptions(String text) {
            this.text = text;
        }

        public String getText() {
            return text;
        }

        // EFFECTS: Generates and returns a string with all menu options,
        // formatted as a numbered list.
        public static String listAllOptions() {
            String summary = "";

            for (int i = 1; i <= values().length; i++) {
                summary += "\t" + i + ": " + values()[ i - 1 ].getText() + "\n";
            }

            return summary;
        }
    }
}
