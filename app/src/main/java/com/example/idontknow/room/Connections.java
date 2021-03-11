package com.example.idontknow.room;

import android.content.Context;
import android.widget.Toast;

import androidx.room.Room;

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

    public AppDatabase getDatabase(){
        return database;
    }
}
