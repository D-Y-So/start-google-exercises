import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        JobPosition se = new JobPosition.Builder(LocalDate.now(), "Software Engineer", false).location("Haifa").build();
        System.out.println(se);
    }
}