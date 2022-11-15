import java.util.HashMap;
import java.util.Map;

public class UtilityLists {

    public final static String[] fullNamesArr = {"James Henderson", "John Coleman", "Robert Simmons", "Michael Patterson", "William Jordan", "David Reynolds", "Richard West", "Charles Marshall", "Joseph Woods", "Thomas Mason"};
    public final static String[] teamNamesArr = {"Profs", "The Captivators", "Athletic Hippies", "Fighting Hornets", "Rangers", "Bald Eagles", "KnockOut Legends"};

    public final static Map<FootballPlayerPosition, Integer> formation1 = Map.of(FootballPlayerPosition.GOALKEEPER,1,
                                                                                FootballPlayerPosition.DEFENDER, 4,
                                                                                FootballPlayerPosition.MIDFIELDER, 4,
                                                                                FootballPlayerPosition.ATTACKER, 2);

}
