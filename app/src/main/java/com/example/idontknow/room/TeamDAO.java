package com.example.idontknow.room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;


@Dao
public interface TeamDAO {
    @Insert
    void insert(Team team);

    @Delete
    void delete(Team team);

    @Update
    void update(Team team);

    @Query("select * from Team order by teamName")
    List<Team> getTeam();

    @Query("select * from Team where tid=:id")
    Team getTeamById(int id);

    @Query("DELETE FROM Team")
    void deleteAllFromTeam();

    @Query("SELECT * FROM TEAM T JOIN Sport S ON (T.sportid=S.sid)")
    List<SportAndTeam> getTeamWithSport();

}


