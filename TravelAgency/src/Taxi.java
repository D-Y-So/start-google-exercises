public class Taxi implements Vehicle{
    @Override
    public void transport(Passenger passenger) {
        System.out.println("Passenger " + passenger.getName() + " was successfully transported on a Taxi.");
    }
}
