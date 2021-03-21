package com.example.idontknow.room;

import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.RequiresApi;

public class TeamMatch extends Match {
    private Team team1;
    private Team team2;


    public TeamMatch(){

    super();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public TeamMatch(Team team1, Team team2, Sport sport, String date, String city, String country) {
        super(sport,date,city,country);
        this.team1 = team1;
        this.team2 = team2;
        super.setPerformance(new TeamScore(team1,team2));
    }
    @RequiresApi(api = Build.VERSION_CODES.N)

    public Team getTeam1() {
        return team1;
    }

    public void setTeam1(Team team1) {
        this.team1 = team1;
    }

    public Team getTeam2() {
        return team2;
    }

    public void setTeam2(Team team2) {
        this.team2 = team2;
    }

    public Sport getSport() {
        return super.getSport();
    }

    public void setSport(Sport sport) {
        super.setSport(sport);
    }





    public String getDate() {
        return super.getDate();
    }



    public String getCity() {
        return super.getCity();
    }

    public void setCity(String city) {
        super.setCity(city);
    }

    public String getCountry() {
        return super.getCountry();
    }

    public void setCountry(String country) {
        super.setCountry(country);
    };

    @Override
    public String toString() {
        return super.toString()+"TeamMatch{" +
                "team1=" + team1 +
                ", team2=" + team2 +

                "} }";
    }


}
