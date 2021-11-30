package com.romanm.pis.domain;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "events")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(name = "short_description")
    private String shortDescription;

    @Column(name = "date_time")
    private LocalDateTime dateAndTime;

    @Column(name = "long_description")
    public String longDescription;

    @OneToMany(mappedBy="event")
    private List<Report> reports;

    public Event() {
    }

    public Event(Long id, String shortDescription, LocalDateTime dateAndTime, String longDescription, List<Report> reports) {
        this.id = id;
        this.shortDescription = shortDescription;
        this.dateAndTime = dateAndTime;
        this.longDescription = longDescription;
        this.reports = reports;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public LocalDateTime getDateAndTime() {
        return dateAndTime;
    }

    public void setDateAndTime(LocalDateTime dateAndTime) {
        this.dateAndTime = dateAndTime;
    }

    public String getLongDescription() {
        return longDescription;
    }

    public void setLongDescription(String description) {
        this.longDescription = description;
    }

    public List<Report> getReports() {
        return reports;
    }

    public void setReports(List<Report> reports) {
        this.reports = reports;
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", dateAndTime=" + dateAndTime +
                ", description='" + longDescription + '\'' +
                '}';
    }
}
