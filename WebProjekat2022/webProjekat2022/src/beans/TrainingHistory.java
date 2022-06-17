package beans;

import java.io.Serializable;
import java.util.Date;

public class TrainingHistory implements Serializable {
    private Date applicationDate;
    private Training training;
    private Customer customer;
    private String trainer;
}
