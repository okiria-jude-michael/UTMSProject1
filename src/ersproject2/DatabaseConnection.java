/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ersproject2;

/**
 *
 * @author Eng.MIKE
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String DB_URL = "jdbc:ucanaccess://VUE_Exhibition.accdb";
    private static Connection conn;

    public static Connection getConnection() {
        if (conn == null) {
            try {
                conn = DriverManager.getConnection(DB_URL);
                System.out.println("Connected to database successfully");
            } catch (SQLException e) {
                System.err.println("Database connection failed: " + e.getMessage());
            }
        }
        return conn;
    }
}
