<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>注册功能测试</title>
</head>
<body>
    <h1>注册功能测试</h1>
    <hr>
    
    <h2>1. 数据库连接测试</h2>
    <% 
        try {
            Class.forName("com.mysql.jdbc.Driver");
            out.println("<p style='color: green;'>驱动加载成功</p>");
            
            java.sql.Connection conn = java.sql.DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/lostfound?useUnicode=true&characterEncoding=UTF-8",
                "root",
                "root"
            );
            out.println("<p style='color: green;'>数据库连接成功</p>");
            
            // 检查t_user表是否存在
            java.sql.Statement stmt = conn.createStatement();
            java.sql.ResultSet rs = stmt.executeQuery("SHOW TABLES LIKE 't_user'");
            if (rs.next()) {
                out.println("<p style='color: green;'>t_user表存在</p>");
                
                // 检查表结构
                java.sql.ResultSet rs2 = stmt.executeQuery("DESCRIBE t_user");
                out.println("<h3>t_user表结构：</h3>");
                out.println("<table border='1'>");
                out.println("<tr><th>Field</th><th>Type</th><th>Null</th><th>Key</th><th>Default</th><th>Extra</th></tr>");
                while (rs2.next()) {
                    out.println("<tr>");
                    out.println("<td>" + rs2.getString(1) + "</td>");
                    out.println("<td>" + rs2.getString(2) + "</td>");
                    out.println("<td>" + rs2.getString(3) + "</td>");
                    out.println("<td>" + rs2.getString(4) + "</td>");
                    out.println("<td>" + rs2.getString(5) + "</td>");
                    out.println("<td>" + rs2.getString(6) + "</td>");
                    out.println("</tr>");
                }
                out.println("</table>");
                rs2.close();
            } else {
                out.println("<p style='color: red;'>t_user表不存在</p>");
            }
            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            out.println("<p style='color: red;'>数据库连接失败：" + e.getMessage() + "</p>");
            e.printStackTrace(new java.io.PrintWriter(out));
        }
    %>
    
    <hr>
    
    <h2>2. 注册表单测试</h2>
    <form action="user_Register.action" method="post">
        <table>
            <tr>
                <td>用户名：</td>
                <td><input type="text" name="username" value="testuser" required></td>
            </tr>
            <tr>
                <td>密码：</td>
                <td><input type="password" name="userpassword" value="123456" required></td>
            </tr>
            <tr>
                <td>昵称：</td>
                <td><input type="text" name="usernickname" value="测试用户"></td>
            </tr>
            <tr>
                <td>电话：</td>
                <td><input type="text" name="userphone" value="13800138000"></td>
            </tr>
            <tr>
                <td>QQ：</td>
                <td><input type="text" name="userqq" value="123456789"></td>
            </tr>
            <tr>
                <td colspan="2"><input type="submit" value="注册测试"></td>
            </tr>
        </table>
    </form>
</body>
</html>