package com.example.proiect.CinemaApp.model;
import java.util.Date;
import java.util.List;
import jakarta.persistence.*;

@Entity
@Table(name = "Movie")
public class Movie {
    @Id
    @Column(name = "Id")
    private String id;

    @Column(name = "Title")
    private String title;

    @Column(name = "DurationMin")
    private int DurationMin;

    @Transient
    private List<Screening> screenings;

    @Column(name = "Rating")
    private double Rating;

    @Transient
    private Date date;

    public Movie() {

    }


    public double getRating() {
        return Rating;
    }

    public void setRating(double rating) {
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

    public Movie(String id, String title, int durationMin, double Rating ,List<Screening> screenings) {
        this.id = id;
        this.title = title;
        DurationMin = durationMin;
        this.screenings = screenings;
        this.Rating = Rating;
    }
}
