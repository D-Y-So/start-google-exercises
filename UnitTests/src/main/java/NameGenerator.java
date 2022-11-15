import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class NameGenerator {
    private static List<String> names;

    public NameGenerator(List<String> names) {
        this.names = names;
    }

    public String randomName(){
        int randomIndex = ThreadLocalRandom.current().nextInt(0, names.size());
        return names.get(randomIndex);
    }
}
