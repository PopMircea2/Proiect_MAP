package com.example.proiect.CinemaApp.model;
import java.util.Date;
import java.util.List;

public class Movie {
    private String id;
    private String title;
    private int DurationMin;
    private List<Screening> screenings;
    private double Rating;
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
