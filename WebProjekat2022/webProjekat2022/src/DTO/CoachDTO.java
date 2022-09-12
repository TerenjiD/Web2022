package DTO;

public class CoachDTO {
    private String username;
    private String password;
    private String name;
    private String lastName;
    private String gender;
    private String dateOfBirth;
    private String role;
    private String trainingHistory;

    public CoachDTO(String username, String password, String name, String lastName, String gender, String dateOfBirth, String role, String trainingHistory) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.lastName = lastName;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.role = role;
        this.trainingHistory = trainingHistory;
    }

    public CoachDTO() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getTrainingHistory() {
        return trainingHistory;
    }

    public void setTrainingHistory(String trainingHistory) {
        this.trainingHistory = trainingHistory;
    }
}
