package com.example.idontknow;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.idontknow.room.Athlete;
import com.example.idontknow.room.AthleteDAO;
import com.example.idontknow.room.Connections;

public class WelcomeFragment extends Fragment {
    AthleteDAO athleteDAO;
    Button create;
    TextView firstNameField,lastNameField,cityOfOriginField,dateOfBirthField,countryField;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v=(View) inflater.inflate(R.layout.fragment_welcome, container, false);
        athleteDAO= Connections.getInstance(getActivity().getApplicationContext()).getDatabase().getAthleteDAO();
        create=  v.findViewById(R.id.createBtn);
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firstNameField=getActivity().findViewById(R.id.FirstNameField);
                lastNameField=getActivity().findViewById(R.id.lastNameField);
                cityOfOriginField=getActivity().findViewById(R.id.cityOfOriginField);
                countryField=getActivity().findViewById(R.id.countryField);
                dateOfBirthField=getActivity().findViewById(R.id.dateField);
                Athlete ath=new Athlete(firstNameField.getText().toString(),
                        lastNameField.getText().toString(),
                        cityOfOriginField.getText().toString(),
                        countryField.getText().toString(),
                        dateOfBirthField.getText().toString(),0
                );
                makeAthlete(ath);
            }
        });
        // Inflate the layout for this fragment
        return v;
    }
    public void makeAthlete(Athlete athlete){


        try{
            athleteDAO.insert(athlete);
            Toast.makeText(getActivity(), athlete.toString(), Toast.LENGTH_SHORT).show();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

}
