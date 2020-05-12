import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;

public class ServerApp {

    public static void main(String[] args) throws SQLException {
        AnnotationConfigApplicationContext context=
                new AnnotationConfigApplicationContext(
                        "config"
                );
    }
}