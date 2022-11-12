package authentication.controllers;

import authentication.entities.LoginCredentials;
import authentication.entities.User;
import authentication.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="auth")
public class AuthController {
    private static AuthController instance;
    @Autowired
    private static AuthService authService;
    private AuthController() {
        authService = AuthService.getInstance();
    }

    public static AuthController getInstance() {
        if(AuthController.instance == null) {
            instance = new AuthController();
        }
        return instance;    }


    @RequestMapping(value = "register" ,method = RequestMethod.POST)
    public ResponseEntity<User> register(@RequestBody User newUser) {
        if(!Validator.isValidName(newUser.getName())) throw new IllegalArgumentException("Invalid name");
        this.checkEmailAndPassword(newUser.getEmail(), newUser.getPassword());
        return ResponseEntity.ok(authService.createNewUser(newUser.getName(), newUser.getEmail(), newUser.getPassword()));
    }

    @RequestMapping(value = "login" ,method = RequestMethod.POST)
    public ResponseEntity<String> login(@RequestBody LoginCredentials loginCred) {
        this.checkEmailAndPassword(loginCred.getEmail(), loginCred.getPassword());
        return ResponseEntity.ok("token: " + authService.validateUserCredentials(loginCred.getEmail(), loginCred.getPassword()));
    }
    public void checkEmailAndPassword(String email, String password) {
        if(!Validator.isValidEmail(email)) {
            throw new IllegalArgumentException("Invalid email");}

        if(!Validator.isValidPassword(password)) {
            throw new IllegalArgumentException("Invalid password, password must contain :" + Validator.getPasswordConstraints());
        }
    }
}
