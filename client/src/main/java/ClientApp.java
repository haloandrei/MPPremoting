import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ui.Console;

public class ClientApp {

    public static void main(String[] args) {
        System.out.println("Client");

        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(
                        "config"
                );

//        Console ui = new Console(context.getBean(ServiceMovieInterface.class),context.getBean(ServiceClientInterface.class),
//                context.getBean(ServiceAcquisitionInterface.class));
        Console ui=context.getBean(Console.class);
        ui.runConsole();
    }
}
