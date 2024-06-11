package jamjomm.stronglobby.DataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseHandler {
    private Connection connection;

    public void connect() {
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:banwarn.db");
            createTables();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void createTables() {
        try (PreparedStatement stmt = connection.prepareStatement(
                "CREATE TABLE IF NOT EXISTS users (username TEXT PRIMARY KEY, warnings INTEGER, banned INTEGER, ban_until TEXT)")) {
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void warnPlayer(String username) {
        try (PreparedStatement stmt = connection.prepareStatement(
                "INSERT INTO users (username, warnings, banned, ban_until) VALUES (?, 1, 0, NULL) ON CONFLICT(username) DO UPDATE SET warnings = warnings + 1")) {
            stmt.setString(1, username);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void banPlayer(String username, String banUntil) {
        try (PreparedStatement stmt = connection.prepareStatement(
                "INSERT INTO users (username, warnings, banned, ban_until) VALUES (?, 0, 1, ?) ON CONFLICT(username) DO UPDATE SET banned = 1, ban_until = ?")) {
            stmt.setString(1, username);
            stmt.setString(2, banUntil);
            stmt.setString(3, banUntil);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void unbanPlayer(String username) {
        try (PreparedStatement stmt = connection.prepareStatement(
                "UPDATE users SET banned = 0, ban_until = NULL WHERE username = ?")) {
            stmt.setString(1, username);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean isBanned(String username) {
        try (PreparedStatement stmt = connection.prepareStatement(
                "SELECT banned, ban_until FROM users WHERE username = ?")) {
            stmt.setString(1, username);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int banned = rs.getInt("banned");
                    String banUntil = rs.getString("ban_until");
                    if (banned == 1 && (banUntil == null || banUntil.compareTo(java.time.LocalDateTime.now().toString()) > 0)) {
                        return true;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public int getWarnings(String username) {
        try (PreparedStatement stmt = connection.prepareStatement(
                "SELECT warnings FROM users WHERE username = ?")) {
            stmt.setString(1, username);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("warnings");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
