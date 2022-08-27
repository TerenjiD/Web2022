package DTO;

import beans.Content;
import beans.ContentType;
import beans.Customer;

import java.time.LocalDate;

public class TrainingHistoryDTO {
    private String id;
    private String applicationDate;
    private String nameID;
    private String facilityName;
    private String name;
    private ContentType type;
    private String logo;
    private String description;
    private String duration;
    private String customer;
    private String coach;

    public TrainingHistoryDTO(String id, String applicationDate, String nameID, String facilityName, String name,
                              ContentType type, String logo, String description, String duration, String customer,
                              String coach) {
        this.id = id;
        this.applicationDate = applicationDate;
        this.nameID = nameID;
        this.facilityName = facilityName;
        this.name = name;
        this.type = type;
        this.logo = logo;
        this.description = description;
        this.duration = duration;
        this.customer = customer;
        this.coach = coach;
    }

    public TrainingHistoryDTO() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getApplicationDate() {
        return applicationDate;
    }

    public void setApplicationDate(String applicationDate) {
        this.applicationDate = applicationDate;
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

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getCoach() {
        return coach;
    }

    public void setCoach(String coach) {
        this.coach = coach;
    }
}
