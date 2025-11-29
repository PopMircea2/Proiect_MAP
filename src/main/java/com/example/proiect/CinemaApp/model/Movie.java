package com.example.proiect.CinemaApp.model;
import java.util.List;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "Movie")
public class Movie {
    @Id
    @Column(name = "Id")
    private String id;

    @NotBlank(message = "Title is required")
    @Size(min = 1, max = 200, message = "Title must be between 1 and 200 characters")
    @Column(name = "Title")
    private String title;

    @Positive(message = "Duration must be positive")
    @Column(name = "DurationMin")
    private int DurationMin;

    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Screening> screenings;

    @DecimalMin(value = "0.0", message = "Rating must be at least 0.0")
    @DecimalMax(value = "10.0", message = "Rating must not exceed 10.0")
    @Column(name = "Rating")
    private Double Rating;

    public Movie() {

    }


    public Double getRating() {
        return Rating;
    }

    public void setRating(Double rating) {
        Rating = rating;
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
        return DurationMin;
    }

    public void setDurationMin(int durationMin) {
        DurationMin = durationMin;
    }

    public List<Screening> getScreenings() {
        return screenings;
    }

    public void setScreenings(List<Screening> screenings) {
        this.screenings = screenings;
    }

    public Movie(String id, String title, int durationMin, Double Rating ,List<Screening> screenings) {
        this.id = id;
        this.title = title;
        DurationMin = durationMin;
        this.screenings = screenings;
        this.Rating = Rating;
    }
}
