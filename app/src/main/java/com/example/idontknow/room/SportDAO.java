package com.example.idontknow.room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface SportDAO {
    @Insert
    void insert(Sport sport);

    @Delete
    void delete(Sport sport);

    @Update
    void update(Sport sport);

    @Query("select * from Sport order by name")
    List<Sport> getSports();

    @Query("select * from Sport where sid=:id")
    Sport getSportById(int id);

    @Query("DELETE FROM Sport")
    void deleteAllFromSport();

}
