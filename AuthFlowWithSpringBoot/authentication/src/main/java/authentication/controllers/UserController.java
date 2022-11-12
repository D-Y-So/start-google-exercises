package authentication.controllers;

import authentication.entities.User;
import authentication.services.AuthService;
import authentication.services.Fields;
import authentication.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(value="users")
public class UserController {
    @Autowired
    private final UserService userService;
    @Autowired
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

    @RequestMapping(value = "updateName" ,method = RequestMethod.PUT)
    public ResponseEntity<User> updateName(@RequestParam String token, @RequestBody String name) {
        int id = this.isValidToken(token);
        if(!Validator.isValidName(name)) {
            throw new IllegalArgumentException("Invalid name");
        }
        return ResponseEntity.ok(userService.updateUserDetails(id, Fields.NAME, name));
    }

    @RequestMapping(value = "updateEmail" ,method = RequestMethod.PUT)
    public ResponseEntity<User> updateEmail(@RequestParam String token, @RequestBody String email) {
        int id = this.isValidToken(token);
        if(!Validator.isValidEmail(email)) {
            throw new IllegalArgumentException("Invalid email");
        }
        return ResponseEntity.ok(userService.updateUserDetails(id, Fields.EMAIL, email));
    }

    @RequestMapping(value = "updatePassword" ,method = RequestMethod.PUT)
    public ResponseEntity<User> updatePassword(@RequestParam String token, @RequestBody String password) {
        System.out.println(password);
        int id = this.isValidToken(token);
        if(!Validator.isValidPassword(password)) {
            throw new IllegalArgumentException("Invalid password, password must contain :" + Validator.getPasswordConstraints());
        }
        return ResponseEntity.ok(userService.updateUserDetails(id, Fields.PASSWORD, password));
    }

    @RequestMapping(value = "delete" ,method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@RequestParam String token) {
        int id = this.isValidToken(token);
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }

    private int isValidToken(String token) {
        int id = authService.isValidToken(token);
        if(id < 0) {
            throw new IllegalArgumentException("Invalid token");
        }
        return id;
    }

    @RequestMapping(value="list",method = RequestMethod.GET)
    public ResponseEntity<List<User>> getAllUsers(){
        return ResponseEntity.ok(userService.getAllUsers());
    }


}
