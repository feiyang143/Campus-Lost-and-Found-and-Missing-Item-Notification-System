import com.phn.entity.User;
import java.lang.reflect.Field;
import java.lang.annotation.Annotation;

public class CheckUserEntity {
    public static void main(String[] args) {
        try {
            // Get User class
            Class<User> userClass = User.class;
            
            // Print class name
            System.out.println("User class: " + userClass.getName());
            
            // Check for table name annotation
            Annotation[] classAnnotations = userClass.getAnnotations();
            System.out.println("\nClass annotations:");
            for (Annotation annotation : classAnnotations) {
                System.out.println("- " + annotation.toString());
            }
            
            // Print fields
            Field[] fields = userClass.getDeclaredFields();
            System.out.println("\nFields:");
            for (Field field : fields) {
                System.out.println("- " + field.getName());
            }
            
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}