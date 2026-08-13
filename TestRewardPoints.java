import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class TestRewardPoints {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/lostfound?useUnicode=true&characterEncoding=UTF-8";
        String username = "root";
        String password = "root";
        
        try {
            // Load driver
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Driver loaded successfully");
            
            // Establish connection
            Connection conn = DriverManager.getConnection(url, username, password);
            System.out.println("Database connected successfully");
            
            // Test 1: Query t_user table structure
            System.out.println("\n=== Test 1: Query t_user table structure ===");
            Statement stmt1 = conn.createStatement();
            ResultSet rs1 = stmt1.executeQuery("DESCRIBE t_user");
            while (rs1.next()) {
                System.out.println(rs1.getString("Field") + " - " + rs1.getString("Type"));
            }
            rs1.close();
            stmt1.close();
            
            // Test 2: Query t_goods table structure
            System.out.println("\n=== Test 2: Query t_goods table structure ===");
            Statement stmt2 = conn.createStatement();
            ResultSet rs2 = stmt2.executeQuery("DESCRIBE t_goods");
            while (rs2.next()) {
                System.out.println(rs2.getString("Field") + " - " + rs2.getString("Type"));
            }
            rs2.close();
            stmt2.close();
            
            // Test 3: Query all lost items (goodsstatus=1)
            System.out.println("\n=== Test 3: Query all lost items ===");
            Statement stmt3 = conn.createStatement();
            ResultSet rs3 = stmt3.executeQuery("SELECT id, goodsname, goodsstatus FROM t_goods WHERE goodsstatus = 1");
            int count = 0;
            while (rs3.next()) {
                count++;
                System.out.println("ID: " + rs3.getInt("id") + ", Name: " + rs3.getString("goodsname") + ", Status: " + rs3.getInt("goodsstatus"));
            }
            System.out.println("Found " + count + " lost items");
            rs3.close();
            stmt3.close();
            
            // Test 4: Test updating user reward points
            System.out.println("\n=== Test 4: Test updating user reward points ===");
            // Assume user ID is 1
            int testUserId = 1;
            // First query current points
            PreparedStatement queryStmt = conn.prepareStatement("SELECT reward_points FROM t_user WHERE id = ?");
            queryStmt.setInt(1, testUserId);
            ResultSet rs4 = queryStmt.executeQuery();
            if (rs4.next()) {
                int currentPoints = rs4.getInt("reward_points");
                System.out.println("User ID " + testUserId + " current points: " + currentPoints);
                
                // Update points
                PreparedStatement updateStmt = conn.prepareStatement("UPDATE t_user SET reward_points = reward_points + 1 WHERE id = ?");
                updateStmt.setInt(1, testUserId);
                int rowsAffected = updateStmt.executeUpdate();
                System.out.println("Update successful, rows affected: " + rowsAffected);
                
                // Query updated points
                queryStmt.executeQuery();
                if (rs4.next()) {
                    int updatedPoints = rs4.getInt("reward_points");
                    System.out.println("User ID " + testUserId + " updated points: " + updatedPoints);
                }
                updateStmt.close();
            } else {
                System.out.println("User ID " + testUserId + " not found");
            }
            rs4.close();
            queryStmt.close();
            
            // Test 5: Test querying goods status
            System.out.println("\n=== Test 5: Test querying goods status ===");
            // Assume goods ID is 1
            int testGoodsId = 1;
            PreparedStatement goodsStmt = conn.prepareStatement("SELECT goodsstatus FROM t_goods WHERE id = ?");
            goodsStmt.setInt(1, testGoodsId);
            ResultSet rs5 = goodsStmt.executeQuery();
            if (rs5.next()) {
                int goodsStatus = rs5.getInt("goodsstatus");
                System.out.println("Goods ID " + testGoodsId + " status: " + goodsStatus);
                System.out.println("Is lost item: " + (goodsStatus == 1 ? "Yes" : "No"));
            } else {
                System.out.println("Goods ID " + testGoodsId + " not found");
            }
            rs5.close();
            goodsStmt.close();
            
            // Close connection
            conn.close();
            System.out.println("\nDatabase connection closed");
            
        } catch (Exception e) {
            System.err.println("Exception during test: " + e.getMessage());
            e.printStackTrace();
        }
    }
}