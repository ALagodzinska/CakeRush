package ui.screens;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.GameRound;
import model.GameSession;
import ui.MainPanel;
import ui.components.ListBase;
import ui.components.Title;
import ui.components.buttons.ReturnButton;
import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;

@ExcludeFromJacocoGeneratedReport
// Represents a screen where are displayed all played rounds in a list format.
public class RoundList extends JPanel {
    private String[] columnNames = {"ROUND NUMBER", "ROUND TIME", "ROUND SCORE", "CORRECTLY GUESSED"};
    private MainPanel mainPanel;
    private ListBase listDisplay;

    // EFFECTS: Constructs a round list screen.
    public RoundList(GameSession game, MainPanel mainPanel) {
        super();
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.mainPanel = mainPanel;

        setup(game);
    }

    // MODIFIES: this
    // EFFECTS: Sets up a layout for the round screen and adds all components - title, list and return button.
    private void setup(GameSession game) {
        Title title = new Title("GAME " + game.getGameID() + " ROUNDS");
        this.add(title);
        this.add(Box.createRigidArea(new Dimension(0, 40)));

        this.listDisplay = new ListBase(columnNames, createDataRows(game.getRounds())); 
        this.add(listDisplay); 

        addReturnBtn();
    }

    // MODIFIES: this
    // RETURNS: Adds return button to this screen.
    private void addReturnBtn() {
        this.add(Box.createRigidArea(new Dimension(0, 30)));

        JButton goBack = new ReturnButton("GO BACK");
        goBack.addActionListener(e -> mainPanel.displayScreen(new MainMenu(mainPanel)));
        
        JPanel bottomCorner = new JPanel(new FlowLayout(FlowLayout.LEFT));
        bottomCorner.add(goBack);
        
        this.add(bottomCorner, BorderLayout.SOUTH);
    }

    // EFFECTS: Returns a list of panels, each containg data about one round.
    private List<JPanel> createDataRows(ArrayList<GameRound> rounds) {
        List<JPanel> rows = new ArrayList<>();

        for (int i = 0; i < rounds.size(); i++) {
            GameRound currentRound = rounds.get(i);

            rows.add(createRowPanel(currentRound, i));
        }

        return rows;
    }

    // EFFECTS: Creates and returns a panel that represents a row that stores round data - round number, time, score, lives and
    // game statistics.
    private JPanel createRowPanel(GameRound round, int index) {
        JPanel row = new JPanel();
        row.setLayout(new BoxLayout(row, BoxLayout.X_AXIS));

        JPanel gameIDElement = ListBase.createfixedSizeElement(new JLabel(Integer.toString(index + 1)));
        row.add(gameIDElement);

        JPanel timeElement = ListBase.createfixedSizeElement(new JLabel(formatTime(round.getRoundTime())));
        row.add(timeElement);

        JPanel scoreElement = ListBase.createfixedSizeElement(new JLabel(round.getScore() + ""));
        row.add(scoreElement);

        JPanel livesElement = ListBase.createfixedSizeElement(new JLabel(
                round.getUserCake().getComparisonAccuracy(round.getTargetCake())));
        row.add(livesElement);

        return row;
    }

    // REQUIRES: timeInSeconds >= 0
    // EFFECTS: Return time as a formatted string that contains minutes and seconds.
    public static String formatTime(int timeInSeconds) {
        return String.format("00:%02d", timeInSeconds);
    }
}
