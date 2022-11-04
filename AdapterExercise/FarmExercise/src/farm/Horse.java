package farm;

class Horse extends AnimalAbstract {

    private static final double MIN_WEIGHT = 26.0; //According to world records
    private static final double MAX_WEIGHT = 1524.0;

    private Horse(String id, Gender gender, double weight){
        super(id,gender,weight);
    }

    public static Horse createHorse(String id, Gender gender, double weight){
        return new Horse(id,gender,weight);

    }

    public static Horse copyOf(Horse horse){
        return new Horse(horse.id, horse.gender, horse.weight);
    }

    public static Horse randomHorse(){
        return new Horse(Utility.randomId(), Utility.randomGender(), Utility.randomWeight(0.680388, 21.3));
    }

    @Override
    public void move() {
        System.out.println("Farm.Horse is galloping.");
    }

    @Override
    public Animal mate(Animal partner) {
        if(partner.getClass() == this.getClass() && partner.getGender()!=this.gender) {
            return randomHorse();
        } else {
            System.out.println("Wrong partner. Provide a " + Gender.oppositeOf(partner.getGender()) + " Farm.Farm.Chicken partner.\n Returning the partner Object.");
            return partner;
        }
    }

    @Override
    public String toString() {
        return "Farm.Horse{" + "id: " + id + ", gender=" + gender + ", weight=" + weight + '}';
    }
}
