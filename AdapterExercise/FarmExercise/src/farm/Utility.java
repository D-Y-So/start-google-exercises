package farm;

import java.util.concurrent.ThreadLocalRandom;

public class Utility {

    private final static int MIN_ID = 100000000;
    private final static int MAX_ID = 999999999;

    public static String randomId(){
        return String.valueOf(ThreadLocalRandom.current().nextInt(MIN_ID,MAX_ID));
    }

    public static Gender randomGender(){
        Gender[] genders = Gender.values();
        return genders[ThreadLocalRandom.current().nextInt(0,genders.length)];
    }

    public static double randomWeight(double min, double max){
        return ThreadLocalRandom.current().nextDouble(min,max+1);
    }

}
