import farm.*;

public class Main {
    public static void main(String[] args) {

        WoodenStructures woodenHorse = new WoodenHorse();
        Animal woodenStructureAdapter = new WoodenStructureToAnimalAdapter(woodenHorse);
        woodenStructureAdapter.move();
        Animal newWoodenHorse = woodenStructureAdapter.mate(new WoodenStructureToAnimalAdapter(woodenHorse));


        /*
        Farm farm = Farm.createFarm(20);
        System.out.println(farm);

        System.out.println("Enter animal id you want to move: ");
        Scanner sc = new Scanner(System.in);
        String animalId = sc.nextLine();
        Farmer.makeAnimalMove(Farmer.requestAnimalFromFarm(farm,animalId));

        System.out.println("\nEnter animal ids you want to mate: ");
        System.out.println("Enter father id: ");
        animalId = sc.nextLine();
        System.out.println("Enter mother id: ");
        String motherId = sc.nextLine();
        System.out.println("The result is:\n"+ farm.createByMating(animalId,motherId));

         */

    }
}
