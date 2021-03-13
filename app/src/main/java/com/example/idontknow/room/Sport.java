package com.example.idontknow.room;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "Sport", indices = {@Index(value = {"sid"}, unique = true)})
public class Sport {

    @NonNull
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="sid")
    private int sid;

    @ColumnInfo(name="name")
    private String name;

    @ColumnInfo(name="gender")
    private String gender;

    @ColumnInfo(name="type")
    private String type;



    @Ignore
    public Sport(int id, String name, String gender, String type) {
        this.sid = sid;
        this.name = name;
        this.gender = gender;
        this.type = type;
    }
    @Ignore
    public Sport(String name, String gender, String type) {
        this.name = name;
        this.gender = gender;
        this.type = type;
    }
    public Sport(){


    }    @Override
    public String toString() {
        return "Sport{" +
                "id=" + sid +
                ", name='" + name + '\'' +
                ", gender=" + gender +
                ", type=" + type +
                '}';
    }

    public int getSid() {
        return sid;
    }

    public void setSid(int id) {
        this.sid = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
