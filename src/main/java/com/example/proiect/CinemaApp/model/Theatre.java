package com.example.proiect.CinemaApp.model;

import java.util.List;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "Theatre")
public class Theatre {
    @Id
    @NotBlank(message = "ID is required")
    @Column(name = "Id")
    private String id;

    @NotBlank(message = "Name is required")
    @Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters")
    @Column(name = "Name")
    private String name;

    @NotBlank(message = "City is required")
    @Size(min = 2, max = 100, message = "City must be between 2 and 100 characters")
    @Column(name = "City")
    private String city;

    @OneToMany(mappedBy = "theatre", cascade = CascadeType.ALL, orphanRemoval = true)
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
