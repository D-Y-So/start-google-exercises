public class Main {
    public static void main(String[] args) {
        TravelAgency agency = TravelAgency.createTravelAgency(1,4,3,8);
        for (int i = 0; i < 17; i++) {
            agency.assignVehicleToPassenger(Passenger.generateRandomPassenger());
        }
        agency.invokeTransport();
    }
}