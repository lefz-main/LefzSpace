package gui;

import com.formdev.flatlaf.FlatLightLaf;
import httpClient.httpPythonClient;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class LoginPage {

    public static JFrame createLoginFrame() {
        // Maak het hoofdframe
        JFrame frame = new JFrame("Lefz E-mail Client - Login");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null); //Centreer

        //Hoofdpanel
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        //Titel
        JLabel titleLabel = new JLabel("Lefz Students Mail Login");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(titleLabel, gbc);

        // Gebruikersnaam label en veld
        JLabel usernameLabel = new JLabel("Email:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.LINE_END;
        panel.add(usernameLabel, gbc);

        JTextField usernameField = new JTextField(20);
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        panel.add(usernameField, gbc);

        // Wachtwoord label en veld
        JLabel passwordLabel = new JLabel("Password:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.LINE_END;
        panel.add(passwordLabel, gbc);

        JPasswordField passwordField = new JPasswordField(20);
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        panel.add(passwordField, gbc);

        // Login-knop
        JButton loginButton = new JButton("Login");
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        loginButton.addActionListener((ActionEvent e) -> {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
            if (validateCredentials(username, password)) {
                JOptionPane.showMessageDialog(frame, "Login successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(frame, "Invalid email or password.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        panel.add(loginButton, gbc);

        // Voeg panel toe aan het frame
        frame.add(panel);
        return frame;
    }

    static httpPythonClient client = new httpPythonClient();
    public static boolean validateCredentials(String username, String password) {
        String loginResponse = client.tryLogin(username, password);
        System.out.println(loginResponse);
        if ("Logged In!".equals(loginResponse)){
            return true;
        } else {
            return false;
        }
}}
