package DTO;

public class ContentDTO {
    private String nameID;
    private String facilityName;
    private String name;
    private String type;
    private String coach;
    private String logo;
    private String description;
    private String duration;

    private String startTime;
    private String endTime;

    public ContentDTO(String nameID, String facilityName, String name, String type, String logo, String description, String duration) {
        this.nameID = nameID;
        this.facilityName = facilityName;
        this.name = name;
        this.type = type;
        this.logo = logo;
        this.description = description;
        this.duration = duration;
    }

    public ContentDTO(String nameID, String facilityName, String name, String type, String coach, String logo, String description, String duration) {
        this.nameID = nameID;
        this.facilityName = facilityName;
        this.name = name;
        this.type = type;
        this.coach = coach;
        this.logo = logo;
        this.description = description;
        this.duration = duration;
    }

    public ContentDTO(String nameID, String facilityName, String name, String type, String coach, String logo,
                      String description, String duration, String startTime, String endTime) {
        this.nameID = nameID;
        this.facilityName = facilityName;
        this.name = name;
        this.type = type;
        this.coach = coach;
        this.logo = logo;
        this.description = description;
        this.duration = duration;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public ContentDTO() {
    }

    public String getCoach() {
        return coach;
    }

    public void setCoach(String coach) {
        this.coach = coach;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getNameID() {
        return nameID;
    }

    public void setNameID(String nameID) {
        this.nameID = nameID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getFacilityName() {
        return facilityName;
    }

    public void setFacilityName(String facilityName) {
        this.facilityName = facilityName;
    }
}
