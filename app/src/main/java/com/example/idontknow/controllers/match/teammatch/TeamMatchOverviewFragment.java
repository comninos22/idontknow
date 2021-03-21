package com.example.idontknow.controllers.match.teammatch;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import com.example.idontknow.R;
import com.google.android.gms.common.util.ArrayUtils;

import java.util.LinkedList;

public class TeamMatchOverviewFragment extends Fragment {
    private String id;
    private String date;
    private String country;
    private String city;
    private String participant1;
    private String particiapant2;
    private String sport;
    private int[] performance1;
    private int[] performance2;
    private TextView dateField,countryField,cityField,participant1Field,participant2Field,performance1Field,performance2Field,sportField;

    public void onViewCreated(View view, Bundle savedInstanceState) {
        dateField=getActivity().findViewById(R.id.teamMatchDate);
        countryField =getActivity().findViewById(R.id.teamMatchCountry);
        cityField =getActivity().findViewById(R.id.teamMatchCity);
        participant1Field=getActivity().findViewById(R.id.teamMatchContestant1);
        participant2Field=getActivity().findViewById(R.id.teamMatchContestant2);
        performance1Field=getActivity().findViewById(R.id.teamMatchTeam1Score);
        performance2Field=getActivity().findViewById(R.id.teamMatchTeam2Score);
        sportField=getActivity().findViewById(R.id.teamMatchSport);
        LinearLayout detailedScore=getActivity().findViewById(R.id.teamMatchDetailedPerformance);
        sportField.setText(sport);
        dateField.setText(date);
        countryField.setText(country);
        cityField.setText(city);
        participant1Field.setText(participant1);
        participant2Field.setText(particiapant2);
        for(int per:performance1){
            System.out.println(per);
        }

        LinkedList<String> allPerformances=new LinkedList();
        for(int p:performance1){
            allPerformances.add(participant1+" scored on " +p);
        }
        for(int p:performance2){
            allPerformances.add(particiapant2+" scored on "+p);
        }
        LinkedList<String> allPerformancesTemp=new LinkedList();
        while(!allPerformances.isEmpty()){
            allPerformancesTemp.addLast(allPerformances.removeLast());
        }
        for(String performance:allPerformancesTemp){
            TextView t=new TextView(getContext());
            t.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT));
            t.setText(performance);
            detailedScore.addView(t,0);
        }
        performance1Field.setText(""+performance1.length);
        performance2Field.setText(""+performance2.length);

    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        id=getArguments().getString("id");
        date=getArguments().getString("date");
        country=getArguments().getString("country");
        city=getArguments().getString("city");
        sport=getArguments().getString("sport");
        participant1=getArguments().getString("participant1");
        particiapant2=getArguments().getString("participant2");
        performance1=getArguments().getIntArray("performance1");
        performance2=getArguments().getIntArray("performance2");
        return inflater.inflate(R.layout.team_match_overview_fragment, container, false);
    }
}
