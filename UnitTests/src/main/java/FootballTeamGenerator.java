import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


public class FootballTeamGenerator {

    final private static int MAX_GOALKEEPER_NUM = 1;
    final private static int MIN_PLAYER_NUM = 2;


    /*randomizes players for the team until it finds the requested amount of players with the requested position,
    and unique jersey numbers.
    Add the players that answer the criteria to the team collection (Map).
     */
    private static void addPlayersByPosition(Map<Integer, FootballPlayer> footballTeam, FootballPlayerPosition position, int amount) {

        for (FootballPlayer player = FootballPlayerGenerator.randomPlayerByPosition(position); amount > 0; player = FootballPlayerGenerator.randomPlayerByPosition(position)) {
            if (footballTeam.containsKey(player.getJerseyNumber()) == false) {
                footballTeam.put(player.getJerseyNumber(), player);
                amount--;
            }
        }
   }

    public static Map<Integer, FootballPlayer> randomPlayers() {

       Map<Integer, FootballPlayer> players = new HashMap<>();

        //the team must have only 1 goalkeeper
        addPlayersByPosition(players, FootballPlayerPosition.GOALKEEPER, MAX_GOALKEEPER_NUM);

        // randomize 2 attackers for the team:
        addPlayersByPosition(players, FootballPlayerPosition.ATTACKER, MIN_PLAYER_NUM);

        // randomize 2 defenders for the team:
        addPlayersByPosition(players, FootballPlayerPosition.DEFENDER, MIN_PLAYER_NUM);

        // randomize 2 midfielders for the team:
        addPlayersByPosition(players, FootballPlayerPosition.MIDFIELDER, MIN_PLAYER_NUM);

        //the rest 4 players can be of any position except for goalkeeper:
        int num = 4;
        for (FootballPlayer randomPlayer = FootballPlayerGenerator.randomPlayer(); num > 0; randomPlayer = FootballPlayerGenerator.randomPlayer()) {
            //player jersey number must be unique, and there can be no more goalkeepers
            if (players.containsKey(randomPlayer.getJerseyNumber()) == false && randomPlayer.getPosition() != FootballPlayerPosition.GOALKEEPER) {
                players.put(randomPlayer.getJerseyNumber(), randomPlayer);
                num--;
            }
        }

        return players;

    }

    public static String randomTeamName(){
        NameGenerator teamNameGen = new NameGenerator(Arrays.asList(UtilityLists.teamNamesArr));
        return teamNameGen.randomName();
    }

    public static Map<Integer, FootballPlayer> randomPlayersByFormation(Map<FootballPlayerPosition, Integer> formation){
        Map<Integer, FootballPlayer> players = new HashMap<>();
        formation.forEach( (key, value) ->  addPlayersByPosition(players, key, value) );
        return players;
    }
}
