import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class HabitTrackerGUI extends JFrame {
    private JTextField habitField;
    private JComboBox<String> moodBox;
    private JTextArea displayArea;

    public HabitTrackerGUI() {
        setTitle("Smart Habit Tracker");
        setSize(500, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Top Panel - Add Habit & Mood
        JPanel topPanel = new JPanel();
        topPanel.add(new JLabel("Habit:"));
        habitField = new JTextField(15);
        topPanel.add(habitField);

        JButton addHabitBtn = new JButton("Add Habit");
        addHabitBtn.addActionListener(e -> addHabit());
        topPanel.add(addHabitBtn);

        topPanel.add(new JLabel("Mood:"));
        moodBox = new JComboBox<>(new String[]{"Happy", "Neutral", "Stressed", "Sad"});
        topPanel.add(moodBox);

        JButton logMoodBtn = new JButton("Log Mood");
        logMoodBtn.addActionListener(e -> logMood());
        topPanel.add(logMoodBtn);

        add(topPanel, BorderLayout.NORTH);

        // Middle Area - Display
        displayArea = new JTextArea();
        displayArea.setEditable(false);
        add(new JScrollPane(displayArea), BorderLayout.CENTER);

        // Bottom Panel - View Data
        JPanel bottomPanel = new JPanel();
        JButton viewBtn = new JButton("View Data");
        viewBtn.addActionListener(e -> viewData());
        bottomPanel.add(viewBtn);

        add(bottomPanel, BorderLayout.SOUTH);

        DBHelper.createTables();
    }

    private void addHabit() {
        String habit = habitField.getText().trim();
        if (habit.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a habit.");
            return;
        }
        try (Connection conn = DBHelper.connect();
             PreparedStatement ps = conn.prepareStatement("INSERT INTO habits (name) VALUES (?)")) {
            ps.setString(1, habit);
            ps.executeUpdate();
            habitField.setText("");
            JOptionPane.showMessageDialog(this, "Habit added!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error adding habit: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void logMood() {
        String mood = (String) moodBox.getSelectedItem();
        try (Connection conn = DBHelper.connect();
             PreparedStatement ps = conn.prepareStatement("INSERT INTO moods (mood) VALUES (?)")) {
            ps.setString(1, mood);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(this,
                    "Mood logged! Suggestion: " + MoodSuggestions.getSuggestion(mood));
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error logging mood: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void viewData() {
        StringBuilder sb = new StringBuilder();
        try (Connection conn = DBHelper.connect();
             Statement stmt = conn.createStatement()) {

            // Fetch habits
            ResultSet rsHabits = stmt.executeQuery("SELECT * FROM habits");
            sb.append("Habits:\n");
            while (rsHabits.next()) {
                sb.append("- ").append(rsHabits.getString("name")).append("\n");
            }

            // Fetch moods
            sb.append("\nMood Logs:\n");
            ResultSet rsMoods = stmt.executeQuery("SELECT * FROM moods");
            while (rsMoods.next()) {
                sb.append(rsMoods.getString("date"))
                  .append(": ")
                  .append(rsMoods.getString("mood"))
                  .append("\n");
            }

            displayArea.setText(sb.toString());

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error fetching data: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
