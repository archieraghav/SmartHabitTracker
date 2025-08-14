public class Main {
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            HabitTrackerGUI gui = new HabitTrackerGUI();
            gui.setVisible(true);
        });
    }
}
