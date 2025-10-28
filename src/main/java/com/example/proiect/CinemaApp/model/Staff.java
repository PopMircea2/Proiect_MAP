package com.example.proiect.CinemaApp.model;

public abstract class Staff {
    private String id;
    private String name;
    private double HourlyRate;
    private int age;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getHourlyRate() {
        return HourlyRate;
    }

    public void setHourlyRate(double hourlyRate) {
        HourlyRate = hourlyRate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Staff(String id, String name,double hourlyRate, int age) {
        this.id = id;
        this.name = name;
        this.HourlyRate = hourlyRate;
        this.age = age;
    }
}
