package beans;

import java.io.Serializable;
import java.util.Comparator;

public class Facility implements Serializable,Comparable<Facility> {
    private String name;
    private FacilityType facilityType;
    private ContentType contentType;
    private FacilityStatus status;
    private String logo;
    private Location location;
    private String workingHours;
    private String rating;

    public Facility(String name, FacilityType facilityType, ContentType contentType,
                    FacilityStatus facilityStatus, String logo, Location location,String workingHours,
                    String rating){
        this.name=name;
        this.facilityType=facilityType;
        this.contentType=contentType;
        this.status=facilityStatus;
        this.logo=logo;
        this.location=location;
        this.workingHours=workingHours;
        this.rating=rating;
    }

    public String getName(){
        return name;
    }

    public FacilityType getFacilityType(){
        return facilityType;
    }

    public Location getLocation(){
        return location;
    }

    public String getRating(){
        return rating;
    }

    public FacilityStatus getStatus(){
        return status;
    }

    @Override
    public int compareTo(Facility o) {
        return this.getStatus().compareTo(o.getStatus());
    }
}
