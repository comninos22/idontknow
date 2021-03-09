package com.example.idontknow.room;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.util.Date;
@Entity(tableName = "Athlete",
        foreignKeys = {
        @ForeignKey(entity = Sport.class,
                parentColumns = "id",
                childColumns = "sportid",
                onDelete = ForeignKey.CASCADE)
        })
public class Athlete {


    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="id")
    private int id;
    @ColumnInfo(name="firstName")
    private String firstName;
    @ColumnInfo(name="lastName")
    private String lastName;
    @ColumnInfo(name="cityOfOrigin")
    private String cityOfOrigin;
    @ColumnInfo(name="country")
    private String country;
    @ColumnInfo(name="sportid")

    private int sportid;
    @ColumnInfo(name="dateOfBirth")
    private String dateOfBirth;

    public Athlete(String firstName, String lastName, String cityOfOrigin, String country, String dateOfBirth,int sportid) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.cityOfOrigin = cityOfOrigin;
        this.country = country;
        this.dateOfBirth = dateOfBirth;
        this.sportid=sportid;
    }

    @Override
    public String toString() {
        return "Athlete{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", cityOfOrigin='" + cityOfOrigin + '\'' +
                ", country='" + country + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                '}';
    }

    public int getSportid() {
        return sportid;
    }

    public void setSportid(int sportid) {
        this.sportid = sportid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCityOfOrigin() {
        return cityOfOrigin;
    }

    public void setCityOfOrigin(String cityOfOrigin) {
        this.cityOfOrigin = cityOfOrigin;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
}

