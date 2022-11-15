public class FootballPlayer {

    private final String name;
    private final int jerseyNumber;
    private final float grade;
    private final FootballPlayerPosition position;

    private FootballPlayer(String name, int jerseyNumber, float grade, FootballPlayerPosition position) {
        this.name = name;
        this.jerseyNumber = jerseyNumber;
        this.grade = grade;
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public int getJerseyNumber() {
        return jerseyNumber;
    }

    public float getGrade() {
        return grade;
    }

    public FootballPlayerPosition getPosition() {
        return position;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Name: ");
        stringBuilder.append(name);
        stringBuilder.append(", Jersey Number: ");
        stringBuilder.append(jerseyNumber);
        stringBuilder.append(", Grade: ");
        stringBuilder.append(grade);
        stringBuilder.append(", Position: ");
        stringBuilder.append(position);
        return stringBuilder.toString();
    }

    public static FootballPlayer createFootballPlayer(String name, int jerseyNumber, float grade, FootballPlayerPosition position){
        return new FootballPlayer(name,jerseyNumber,grade,position);
    }

}

