package ui.components;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

// Represents the list used to display a table that contains column names and JPanels as rows.
public class ListBase extends JPanel {
    List<JPanel> rows;

    JScrollPane scrollList;

    // EFFECTS: Creates a scrollable list with column headers and uses JPanels for each individual data row.
    public ListBase(String[] columnNames, List<JPanel> rows) {
        super();
        this.rows = rows;
        
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        scrollList = new JScrollPane(createTableData());
        this.add(createHeaders(columnNames));
        this.add(scrollList);
    }    

    // EFFECTS: Creates and returns a JPanel that stores column names in a single styled row.
    private JPanel createHeaders(String[] columnNames) {
        JPanel headerRow = new JPanel();
        headerRow.setLayout(new BoxLayout(headerRow, BoxLayout.X_AXIS));

        for (String name: columnNames) {
            JLabel label = new JLabel(name);
            label.setFont(new Font("Arial", Font.BOLD, 14));
            headerRow.add(createfixedSizeElement(label));
        }

        headerRow.setMaximumSize(new Dimension(headerRow.getPreferredSize().width, 40));
        
        return headerRow;
    }

    // EFFECTS: Creates and returns a Jpanel element that wraps the given component inside a fixed panel container.
    public static JPanel createfixedSizeElement(Component component) {
        JPanel element = new JPanel();
        element.setPreferredSize(new Dimension(150, 30));
        element.setMinimumSize(new Dimension(150, 30));
        element.setMaximumSize(new Dimension(150, 30));
        element.add(component);

        return element;
    }

    // EFFECTS: Creates and returns a panel that wraps all provided rows into a single container panel.
    private JPanel createTableData() {
        JPanel dataRows = new JPanel();
        dataRows.setLayout(new BoxLayout(dataRows, BoxLayout.Y_AXIS));

        for (JPanel row: rows) {
            dataRows.add(row);
        }      
        
        return dataRows;
    }

    // MODIFIES: this
    // EFFECTS: Updates the rows and redraws the table data.
    public void updateDataRows(List<JPanel> newRows) {
        this.rows = newRows;
        redrawTable();
    }

    // MODIFIES: this
    // EFFECTS: Remove the current data list that contains all the data and adds a new list to this panel.
    // Repaints this panel.
    public void redrawTable() {
        this.remove(scrollList);
        scrollList = new JScrollPane(createTableData());
        this.add(scrollList);
        
        this.revalidate();
        this.repaint(); 
    }
}
