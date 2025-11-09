package ui.screens;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import model.GameRound;
import model.GameSession;
import ui.MainPanel;
import ui.components.ReturnButton;
import ui.components.Title;

public class RoundList extends JPanel {
    private String[] columnNames = {"ROUND NUMBER", "ROUND TIME", "ROUND SCORE", "CORRCTLY GUESSED"};
    private Object[][] roundsData;


    public RoundList(GameSession game, MainPanel mainPanel) {
        super();
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        Title title = new Title("Round list for Game " + game.getGameID());

        this.roundsData = converRoundsDataTo2DArray(game.getRounds());

        this.add(title);
        this.add(Box.createRigidArea(new Dimension(0, 40)));
        addTable();

        JButton goBack = new ReturnButton("GO BACK");
        goBack.addActionListener(e -> mainPanel.displayScreen(new GameMenu(game, mainPanel)));
        this.add(Box.createRigidArea(new Dimension(0, 30)));
        this.add(goBack);
    }

    private Object[][] converRoundsDataTo2DArray(ArrayList<GameRound> rounds) {
        int numOfColumns = 4;
        int numOfRows = rounds.size();
        Object[][] roundsForTable = new Object[numOfRows][numOfColumns];

        for (int i = 0; i < rounds.size(); i++) {
            GameRound currentRound = rounds.get(i);
            roundsForTable[i] = new Object[]
            {Integer.toString(i + 1),
                formatTime(currentRound.getRoundTime()),
                currentRound.getScore() + "",
                currentRound.getUserCake().getComparisonAccuracy(currentRound.getTargetCake())};
        }

        return roundsForTable;
    }

    public void addTable() {
        JTable table = new JTable(roundsData, columnNames);

        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 16));
        table.setPreferredScrollableViewportSize(new Dimension(800, 600));
        table.setRowHeight(30);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(800, 600));
        scrollPane.setMaximumSize(new Dimension(800, 600));

        this.add(scrollPane, BorderLayout.CENTER);
    }

    private String formatTime(int timeInSeconds) {
        return String.format("00:%02d", timeInSeconds);
    }
}
