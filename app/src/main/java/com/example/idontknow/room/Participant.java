package com.example.idontknow.room;

public class Participant {
    private int performance;
    private Athlete athlete;


    public Participant(Athlete athlete, int performance) {
        this.athlete = athlete;
        this.performance = performance;
    }
    public int getPerformance() {
        return performance;
    }

    public void setPerformance(int performance) {
        this.performance = performance;
    }
    public Athlete getAthlete() {
        return athlete;
    }

    public void setAthlete(Athlete athlete) {
        this.athlete = athlete;
    }



    @Override
    public String toString() {
        return "Participant{" +
                "athlete=" + athlete +
                ", performance=" + performance +
                '}';
    }
}
