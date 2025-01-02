import httpClient.httpPythonClient;
import com.formdev.flatlaf.FlatLightLaf;
import gui.LoginPage;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Roep de loginpagina aan
        javax.swing.SwingUtilities.invokeLater(() -> {
            LoginPage.createLoginFrame().setVisible(true);
        });

    }
}