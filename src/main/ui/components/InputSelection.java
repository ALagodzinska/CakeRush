package ui.components;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import model.Cake;
import model.CakeElements.CakeColor;
import model.CakeElements.Decoration;
import model.CakeElements.Glaze;
import model.CakeElements.Topping;
import ui.CakeDisplay;

import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;

@ExcludeFromJacocoGeneratedReport
// Represents the panel that displays all buttons used for customizing and controlling cake display.
public class InputSelection extends JPanel {
    private ElementSelectionButtons numOfTiers;
    private ElementSelectionButtons cakeColors;
    private ElementSelectionButtons glaze;
    private ElementSelectionButtons toppings;
    private ElementSelectionButtons decorations;
    private JButton submitButton;

    private Cake cake;
    private CakeDisplay display;

    // EFFECTS: Initializes the selection panel, creates a button group for each element, and 
    // assigns the specified actions for the defined cake display.
    public InputSelection(CakeDisplay display) {
        super();
        this.numOfTiers = new ElementSelectionButtons(new Integer[]{1,2,3},"Number of tiers", 
                new ChangeNumberOfTiersListener());
        this.cakeColors = new ElementSelectionButtons(CakeColor.values(),"Color", new ChangeColorListener());
        this.glaze = new ElementSelectionButtons(Glaze.values(),"Glaze", new ChangeGlazeListener());
        this.toppings = new ElementSelectionButtons(Topping.values(),"Toppings", new ChangeToppingListener());
        this.decorations = new ElementSelectionButtons(Decoration.values(),"Decorations", 
                new ChangeDecorationListener());

        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        addButtonsToPanel();
        addSubmitButton();
        
        this.display = display;
        this.cake = display.getDisplayedCake();
    }    

    // MODIFIES: this
    // EFFECTS: adds all element selection buttons to this panel.
    private void addButtonsToPanel() {
        this.add(numOfTiers);
        this.add(Box.createRigidArea(new Dimension(50, 0)));
        this.add(cakeColors);
        this.add(Box.createRigidArea(new Dimension(50, 0)));
        this.add(glaze);
        this.add(Box.createRigidArea(new Dimension(50, 0)));
        this.add(toppings);
        this.add(Box.createRigidArea(new Dimension(50, 0)));
        this.add(decorations);
        this.add(Box.createRigidArea(new Dimension(50, 0)));
    }

    // MODIFIES: this
    // EFFECTS: initializes and adds submit button to this panel.
    private void addSubmitButton() {
        submitButton = new JButton("SUBMIT");
        
        submitButton.setPreferredSize(new Dimension(80, 80));

        this.add(submitButton);
    }       

    // EFFECTS: assigns specified action to the submit button on this panel.
    public void setActionOnSumbit(ActionListener action) {
        submitButton.addActionListener(action);
    }

    // EFFECTS: Enables or disables all the buttons on this panel based on the given boolean value.
    @Override
    public void setEnabled(boolean enable) {
        numOfTiers.setEnableAll(enable);
        cakeColors.setEnableAll(enable);
        glaze.setEnableAll(enable);
        toppings.setEnableAll(enable);
        decorations.setEnableAll(enable);
        submitButton.setEnabled(enable);
    }


    @ExcludeFromJacocoGeneratedReport
    // Action listener that handles number of tiers button group clicks.
    private class ChangeNumberOfTiersListener implements ActionListener {
        // MODIFIES: this, Cake, CakeDisplay
        // EFFECTS: Changes the value of the cake tiers to the value selected by button.
        // Repaints the cake display component.
        @Override
        public void actionPerformed(ActionEvent e) {
            JRadioButton selectedButton = (JRadioButton) e.getSource();
            InputSelection.this.cake.setNumberOfTiers(Integer.parseInt(selectedButton.getText()));
            InputSelection.this.display.repaint();  
        }

    }

    @ExcludeFromJacocoGeneratedReport
    // Action listener that handles color button group clicks.
    private class ChangeColorListener implements ActionListener {
        // MODIFIES: this, Cake, CakeDisplay
        // EFFECTS: Changes the value of the cake color to the value selected by button.
        // Repaints the cake display component.
        @Override
        public void actionPerformed(ActionEvent e) {
            JRadioButton selectedButton = (JRadioButton) e.getSource();
            InputSelection.this.cake.setCakeColor(CakeColor.valueOf(selectedButton.getText()));
            InputSelection.this.display.repaint();  
        }

    }

    @ExcludeFromJacocoGeneratedReport
    // Action listener that handles glaze button group clicks.
    private class ChangeGlazeListener implements ActionListener {
        // MODIFIES: this, Cake, CakeDisplay
        // EFFECTS: Changes the value of the cake glaze to the value selected by button.
        // Repaints the cake display component.
        @Override
        public void actionPerformed(ActionEvent e) {
            JRadioButton selectedButton = (JRadioButton) e.getSource();
            InputSelection.this.cake.setGlaze(Glaze.valueOf(selectedButton.getText()));
            InputSelection.this.display.repaint();  
        }
    }

    @ExcludeFromJacocoGeneratedReport
    // Action listener that handles glaze button group clicks.
    private class ChangeToppingListener implements ActionListener {
        // MODIFIES: this, Cake, CakeDisplay
        // EFFECTS: Changes the value of the topping to the value selected by button. 
        // Repaints the cake display component.
        @Override
        public void actionPerformed(ActionEvent e) {
            JRadioButton selectedButton = (JRadioButton) e.getSource();
            InputSelection.this.cake.setTopping(Topping.valueOf(selectedButton.getText()));
            InputSelection.this.display.repaint();  
        }
    }

    @ExcludeFromJacocoGeneratedReport
    // Action listener that handles glaze button group clicks.
    private class ChangeDecorationListener implements ActionListener {
        // MODIFIES: this, Cake, CakeDisplay
        // EFFECTS: Changes the value of the decoration to the value selected by button.
        // Repaints the cake display component.
        @Override
        public void actionPerformed(ActionEvent e) {
            JRadioButton selectedButton = (JRadioButton) e.getSource();
            InputSelection.this.cake.setDecoration(Decoration.valueOf(selectedButton.getText()));
            InputSelection.this.display.repaint();  
        }
    }
}
