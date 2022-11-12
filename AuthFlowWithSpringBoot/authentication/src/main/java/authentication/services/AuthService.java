package authentication.services;

import authentication.entities.User;
import authentication.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class AuthService {
    private final Map<String, Integer> tokenId;
    private static AuthService instance;
    @Autowired
    private final UsersRepository usersRepository;

    private AuthService() {
        tokenId = new HashMap<>();
        usersRepository = UsersRepository.getInstance();
    }
    public static AuthService getInstance() {
        if(instance == null) {
            instance = new AuthService();
        }
        return instance;
    }

    public User createNewUser(String name, String email, String password){
        if(!usersRepository.emailIsFree(email)){
            throw new Error("Email is occupied, please enter a different one");
        }
        User newUser = new User(createId(), name, email, password);
        return usersRepository.writeUserToRepo(newUser);
    }

    public String validateUserCredentials(String email, String password){
        int id = usersRepository.userIsValid(email, password);
        if(id < 0){
            throw new Error("One or more details are incorrect");
        }
        String token = createToken();
        return usersRepository.addTokenToRepo(id,token);
    }

    public int isValidToken(String token){
        return usersRepository.getIdByToken(token);
    }

    private String createToken(){
        String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder stringBuilder;
        do {
            stringBuilder = new StringBuilder(6);
            for (int i = 0; i < 6; i++) {
                stringBuilder.append(chars.charAt(ThreadLocalRandom.current().nextInt(chars.length())));
            }
        }
        while (tokenId.get(stringBuilder) != null);
        return stringBuilder.toString();
    }

    private int createId(){
        int newId;
        do {
            newId = ThreadLocalRandom.current().nextInt(999);
        }
        while(!usersRepository.idIsFree(newId));
        return newId;
    }
}
