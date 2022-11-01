public class Passenger {
    private final String name;
    private final Vehicle favoriteVehicle;

    private Passenger(String name, Vehicle vehicle) {
        this.name = name;
        this.favoriteVehicle = vehicle;
    }

    public String getName() {
        return name;
    }

    public Vehicle getFavoriteVehicle() {
        return favoriteVehicle;
    }

    public static Passenger generateRandomPassenger(){
        return new Passenger(Utility.randomName(),Utility.randomVehicle());
    }

    @Override
    public String toString() {
        return "Passenger{" +
                "name: '" + name + '\'' +
                ", favorite vehicle: " + favoriteVehicle.getClass().getName() +
                '}';
    }
}
