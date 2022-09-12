package beans;

import java.io.Serializable;

public class  CustomerType implements Serializable {

    private String id;
    private String name;
    private int discount;
    private int requiredPoints;

    public CustomerType(String name, int discount, int requiredPoints) {
        this.name = name;
        this.discount = discount;
        this.requiredPoints = requiredPoints;
    }

    public CustomerType() {
    }

    public CustomerType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public int getRequiredPoints() {
        return requiredPoints;
    }

    public void setRequiredPoints(int requiredPoints) {
        this.requiredPoints = requiredPoints;
    }
}
