package beans;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Membership implements Serializable {
    private String id;
    private String facility;
    private MembershipType type;
    private LocalDateTime paymentDate;
    private LocalDateTime expirationDate;
    private int price;
    private String customer;
    private MembershipStatus status;
    private String appointmentNumber;

    public Membership(String id, MembershipType type, LocalDateTime paymentDate, LocalDateTime expirationDate,
                      int price, String customer, MembershipStatus status, String appointmentNumber) {
        this.id = id;
        this.type = type;
        this.paymentDate = paymentDate;
        this.expirationDate = expirationDate;
        this.price = price;
        this.customer = customer;
        this.status = status;
        this.appointmentNumber = appointmentNumber;
    }

    public Membership(String id, String facility, MembershipType type, LocalDateTime paymentDate,
                      LocalDateTime expirationDate, int price, String customer, MembershipStatus status, String appointmentNumber) {
        this.id = id;
        this.facility = facility;
        this.type = type;
        this.paymentDate = paymentDate;
        this.expirationDate = expirationDate;
        this.price = price;
        this.customer = customer;
        this.status = status;
        this.appointmentNumber = appointmentNumber;
    }

    public String getFacility() {
        return facility;
    }

    public void setFacility(String facility) {
        this.facility = facility;
    }

    public Membership() {
    }

    public Membership(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public MembershipType getType() {
        return type;
    }

    public void setType(MembershipType type) {
        this.type = type;
    }

    public LocalDateTime getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDateTime paymentDate) {
        this.paymentDate = paymentDate;
    }

    public LocalDateTime getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDateTime expirationDate) {
        this.expirationDate = expirationDate;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public MembershipStatus getStatus() {
        return status;
    }

    public void setStatus(MembershipStatus status) {
        this.status = status;
    }

    public String getAppointmentNumber() {
        return appointmentNumber;
    }

    public void setAppointmentNumber(String appointmentNumber) {
        this.appointmentNumber = appointmentNumber;
    }
}
