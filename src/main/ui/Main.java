package ui;

import ui.screens.MainScreenHandler;

import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;

@ExcludeFromJacocoGeneratedReport
// Starts the game.
public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println("Welcome to the Cake Rush!");
        MainScreenHandler mh = new MainScreenHandler();
        mh.runMainMenu();
    }
}
