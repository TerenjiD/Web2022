package beans;

import java.io.Serializable;

public class Customer extends User implements Serializable {
    private double points;
    private String customerType;
    private String membership;

    public Customer() {
    }

    public Customer(String username, String password, String name, String lastName, Gender gender, String dateOfBirth, Role role, double points, String customerType, String membership) {
        super(username, password, name, lastName, gender, dateOfBirth, role);
        this.points = points;
        this.customerType = customerType;
        this.membership = membership;
    }

    public double getPoints() {
        return points;
    }

    public void setPoints(double points) {
        this.points = points;
    }

    public String getCustomerType() {
        return customerType;
    }

    public void setCustomerType(String customerType) {
        this.customerType = customerType;
    }

    public String getMembership() {
        return membership;
    }

    public void setMembership(String membership) {
        this.membership = membership;
    }
}
