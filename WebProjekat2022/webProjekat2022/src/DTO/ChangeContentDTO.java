package DTO;

import beans.ContentType;

public class ChangeContentDTO {
    private String nameID;
    private String name;
    private ContentType type;
    private String logo;
    private String description;
    private String duration;

    public ChangeContentDTO(String nameID, String name, ContentType type, String logo, String description, String duration) {
        this.nameID = nameID;
        this.name = name;
        this.type = type;
        this.logo = logo;
        this.description = description;
        this.duration = duration;
    }

    public ChangeContentDTO() {
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
}
