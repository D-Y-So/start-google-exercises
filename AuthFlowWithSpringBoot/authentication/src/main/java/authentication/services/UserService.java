package authentication.services;

import authentication.entities.User;
import authentication.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private static UserService instance;
    @Autowired
    private static UsersRepository usersRepository;

    private UserService() {
        usersRepository = UsersRepository.getInstance();
    }

    public static UserService getInstance() {
        if(instance == null) {
            instance = new UserService();
        }
        return instance;
    }

    public User updateUserDetails(int id, Fields field, String change) {
        User user = usersRepository.getUserById(id).orElseThrow(NullPointerException::new);

        switch(field) {
            case NAME:
                user.setName(change);
                break;
            case EMAIL:
                user.setEmail(change);
                break;
            case PASSWORD:
                user.setPassword(change);
                break;
        }
        return usersRepository.updateUserDetails(user);
    }

    public void delete(int id) {
        usersRepository.deleteUser(id);
    }

    public List<User> getAllUsers(){
        return usersRepository.getAllUsers();
    }
}
