package com.example.idontknow.room;

import androidx.room.Embedded;

public class SportAndTeam {
    @Embedded
    Sport sport;
    @Embedded
    Team team;

    public SportAndTeam(Sport sport, Team team) {
        this.sport = sport;
        this.team = team;
    }

    @Override
    public String toString() {
        return "SportAndTeam{" +
                "sport=" + sport +
                ", team=" + team +
                '}';
    }

    public Sport getSport() {
        return sport;
    }

    public void setSport(Sport sport) {
        this.sport = sport;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
}
