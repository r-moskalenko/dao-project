package com.romanm.pis.domain;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
public class User {
    @Id
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name="user_type_id")
    private UserType userType;

    @OneToMany(mappedBy="user")
    private List<Report> reports;

    public User() {
    }

    public User(Long id, String name, UserType userType, List<Report> reports) {
        this.id = id;
        this.name = name;
        this.userType = userType;
        this.reports = reports;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public List<Report> getReports() {
        return reports;
    }

    public void setReports(List<Report> reports) {
        this.reports = reports;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", userType=" + userType +
                ", reports=" + reports +
                '}';
    }
}
