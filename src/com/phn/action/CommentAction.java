package com.phn.action;

import com.opensymphony.xwork2.ActionSupport;
import com.phn.entity.Comment;
import com.phn.entity.Goods;
import com.phn.entity.User;
import com.phn.service.CommentService;
import com.phn.service.UserService;
import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;

@Controller("commentAction")
public class CommentAction extends ActionSupport {
    private Comment comment;
    private Goods goods;
    private CommentService commentService;
    private UserService userService;
    private int index;

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    public CommentService getCommentService() {
        return commentService;
    }

    public void setCommentService(CommentService commentService) {
        this.commentService = commentService;
    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String NewInfo() throws Exception {
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("sessionUser");

        if (user != null) {
            System.out.println("CommentAction.NewInfo() - User found: " + user.getUsername() + ", ID: " + user.getId());
            
            comment.setCommentuser(user);
            comment.setCommenttime(new Date());
            comment.setCommentgoods(goods);

            // Save comment
            System.out.println("CommentAction.NewInfo() - Saving comment...");
            commentService.insert(comment);
            System.out.println("CommentAction.NewInfo() - Comment saved successfully");

            // Check goods object
            if (goods == null) {
                System.out.println("CommentAction.NewInfo() - ERROR: goods is null");
            } else {
                System.out.println("CommentAction.NewInfo() - Goods ID: " + goods.getId());
                
                // Query complete goods object to get goodsstatus
                try {
                    System.out.println("CommentAction.NewInfo() - Connecting to database...");
                    Class.forName("com.mysql.jdbc.Driver");
                    java.sql.Connection conn = java.sql.DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/lostfound?useUnicode=true&characterEncoding=UTF-8",
                        "root",
                        "root"
                    );
                    System.out.println("CommentAction.NewInfo() - Database connected successfully");
                    
                    // Query goods status
                    System.out.println("CommentAction.NewInfo() - Querying goods status for ID: " + goods.getId());
                    java.sql.PreparedStatement queryStmt = conn.prepareStatement(
                        "SELECT goodsstatus FROM t_goods WHERE id = ?"
                    );
                    queryStmt.setInt(1, goods.getId());
                    java.sql.ResultSet rs = queryStmt.executeQuery();
                    
                    if (rs.next()) {
                        int goodsStatus = rs.getInt("goodsstatus");
                        System.out.println("CommentAction.NewInfo() - Goods status: " + goodsStatus);
                        
                        // Only add reward points for lost items (goodsstatus=1)
                        if (goodsStatus == 1) {
                            System.out.println("CommentAction.NewInfo() - This is a lost item (goodsstatus=1), adding reward points...");
                            // Execute update operation, directly increase reward points in database
                            java.sql.PreparedStatement pstmt = conn.prepareStatement(
                                "UPDATE t_user SET reward_points = reward_points + 1 WHERE id = ?"
                            );
                            pstmt.setInt(1, user.getId());
                            int rowsAffected = pstmt.executeUpdate();
                            System.out.println("Update user reward points success, affected rows: " + rowsAffected);
                            pstmt.close();
                            
                            // Update session user info
                            // Get user info again to ensure session data is latest
                            System.out.println("CommentAction.NewInfo() - Updating session user info...");
                            User updatedUser = userService.find(user.getUsername());
                            if (updatedUser != null) {
                                System.out.println("CommentAction.NewInfo() - Updated user reward points: " + updatedUser.getReward_points());
                                session.setAttribute("sessionUser", updatedUser);
                            } else {
                                System.out.println("CommentAction.NewInfo() - ERROR: Updated user is null");
                            }
                        } else {
                            System.out.println("CommentAction.NewInfo() - This is not a lost item (goodsstatus != 1), no reward points added");
                        }
                    } else {
                        System.out.println("CommentAction.NewInfo() - ERROR: Goods not found for ID: " + goods.getId());
                    }
                    
                    rs.close();
                    queryStmt.close();
                    conn.close();
                    System.out.println("CommentAction.NewInfo() - Database connection closed");
                } catch (Exception e) {
                    System.out.println("Exception when updating user reward points: " + e.getMessage());
                    e.printStackTrace();
                }
            }

            // Set redirect parameter
            index = goods.getId();
            System.out.println("CommentAction.NewInfo() - Redirecting to goods ID: " + index);

            return "comment_newInfo_success";
        } else {
            System.out.println("CommentAction.NewInfo() - ERROR: User not logged in");
            return "comment_newInfo_failed";
        }
    }
}