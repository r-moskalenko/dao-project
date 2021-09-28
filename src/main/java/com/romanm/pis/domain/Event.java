package com.romanm.pis.domain;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "events")
public class Event {
    @Id
    public Long id;

    @Column(name = "date_time")
    private LocalDateTime dateAndTime;

    @Column(name = "description")
    public String description;

    @OneToMany(mappedBy="event")
    private List<Report> reports;

    public Event() {
    }

    public Event(Long id, LocalDateTime dateAndTime, String description, List<Report> reports) {
        this.id = id;
        this.dateAndTime = dateAndTime;
        this.description = description;
        this.reports = reports;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDateAndTime() {
        return dateAndTime;
    }

    public void setDateAndTime(LocalDateTime dateAndTime) {
        this.dateAndTime = dateAndTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
                ", description='" + description + '\'' +
                ", reports=" + reports +
                '}';
    }
}
