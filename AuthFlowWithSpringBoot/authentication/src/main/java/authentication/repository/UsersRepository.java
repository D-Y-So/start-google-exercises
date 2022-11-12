package authentication.repository;

import authentication.entities.User;
import org.springframework.stereotype.Repository;
import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.*;

@Repository
public class UsersRepository {
    private final Map<Integer, User> userMap;
    private final Map<Integer, String> idToken;
    private static UsersRepository instance;


    private UsersRepository() {
        userMap = new HashMap<>();
        idToken = new HashMap<>();
        this.loadMap();
        this.loadTokens();
    }

    public static UsersRepository getInstance() {
        if (instance == null) {
            instance = new UsersRepository();
        }
        return instance;
    }

    private void loadTokens(){
        File tokenFile = new File("tokens.json");
        try {
            tokenFile.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException("file tokens.json can't be created", e);
        }
        Map<String, String> tokens;
        tokens = ReadWriteToJson.readFromJson("tokens.json");
        if (tokens!=null) {
            for (Map.Entry<String, String> entry : tokens.entrySet()) {
                this.idToken.put(Integer.parseInt(entry.getKey()), entry.getValue());
            }
        }
    }

    private void loadMap() {
        String userDirectory = FileSystems.getDefault().getPath("").toAbsolutePath().toString();
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(Paths.get(userDirectory), "*.json")) {
            Map<String, String> user;
            for (Path p : stream) {
                user = ReadWriteToJson.readFromJson(p.toString());
                if (user != null && !user.isEmpty() && user.containsKey("id") && user.containsKey("name") && user.containsKey("email") && user.containsKey("password")) {
                    this.userMap.put(Integer.parseInt(user.get("id")), new User(Integer.parseInt(user.get("id")), user.get("name"), user.get("email"), user.get("password")));
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public User writeUserToRepo(User newUser) {
        String userDirectory = FileSystems.getDefault().getPath("").toAbsolutePath().toString();
        String fileName = userDirectory + "/" + newUser.getId() + ".json";
        Map<String, String> user = new HashMap<>();
        user.put("id", String.valueOf(newUser.getId()));
        user.put("name", newUser.getName());
        user.put("email", newUser.getEmail());
        user.put("password", newUser.getPassword());
        this.userMap.put(Integer.parseInt(user.get("id")), new User(Integer.parseInt(user.get("id")), user.get("name"), user.get("email"), user.get("password")));
        ReadWriteToJson.writeToJson(fileName, user);
        return newUser;
    }

    public Optional<User> readUserFromRepo(String userEmail) {
        if (this.userMap != null && !this.userMap.isEmpty()) {
            for (User user : this.userMap.values()) {
                if (user.getEmail().equals(userEmail)) {
                    return Optional.of(user);
                }
            }
        }
        return Optional.empty();
    }

    public Optional<User> getUserById(int id) {
        if (this.userMap != null && !this.userMap.isEmpty()) {
            return Optional.of(this.userMap.get(id));
        }
        return Optional.empty();
    }

    public int userIsValid(String email, String password) {
        for (User user : userMap.values()) {
            if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
                return user.getId();
            }
        }
        return -1;
    }

    public boolean emailIsFree(String email) {
        for (User user : userMap.values()) {
            if (user.getEmail().equals(email)) {
                return false;
            }
        }
        return true;
    }

    public boolean idIsFree(int id) {
        if ((this.userMap != null && !this.userMap.isEmpty() && userMap.get(id) == null) || this.userMap.isEmpty()) {
            return true;
        }
        return false;
    }

    public void deleteUser(int id) {
        String userDirectory = FileSystems.getDefault().getPath("").toAbsolutePath().toString();
        File file = new File(userDirectory + "/" + id + ".json");
        if (file.delete()) {
            System.out.println("Deleted the file: " + file.getName());
            userMap.remove(id);
            idToken.remove(id);
            Map<String, String> tokens = new HashMap<>();
            for(Map.Entry<Integer,String> entry : idToken.entrySet() ){
                tokens.put(entry.getKey().toString(), entry.getValue());
            }
            ReadWriteToJson.writeToJson("tokens.json", tokens);
        } else {
            System.out.println("Can't delete file. File " + file.getName() + " doesn't exist.");
        }
    }

    public User updateUserDetails(User user) {
        if (this.userMap != null && !this.userMap.isEmpty()) {
            User editedUser = userMap.get(user.getId());
            editedUser.setName(user.getName());
            editedUser.setEmail(user.getEmail());
            editedUser.setPassword(user.getPassword());
            return writeUserToRepo(editedUser);
        }
        return user;
    }

    public List<User> getAllUsers(){
        return new ArrayList<User>(userMap.values());
    }

    public String addTokenToRepo(int id, String token){
        this.idToken.put(id,token);
        Map<String, String> tokens = new HashMap<>();
        for(Map.Entry<Integer,String> entry : idToken.entrySet() ){
            tokens.put(entry.getKey().toString(), entry.getValue());
        }
        ReadWriteToJson.writeToJson("tokens.json", tokens);
        return token;
    }

    public int getIdByToken(String token){
        this.loadTokens();
        Optional<Map.Entry<Integer,String>> idTok = this.idToken.entrySet().stream().filter(entry -> token.equals(entry.getValue())).findFirst();
        if(idTok.isPresent()){
            Integer id = idTok.get().getKey();
            if (id!=null){
                return id;
            }
        }
        return -1;
    }
}
