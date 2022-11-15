import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

public class FootballPlayerGenerator {

    private static String randomFullName(){
        NameGenerator fullNameGen = new NameGenerator(Arrays.asList(UtilityLists.fullNamesArr));
        return fullNameGen.randomName();
    }

    private static int randomJerseyNumber(){
        return ThreadLocalRandom.current().nextInt(1, 100);
    }

    private static float randomPlayerGrade(){
        float randomGrade = ThreadLocalRandom.current().nextFloat()*(10-0);
        DecimalFormat df= new DecimalFormat("0.0");
        return Float.parseFloat(df.format(randomGrade));
    }

    private static FootballPlayerPosition randomPlayerPosition(){
        FootballPlayerPosition[] arrPositions = FootballPlayerPosition.values();
        int randomIndex = ThreadLocalRandom.current().nextInt(arrPositions.length);
        return arrPositions[randomIndex];
    }

    public static FootballPlayer randomPlayer(){
        return FootballPlayer.createFootballPlayer(randomFullName(),randomJerseyNumber(),randomPlayerGrade(),randomPlayerPosition());
    }

    public static FootballPlayer randomPlayerByPosition(FootballPlayerPosition position){

        switch (position){
            case GOALKEEPER:
                return FootballPlayer.createFootballPlayer(randomFullName(),randomJerseyNumber(),randomPlayerGrade(),FootballPlayerPosition.GOALKEEPER);

            case ATTACKER:
                return FootballPlayer.createFootballPlayer(randomFullName(),randomJerseyNumber(),randomPlayerGrade(),FootballPlayerPosition.ATTACKER);

            case DEFENDER:
               return FootballPlayer.createFootballPlayer(randomFullName(),randomJerseyNumber(),randomPlayerGrade(),FootballPlayerPosition.DEFENDER);

            case MIDFIELDER:
                return FootballPlayer.createFootballPlayer(randomFullName(),randomJerseyNumber(),randomPlayerGrade(),FootballPlayerPosition.MIDFIELDER);

            default:
                throw new IllegalArgumentException("No such position: " + position);

        }

    }

}
