package com.example.idontknow.room;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.security.acl.Owner;

public class SportAndAthlete {
        @Embedded
        Athlete athlete;

        @Embedded
        Sport sport;

        public SportAndAthlete(Athlete athlete, Sport sport) {
                this.athlete = athlete;
                this.sport = sport;
        }

        public Athlete getAthlete() {
                return athlete;
        }

        public void setAthlete(Athlete athlete) {
                this.athlete = athlete;
        }

        public Sport getSport() {
                return sport;
        }

        public void setSport(Sport sport) {
                this.sport = sport;
        }

        @Override
        public String toString() {
                return "SportAndAthlete{" +
                        "athlete=" + athlete +
                        ", sport=" + sport +
                        '}';
        }
}
