import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class TestTUser {
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
            
            // Test query - select from t_user
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM t_user");
            
            int count = 0;
            while (resultSet.next()) {
                count++;
                System.out.println("User " + count + ": " + resultSet.getString("username"));
            }
            
            System.out.println("Total users in t_user: " + count);
            
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