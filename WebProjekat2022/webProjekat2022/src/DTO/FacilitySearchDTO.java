package DTO;

public class FacilitySearchDTO {

    private String name;
    private String type;
    private String location;
    private String rating;

    private String sortType;
    private String sortBy;

    private String filterBy;

    public FacilitySearchDTO(String name, String type, String location, String rating, String sortType, String sortBy, String filterBy) {
        this.name = name;
        this.type = type;
        this.location = location;
        this.rating = rating;
        this.sortType = sortType;
        this.sortBy = sortBy;
        this.filterBy = filterBy;
    }

    public FacilitySearchDTO() {}

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public void setSortType(String sortType) {
        this.sortType = sortType;
    }

    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }

    public void setFilterBy(String filterBy) {
        this.filterBy = filterBy;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getLocation() {
        return location;
    }

    public String getRating() {
        return rating;
    }

    public String getSortType() {
        return sortType;
    }

    public String getSortBy() {
        return sortBy;
    }

    public String getFilterBy() {
        return filterBy;
    }
}
