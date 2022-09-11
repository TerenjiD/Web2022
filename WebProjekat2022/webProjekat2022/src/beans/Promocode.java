package beans;

import java.time.LocalDate;

public class Promocode {
    private int idNum;
    private String name;
    private LocalDate startTime;
    private LocalDate endTime;
    private int numberOfCode;
    private int percent;
    private int isDeleted;

    public Promocode() {
    }

    public Promocode(int id, String name, LocalDate startTime, LocalDate endTime, int numberOfCode, int percent, int isDeleted) {
        this.idNum = id;
        this.name = name;
        this.startTime = startTime;
        this.endTime = endTime;
        this.numberOfCode = numberOfCode;
        this.percent = percent;
        this.isDeleted = isDeleted;
    }

    public int getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(int isDeleted) {
        this.isDeleted = isDeleted;
    }

    public int getId() {
        return idNum;
    }

    public void setId(int id) {
        this.idNum = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDate startTime) {
        this.startTime = startTime;
    }

    public LocalDate getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDate endTime) {
        this.endTime = endTime;
    }

    public int getNumberOfCode() {
        return numberOfCode;
    }

    public void setNumberOfCode(int numberOfCode) {
        this.numberOfCode = numberOfCode;
    }

    public int getPercent() {
        return percent;
    }

    public void setPercent(int percent) {
        this.percent = percent;
    }
}
