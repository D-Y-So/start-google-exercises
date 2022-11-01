public class Plane implements Vehicle{
    @Override
    public void transport(Passenger passenger) {
        System.out.println("Passenger " + passenger.getName() + " was successfully transported on a Plane.");
    }
}
