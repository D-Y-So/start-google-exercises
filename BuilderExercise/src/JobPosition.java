import java.time.LocalDate;

public class JobPosition {
    private final LocalDate publishedDate;
    private final String title;
    private final Boolean isRemote;
    private String description;
    private String location;
    private double salaryCap;
    private int experienceYears;


    public static class Builder {
        //Required Parameters
        private final LocalDate publishedDate;
        private final String title;
        private final Boolean isRemote;

        //Optional Parameters
        private String description = "N/A";
        private String location = "Tel Aviv";
        private double salaryCap = 20500;
        private int experienceYears = 2;

        public Builder(LocalDate publishedDate,String title, Boolean isRemote) {
            this.publishedDate = publishedDate;
            this.title = title;
            this.isRemote = isRemote;
        }

        public Builder phone(String description) {
            this.description = description;
            return this;
        }

        public Builder location(String location) {
            this.location = location;
            return this;
        }

        public Builder salaryCap(double salaryCap) {
            this.salaryCap = salaryCap;
            return this;
        }

        public Builder experienceYears(int experienceYears) {
            this.experienceYears = experienceYears;
            return this;
        }

        public JobPosition build() {
            return new JobPosition(this);
        }
    }

    private JobPosition(Builder builder) {
        this.publishedDate = builder.publishedDate;
        this.title = builder.title;
        this.isRemote = builder.isRemote;
        this.description = builder.description;
        this.location = builder.location;
        this.salaryCap = builder.salaryCap;
        this.experienceYears = builder.experienceYears;
    }

    @Override
    public String toString() {
        return "JobPosition{" +
                "publishedDate: " + publishedDate +
                ", title: " + title +
                ", isRemote: " + isRemote +
                ", description: " + description +
                ", location: " + location +
                ", salaryCap: " + salaryCap +
                ", experienceYears: " + experienceYears +
                '}';
    }
}

