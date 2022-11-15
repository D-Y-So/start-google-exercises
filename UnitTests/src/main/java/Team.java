import java.util.Map;

public class Team {
    private final String name;
    private final Map<Integer, FootballPlayer> players;

    private Team(String name, Map<Integer, FootballPlayer> players) {
        this.name = name;
        this.players = players;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        String ls = System.getProperty("line.separator");
        stringBuilder.append("Team Name: ");
        stringBuilder.append(this.name);
        stringBuilder.append(ls);
        stringBuilder.append("Team Players: ");
        stringBuilder.append(ls);
        for (Map.Entry<Integer,FootballPlayer> entry : players.entrySet()) {
            stringBuilder.append(entry.getValue().toString());
            stringBuilder.append(ls);
        }
        return stringBuilder.toString();
    }

    /*creates a random team with the team name provided in parameter name, and players collection
    provided in the players Map parameter.
    */
    public static Team createNewTeam(String name, Map<Integer, FootballPlayer> players) {
        return new Team(name,players);
    }

    /*creates a random team with the team name provided in parameter name.
     */
    public static Team createTeamByName(String name) {
        return new Team(name,FootballTeamGenerator.randomPlayers());
    }

    /*creates a random team with random name and provided formation in Map param formation.
     */
    public static Team createTeamByFormation(Map<FootballPlayerPosition, Integer> formation) {
        return new Team(FootballTeamGenerator.randomTeamName(),FootballTeamGenerator.randomPlayersByFormation(formation));
    }

    /*creates a random team with provided name and provided formation in Map param formation.
     */
    public static Team createTeamByNameFormation(String name, Map<FootballPlayerPosition, Integer> formation) {
        return new Team(name,FootballTeamGenerator.randomPlayersByFormation(formation));
    }

    //Create a team with random name , random players with random formation.
    public static Team createRandomTeam() {
        return new Team(FootballTeamGenerator.randomTeamName(),FootballTeamGenerator.randomPlayers());
    }

    public void writeTeamToFile(String filePath){
        WriterToFile fw = new WriterToFile(filePath);
        fw.writeToFile(this.toString());
    }

}
