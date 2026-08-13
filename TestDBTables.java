import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class TestDBTables {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/lostfound?useUnicode=true&characterEncoding=UTF-8";
        String username = "root";
        String password = "root";
        
        try {
            // Load the MySQL driver
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Driver loaded successfully");
            
            // Establish connection
            Connection connection = DriverManager.getConnection(url, username, password);
            System.out.println("Connection established successfully");
            
            // Check if database exists
            System.out.println("Database connection test passed!");
            
            // Test query - show tables
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SHOW TABLES");
            
            System.out.println("Tables in lostfound database:");
            while (resultSet.next()) {
                System.out.println("- " + resultSet.getString(1));
            }
            
            // Check t_goods table structure
            System.out.println("\nt_goods table structure:");
            resultSet = statement.executeQuery("DESCRIBE t_goods");
            while (resultSet.next()) {
                System.out.println("- " + resultSet.getString("Field") + " (" + resultSet.getString("Type") + ")");
            }
            
            // Check t_user table structure
            System.out.println("\nt_user table structure:");
            resultSet = statement.executeQuery("DESCRIBE t_user");
            while (resultSet.next()) {
                System.out.println("- " + resultSet.getString("Field") + " (" + resultSet.getString("Type") + ")");
            }
            
            // Close resources
            resultSet.close();
            statement.close();
            connection.close();
            System.out.println("Connection closed");
        } catch (ClassNotFoundException e) {
            System.err.println("Driver not found: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("SQL error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}