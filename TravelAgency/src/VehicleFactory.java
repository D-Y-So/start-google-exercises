public class VehicleFactory {
    private static VehicleFactory instance;

    public static VehicleFactory getInstance(){
        if(instance==null){
            instance = new VehicleFactory();
        }
        return instance;
    }

    private VehicleFactory(){

    }

    public Vehicle createVehicle(Vehicles type){
        switch (type){
            case BUS:
                return new Bus();
            case TAXI:
                return new Taxi();
            case PLANE:
                return new Plane();
            case BOAT:
                return new Boat();
            default:
                throw new IllegalArgumentException(String.format("Vehicle type not supported: %s", type));
        }
    }
}
