package com.example.proiect.CinemaApp.model;

import java.util.List;
import jakarta.persistence.*;

@Entity
@Table(name = "Theatre")
public class Theatre {
    @Id
    private String id;
    private String name;
    private String city;

    @Transient
    private List<Hall> halls;

    public Theatre() {}

    public String getId() {
        return id;
    }
    public List<Hall> getHalls() {
        return halls;
    }
    public String getCity() {
        return city;
    }
    public String getName() {
        return name;
    }

    public Theatre(String id, String name, String city, List<Hall> halls) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.halls = halls;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setHalls(List<Hall> halls) {
        this.halls = halls;
    }
}
