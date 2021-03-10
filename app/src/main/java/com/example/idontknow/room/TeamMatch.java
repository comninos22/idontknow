package com.example.idontknow.room;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class TeamMatch {
    private Team team1;
    private Team team2;
    private Sport sport;
    private Score score;
    private int winnerScore;
    private int loserScore;
    private String winnerTeam;
    private String loserTeam;
    private String date;
    private String city;
    private String country;
    public TeamMatch(){

    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    public TeamMatch(Team team1, Team team2, Sport sport, String date, String city, String country) {
        this.team1 = team1;
        this.team2 = team2;
        this.sport = sport;
        this.date = date;
        this.city = city;
        this.country = country;
        this.calcResult();
    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    public void calcResult(){
        this.score=new Score();
        int team1goals=(int)(Math.random()*5)+1;
        int team2goals=(int)(Math.random()*5)+1;
        for(int i=0;i<team1goals;i++){
            this.score.team1score.add((int)(Math.random()*90));
        }

        for(int i=0;i<team2goals;i++){
            this.score.team2score.add((int)(Math.random()*90));
        }

        this.score.team1score.sort(Comparator.naturalOrder());
        this.score.team2score.sort(Comparator.naturalOrder());
        if(team1goals>team2goals){
            this.winnerScore=team1goals;
            this.loserScore=team2goals;
            this.winnerTeam=this.team1.getTeamName();
            this.loserTeam=this.team2.getTeamName();
        }else if(team1goals<team2goals){
            this.winnerScore=team2goals;
            this.loserScore=team1goals;
            this.winnerTeam=this.team2.getTeamName();
            this.loserTeam=this.team1.getTeamName();
        }else{
            this.winnerScore=team1goals;
            this.loserScore=team1goals;
            this.winnerTeam="draw";
            this.loserTeam="draw";
        }
    }
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
        return sport;
    }

    public void setSport(Sport sport) {
        this.sport = sport;
    }

    public Score getScore() {
        return score;
    }

    public void setScore(Score score) {
        this.score = score;
    }

    public int getWinnerScore() {
        return winnerScore;
    }

    public void setWinnerScore(int winnerScore) {
        this.winnerScore = winnerScore;
    }

    public int getLoserScore() {
        return loserScore;
    }

    public void setLoserScore(int loserScore) {
        this.loserScore = loserScore;
    }

    public String getWinnerTeam() {
        return winnerTeam;
    }

    public void setWinnerTeam(String winnerTeam) {
        this.winnerTeam = winnerTeam;
    }

    public String getLoserTeam() {
        return loserTeam;
    }

    public void setLoserTeam(String loserTeam) {
        this.loserTeam = loserTeam;
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

    @Override
    public String toString() {
        return "TeamMatch{" +
                "team1=" + team1 +
                ", team2=" + team2 +
                ", sport=" + sport +
                ", score=" + score +
                ", winnerScore=" + winnerScore +
                ", loserScore=" + loserScore +
                ", winnerTeam=" + winnerTeam +
                ", loserTeam=" + loserTeam +
                ", date='" + date + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}
