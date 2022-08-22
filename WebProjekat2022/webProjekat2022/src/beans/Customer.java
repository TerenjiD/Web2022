package beans;

import java.io.Serializable;

public class Customer extends User implements Serializable {
    private int points;
    private CustomerType customerType;
    private Membership membership;

    public Customer() {
    }

    public Customer(String username, String password, String name, String lastName, Gender gender, String dateOfBirth, Role role, int points, CustomerType customerType, Membership membership) {
        super(username, password, name, lastName, gender, dateOfBirth, role);
        this.points = points;
        this.customerType = customerType;
        this.membership = membership;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public CustomerType getCustomerType() {
        return customerType;
    }

    public void setCustomerType(CustomerType customerType) {
        this.customerType = customerType;
    }

    public Membership getMembership() {
        return membership;
    }

    public void setMembership(Membership membership) {
        this.membership = membership;
    }
}
