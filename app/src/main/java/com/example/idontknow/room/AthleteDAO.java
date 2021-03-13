package com.example.idontknow.room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Relation;
import androidx.room.Update;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface  AthleteDAO {
    @Insert
    void insert(Athlete athlete);

    @Delete
    void delete(Athlete athlete);

    @Update
    void update(Athlete athlete);

    @Query("select * from Athlete order by firstName")
    List<Athlete> getAthletes();

    @Query("select * from Athlete where aid=:id")
    Athlete getAthleteById(int id);

    @Query("select * from Athlete A join Sport S on (A.sportid = S.sid)")
    List<SportAndAthlete> getAthletesWithSport();

    @Query("DELETE FROM Athlete")
    void deleteAllFromAthlete();

}
