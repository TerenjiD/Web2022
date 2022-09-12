package beans;

import java.io.Serializable;

public class Manager extends User implements Serializable {
    public String facility;

    public Manager(String facility) {
        this.facility = facility;
    }

    public Manager(String username, String password, String name, String lastName, Gender gender, String dateOfBirth, Role role, String facility) {
        super(username, password, name, lastName, gender, dateOfBirth, role);
        this.facility = facility;
    }

    public Manager() {
    }

    public String getFacility() {
        return facility;
    }


    public void setFacility(String facility) {
        this.facility = facility;
    }
}
