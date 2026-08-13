import com.phn.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestHibernate {
    public static void main(String[] args) {
        try {
            // Load Spring context
            ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
            System.out.println("Spring context loaded successfully");
            
            // Get SessionFactory
            SessionFactory sessionFactory = (SessionFactory) context.getBean("sessionFactory");
            System.out.println("SessionFactory obtained successfully");
            
            // Open session
            Session session = sessionFactory.openSession();
            System.out.println("Session opened successfully");
            
            // Test HQL query
            java.util.List<User> users = session.createQuery("from User us order by us.id desc").list();
            System.out.println("Query executed successfully");
            System.out.println("Total users: " + users.size());
            
            for (User user : users) {
                System.out.println("User: " + user.getUsername());
            }
            
            // Close session
            session.close();
            System.out.println("Session closed successfully");
            
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}