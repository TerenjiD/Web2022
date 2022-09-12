package beans;

import java.io.Serializable;
import java.time.LocalDate;


public class TrainingHistory implements Serializable {
    private String id;
    private LocalDate applicationDate;
    private Content training;
    private Customer customer;
    private String coach;

    public TrainingHistory(LocalDate applicationDate, Content training, Customer customer, String coach) {
        this.applicationDate = applicationDate;
        this.training = training;
        this.customer = customer;
        this.coach = coach;
    }

    public TrainingHistory(String id, LocalDate applicationDate, Content training, Customer customer, String coach) {
        this.id = id;
        this.applicationDate = applicationDate;
        this.training = training;
        this.customer = customer;
        this.coach = coach;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public TrainingHistory() {
    }

    public LocalDate getApplicationDate() {
        return applicationDate;
    }

    public void setApplicationDate(LocalDate applicationDate) {
        this.applicationDate = applicationDate;
    }

    public Content getTraining() {
        return training;
    }

    public void setTraining(Content training) {
        this.training = training;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getCoach() {
        return coach;
    }

    public void setCoach(String coach) {
        this.coach = coach;
    }

}
