package beans;

import java.io.Serializable;

public class User implements Serializable {

    private String username;
    private String password;
    private String name;
    private String lastName;
    private Gender gender;
    private String dateOfBirth;
    private Role role;

    public User (){
        super();
    }

    public User(String username, String password, String name, String lastName, Gender gender,String dateOfBirth ,Role role){
        super();
        this.username=username;
        this.password=password;
        this.name= name;
        this.lastName= lastName;
        this.gender=gender;
        this.dateOfBirth= dateOfBirth;
        this.role=role;
    }

    public String getUsername(){
        return username;
    }

    public void setUsername(String username){
        this.username=username;
    }

    public String getPassword(){
        return password;
    }

    public void setPassword(String password){
        this.password=password;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name=name;
    }

    public String getLastName(){
        return lastName;
    }

    public void setLastName(String lastName){
        this.lastName=lastName;
    }

    public Gender getGender(){
        return gender;
    }

    public void setGender(Gender gender){
        this.gender=gender;
    }

    public String getDateOfBirth(){
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth){
        this.dateOfBirth=dateOfBirth;
    }

    public Role getRole(){
        return role;
    }

    public void setRole(Role role){
        this.role=role;
    }

    public String ToString(){
        return new StringBuffer(this.username).append(";").append(this.password).append(";")
                .append(this.name).append(";").append(this.lastName).append(";").append(this.gender.toString()).append(";")
                .append(this.dateOfBirth).append(";").append(this.role.toString()).toString();
    }
}
