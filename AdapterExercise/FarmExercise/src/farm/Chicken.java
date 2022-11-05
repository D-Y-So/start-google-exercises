package farm;

class Chicken extends AnimalAbstract {

    private static final double MIN_WEIGHT = 0.249; //According to world records
    private static final double MAX_WEIGHT = 10.0;

    private Chicken(String id, Gender gender, double weight){
        super(id,gender,weight);
    }


    public static Chicken createChicken(String id, Gender gender, double weight){
        return new Chicken(id,gender,weight);

    }

    public static Chicken copyOf(Chicken chicken){
        return new Chicken(chicken.id, chicken.gender, chicken.weight);
    }

    public static Chicken randomChicken(){
        return new Chicken(Utility.randomId(), Utility.randomGender(), Utility.randomWeight(0.680388, 21.3));
    }


    @Override
    public void move() {
        System.out.println("Farm.Farm.Chicken is hopping and flapping.");
    }

    @Override
    public Animal mate(Animal partner) {
        if(partner.getClass() == this.getClass() && partner.getGender()!=this.gender) {
            return randomChicken();
        } else {
            System.out.println("Wrong partner. Provide a " + Gender.oppositeOf(partner.getGender()) + " Farm.Farm.Chicken partner.\n Returning the partner Object.");
            return partner;
        }
    }

    @Override
    public String toString() {
        return "Farm.Farm.Chicken{" + "id: " + id + ", gender=" + gender + ", weight=" + weight + '}';
    }
}
