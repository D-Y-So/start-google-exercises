package farm;

import java.util.ArrayList;
import java.util.List;

public class Farm {
    private final List<Animal> farmAnimals;

    private Farm(int animalAmount) {
        farmAnimals = new ArrayList<>();
        for(int i =0; i<animalAmount; i++){
            acquire();
        }
    }

    private List<Animal> copyAnimalList(List<Animal> inList){
        List<Animal> outList = new ArrayList<>();
        for(Animal anim: inList){
            switch(anim.getClass().getName()){
                case "Farm.Farm.Cat":
                    outList.add(Cat.copyOf((Cat)anim));
                    break;
                case "Farm.Farm.Chicken":
                    outList.add(Chicken.copyOf((Chicken)anim));
                    break;
                case "Farm.Horse":
                    outList.add(Horse.copyOf((Horse)anim));
                    break;
                default:
                    System.out.println("No such animal. Using random Farm.Farm.Cat instead");
                    outList.add(Cat.randomCat());
            }
        }
        return outList;
    }


    public static Farm createFarm(int animalAmount){
        return new Farm(animalAmount);
    }


   public void acquire(){
        farmAnimals.add(AnimalAbstract.randomAnimal());
   }

   public Animal createByMating(String motherId, String fatherId){
        Animal mother = findAnimalById(motherId);
        Animal father = findAnimalById(fatherId);
        if (mother!=null && father!=null){
            return mother.mate(father);
        }
        return null;
   }

    public void addAnimal(Species type, String id, Gender gender, double weight){
        switch(type){
            case CAT:
                farmAnimals.add(Cat.createCat(id,gender,weight));
               break;
            case CHICKEN:
                farmAnimals.add(Chicken.createChicken(id,gender,weight));
                break;
            case HORSE:
                farmAnimals.add(Horse.createHorse(id,gender,weight));
                break;
            default:
                System.out.println("No such animal. Adding random Farm.Farm.Cat instead.");
                farmAnimals.add(Cat.randomCat());
        }
    }

    public Animal findAnimalById(String id){
        for(Animal animal: farmAnimals) {
            if (animal.getId().equals(id)) {
                return animal;
            }
        }
        System.out.println("No animal with id " + id);
        return null;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        String ls = System.getProperty("line.separator");
        stringBuilder.append("Farm.Farm: ");
        stringBuilder.append(ls);
        for (Animal animal : farmAnimals) {
            stringBuilder.append(animal.toString());
            stringBuilder.append(ls);
        }
        return stringBuilder.toString();
    }
}


