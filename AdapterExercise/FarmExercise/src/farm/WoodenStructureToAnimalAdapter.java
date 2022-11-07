package farm;

public class WoodenStructureToAnimalAdapter implements Animal {
    WoodenStructures woodenHorse;

    public WoodenStructureToAnimalAdapter(WoodenStructures woodenHorse){
        this.woodenHorse=woodenHorse;
    }
    @Override
    public void move() {
        woodenHorse.roll();
    }

    @Override
    public Animal mate(Animal partner) {
        return new WoodenStructureToAnimalAdapter(woodenHorse.replicate());
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
