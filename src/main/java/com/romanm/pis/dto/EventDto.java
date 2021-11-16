package com.romanm.pis.dto;

public class EventDto {
    private String shortDescription;

    private String dateAndTime;

    public String longDescription;

    public EventDto(String shortDescription, String dateAndTime, String longDescription) {
        this.shortDescription = shortDescription;
        this.dateAndTime = dateAndTime;
        this.longDescription = longDescription;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getDateAndTime() {
        return dateAndTime;
    }

    public void setDateAndTime(String dateAndTime) {
        this.dateAndTime = dateAndTime;
    }

    public String getLongDescription() {
        return longDescription;
    }

    public void setLongDescription(String longDescription) {
        this.longDescription = longDescription;
    }
}
