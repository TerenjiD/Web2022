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

    public Content(String nameID, String facilityName, String name, ContentType type, String logo, String description, String duration) {
        this.nameID = nameID;
        this.facilityName = facilityName;
        this.name = name;
        this.type = type;
        this.logo = logo;
        this.description = description;
        this.duration = duration;
    }

    public Content(String nameID, String facilityName, String name, ContentType type, String coachID, String logo, String description, String duration) {
        this.nameID = nameID;
        this.facilityName = facilityName;
        this.name = name;
        this.type = type;
        this.coachID = coachID;
        this.logo = logo;
        this.description = description;
        this.duration = duration;
    }

    public Content() {
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
