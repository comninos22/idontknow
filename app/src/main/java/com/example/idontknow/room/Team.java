package com.example.idontknow.room;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import javax.annotation.Nonnull;

@Entity(tableName = "Team",
        foreignKeys = {
                @ForeignKey(entity = Sport.class,
                        parentColumns = "sid",
                        childColumns = "sportid",
                        onDelete = ForeignKey.CASCADE)
        })
public class Team {

    @PrimaryKey(autoGenerate = true)
    @Nonnull
    @ColumnInfo(name = "tid")
    private int id;

    @ColumnInfo(name = "teamName")
    private String teamName;

    @ColumnInfo(name = "stadiumName")
    private String stadiumName;

    @ColumnInfo(name = "headquarters")
    private String headquarters;

    @ColumnInfo(name = "country")
    private String country;

    @ColumnInfo(name = "sportid")
    private int sportid;

    @ColumnInfo(name = "established")
    private String established;

    @ColumnInfo(name = "imgURL")
    private String imgURL;

    @Ignore
    public Team(int id, String teamName, String stadiumName, String headquarters, String country, int sportid, String established,String imgURL) {
        this.id = id;
        this.teamName = teamName;
        this.stadiumName = stadiumName;
        this.headquarters = headquarters;
        this.country = country;
        this.sportid = sportid;
        this.established = established;
        this.imgURL=imgURL;
    }
    @Ignore
    public Team(String teamName, String stadiumName, String headquarters, String country, int sportid, String established,String imgURL) {
        this.teamName = teamName;
        this.stadiumName = stadiumName;
        this.headquarters = headquarters;
        this.country = country;
        this.sportid = sportid;
        this.established = established;
        this.imgURL=imgURL;
    }

    public Team(){

    }
    @Override
    public String toString() {
        return "Team{" +
                "id=" + id +
                ", teamName='" + teamName + '\'' +
                ", stadiumName='" + stadiumName + '\'' +
                ", headquarters='" + headquarters + '\'' +
                ", country='" + country + '\'' +
                ", sportid=" + sportid +
                ", established='" + established + '\'' +
                '}';
    }

    public String getImgURL() {
        return imgURL;
    }

    public void setImgURL(String imgURL) {
        this.imgURL = imgURL;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getStadiumName() {
        return stadiumName;
    }

    public void setStadiumName(String stadiumName) {
        this.stadiumName = stadiumName;
    }

    public String getHeadquarters() {
        return headquarters;
    }

    public void setHeadquarters(String headquarters) {
        this.headquarters = headquarters;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getSportid() {
        return sportid;
    }

    public void setSportid(int sportid) {
        this.sportid = sportid;
    }

    public String getEstablished() {
        return established;
    }

    public void setEstablished(String established) {
        this.established = established;
    }
}
