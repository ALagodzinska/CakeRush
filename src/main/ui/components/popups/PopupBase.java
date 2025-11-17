package ui.components.popups;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import ui.MainPanel;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;

import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;

@ExcludeFromJacocoGeneratedReport
// Represents a popup that has a title, description and two action buttons.
public abstract class PopupBase extends JDialog {
    private String title;
    private String description;
    private String cancelButtonLabel;
    private String continueButtonLabel;

    private JButton continueButton;
    private JButton cancelButton;
    private JLabel descriptionLabel;

    // EFFECTS: Initializes a popup with specified title, description and two buttons.
    public PopupBase(MainPanel mainPanel, String title, String description,
            String cancelButtonLabel, String continueButtonLabel) {
        super(mainPanel, "Popup", Dialog.ModalityType.APPLICATION_MODAL);
        this.title = title;
        this.description = description;
        this.cancelButtonLabel = cancelButtonLabel;
        this.continueButtonLabel = continueButtonLabel;

        setup();
    }

    // EFFECTS: Adds the given panel to the top of the popup window, then clears and updates the popup’s content.
    public void addResultsPanel(JPanel resultsPanel) {
        JPanel newContent = new JPanel();
        newContent.setLayout(new BoxLayout(newContent, BoxLayout.Y_AXIS));
        newContent.setPreferredSize(new Dimension(500, 300));
        newContent.add(resultsPanel);

        JPanel popupPanel = createPopupPanel();
        popupPanel.setMaximumSize(new Dimension(400, 150));
        popupPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        resultsPanel.setMaximumSize(new Dimension(400,70));
        resultsPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        newContent.add(popupPanel);
        
        updatePopupContent(newContent);
    }

    // MODIFIES: this
    // EFFECTS: Clears and updates popup content with a new panel.
    private void updatePopupContent(JPanel newContent) {
        clearPopupContent();
        this.setContentPane(newContent);

        this.pack();
        this.revalidate();
        this.repaint();
        this.setLocationRelativeTo(this);
    }

    // MODIFIES: this
    // EFFECTS: Clears popup content.
    private void clearPopupContent() {
        this.getContentPane().removeAll();        
    }

    // MODIFIES: this
    // EFFECTS: Styles the JDialog and adds popup content to it.
    private void setup() {
        this.setUndecorated(true);
        this.setAlwaysOnTop(true);

        JPanel panel = createPopupPanel();
        panel.setSize(500, 200);
        panel.setPreferredSize(new Dimension(500,150));
        panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        this.setContentPane(panel);

        this.pack();
        this.setLocationRelativeTo(this);        
    }

    // EFFECTS: displays popup to the user.
    public void open() {
        this.setVisible(true);
    }

    // EFFECTS: closes the popup window.
    public void close() {
        this.setVisible(false);
    }

    // EFFECTS: controls whether continue button is enabled or disabled.
    public void setEnabledContinueBtn(boolean isEnabled) {
        this.continueButton.setEnabled(isEnabled);
    }

    // EFFECTS: Creates and returns panel that contains popup's content and action buttons.
    private JPanel createPopupPanel() {
        JPanel popupPanel = new JPanel();

        popupPanel.setLayout(new BoxLayout(popupPanel, BoxLayout.Y_AXIS));
        // popupPanel.setSize(500, 200);
        // popupPanel.setPreferredSize(new Dimension(500,150));
        // popupPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        // TEST
        

        addPanelComponents(popupPanel);

        return popupPanel;
    }

    // EFFECTS: Adds title, description and button components to the specified parent component.
    private void addPanelComponents(JPanel popupPanel) {
        JLabel popupTitle = new JLabel(this.title);
        popupTitle.setFont(new Font("Arial", Font.BOLD, 20));
        popupTitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel popupDescription = new JLabel(this.description);
        popupDescription.setAlignmentX(Component.CENTER_ALIGNMENT);

        this.descriptionLabel = popupDescription;

        JPanel buttonPanel = createButtonPanel();
        
        popupPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        popupPanel.add(popupTitle);
        popupPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        popupPanel.add(popupDescription);
        popupPanel.add(Box.createRigidArea(new Dimension(0, 40)));
        popupPanel.add(buttonPanel);
    }

    // EFFECTS: Creates and returns a panel that consists of cancel and contiunue buttons.
    private JPanel createButtonPanel() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));

        JButton exitButton = new JButton(cancelButtonLabel);
        JButton continueButton = new JButton(continueButtonLabel);

        this.continueButton = continueButton;
        this.cancelButton = exitButton;

        buttonPanel.add(exitButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(50, 0)));
        buttonPanel.add(continueButton);

        return buttonPanel;
    }

    // EFFECTS: Adds specified action to continue button.
    public void addActionToContinueButton(ActionListener action) {
        this.continueButton.addActionListener(action);
    }

    // EFFECTS: Adds specified action to cancel button.
    public void addActionToCancelButton(ActionListener action) {
        this.cancelButton.addActionListener(action);
    }

    // EFFECTS: Sets text to the popup description label.
    public void setDescription(String text) {
        this.descriptionLabel.setText(text);
    }
}
