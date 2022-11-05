package farm;

class Cat extends AnimalAbstract {

    private static final double MIN_WEIGHT = 0.68; //According to world records
    private static final double MAX_WEIGHT = 21.3;
    private Cat(String id, Gender gender, double weight){
        super(id,gender,weight);
    }

    public static Cat randomCat(){
        return new Cat(Utility.randomId(), Utility.randomGender(), Utility.randomWeight(MIN_WEIGHT, MAX_WEIGHT));
    }


    public static Cat createCat(String id, Gender gender, double weight){
        return new Cat(id,gender,weight);

    }

    public static Cat copyOf(Cat cat){
        return new Cat(cat.id, cat.gender, cat.weight);
    }

    @Override
    public void move() {
        System.out.println("Farm.Farm.Cat is prowling.");
    }

    @Override
    public Animal mate(Animal partner) {
        if(partner.getClass() == this.getClass() && partner.getGender()!=this.gender) {
            return randomCat();
        } else {
            System.out.println("Wrong partner. Provide a " + Gender.oppositeOf(partner.getGender()) + " Farm.Farm.Cat partner.\n Returning the partner.");
            return partner;
        }
    }

    @Override
    public String toString() {
        return "Farm.Farm.Cat{" + "id: " + id + ", gender=" + gender + ", weight=" + weight + '}';
    }
}
