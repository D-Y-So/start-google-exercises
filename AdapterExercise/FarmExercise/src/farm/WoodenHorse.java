package farm;

public class WoodenHorse implements WoodenStructures {
    @Override
    public void roll() {
        System.out.println("Wooden horse is rolling.");
    }

    @Override
    public WoodenStructures replicate() {
        System.out.println("Replicating wooden horse.");
        return new WoodenHorse();
    }
}
