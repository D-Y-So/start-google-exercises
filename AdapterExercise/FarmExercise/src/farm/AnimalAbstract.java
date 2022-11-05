package farm;

import java.util.concurrent.ThreadLocalRandom;

abstract class AnimalAbstract implements Animal {

    protected String id;
    protected Gender gender;
    protected double weight;

    protected AnimalAbstract(String id, Gender gender, double weight){
        this.id = id;
        this.gender = gender;
        this.weight = weight;
    }
    abstract public void move();
    abstract public Animal mate(Animal partner);

    public Gender getGender(){
        return this.gender;
    }

    public String getId(){
        return this.id;
    }

    public static Animal randomAnimal(){
        Species[] types = Species.values();
        switch(types[ThreadLocalRandom.current().nextInt(0,types.length)]){
            case CAT:
                return Cat.randomCat();
            case CHICKEN:
                return Chicken.randomChicken();
            case HORSE:
                return Horse.randomHorse();
            default:
                System.out.println("No such animal. Using random Farm.Farm.Cat instead");
                return (Cat.randomCat());

        }
    }


}
