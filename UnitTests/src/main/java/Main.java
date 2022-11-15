import java.util.Map;

public class Main {

    public static void main(String[] args) {

        Team team = Team.createRandomTeam();
        System.out.println(team);
        team.writeTeamToFile("team details.txt");
    }

}