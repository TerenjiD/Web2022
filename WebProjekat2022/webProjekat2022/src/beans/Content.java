package beans;

public class Content {
    private String nameID;
    private String facilityName;
    private String name;
    private ContentType type;
    private String coachID;
    private String logo;
    private String description;
    private String duration;

    private String startTime;
    private String endTime;

    private int isDeleted;


    public Content(String nameID, String facilityName, String name, ContentType type, String coachID, String logo,
                   String description, String duration, int isDeleted) {
        this.nameID = nameID;
        this.facilityName = facilityName;
        this.name = name;
        this.type = type;
        this.coachID = coachID;
        this.logo = logo;
        this.description = description;
        this.duration = duration;
        this.isDeleted = isDeleted;
    }

    public Content(String nameID, String facilityName, String name, ContentType type, String coachID, String logo,
                   String description, String duration, String startTime, String endTime, int isDeleted) {
        this.nameID = nameID;
        this.facilityName = facilityName;
        this.name = name;
        this.type = type;
        this.coachID = coachID;
        this.logo = logo;
        this.description = description;
        this.duration = duration;
        this.startTime = startTime;
        this.endTime = endTime;
        this.isDeleted = isDeleted;
    }

    public Content() {
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

    public int getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(int isDeleted) {
        this.isDeleted = isDeleted;
    }

    public String getNameID() {
        return nameID;
    }

    public void setNameID(String nameID) {
        this.nameID = nameID;
    }

    public String getFacilityName() {
        return facilityName;
    }

    public void setFacilityName(String facilityName) {
        this.facilityName = facilityName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ContentType getType() {
        return type;
    }

    public void setType(ContentType type) {
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

    public String getCoachID() {
        return coachID;
    }

    public void setCoachID(String coachID) {
        this.coachID = coachID;
    }
}
