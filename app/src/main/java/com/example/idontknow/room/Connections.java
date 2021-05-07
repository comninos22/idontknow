package com.example.idontknow.room;

import android.content.Context;
import android.widget.Toast;

import androidx.room.Room;

import java.util.List;

public class Connections {
    private static Connections instance;
    private AppDatabase database;
    private AthleteDAO athleteDAO;
    private SportDAO sportDAO;
    private TeamDAO teamDAO;

    private Connections(Context context){
        database= Room.databaseBuilder(context,AppDatabase.class,"SportsDB").allowMainThreadQueries().fallbackToDestructiveMigration().build();
        athleteDAO= database.getAthleteDAO();
        sportDAO= database.getSportDAO();
        teamDAO= database.getTeamDAO();
    }

    public static Connections getInstance(Context context){
        synchronized (Connections.class){
            if(instance==null){
                instance=new Connections(context);
            }
            return instance;
        }
    }

    public List<Team> getTeams(){
        try{
            return teamDAO.getTeam();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }
    public List<Athlete> getAthletes(){
        try{
            return athleteDAO.getAthletes();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }
    public List<Sport> getSports(){
        try{
            return sportDAO.getSports();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    public void makeAthlete(Athlete athlete){
        try{
            athleteDAO.insert(athlete);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    public void makeSport(Sport sport){
        try{
            sportDAO.insert(sport);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    public void makeTeam(Team team){
        try{
            teamDAO.insert(team);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void deleteAthlete(Athlete athlete){
        try{
            athleteDAO.delete(athlete);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    public void deleteSport(Sport sport){
        try{
            sportDAO.delete(sport);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    public void deleteTeam(Team team){
        try{
            teamDAO.delete(team);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void updateAthlete(Athlete athlete){
        try{
            athleteDAO.update(athlete);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    public void updateSport(Sport sport){
        try{
            sportDAO.update(sport);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    public void updateTeam(Team team){
        try{
            teamDAO.update(team);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public List<SportAndTeam> getTeamsWithSport(){
        try{
            return teamDAO.getTeamWithSport();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    public List<SportAndAthlete> getAthletesWithSport(){
        try{
            return athleteDAO.getAthletesWithSport();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    public List<String> getCountries(){
        try{
            return athleteDAO.getCountries();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    public AppDatabase getDatabase(){
        return database;
    }
}
