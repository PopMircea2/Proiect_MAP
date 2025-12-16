package com.example.proiect.CinemaApp.model;
import java.util.List;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "Movie")
public class Movie {
    @Id
    @NotBlank(message = "ID is required")
    @Column(name = "Id")
    private String id;

    @NotBlank(message = "Title is required")
    @Size(min = 1, max = 200, message = "Title must be between 1 and 200 characters")
    @Column(name = "Title")
    private String title;

    @Positive(message = "Duration must be positive")
    @Column(name = "DurationMin")
    private int durationMin;

    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Screening> screenings;

    @DecimalMin(value = "0.0", message = "Rating must be at least 0.0")
    @DecimalMax(value = "10.0", message = "Rating must not exceed 10.0")
    @Column(name = "Rating")
    private Double rating;

    public Movie() {

    }


    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getDurationMin() {
        return durationMin;
    }

    public void setDurationMin(int durationMin) {
        this.durationMin = durationMin;
    }

    public List<Screening> getScreenings() {
        return screenings;
    }

    public void setScreenings(List<Screening> screenings) {
        this.screenings = screenings;
    }

    public Movie(String id, String title, int durationMin, Double rating ,List<Screening> screenings) {
        this.id = id;
        this.title = title;
        this.durationMin = durationMin;
        this.screenings = screenings;
        this.rating = rating;
    }
}
