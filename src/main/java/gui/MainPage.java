package gui;

import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainPage {

    private static boolean isSettingsVisible = false;
    private JTabbedPane tabbedPane;

    public static void createMainPage() {
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (Exception e) {
            e.printStackTrace();
        }

        JFrame mainFrame = new JFrame("Student Email Client");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(800, 600);
        mainFrame.setLocationRelativeTo(null);

        //menu en tabbladen
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        splitPane.setDividerSize(5);
        splitPane.setDividerLocation(200); // Breedte van het hamburger menu
        splitPane.setLeftComponent(createHamburgerMenu());
        JTabbedPane tabbedPane = createTabbedPane();
        splitPane.setRightComponent(tabbedPane);

        //Instellingenknop
        JButton settingsButton = new JButton("⚙");
        settingsButton.setFont(new Font("Arial", Font.PLAIN, 16));
        settingsButton.setToolTipText("Settings");
        settingsButton.addActionListener(e -> openSettingsPopup(mainFrame));

        JPanel settingsPanel = new JPanel(new BorderLayout());
        settingsPanel.add(settingsButton, BorderLayout.EAST);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(settingsPanel, BorderLayout.NORTH);
        mainPanel.add(splitPane, BorderLayout.CENTER);

        mainFrame.add(mainPanel);
        mainFrame.setVisible(true);
    }

    private static JPanel createHamburgerMenu() {
        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS));
        menuPanel.setBackground(Color.LIGHT_GRAY);

        JLabel menuLabel = new JLabel("Menu", SwingConstants.CENTER);
        menuLabel.setFont(new Font("Arial", Font.BOLD, 16));
        menuLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton inboxButton = new JButton("Inbox");
        JButton sentButton = new JButton("Sent");
        JButton draftsButton = new JButton("Drafts");
        JButton trashButton = new JButton("Trash");
        JButton exitButton = new JButton("Exit");

        exitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        inboxButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        sentButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        draftsButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        trashButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //bevestigings
                int option = JOptionPane.showConfirmDialog(menuPanel,
                        "Weet je zeker dat je de applicatie wilt afsluiten?",
                        "Bevestig Afsluiten", JOptionPane.YES_NO_OPTION);

                if (option == JOptionPane.YES_OPTION) {
                    System.exit(0); // Sluit de applicatie
                }
            }
        });

        menuPanel.add(menuLabel);
        menuPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        menuPanel.add(inboxButton);
        menuPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        menuPanel.add(sentButton);
        menuPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        menuPanel.add(draftsButton);
        menuPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        menuPanel.add(trashButton);
        menuPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Afstand tussen knoppen
        menuPanel.add(exitButton);

        return menuPanel;
    }

    private static JTabbedPane createTabbedPane() {
        JTabbedPane tabbedPane = new JTabbedPane();

        JPanel emailPanel = new JPanel();
        emailPanel.setLayout(new BorderLayout());
        emailPanel.add(new JLabel("Email Content", SwingConstants.CENTER), BorderLayout.CENTER);
        tabbedPane.addTab("Email", emailPanel);

        AgendaPage agendaPage = new AgendaPage();
        tabbedPane.addTab("Agenda", agendaPage);  //Voeg Agenda aan tab

        return tabbedPane;
    }


    private static void openSettingsPopup(JFrame parentFrame) {
        JDialog settingsDialog = new JDialog(parentFrame, "Settings", true);
        settingsDialog.setSize(400, 300);
        settingsDialog.setLocationRelativeTo(parentFrame);

        settingsDialog.add(new SettingsPane(parentFrame));
        settingsDialog.setVisible(true);
    }

}
