import java.sql.*;

public class TestUserPassword {
    public static void main(String[] args) {
        try {
            // Load MySQL driver
            Class.forName("com.mysql.jdbc.Driver");
            
            // Connect to database
            String url = "jdbc:mysql://localhost:3306/lostfound?useUnicode=true&characterEncoding=UTF-8";
            String username = "root";
            String password = "root";
            Connection conn = DriverManager.getConnection(url, username, password);
            System.out.println("Database connected successfully");
            
            // Query user table
            String sql = "SELECT id, username, userpassword FROM t_user";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            
            // Print user information
            System.out.println("User information from t_user table:");
            System.out.println("ID | Username | Password");
            System.out.println("------------------------");
            while (rs.next()) {
                int id = rs.getInt("id");
                String user = rs.getString("username");
                String pass = rs.getString("userpassword");
                System.out.println(id + " | " + user + " | " + pass);
            }
            
            // Close resources
            rs.close();
            stmt.close();
            conn.close();
            System.out.println("Database connection closed");
            
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}