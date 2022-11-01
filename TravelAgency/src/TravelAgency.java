import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public class TravelAgency {

    private final int planeCount;
    private final int boatCount;
    private final int busCount;
    private final int taxiCount;

    private final List<Vehicle>  vehicleList;
    private final Map<Passenger, Vehicle> passengerVehicleMap;

    private TravelAgency(int planeCount, int busCount, int boatCount, int taxiCount) {
        this.planeCount = planeCount;
        this.busCount = busCount;
        this.boatCount = boatCount;
        this.taxiCount = taxiCount;
        this.passengerVehicleMap = new HashMap<>();
        this.vehicleList = new ArrayList<>();
        fillVehicleList();

    }

    private void fillVehicleType(Vehicles type, int amount){
        VehicleFactory factory = VehicleFactory.getInstance();
        for(int i = 0; i<amount; i++){
            vehicleList.add(factory.createVehicle(type));
        }
    }
    private void fillVehicleList(){
        fillVehicleType(Vehicles.BOAT,boatCount);
        fillVehicleType(Vehicles.BUS,busCount);
        fillVehicleType(Vehicles.PLANE,planeCount);
        fillVehicleType(Vehicles.TAXI,taxiCount);
    }

    public static TravelAgency createTravelAgency(int planeCount, int busCount, int boatCount, int taxiCount){
        return new TravelAgency(planeCount,busCount,boatCount,taxiCount);
    }

    private void assignRandomVehicleToPassenger(Passenger passenger){
        if(!allVehiclesUsed()){
            int randomIndex = ThreadLocalRandom.current().nextInt(vehicleList.size());
            passengerVehicleMap.put(passenger,vehicleList.get(randomIndex));
            vehicleList.remove(randomIndex);
        }else{
            System.out.println("Can't assign vehicle to passenger " + passenger.getName() + ", no more vehicles in agency.");
        }
    }

    public void assignVehicleToPassenger(Passenger passenger){
        Boolean assigned = false;
        if(!allVehiclesUsed()){
            for(int i =0 ; i<vehicleList.size() && assigned==false; i++){
                if(vehicleList.get(i).getClass() == passenger.getFavoriteVehicle().getClass()){
                    passengerVehicleMap.put(passenger,vehicleList.get(i));
                    vehicleList.remove(i);
                    assigned=true;
                }
            }
            if(assigned==false){
                assignRandomVehicleToPassenger(passenger);
            }
        }else{
            System.out.println("Can't assign vehicle to passenger " + passenger.getName() + ", no more vehicles in agency.");
        }
    }

    private boolean allVehiclesUsed(){
        if (vehicleList.isEmpty()){
            return true;
        }
        return false;
    }

    public void invokeTransport(){
        if(allVehiclesUsed()==true){
            for(Map.Entry<Passenger,Vehicle> entry : passengerVehicleMap.entrySet()){
                entry.getValue().transport(entry.getKey());
            }
        }else{
            System.out.println("Not all vehicles are used yet, can't invoke transport, assign more passengers");
        }
    }

}
