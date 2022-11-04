package farm;

public class WoodenStructureAdapter implements Animal {
    WoodenHorse woodenHorse;

    public WoodenStructureAdapter(WoodenHorse woodenHorse){
        this.woodenHorse=woodenHorse;
    }
    @Override
    public void move() {
        woodenHorse.roll();
    }

    @Override
    public Animal mate(Animal partner) {
        woodenHorse.replicate();
        return new WoodenStructureAdapter(new WoodenHorse());
    }

    @Override
    public String getId() {
        return "575767";
    }

    @Override
    public Gender getGender() {
        return Gender.MALE;
    }

}
