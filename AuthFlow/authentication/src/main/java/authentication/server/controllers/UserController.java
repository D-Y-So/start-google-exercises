package authentication.server.controllers;

import authentication.server.controllers.Utils.Validetor;
import authentication.server.services.AuthService;
import authentication.server.services.Fields;
import authentication.server.services.UserService;

public class UserController {
    private final UserService userService;
    private final AuthService authService;
    private static UserController instance;
    private UserController() {
        userService = UserService.getInstance();
        authService = AuthService.getInstance();
    }

    public static UserController getInstance() {
        if(instance == null) {
            instance = new UserController();
        }
        return instance;
    }

    public void updateName(String token, String name) {
        int id = this.isValidToken(token);

        if(!Validetor.isValidName(name)) {
            throw new IllegalArgumentException("Invalid name");
        }
        userService.updateUserDetails(id, Fields.NAME, name);
    }

    public void updateEmail(String token, String email) {
        int id = this.isValidToken(token);

        if(!Validetor.isValidEmail(email)) {
            throw new IllegalArgumentException("Invalid email");
        }
        userService.updateUserDetails(id, Fields.EMAIL, email);
    }

    public void updatePassword(String token, String password) {
        int id = this.isValidToken(token);

        if(!Validetor.isValidPassword(password)) {
            throw new IllegalArgumentException("Invalid password, password must contain :" + Validetor.getPasswordConstraints());
        }
        userService.updateUserDetails(id, Fields.PASSWORD, password);
    }

    public void delete(String token) {
        int id = this.isValidToken(token);
        userService.delete(id);
    }

    public int isValidToken(String token) {
        int id = authService.isValidToken(token);
        if(id < 0) {
            throw new IllegalArgumentException("Invalid token");
        }
        return id;
    }
}
