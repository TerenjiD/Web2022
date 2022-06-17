package beans;

import java.io.Serializable;

public class Facility implements Serializable {
    private String name;
    private FacilityType facilityType;
    private ContentType contentType;
    private FacilityStatus status;
    private String logo;
    private Location location;
    private String workingHours;
    private String rating;
}
