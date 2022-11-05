package farm;

public interface Animal {
    void move();

    Animal mate(Animal partner);

    String getId();

    Gender getGender();
}
