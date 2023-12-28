/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package loginpage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    /**
     * @param args the command line arguments
     */
    
    static Connection connection;
        
    public static Connection getConnection() {
        try {
            String url = "jdbc:mysql://localhost/elearning";
            String user = "root";
            String password = "";
                
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            connection = DriverManager.getConnection(url, user, password);
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        
        return connection;
    }
        
    public static void main(String[] args) {
        getConnection();
    }
    
}