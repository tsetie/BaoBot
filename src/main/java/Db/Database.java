package Db;

import java.sql.Connection;
import java.sql.DriverManager;

public class Database {

    /**
    * Establishes a connection to the PostgreSQL database
    * @return A Connection object the represents the established db connection, null if connection not established
    */

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

    public void createTable(Connection conn)


}

