package gui;
import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class AgendaPage extends JPanel {

    private List<String> afspraken;

    public AgendaPage() {
        afspraken = new ArrayList<>();
        setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Mijn Agenda", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(Color.decode("#3F9CBB"));
        add(titleLabel, BorderLayout.NORTH);

        //voorbeeld inhoud
        JPanel agendaContent = new JPanel();
        agendaContent.setLayout(new BoxLayout(agendaContent, BoxLayout.Y_AXIS));

        //voorbeeldafspraken
        LocalDate[] dates = {
                LocalDate.now().plusDays(1),
                LocalDate.now().plusDays(2),
                LocalDate.now().plusDays(3),
                LocalDate.now().plusDays(4)
        };

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy");

        //voeg afspraken
        for (LocalDate date : dates) {
            JPanel panel = new JPanel();
            panel.setLayout(new FlowLayout(FlowLayout.LEFT));
            panel.setBackground(Color.white);

            // Datum
            JLabel dateLabel = new JLabel(date.format(formatter) + " - Vergadering");
            dateLabel.setFont(new Font("Arial", Font.PLAIN, 16));
            panel.add(dateLabel);


            JButton deleteButton = new JButton("Verwijderen");
            deleteButton.setFont(new Font("Arial", Font.PLAIN, 12));
            deleteButton.setBackground(Color.decode("#FF6F61"));
            deleteButton.setForeground(Color.white);
            deleteButton.setFocusPainted(false);
            deleteButton.addActionListener(e -> removeAfspraken(date));
            panel.add(deleteButton);

            panel.setBorder(BorderFactory.createLineBorder(Color.decode("#3F9CBB"), 1));
            agendaContent.add(panel);
        }

        //Scrollpane voor agenda-inhoud
        JScrollPane scrollPane = new JScrollPane(agendaContent);
        add(scrollPane, BorderLayout.CENTER);

        // Voeg een knop toe om een nieuwe afspraak toe te voegen
        JPanel bottomPanel = new JPanel();
        JButton addButton = new JButton("Nieuwe Afspraak");
        addButton.setFont(new Font("Arial", Font.BOLD, 14));
        addButton.setBackground(Color.decode("#3F9CBB"));
        addButton.setForeground(Color.white);
        addButton.setFocusPainted(false);
        addButton.addActionListener(e -> addAfspraken());
        bottomPanel.add(addButton);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    private void addAfspraken() {
        LocalDate today = LocalDate.now();
        afspraken.add("Nieuwe afspraak op " + today.plusDays(1).format(DateTimeFormatter.ofPattern("dd MMM yyyy")));
        refreshAgenda();
    }

    private void removeAfspraken(LocalDate date) {
        afspraken.removeIf(afspraak -> afspraak.contains(date.format(DateTimeFormatter.ofPattern("dd MMM yyyy"))));
        refreshAgenda();
    }

    private void refreshAgenda() {
        removeAll();
        setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Mijn Agenda", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(Color.decode("#3F9CBB"));
        add(titleLabel, BorderLayout.NORTH);

        JPanel agendaContent = new JPanel();
        agendaContent.setLayout(new BoxLayout(agendaContent, BoxLayout.Y_AXIS));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy");

        for (String afspraak : afspraken) {
            JPanel panel = new JPanel();
            panel.setLayout(new FlowLayout(FlowLayout.LEFT));
            panel.setBackground(Color.white);

            JLabel dateLabel = new JLabel(afspraak);
            dateLabel.setFont(new Font("Arial", Font.PLAIN, 16));
            panel.add(dateLabel);

            JButton deleteButton = new JButton("Verwijderen");
            deleteButton.setFont(new Font("Arial", Font.PLAIN, 12));
            deleteButton.setBackground(Color.decode("#FF6F61"));
            deleteButton.setForeground(Color.white);
            deleteButton.setFocusPainted(false);
            deleteButton.addActionListener(e -> removeAfspraken(LocalDate.parse(afspraak.split(" ")[4], DateTimeFormatter.ofPattern("ddMMM"))));
            panel.add(deleteButton);

            panel.setBorder(BorderFactory.createLineBorder(Color.decode("#3F9CBB"), 1));
            agendaContent.add(panel);
        }

        // Scrollpane voor de afspraken
        JScrollPane scrollPane = new JScrollPane(agendaContent);
        add(scrollPane, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel();
        JButton addButton = new JButton("Nieuwe Afspraak");
        addButton.setFont(new Font("Arial", Font.BOLD, 14));
        addButton.setBackground(Color.decode("#3F9CBB"));
        addButton.setForeground(Color.white);
        addButton.setFocusPainted(false);
        addButton.addActionListener(e -> addAfspraken());
        bottomPanel.add(addButton);
        add(bottomPanel, BorderLayout.SOUTH);

        revalidate();
        repaint();
    }
}
