import java.util.concurrent.ThreadLocalRandom;

public class Utility {

    private final static int MIN_CHAR = 97;
    private final static int MAX_CHAR = 123;
    private final static int MIN_NAME_LENGTH = 3;
    private final static int MAX_NAME_LENGTH = 20;


    public static String randomName() {
        int randomNameLength = ThreadLocalRandom.current().nextInt(MIN_NAME_LENGTH,MAX_NAME_LENGTH);
        String randomName = "";
        for(int i = 0; i<randomNameLength; i++) {
            randomName += (char) ThreadLocalRandom.current().nextInt(MIN_CHAR, MAX_CHAR);;
        }
        return randomName.substring(0, 1).toUpperCase() + randomName.substring(1);
    }


    public static Vehicle randomVehicle(){
        Vehicles[] types = Vehicles.values();
        VehicleFactory factory = VehicleFactory.getInstance();
        return factory.createVehicle(types[ThreadLocalRandom.current().nextInt(types.length)]);
    }
}
