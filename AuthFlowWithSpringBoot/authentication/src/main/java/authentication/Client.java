package authentication;

import authentication.controllers.AuthController;
import authentication.controllers.UserController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;

@SpringBootApplication
public class Client {
    public static void main(String[] args) {
        SpringApplication.run(Client.class, args);

        File file = new File("tokens.json");
        file.deleteOnExit();

        /*
        AuthController authController = AuthController.getInstance();
        UserController userController = UserController.getInstance();
        authController.register(new User("omar", "omar@gmail.com", "Omar1999$"));
        authController.register(new User("maria", "maria@gmail.com", "Maria123$"));

        String token = authController.logIn(new LoginCredentials("omar@gmail.com", "Omar1999$")).getBody();
        System.out.println(token);
        String token2 = authController.logIn(new LoginCredentials("maria@gmail.com", "Maria123$")).getBody();

        userController.updateName(token, "Eden");
        userController.updateEmail(token, "Eden@gmail.com");
        userController.updatePassword(token, "Eden123$");

        userController.delete(token2);
        */
    }
}