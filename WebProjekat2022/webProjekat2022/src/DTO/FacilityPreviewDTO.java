package DTO;

public class FacilityPreviewDTO {
    private String name;
    private String facilityType;
    private String contentType;
    private String status;
    private String logo;
    private String latitude;
    private String longitude;
    private String street;
    private String number;
    private String city;
    private String country;
    private String workingHours;
    private String rating;
    private String managerName;
    private String managerLastname;

    public FacilityPreviewDTO(String name, String facilityType, String contentType, String status, String logo, String latitude, String longitude, String street, String number, String city, String country, String workingHours, String rating, String managerName, String managerLastname) {
        this.name = name;
        this.facilityType = facilityType;
        this.contentType = contentType;
        this.status = status;
        this.logo = logo;
        this.latitude = latitude;
        this.longitude = longitude;
        this.street = street;
        this.number = number;
        this.city = city;
        this.country = country;
        this.workingHours = workingHours;
        this.rating = rating;
        this.managerName = managerName;
        this.managerLastname = managerLastname;
    }

    public FacilityPreviewDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFacilityType() {
        return facilityType;
    }

    public void setFacilityType(String facilityType) {
        this.facilityType = facilityType;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getWorkingHours() {
        return workingHours;
    }

    public void setWorkingHours(String workingHours) {
        this.workingHours = workingHours;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    public String getManagerLastname() {
        return managerLastname;
    }

    public void setManagerLastname(String managerLastname) {
        this.managerLastname = managerLastname;
    }
}
