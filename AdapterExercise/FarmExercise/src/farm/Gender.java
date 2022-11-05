package farm;

public enum Gender {
    FEMALE,
    MALE;

    public static Gender oppositeOf(Gender gender){
        return gender==FEMALE ?  MALE: FEMALE;
    }
}
