package com.example.idontknow.room;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.io.Serializable;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class TeamScore {
    List<Integer> team1score=new LinkedList<Integer>();
    List<Integer> team2score=new LinkedList<Integer>();
    int team1NumOfGoals;
    int team2NumOfGoals;
    Object winner;
    Object loser;


    @RequiresApi(api = Build.VERSION_CODES.N)
    public TeamScore(Object part1,Object part2){
        this.calcResult(part1,part2);
    }

    public TeamScore(){}

    @RequiresApi(api = Build.VERSION_CODES.N)

    public void calcResult(Object team1,Object team2){
        int team1goals=(int)(Math.random()*5)+1;
        int team2goals=(int)(Math.random()*5)+1;
        for(int i=0;i<team1goals;i++){
            this.team1score.add((int)(Math.random()*90));
        }

        for(int i=0;i<team2goals;i++){
            this.team2score.add((int)(Math.random()*90));
        }
        this.team1score.sort(Comparator.naturalOrder());
        this.team2score.sort(Comparator.naturalOrder());
        this.team2NumOfGoals=this.team2score.size();
        this.team1NumOfGoals=this.team1score.size();
        if(team1NumOfGoals>team2NumOfGoals){
            this.winner=team1;
            this.loser=team2;
        }else if(team2NumOfGoals<team1NumOfGoals){
            this.winner=team2;

            this.loser=team1;
        }
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

    public int getTeam1NumOfGoals() {
        return team1NumOfGoals;
    }

    public void setTeam1NumOfGoals(int team1NumOfGoals) {
        this.team1NumOfGoals = team1NumOfGoals;
    }

    public int getTeam2NumOfGoals() {
        return team2NumOfGoals;
    }

    public void setTeam2NumOfGoals(int team2NumOfGoals) {
        this.team2NumOfGoals = team2NumOfGoals;
    }

    public Object getWinner() {
        return winner;
    }

    public void setWinner(Object winner) {
        this.winner = winner;
    }

    public Object getLoser() {
        return loser;
    }

    public void setLoser(Object loser) {
        this.loser = loser;
    }

    public boolean isDraw(){
        return team2NumOfGoals == team1NumOfGoals;
    }

    @Override
    public String toString() {
        if(!this.isDraw()){
            return "TeamScore{" +
                    "team1score=" + team1score +
                    ", team2score=" + team2score +
                    ", team1NumOfGoals=" + team1NumOfGoals +
                    ", team2NumOfGoals=" + team2NumOfGoals +
                    ", winner=" + winner +
                    ", loser=" + loser +
                    '}';
        }
        else{
            return "TeamScore{" +
                    "team1score=" + team1score +
                    ", team2score=" + team2score +
                    ", team1NumOfGoals=" + team1NumOfGoals +
                    ", team2NumOfGoals=" + team2NumOfGoals +
                    "The match appears to be a draw"+
                    '}';
        }
    }
}
