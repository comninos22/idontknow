package com.example.idontknow.room;

import androidx.room.Database;
import androidx.room.ForeignKey;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverter;

@Database(entities = {Athlete.class,Sport.class,Team.class},version = 2, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract AthleteDAO getAthleteDAO();
    public abstract SportDAO getSportDAO();
    public abstract TeamDAO getTeamDAO();
}
