package com.example.idontknow.room;

import java.util.LinkedList;
import java.util.List;

public class Score {
    List<Integer> team1score=new LinkedList<Integer>();
    List<Integer> team2score=new LinkedList<Integer>();
    public Score(){

    }

    public List<Integer> getTeam1score() {
        return team1score;
    }

    public void setTeam1score(List<Integer> team1score) {
        this.team1score = team1score;
    }

    public List<Integer> getTeam2score() {
        return team2score;
    }

    public void setTeam2score(List<Integer> team2score) {
        this.team2score = team2score;
    }
}
