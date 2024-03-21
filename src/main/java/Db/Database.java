package Db;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import javax.swing.plaf.nimbus.State;
import javax.xml.transform.Result;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Database {

    /**
    * Establishes a connection to the PostgreSQL database
    * @return A Connection object the represents the established db connection, null if connection not established
    */
    String tableName = "Reminders";
    public Connection connectToDb(){
        Connection conn = null;

        try{
            // Load the postgres JDBC driver class
            Class.forName("org.postgresql.Driver");

            // Establish connection to postgres db
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/" + "DiscordBot", "postgres", System.getenv("password"));

            // Check if connection is successful
            if(conn!=null){
                System.out.println("Connection Established");
            }else{
                System.out.println("Connection failed");
            }
        }catch(Exception e){
            System.out.println(e);
        }
        return conn;
    }

    public void createTable(Connection conn){
        // statement variable used to execute SQL statements to the db
        Statement statement;
        try{
            String query = "create table Reminders (reminderID SERIAL, reminder varchar(200), username varchar(200), primary key(reminderID));";
            statement = conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("Table Created!!! :)");
        }catch(Exception e){
            System.out.println(e);
        }
    }

    public void addReminder(Connection conn, String username, String reminder){
        Statement statement;

        try{
            String query = String.format("insert into %s (username, reminder) values ('%s', '%s');",tableName, username, reminder);
            statement = conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("Reminder added! :)");
        }catch(Exception e){
            System.out.println(e);
        }
    }

    public void deleteReminder(Connection conn, String username, int reminderID){
        Statement statement;
        try{
            String query = String.format("delete from %s where username = '%s' and reminderID = %d;", tableName, username, reminderID);
            statement = conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("Reminder deleted! :)");
        }catch(Exception e){
            System.out.println(e);
        }

    }

    public void deleteAllReminders(Connection conn, String username){
        Statement statement;
        try{
            String query = String.format("delete from %s where username = '%s';",tableName,username );
            statement = conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("ALL reminders deleted! :O");
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    public ResultSet showAllReminders(Connection conn, String username, MessageReceivedEvent event){
        Statement statement;
        // Will hold the output/result from the query
        ResultSet rs = null;
        try{
            String query = String.format("select reminderID, reminder from %s where username = '%s';", tableName, username);
            statement = conn.createStatement();
            rs = statement.executeQuery(query);
            System.out.println("Listed all reminders :)");
        }catch(Exception e){
            System.out.println(e);
        }
        return rs;

    }


}

