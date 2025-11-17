package ui.screens;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JToggleButton;

import model.GameLibrary;
import model.GameScoreComparator;
import model.GameSession;
import ui.MainPanel;
import ui.components.ListBase;
import ui.components.Title;
import ui.components.buttons.ReturnButton;
import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;

@ExcludeFromJacocoGeneratedReport
// Represents a screen where are displayed all played games in a list format .
public class GameList extends JPanel {
    private String[] columnNames = {"GAME ID", "TIME PLAYED", "TOTAL SCORE", "LIVES LEFT", "CONTINUE"};
    private MainPanel mainPanel;    
    private ListBase listDisplay;

    private GameLibrary gameLibrary;
    private List<GameSession> filteredListOfGames;
    private boolean isFiltered;

    // EFFECTS: Constructs a game list screen.
    public GameList(MainPanel mainPanel) {
        super();
        this.gameLibrary = mainPanel.getGameLibrary();
        this.filteredListOfGames = this.gameLibrary.getGames();
        this.mainPanel = mainPanel;
        this.isFiltered = false;
        
        setup();
    }

    // MODIFIES: this
    // EFFECTS: Sets up a layout for the game screen and adds all components - title, list and return button.
    private void setup() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        Title title = new Title("PLAYED GAMES");
        this.add(title);  

        addSortButtons();

        this.listDisplay = new ListBase(columnNames, createDataRows());   
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

    // MODIFIES: this
    // EFFECTS: Adds the buttons used for sorting and filtering data to this screen.
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

    // EFFECTS: Creates and returns a JPanel that contains buttons used for filtering games by their total score.
    private JPanel createScoreSortBtn() {
        JPanel sortPanel = new JPanel();
        sortPanel.setLayout(new BoxLayout(sortPanel, BoxLayout.X_AXIS));

        ButtonGroup sortButtons = new ButtonGroup();

        addClearSortButton(sortPanel, sortButtons);
        addSortAscendingBtn(sortPanel, sortButtons);
        addSortDescendingBtn(sortPanel, sortButtons);        

        return sortPanel;
    }

    // EFFECTS: Adds radio button to the specified panel and button group. 
    // The button clears any applied sorting from the data.
    private void addClearSortButton(JPanel btnPanel, ButtonGroup sortButtons) {
        JRadioButton defaultBtn = new JRadioButton("NONE");

        defaultBtn.addActionListener(e -> {          
            List<GameSession> gamesToSort = isFiltered ? this.filteredListOfGames : this.gameLibrary.getGames();   
            this.filteredListOfGames = new ArrayList<GameSession>(gamesToSort);
            this.listDisplay.updateDataRows(createDataRows());
        });

        sortButtons.add(defaultBtn);
        defaultBtn.setSelected(true);

        btnPanel.add(defaultBtn); 
    }

    // EFFECTS: Adds radio button to the specified panel and button group. 
    // The button sorts data in ascending order based on game score.
    private void addSortAscendingBtn(JPanel btnPanel, ButtonGroup sortButtons) {
        JRadioButton ascBtn = new JRadioButton("↑");

        ascBtn.addActionListener(e -> {             
            this.filteredListOfGames = new ArrayList<GameSession>(this.filteredListOfGames);
            this.filteredListOfGames.sort(new GameScoreComparator().reversed()); 
            this.listDisplay.updateDataRows(createDataRows());  
        });

        sortButtons.add(ascBtn);
        btnPanel.add(ascBtn);
    }

    // EFFECTS: Adds radio button to the specified panel and button group. 
    // The button sorts data in descending order based on game score.
    private void addSortDescendingBtn(JPanel btnPanel, ButtonGroup sortButtons) {
        JRadioButton descBtn = new JRadioButton("↓");

        descBtn.addActionListener(e -> {             
            this.filteredListOfGames = new ArrayList<GameSession>(this.filteredListOfGames);
            this.filteredListOfGames.sort(new GameScoreComparator()); 
            this.listDisplay.updateDataRows(createDataRows());    
        });        
        
        sortButtons.add(descBtn);
        btnPanel.add(descBtn);
    }

    // MODIFIES: this
    // EFFECTS: Creates and returns a toggle button used to filter data by game status: active(playable) or finished.
    private JToggleButton createPlayableFilterBtn() {
        JToggleButton filterPlayableBtn = new JToggleButton("Show active games");

        filterPlayableBtn.addActionListener(e -> {
            if (filterPlayableBtn.isSelected()) {
                filterPlayableBtn.setText("Show all games");                
                this.filteredListOfGames = this.mainPanel.getGameLibrary().getPlayableGames();
                this.isFiltered = true;
                this.listDisplay.updateDataRows(createDataRows());    
            } else {
                filterPlayableBtn.setText("Show active games");
                this.filteredListOfGames = this.gameLibrary.getGames();
                this.isFiltered = false;

                this.listDisplay.updateDataRows(createDataRows());    
            }
        });

        return filterPlayableBtn;
    }

    // EFFECTS: Returns a list of panels, each containg data about one game.
    public List<JPanel> createDataRows() {
        List<JPanel> dataRows = new ArrayList<JPanel>();
        List<GameSession> games = this.filteredListOfGames;

        System.out.println(games.get(0).getTotalScore());

        for (GameSession game: games) {
            JPanel row = createRowPanel(game);
            dataRows.add(row);
        }      
        
        return dataRows;
    }

    // EFFECTS: Creates and returns a panel that represents a row that stores game data - id, time, score, lives and
    // button to continue playing.
    private JPanel createRowPanel(GameSession game) {
        JPanel row = new JPanel();
        row.setLayout(new BoxLayout(row, BoxLayout.X_AXIS));

        JPanel gameIDElement = ListBase.createfixedSizeElement(new JLabel(Integer.toString(game.getGameID())));
        row.add(gameIDElement);

        JPanel timeElement = ListBase.createfixedSizeElement(new JLabel(formatTime(game.getTotalTimePLayed())));
        row.add(timeElement);

        JPanel scoreElement = ListBase.createfixedSizeElement(new JLabel(Integer.toString(game.getTotalScore())));
        row.add(scoreElement);

        JPanel livesElement = ListBase.createfixedSizeElement(new JLabel(Integer.toString(game.getLivesLeft())));
        row.add(livesElement);

        JPanel btnElement = ListBase.createfixedSizeElement(createContinueGameBtn(game));
        row.add(btnElement);

        return row;
    }

    // EFFECTS: Creates and returns a button that is allows to continue to play the specified game.
    // If the game is finished the button is disabled.
    private JButton createContinueGameBtn(GameSession game) {
        JButton continueBtn = new JButton("PLAY");
        continueBtn.setFont(new Font("Arial", Font.CENTER_BASELINE, 7));
        continueBtn.setMaximumSize(new Dimension(70, 20));
        continueBtn.addActionListener(e -> mainPanel.displayScreen(new GameMenu(game, mainPanel)));
        continueBtn.setEnabled(!game.isFinished());

        return continueBtn;
    }

    // REQUIRES: timeInSeconds >= 0
    // EFFECTS: Return time as a formatted string that contains hours, minutes and seconds.
    private String formatTime(int timeInSeconds) {
        int hours = timeInSeconds / 3600;
        int min = (timeInSeconds % 3600) / 60;
        timeInSeconds %= 60;
        return String.format("%02d:%02d:%02d", hours, min, timeInSeconds);
    }
}

