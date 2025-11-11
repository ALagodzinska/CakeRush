package ui.screens;

import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import model.GameSession;
import ui.MainPanel;
import ui.components.ReturnButton;
import ui.components.Title;

import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;

@ExcludeFromJacocoGeneratedReport
public class GameList extends JPanel {
    private String[] columnNames = {"GAME ID", "TIME PLAYED", "TOTAL SCORE", "LIVES LEFT", "CONTINUE"};
    MainPanel mainPanel;
    private ArrayList<GameSession> games;
    private JPanel listDisplay;

    public GameList(ArrayList<GameSession> games, MainPanel mainPanel) {
        super();
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        Title title = new Title("PLAYED GAMES");
        this.add(title);

        this.games = games;
        this.mainPanel = mainPanel;

        listDisplay = new JPanel();
        listDisplay.setLayout(new BoxLayout(listDisplay, BoxLayout.Y_AXIS));

        addColumnNames();
        addGames();

        JScrollPane scroll = new JScrollPane(listDisplay);
        add(scroll);

        JButton goBack = new ReturnButton("GO BACK");
        goBack.addActionListener(e -> mainPanel.displayScreen(new MainMenu(mainPanel)));
        this.add(Box.createRigidArea(new Dimension(0, 30)));
        this.add(goBack);
    }

    public void addColumnNames() {
        JPanel row = new JPanel();
        row.setLayout(new BoxLayout(row, BoxLayout.X_AXIS));

        for (String name: columnNames) {
            JPanel element = fixedSizeElement();
            JLabel label = new JLabel(name);
            label.setFont(new Font("Arial", Font.BOLD, 14));
            element.add(label);
            row.add(element);
        }

        row.setMaximumSize(new Dimension(row.getPreferredSize().width, 40));
        listDisplay.add(row);
    }

    private JPanel fixedSizeElement() {
        JPanel element = new JPanel();
        element.setPreferredSize(new Dimension(150, 30));
        element.setMinimumSize(new Dimension(150, 30));
        element.setMaximumSize(new Dimension(150, 30));

        return element;
    }

    public void addGames() {
        for (GameSession game: games) {
            JPanel row = new JPanel();
            row.setLayout(new BoxLayout(row, BoxLayout.X_AXIS));

            JPanel gameIDElement = fixedSizeElement();
            gameIDElement.add(new JLabel(Integer.toString(game.getGameID())));
            row.add(gameIDElement);

            JPanel timeElement = fixedSizeElement();
            timeElement.add(new JLabel(formatTime(game.getTotalTimePLayed())));
            row.add(timeElement);

            JPanel scoreElement = fixedSizeElement();
            scoreElement.add(new JLabel(Integer.toString(game.getTotalScore())));
            row.add(scoreElement);

            JPanel livesElement = fixedSizeElement();
            livesElement.add(new JLabel(Integer.toString(game.getLivesLeft())));
            row.add(livesElement);

            JButton continueBtn = new JButton("PLAY");
            continueBtn.setFont(new Font("Arial", Font.CENTER_BASELINE, 7));
            continueBtn.setMaximumSize(new Dimension(70, 20));
            continueBtn.addActionListener(e -> mainPanel.displayScreen(new GameMenu(game, mainPanel)));
            continueBtn.setEnabled(!game.isFinished());

            JPanel btnElement = fixedSizeElement();
            btnElement.add(continueBtn);
            row.add(btnElement);

            listDisplay.add(row);
        }        
    }

    private String formatTime(int timeInSeconds) {
        int hours = timeInSeconds / 3600;
        int min = (timeInSeconds % 3600) / 60;
        timeInSeconds %= 60;
        return String.format("%02d:%02d:%02d", hours, min, timeInSeconds);
    }
}

