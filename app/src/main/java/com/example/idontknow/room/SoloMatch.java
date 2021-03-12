package com.example.idontknow.room;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.Comparator;
import java.util.LinkedList;

public class SoloMatch extends Match{
    private int numberOfPlayers;
    private LinkedList<Athlete> athletes;
    public SoloMatch(){}
    @RequiresApi(api = Build.VERSION_CODES.N)
    public SoloMatch(Sport sport, String date, String city, String country, LinkedList<Athlete> athletes) {
        super(sport, date, city, country);
        this.numberOfPlayers = athletes.size();
        this.athletes=athletes;
        if(numberOfPlayers==2){
            Athlete ath1=athletes.poll();
            Athlete ath2=athletes.poll();
            super.setPerformance(new TeamScore(ath1,ath2));
        }else{
            super.setPerformance(new PerformanceOfParticipants(this.athletes));
        }
    }

    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    public void setNumberOfPlayers(int numberOfPlayers) {
        this.numberOfPlayers = numberOfPlayers;
    }

    public LinkedList<Athlete> getAthletes() {
        return athletes;
    }

    public void setAthletes(LinkedList<Athlete> athletes) {
        this.athletes = athletes;
    }
}
