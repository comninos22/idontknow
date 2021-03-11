package com.example.idontknow.room;

import android.os.Build;
import android.provider.Telephony;

import androidx.annotation.RequiresApi;

import java.util.Comparator;
import java.util.LinkedList;

public class PerformanceOfParticipants {
    private LinkedList<Participant> participants=new LinkedList<Participant>();

    @RequiresApi(api = Build.VERSION_CODES.N)
    public PerformanceOfParticipants(LinkedList<Athlete> athletes) {
        while(!athletes.isEmpty()){
            participants.add(new Participant(athletes.pop(),(int)(Math.random()*90)));
        }
        this.participants.sort((o1, o2) -> o1.getPerformance()-o2.getPerformance());

    }

    public void calcResult(){

    }

    public LinkedList<Participant> getParticipants() {
        return participants;
    }

    public void setParticipants(LinkedList<Participant> participants) {
        this.participants = participants;
    }
}
