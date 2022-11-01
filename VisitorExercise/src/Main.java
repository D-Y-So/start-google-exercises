import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Exportable> exportables = new ArrayList<>();
        exportables.add(User.createUser(1234, "Yossi", "dewfd65"));
        exportables.add(Asset.createAsset(3243434, "Yossi", 4.9));
        exportables.add(Group.createGroup(6796, 100));

        ExportingVisitor exportingVisitor = new ExportingVisitor();
        for (Exportable exportble : exportables) {
            System.out.println(exportble.accept(exportingVisitor));
        }

    }
}