package DTO;

public class UsersSearchDTO {

    private String name;
    private String surname;
    private String username;

    private String sortType;
    private String sortBy;

    private String filterBy;

    public UsersSearchDTO(String name, String surname, String username, String sortType, String sortBy, String filterBy) {
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.sortType = sortType;
        this.sortBy = sortBy;
        this.filterBy = filterBy;
    }

    public UsersSearchDTO(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
}
