import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class AddRewardPointsToUser {
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
            
            // Create statement
            Statement statement = connection.createStatement();
            
            // Add reward_points column to t_user table
            String sql = "ALTER TABLE t_user ADD COLUMN reward_points INT(11) DEFAULT 0";
            statement.executeUpdate(sql);
            System.out.println("Successfully added reward_points column to t_user table");
            
            // Close resources
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
