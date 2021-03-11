package com.example.idontknow.room;

public abstract class Match {
    private Sport sport;
    private String date;
    private String city;
    private String country;
    private Object performance;
    public Match(Sport sport, String date, String city, String country) {
        this.sport = sport;
        this.date = date;
        this.city = city;
        this.country = country;

    }
    public Match(){};

    @Override
    public String toString() {
        return "Match{" +
                "sport=" + sport +
                ", date='" + date + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", performance=" + performance +
                '}';
    }

    public Object getPerformance() {
        return performance;
    }

    public void setPerformance(Object performance) {
        this.performance = performance;
    }

    public Sport getSport() {
        return sport;
    }

    public void setSport(Sport sport) {
        this.sport = sport;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
