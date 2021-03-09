package com.example.idontknow.room;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Sport")
public class Sport {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="id")
    private int id;

    @ColumnInfo(name="name")
    private String name;

    @ColumnInfo(name="gender")
    private String gender;

    @ColumnInfo(name="type")
    private String type;

    public Sport(String name, String gender, String type) {
        this.name = name;
        this.gender = gender;
        this.type = type;
    }

    @Override
    public String toString() {
        return "Sport{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", gender=" + gender +
                ", type=" + type +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
