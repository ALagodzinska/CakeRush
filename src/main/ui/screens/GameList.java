package ui.screens;

import java.awt.Dimension;
import java.awt.Font;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JToggleButton;

import model.GameLibrary;
import model.GameScoreComparator;
import model.GameSession;
import ui.MainPanel;
import ui.components.Title;
import ui.components.buttons.ReturnButton;
import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;

@ExcludeFromJacocoGeneratedReport
public class GameList extends JPanel {
    private String[] columnNames = {"GAME ID", "TIME PLAYED", "TOTAL SCORE", "LIVES LEFT", "CONTINUE"};
    MainPanel mainPanel;    
    private JPanel listDisplay;
    private JScrollPane scrollList;

    private GameLibrary gameLibrary;
    private List<GameSession> filteredListOfGames;
    private boolean isFiltered;

    public GameList(MainPanel mainPanel) {
        super();
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        Title title = new Title("PLAYED GAMES");
        this.add(title);

        this.gameLibrary = mainPanel.getGameLibrary();
        // this.originalListOfGames = mainPanel.getGameLibrary().getGames();
        this.filteredListOfGames = this.gameLibrary.getGames();
        this.mainPanel = mainPanel;

        listDisplay = new JPanel();
        listDisplay.setLayout(new BoxLayout(listDisplay, BoxLayout.Y_AXIS));

        this.isFiltered = false;

        // TESTING
        addSortButtons();

        addColumnNames();
        // addGames();

        scrollList = new JScrollPane(createTableData());
        listDisplay.add(scrollList);

        this.add(listDisplay);

        JButton goBack = new ReturnButton("GO BACK");
        goBack.addActionListener(e -> mainPanel.displayScreen(new MainMenu(mainPanel)));
        this.add(Box.createRigidArea(new Dimension(0, 30)));
        this.add(goBack);
    }

    

    private void addSortButtons() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));

        JToggleButton togglePlayableSortBtn = createPlayableFilterBtn();

        JPanel sortByScoreBtns = createScoreSortBtn();
        
        buttonPanel.add(togglePlayableSortBtn);
        buttonPanel.add(Box.createHorizontalStrut(400));
        buttonPanel.add(sortByScoreBtns);

        this.add(buttonPanel);
    }

    private JPanel createScoreSortBtn() {
        JPanel sortPanel = new JPanel();
        sortPanel.setLayout(new BoxLayout(sortPanel, BoxLayout.X_AXIS));

        ButtonGroup sortButtons = new ButtonGroup();
        JRadioButton defaultBtn = new JRadioButton("NONE");
        JRadioButton ascBtn = new JRadioButton("↑");
        JRadioButton descBtn = new JRadioButton("↓");

        defaultBtn.addActionListener(e -> {          
            List<GameSession> gamesToSort = isFiltered ? this.filteredListOfGames : this.gameLibrary.getGames();   
            this.filteredListOfGames = gamesToSort;
            redrawTable();            
        });

        ascBtn.addActionListener(e -> {             
            this.filteredListOfGames.sort(new GameScoreComparator().reversed()); 
            redrawTable();    
        });

        descBtn.addActionListener(e -> {             
            this.filteredListOfGames.sort(new GameScoreComparator()); 
            redrawTable();    
        });

        sortButtons.add(defaultBtn);
        defaultBtn.setSelected(true);
        sortPanel.add(defaultBtn);
        sortButtons.add(ascBtn);
        sortPanel.add(ascBtn);
        sortButtons.add(descBtn);
        sortPanel.add(descBtn);

        return sortPanel;
    }

    private JToggleButton createPlayableFilterBtn() {
        JToggleButton filterPlayableBtn = new JToggleButton("Show active games");

        filterPlayableBtn.addActionListener(e -> {
            if (filterPlayableBtn.isSelected()) {
                filterPlayableBtn.setText("Show all games");                
                this.filteredListOfGames = this.mainPanel.getGameLibrary().getPlayableGames();
                this.isFiltered = true;

                redrawTable();
            } else {
                filterPlayableBtn.setText("Show active games");
                this.filteredListOfGames = this.gameLibrary.getGames();
                this.isFiltered = false;

                redrawTable();
            }
        });

        return filterPlayableBtn;
    }


    private void redrawTable() {
        this.listDisplay.remove(scrollList);
        scrollList = new JScrollPane(createTableData());
        this.listDisplay.add(scrollList);
        
        this.revalidate();
        this.repaint(); 
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

    public JPanel createTableData() {
        JPanel dataRows = new JPanel();
        dataRows.setLayout(new BoxLayout(dataRows, BoxLayout.Y_AXIS));
        List<GameSession> games = this.filteredListOfGames;

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

            dataRows.add(row);
        }      
        
        return dataRows;
    }

    private String formatTime(int timeInSeconds) {
        int hours = timeInSeconds / 3600;
        int min = (timeInSeconds % 3600) / 60;
        timeInSeconds %= 60;
        return String.format("%02d:%02d:%02d", hours, min, timeInSeconds);
    }
}

