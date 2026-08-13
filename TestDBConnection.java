import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class TestDBConnection {
    public static void main(String[] args) {
        try {
            // 加载驱动
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("驱动加载成功");
            
            // 连接数据库
            Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/lostfound?useUnicode=true&characterEncoding=UTF-8",
                "root",
                "root"
            );
            System.out.println("数据库连接成功");
            
            // 检查t_user表
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SHOW TABLES LIKE 't_user'");
            
            if (rs.next()) {
                System.out.println("t_user表存在");
                
                // 查看表结构
                ResultSet rs2 = stmt.executeQuery("DESCRIBE t_user");
                System.out.println("t_user表结构：");
                System.out.println("Field\tType\tNull\tKey\tDefault\tExtra");
                while (rs2.next()) {
                    System.out.print(rs2.getString(1) + "\t");
                    System.out.print(rs2.getString(2) + "\t");
                    System.out.print(rs2.getString(3) + "\t");
                    System.out.print(rs2.getString(4) + "\t");
                    System.out.print(rs2.getString(5) + "\t");
                    System.out.println(rs2.getString(6));
                }
                rs2.close();
            } else {
                System.out.println("t_user表不存在");
            }
            
            // 关闭资源
            rs.close();
            stmt.close();
            conn.close();
            
        } catch (Exception e) {
            System.out.println("错误：" + e.getMessage());
            e.printStackTrace();
        }
    }
}