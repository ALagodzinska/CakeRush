package ui;

import java.util.Scanner;

import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;

@ExcludeFromJacocoGeneratedReport
// Contains methods for processing and validating user input.
public class InputValidator {
    // REQUIRES: minValue and maxValue > 0
    // EFFECTS: Prompts user with the specified message  for a valid numeric input within the given range
    // [minValue, maxValue]. Continues to prompt the user until the valid input is entered. 
    // Returns the recieved valid input.
    public static int getValidUserChoice(Scanner scanner, String promptMessage, int minValue, int maxValue) {
        return getValidUserChoice(scanner, promptMessage, minValue, maxValue, false); 
    }

    // REQUIRES: minValue and maxValue > 0
    // EFFECTS: Prompts user with the specified message for a valid numeric input within the given range
    // [minValue, maxValue] or "exit" String. Continues to prompt the user until the valid input is entered. 
    // Returns the recieved valid input or -1 when canExit is equal to true and the user entered "exit".
    public static int getValidUserChoice(Scanner scanner, String promptMessage, int minValue, int maxValue, 
            boolean canExit) {
        
        while (true) {
            System.out.println(promptMessage);
            String optionValue = scanner.nextLine();
            try {
                int optionNumber = Integer.parseInt(optionValue);
                if (optionNumber >= minValue && optionNumber <= maxValue) {
                    return optionNumber;
                } else {
                    System.out.println(Constants.INVALID_INPUT_MESSAGE);                
                }                
            } catch (NumberFormatException ex) {
                if (canExit) {
                    if (optionValue.equals("exit")) {
                        return -1;
                    }
                } else {
                    System.out.println(Constants.INVALID_INPUT_MESSAGE);
                }                
            }         
        }   
    }
}
