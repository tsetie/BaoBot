package Db;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Database {

    String tableName = "Reminders";

    public Connection connectToDb() {
        Connection conn = null;

        try {
            // Load the SQLite JDBC driver
            Class.forName("org.sqlite.JDBC");

            // Establish connection to SQLite db
            conn = DriverManager.getConnection("jdbc:sqlite:baobot.db");

            // Check if connection is successful
            if (conn != null) {
                System.out.println("Connection Established");
            } else {
                System.out.println("Connection failed");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return conn;
    }

    public void createTable(Connection conn) {
        try {
            String query = "CREATE TABLE IF NOT EXISTS Reminders (reminderID INTEGER PRIMARY KEY AUTOINCREMENT, reminder TEXT, username TEXT);";
            Statement statement = conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("Table Created!!! :)");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void addReminder(Connection conn, String username, String reminder) {
        try {
            String query = String.format("INSERT INTO %s (username, reminder) VALUES ('%s', '%s');", tableName, username, reminder);
            Statement statement = conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("Reminder added! :)");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void deleteReminder(Connection conn, String username, int reminderID) {
        try {
            String query = String.format("DELETE FROM %s WHERE username = '%s' AND reminderID = %d;", tableName, username, reminderID);
            Statement statement = conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("Reminder deleted! :)");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void deleteAllReminders(Connection conn, String username) {
        try {
            String query = String.format("DELETE FROM %s WHERE username = '%s';", tableName, username);
            Statement statement = conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("ALL reminders deleted! :O");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public ResultSet showAllReminders(Connection conn, String username, MessageReceivedEvent event) {
        ResultSet rs = null;
        try {
            String query = String.format("SELECT reminderID, reminder FROM %s WHERE username = '%s';", tableName, username);
            Statement statement = conn.createStatement();
            rs = statement.executeQuery(query);
            System.out.println("Listed all reminders :)");
        } catch (Exception e) {
            System.out.println(e);
        }
        return rs;
    }
}
