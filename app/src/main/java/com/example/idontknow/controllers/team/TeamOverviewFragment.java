package com.example.idontknow.controllers.team;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.idontknow.R;
import com.example.idontknow.utils.ImageHandler;

/**
 * A simple {@link Fragment} subclass.

 * create an instance of this fragment.
 */
public class TeamOverviewFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER



    // TODO: Rename and change types of parameters

    private int id;
    private String teamName;
    private String stadiumName;
    private String headquarters;
    private String country;
    private String sportName;
    private String established;
    private String imgURL;
    private Button editButton;
    private int sportId;
    public TeamOverviewFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            id = getArguments().getInt("id");
            teamName = getArguments().getString("teamName");
            stadiumName = getArguments().getString("stadiumName");
            headquarters = getArguments().getString("headquarters");
            country = getArguments().getString("country");
            established = getArguments().getString("established");
            sportName = getArguments().getString("sportName");
            imgURL = getArguments().getString("imgURL");
            sportId=getArguments().getInt("sportId");
        }
        System.out.println(sportName);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.athlete_overview_fragment, container, false);
    }
    public void onViewCreated(View view, Bundle savedInstanceState) {
        editButton=view.findViewById(R.id.athEditButton);
        ImageView img=view.findViewById(R.id.athleteImage);
        ImageHandler handler=new ImageHandler(getContext());
        img.setImageBitmap(handler.loadImageFromStorage(imgURL));
        TextView teamNameField,stadiumNameField,headquartersField,countryField,establishedField,sportNameField;
        teamNameField=view.findViewById(R.id.teamNameField);
        stadiumNameField=view.findViewById(R.id.athLastNameField);
        headquartersField=view.findViewById(R.id.athCityOfOriginField);
        countryField=view.findViewById(R.id.athCountryField);
        establishedField=view.findViewById(R.id.athDateOfBirthField);
        sportNameField=view.findViewById(R.id.athSportNameField);

        teamNameField.setText("First name: "+teamName);
        stadiumNameField.setText("Stadium: "+stadiumName);
        headquartersField.setText("Headquarters: "+headquarters);
        countryField.setText("Country: "+country);
        establishedField.setText("Established : "+established);
        sportNameField.setText("Sport: "+sportName);

        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle argBundle=new Bundle();
                argBundle.putInt("id",id);
                argBundle.putString("name",teamName);
                argBundle.putString("headquarters",headquarters);
                argBundle.putString("country",country);
                argBundle.putString("established",established);
                argBundle.putString("imgUrl",imgURL);
                argBundle.putString("sportName",sportName);
                argBundle.putString("stadiumName",stadiumName);
                argBundle.putInt("sportId",sportId);
                CreateTeamFragment editTeam=new CreateTeamFragment();

                editTeam.setArguments(argBundle);
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.flContent, editTeam,"editAthlete").addToBackStack("editAthlete").commit();
            }
        });
    }
}