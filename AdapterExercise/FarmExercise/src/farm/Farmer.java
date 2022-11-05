package farm;

public class Farmer {

    public static Animal requestAnimalFromFarm(Farm farm, String id){
       return farm.findAnimalById(id);
    }

    public static void makeAnimalMove(Animal animal){
        if(animal!=null){
            animal.move();
        }else{
            System.out.println("Invalid Animal.");
        }
    }
}
