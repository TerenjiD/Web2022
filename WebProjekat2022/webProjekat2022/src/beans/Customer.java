package beans;

import java.io.Serializable;

public class Customer extends User implements Serializable {
    private int points;
    private CustomerType customerType;
    private Membership membership;
}
