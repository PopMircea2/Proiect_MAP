package com.example.proiect.CinemaApp.model;

import java.util.List;

public class Theatre {
    private String id;
    private String name;
    private String city;
    private List<Hall> halls;

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

    public Theatre() {}
    public Theatre(String id, String name, String city, List<Hall> halls) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.halls = halls;
    }
}
