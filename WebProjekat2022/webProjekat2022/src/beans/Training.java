package beans;

import java.io.Serializable;

public class Training implements Serializable {
    private String name;
    private TrainingType type;
    private String description;
    private String picture;
    private Facility sportsFacility;
    private int duration;
    private String trainer;

    public Training(String name, TrainingType type, String description, String picture, Facility sportsFacility, int duration, String trainer) {
        this.name = name;
        this.type = type;
        this.description = description;
        this.picture = picture;
        this.sportsFacility = sportsFacility;
        this.duration = duration;
        this.trainer = trainer;
    }

    public Training() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TrainingType getType() {
        return type;
    }

    public void setType(TrainingType type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Facility getSportsFacility() {
        return sportsFacility;
    }

    public void setSportsFacility(Facility sportsFacility) {
        this.sportsFacility = sportsFacility;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getTrainer() {
        return trainer;
    }

    public void setTrainer(String trainer) {
        this.trainer = trainer;
    }
}
