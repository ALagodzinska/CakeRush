package ui.components;

import java.awt.event.ActionListener;
import java.util.Enumeration;

import javax.swing.AbstractButton;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;

@ExcludeFromJacocoGeneratedReport
// Represents a group of radio buttons used to select one of the cake elements values.
public class ElementSelectionButtons extends JPanel {
    private String title;
    private Object[] options;
    private ButtonGroup btnGroup;
    private ActionListener action;

    // EFFECTS: Creates a set of radio buttons for the specified list of options,
    // with a defined title and button action.
    public ElementSelectionButtons(Object[] options, String title, ActionListener action) {
        super();
        this.title = title;
        this.options = options;
        this.btnGroup = new ButtonGroup();
        this.action = action;

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        addSelectionButtons();
    }

    // MODIFIES: this
    // EFFECTS: Adds buttons for all options to the button group and this panel.
    private void addSelectionButtons() {
        this.add(new JLabel(this.title));
        for (int i = 0; i < options.length; i++) {
            JRadioButton btn = new JRadioButton(options[i].toString());
            btn.addActionListener(action);
            btnGroup.add(btn);
            this.add(btn);

            if (i == 0) {
                btn.setSelected(true);
            }
        }
    }

    // Adapted from: 
    //   StackOverflow Question Title: Disable group of radio buttons
    //   Author: Braj
    //   URL: https://stackoverflow.com/questions/24980758/disable-group-of-radio-buttons

    // MODIFIES: this
    // EFFECTS: Based on passed boolean value disables or enables all buttons in this button group.
    public void setEnableAll(boolean enable) {
        Enumeration<AbstractButton> allButtons = btnGroup.getElements(); 

        while (allButtons.hasMoreElements()) {
            AbstractButton button = (AbstractButton) allButtons.nextElement();
            button.setEnabled(enable);
        }
    }
}
