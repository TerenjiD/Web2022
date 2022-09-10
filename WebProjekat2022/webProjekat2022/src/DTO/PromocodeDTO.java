package DTO;

import java.time.LocalDate;

public class PromocodeDTO {
    private String name;
    private String startTime;
    private String endTime;
    private String numberOfCode;
    private String percent;

    public PromocodeDTO(String idNum, String name, String startTime, String endTime, String numberOfCode, String percent, String isDeleted) {
        this.name = name;
        this.startTime = startTime;
        this.endTime = endTime;
        this.numberOfCode = numberOfCode;
        this.percent = percent;
    }

    public PromocodeDTO() {
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getNumberOfCode() {
        return numberOfCode;
    }

    public void setNumberOfCode(String numberOfCode) {
        this.numberOfCode = numberOfCode;
    }

    public String getPercent() {
        return percent;
    }

    public void setPercent(String percent) {
        this.percent = percent;
    }

}
