public class User implements Exportable{
    private final int id;
    private final String name;
    private final String password;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    private User(int id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
    }

    public static User createUser(int id, String name, String password){
        return new User(id, name, password);
    }

    @Override
    public String accept(Visitor visitor) {
       return visitor.visit(this);
    }
}
