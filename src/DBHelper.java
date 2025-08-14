import java.sql.*;

public class DBHelper {
    private static final String DB_URL = "jdbc:sqlite:habits.db";

    public static Connection connect() throws SQLException {
        return DriverManager.getConnection(DB_URL);
    }

    public static void createTables() {
        String createHabitsTable = "CREATE TABLE IF NOT EXISTS habits (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name TEXT NOT NULL," +
                "done_today INTEGER DEFAULT 0" +
                ");";

        String createMoodTable = "CREATE TABLE IF NOT EXISTS moods (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "mood TEXT NOT NULL," +
                "date TEXT DEFAULT CURRENT_DATE" +
                ");";

        try (Connection conn = connect();
             Statement stmt = conn.createStatement()) {
            stmt.execute(createHabitsTable);
            stmt.execute(createMoodTable);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
