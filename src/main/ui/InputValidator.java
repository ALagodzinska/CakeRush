package ui;

import java.util.Scanner;

public class InputValidator {
    // EFFECTS: Prompts user with the specified message  for a valid numeric input within the given range
    // [minValue, maxValue]. Continues to prompt the user until the valid input is entered. 
    // Returns the recieved valid input.
    public static int getValidUserChoice(Scanner scanner, String promptMessage, int minValue, int maxValue) {
        
        while (true) {
            System.out.println(promptMessage);
            try {
                int optionValue = scanner.nextInt();
                System.out.println();
                if (optionValue >= minValue && optionValue <= maxValue) {
                    return optionValue;
                } else {
                    System.out.println(Constants.INVALID_INPUT_MESSAGE);                
                }

            }   catch (Exception exception) {
                System.out.println(Constants.INVALID_INPUT_MESSAGE);
                scanner.next();
            }            
        }   
    }
}
