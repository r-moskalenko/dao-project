package com.romanm.pis.domain;

import javax.persistence.*;

@Entity
@Table(name = "reports")
public class Report {
    @Id
    private Long id;

    @Column(name = "topic")
    private String topic;

    @Column(name = "text")
    private String text;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name="event_id")
    private Event event;

    public Report() {
    }

    public Report(Long id, String topic, User user, Event event) {
        this.id = id;
        this.topic = topic;
        this.user = user;
        this.event = event;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    @Override
    public String toString() {
        return "Report{" +
                "id=" + id +
                ", topic='" + topic + '\'' +
                ", user=" + user +
                ", event=" + event +
                '}';
    }
}
