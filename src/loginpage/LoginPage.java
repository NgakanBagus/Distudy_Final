package loginpage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class LoginPage {
    public static void main(String[] args) {
        // TODO code application logic here
        Login LoginFrame = new Login();
        LoginFrame.setVisible(true);
        LoginFrame.pack();
        LoginFrame.setLocationRelativeTo(null);
    }

    public static Connection getKoneksi() {
        Connection connection = null;
    try {
        String url = "jdbc:mysql://localhost/elearning";
        String user = "root";
        String password = "";
        connection = DriverManager.getConnection(url, user, password);
        System.out.println("Connection successful");
    } catch (SQLException t) {
        System.out.println("Error: " + t.getMessage());
    }
    return connection;
    }

    static boolean isValidLogin(String username, String password) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
}
