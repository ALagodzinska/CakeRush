package ui.components;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import model.Cake;
import model.CakeElements.CakeColor;
import model.CakeElements.Decoration;
import model.CakeElements.Glaze;
import model.CakeElements.Topping;
import ui.CakeDisplay;

// Represents the panel that displays all buttons used for customizing and controlling cake display.
public class InputSelection extends JPanel {
    private ElementSelectionButtons numOfTiers;
    private ElementSelectionButtons cakeColors;
    private ElementSelectionButtons glaze;
    private ElementSelectionButtons toppings;
    private ElementSelectionButtons decorations;

    private Cake cake;
    private CakeDisplay display;

    // EFFECTS: Initializes the selection panel, creates a button group for each element, and 
    // assigns the specified actions for the defined cake display.
    public InputSelection(CakeDisplay display) {
        super();
        this.numOfTiers = new ElementSelectionButtons(new Integer[]{1,2,3},"Number of tiers", changeNumberOfTiers());
        this.cakeColors = new ElementSelectionButtons(CakeColor.values(),"Color", changeColor());
        this.glaze = new ElementSelectionButtons(Glaze.values(),"Glaze", changeGlaze());
        this.toppings = new ElementSelectionButtons(Topping.values(),"Toppings", changeTopping());
        this.decorations = new ElementSelectionButtons(Decoration.values(),"Decorations", changeDecoration());

        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        addButtonsToPanel();
        
        this.display = display;
        this.cake = display.getDisplayedCake();
    }

    // TODO: remove duplication; move out cake + cake display

    // MODIFIES: this, Cake, CakeDisplay
    // EFFECTS: Changes the value of the cake tiers to the value selected by button. Repaints the cake display component.
    private ActionListener changeNumberOfTiers() {
        ActionListener action = new ActionListener() {
            public void actionPerformed(ActionEvent evt) {                
                JRadioButton selectedButton = (JRadioButton) evt.getSource();
                InputSelection.this.cake.setNumberOfTiers(Integer.parseInt(selectedButton.getText()));
                InputSelection.this.display.repaint();            
            }
        };

        return action;
    }

    // MODIFIES: this, Cake, CakeDisplay
    // EFFECTS: Changes the value of the cake color to the value selected by button. Repaints the cake display component.
    private ActionListener changeColor() {
        ActionListener action = new ActionListener() {
            public void actionPerformed(ActionEvent evt) {                
                JRadioButton selectedButton = (JRadioButton) evt.getSource();
                InputSelection.this.cake.setCakeColor(CakeColor.valueOf(selectedButton.getText()));
                InputSelection.this.display.repaint();          
            }
        };

        return action;
    }

    // MODIFIES: this, Cake, CakeDisplay
    // EFFECTS: Changes the value of the cake glaze to the value selected by button. Repaints the cake display component.
    private ActionListener changeGlaze() {
        ActionListener action = new ActionListener() {
            public void actionPerformed(ActionEvent evt) {                
                JRadioButton selectedButton = (JRadioButton) evt.getSource();
                InputSelection.this.cake.setGlaze(Glaze.valueOf(selectedButton.getText()));
                InputSelection.this.display.repaint();          
            }
        };

        return action;
    }

    // MODIFIES: this, Cake, CakeDisplay
    // EFFECTS: Changes the value of the topping to the value selected by button. Repaints the cake display component.
    private ActionListener changeTopping() {
        ActionListener action = new ActionListener() {
            public void actionPerformed(ActionEvent evt) {                
                JRadioButton selectedButton = (JRadioButton) evt.getSource();
                InputSelection.this.cake.setTopping(Topping.valueOf(selectedButton.getText()));
                InputSelection.this.display.repaint();          
            }
        };

        return action;
    }

    // MODIFIES: this, Cake, CakeDisplay
    // EFFECTS: Changes the value of the decoration to the value selected by button. Repaints the cake display component.
    private ActionListener changeDecoration() {
        ActionListener action = new ActionListener() {
            public void actionPerformed(ActionEvent evt) {                
                JRadioButton selectedButton = (JRadioButton) evt.getSource();
                InputSelection.this.cake.setDecoration(Decoration.valueOf(selectedButton.getText()));
                InputSelection.this.display.repaint();          
            }
        };

        return action;
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
    }

    // EFFECTS: Disables all buttons and submits the result.
    public void submit() {
        // stub
    }

    // EFFECTS: Enables or disables all the buttons on this panel based on the given boolean value.
    @Override
    public void setEnabled(boolean enable) {
        numOfTiers.setEnableAll(enable);
        cakeColors.setEnableAll(enable);
        glaze.setEnableAll(enable);
        toppings.setEnableAll(enable);
        decorations.setEnableAll(enable);
    }
}
