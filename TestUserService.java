import com.phn.service.UserService;
import com.phn.entity.Pages;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestUserService {
    public static void main(String[] args) {
        try {
            // Load Spring context
            ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
            System.out.println("Spring context loaded successfully");
            
            // Get UserService
            UserService userService = (UserService) context.getBean("userService");
            System.out.println("UserService obtained successfully: " + userService);
            
            // Test GetAll method with correct page parameters
            int pageNo = 1;
            int pageSize = 10;
            System.out.println("Testing with pageNo=" + pageNo + ", pageSize=" + pageSize);
            Pages pages = userService.GetAll(pageNo, pageSize);
            System.out.println("Pages obtained successfully: " + pages);
            
            if (pages != null) {
                System.out.println("All records: " + pages.getAllRecords());
                System.out.println("Current page: " + pages.getCurrentPage());
                System.out.println("Total pages: " + pages.getTotalPages());
                System.out.println("Page size: " + pages.getPageSize());
                System.out.println("User list: " + pages.getListUser());
                System.out.println("User list size: " + (pages.getListUser() != null ? pages.getListUser().size() : 0));
                
                if (pages.getListUser() != null) {
                    for (com.phn.entity.User user : pages.getListUser()) {
                        System.out.println("User: " + user.getId() + " - " + user.getUsername() + " - " + user.getUsernickname());
                    }
                }
            }
            
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}