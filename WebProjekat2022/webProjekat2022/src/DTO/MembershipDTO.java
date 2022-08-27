package DTO;

import beans.MembershipStatus;
import beans.MembershipType;

import java.util.Date;

public class MembershipDTO {
    private String id;
    private String facility;
    private String type;
    private int price;
    private String customer;
    private String status;
    private String appointmentNumber;

    public MembershipDTO(String id, String type, int price, String customer, String status, String appointmentNumber) {
        this.id = id;
        this.type = type;
        this.price = price;
        this.customer = customer;
        this.status = status;
        this.appointmentNumber = appointmentNumber;
    }

    public MembershipDTO(String id, String facility, String type, int price, String customer, String status, String appointmentNumber) {
        this.id = id;
        this.facility = facility;
        this.type = type;
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

    public MembershipDTO() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAppointmentNumber() {
        return appointmentNumber;
    }

    public void setAppointmentNumber(String appointmentNumber) {
        this.appointmentNumber = appointmentNumber;
    }
}
