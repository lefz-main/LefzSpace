package gui;
import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.*;
import java.awt.*;

public class AppLauncher {
    private static boolean isLoggedIn = false; //Sim loginstatus

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (Exception e) {
            e.printStackTrace();
        }

        //Start
        SwingUtilities.invokeLater(AppLauncher::launchApplication);
    }

    private static void launchApplication() {
        JFrame frame = new JFrame("Student Email Client");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null); // Centreer het venster

        JPanel panel = new JPanel(new BorderLayout());
        JLabel welcomeLabel = new JLabel("Welcome to the Student Email Client", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 18));
        panel.add(welcomeLabel, BorderLayout.CENTER);

        if (!isLoggedIn) {
            JButton loginButton = new JButton("Login");
            loginButton.setFont(new Font("Arial", Font.PLAIN, 16));
            loginButton.addActionListener(e -> showLoginPage(frame));
            panel.add(loginButton, BorderLayout.SOUTH);
        }

        frame.add(panel);
        frame.setVisible(true);
    }
    private static void showLoginPage(JFrame mainFrame) {
        JFrame loginFrame = LoginPage.createLoginFrame();
        loginFrame.setVisible(true);

        //Controleer de loginstatus
        new Thread(() -> {
            while (!isLoggedIn) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            //Sluit loginvenster en update de hoofdapplicatie
            SwingUtilities.invokeLater(() -> {
                loginFrame.dispose();
                mainFrame.dispose();
                showMainApp();
            });
        }).start();
    }

    public static void loginSuccessful() {
        isLoggedIn = true;
    }

    private static void showMainApp() {
        MainPage.createMainPage();
    }
}
