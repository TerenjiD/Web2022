package beans;

import java.io.Serializable;
import java.util.Date;

public class Membership implements Serializable {
    private String id;
    private MembershipType type;
    private Date paymentDate;
    private Date expirationDate;
    private int price;
    private String customer;
    private MembershipStatus status;
    private String appointmentNumber;
}
