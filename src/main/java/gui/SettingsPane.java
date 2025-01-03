package gui;

import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.*;
import java.awt.*;
import java.util.Hashtable;

public class SettingsPane extends JPanel {
    private JToggleButton darkModeToggle;
    private JFrame parentFrame;

    public SettingsPane(JFrame parentFrame) {
        this.parentFrame = parentFrame;

        setLayout(new BorderLayout());
        JLabel titleLabel = new JLabel("Settings", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));

        //voorbeeldinstellingen
        JPanel settingsContent = new JPanel();
        settingsContent.setLayout(new GridLayout(4, 2, 10, 10));
        settingsContent.add(new JLabel("Dark Mode:"));

        //Dark Mode
        darkModeToggle = new JToggleButton("OFF");
        darkModeToggle.setFont(new Font("Arial", Font.PLAIN, 14));
        darkModeToggle.setPreferredSize(new Dimension(100, 30));
        darkModeToggle.setSelected(false); // Standaard staat hij op OFF
        darkModeToggle.addActionListener(e -> toggleDarkMode());
        settingsContent.add(darkModeToggle);

        settingsContent.add(new JLabel("Add Account:"));
        JButton addAccountButton = new JButton("Add");
        settingsContent.add(addAccountButton);

        settingsContent.add(new JLabel("Log Out:"));
        JButton logOutButton = new JButton("Log Out");
        settingsContent.add(logOutButton);

        //Save
        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(e -> saveSettings());
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bottomPanel.add(saveButton);

        add(titleLabel, BorderLayout.NORTH);
        add(settingsContent, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    private void toggleDarkMode() {
        if (darkModeToggle.isSelected()) {
            darkModeToggle.setText("ON");
            try {
                UIManager.setLookAndFeel(new FlatDarkLaf());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else {
            darkModeToggle.setText("OFF");
            try {
                UIManager.setLookAndFeel(new FlatLightLaf());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        // Update de UI voor het gehele frame TODO: moet ook nog gefixed worden voor de agenda
        SwingUtilities.updateComponentTreeUI(parentFrame);
    }


    private void saveSettings() {
        //TODO: nu nog echt werkend maken
        JOptionPane.showMessageDialog(this, "Settings saved!", "Success", JOptionPane.INFORMATION_MESSAGE);

        //sluit instellingen terug naar mainapp
        parentFrame.setVisible(true);
        Window window = SwingUtilities.getWindowAncestor(this);
        if (window != null) {
            window.dispose();
        }
    }
}