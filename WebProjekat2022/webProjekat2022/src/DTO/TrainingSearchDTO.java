package DTO;

public class TrainingSearchDTO {

    private String name;
    private String cost;

    public TrainingSearchDTO(String name, String cost, String date, String sortType, String sortBy, String filterBy) {
        this.name = name;
        this.cost = cost;
        this.date = date;
        this.sortType = sortType;
        this.sortBy = sortBy;
        this.filterBy = filterBy;
    }

    public TrainingSearchDTO(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSortType() {
        return sortType;
    }

    public void setSortType(String sortType) {
        this.sortType = sortType;
    }

    public String getSortBy() {
        return sortBy;
    }

    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }

    public String getFilterBy() {
        return filterBy;
    }

    public void setFilterBy(String filterBy) {
        this.filterBy = filterBy;
    }

    private String date;

    private String sortType;
    private String sortBy;

    private String filterBy;
}
