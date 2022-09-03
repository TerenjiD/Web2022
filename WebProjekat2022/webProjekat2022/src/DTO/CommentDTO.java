package DTO;

public class CommentDTO {
    int id;
    String facilityID;
    String customerID;
    String commentText;
    int stars;
    int available;
    int isDeleted;

    public CommentDTO() {
    }



    public CommentDTO(int id, String facilityID, String customerID, String commentText, int stars, int available, int isDeleted) {
        this.id = id;
        this.facilityID = facilityID;
        this.customerID = customerID;
        this.commentText = commentText;
        this.stars = stars;
        this.available = available;
        this.isDeleted = isDeleted;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFacilityID() {
        return facilityID;
    }

    public void setFacilityID(String facilityID) {
        this.facilityID = facilityID;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    public int getAvailable() {
        return available;
    }

    public void setAvailable(int available) {
        this.available = available;
    }

    public int getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(int isDeleted) {
        this.isDeleted = isDeleted;
    }
}
