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
}
