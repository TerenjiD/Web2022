package beans;

import java.io.Serializable;

public class Coach extends User implements Serializable {
    public String trainingHistory;

    public Coach(String trainingHistory) {
        this.trainingHistory = trainingHistory;
    }

    public Coach(String username, String password, String name, String lastName, Gender gender, String dateOfBirth, Role role, String trainingHistory) {
        super(username, password, name, lastName, gender, dateOfBirth, role);
        this.trainingHistory = trainingHistory;
    }

    public Coach() {
    }

    public String getTrainingHistory() {
        return trainingHistory;
    }

    public void setTrainingHistory(String trainingHistory) {
        this.trainingHistory = trainingHistory;
    }
}
