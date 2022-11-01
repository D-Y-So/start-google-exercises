import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ExportingVisitor implements Visitor {
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    @Override
    public String visit(User user) {
        return gson.toJson(user);
    }

    @Override
    public String visit(Asset asset) {
        return gson.toJson(asset);
    }

    @Override
    public String visit(Group group) {
        return gson.toJson(group);
    }
}
