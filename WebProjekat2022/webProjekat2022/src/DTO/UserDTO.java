package DTO;

import java.io.Serializable;

public class UserDTO implements Serializable {
    private String username;
    private String password;
    private String name;
    private String lastName;
    private String gender;
    private String dateOfBirth;

    public UserDTO(String username, String password, String name, String lastName, String gender, String dateOfBirth) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.lastName = lastName;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
    }

    public UserDTO() {
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getGender() {
        return gender;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String ToString(){
        return new StringBuffer(this.username).append(";").append(this.password).append(";")
                .append(this.name).append(";").append(this.lastName).append(";").append(this.gender).append(";")
                .append(this.dateOfBirth).toString();
    }
}
