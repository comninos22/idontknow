package com.example.idontknow;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.idontknow.room.Athlete;
import com.example.idontknow.room.AthleteDAO;
import com.example.idontknow.room.Connections;
import com.example.idontknow.room.Sport;
import com.example.idontknow.room.SportAndAthlete;
import com.example.idontknow.room.SportDAO;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WelcomeFragment extends Fragment {
    AthleteDAO athleteDAO;
    SportDAO sportDAO;
    Button create,createSport,selectAll,firebtnCreate;
    TextView firstNameField,lastNameField,cityOfOriginField,dateOfBirthField,countryField;
    FirebaseFirestore fireDB = FirebaseFirestore.getInstance();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View v=(View) inflater.inflate(R.layout.fragment_welcome, container, false);
        athleteDAO= Connections.getInstance(getActivity().getApplicationContext()).getDatabase().getAthleteDAO();
        sportDAO= Connections.getInstance(getActivity().getApplicationContext()).getDatabase().getSportDAO();

        create=  v.findViewById(R.id.createBtn);
        createSport=v.findViewById(R.id.createSport);
        selectAll = v.findViewById(R.id.selectAll);
        firebtnCreate = v.findViewById(R.id.saveToFire);

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                firstNameField=getActivity().findViewById(R.id.FirstNameField);
//                lastNameField=getActivity().findViewById(R.id.lastNameField);
//                cityOfOriginField=getActivity().findViewById(R.id.cityOfOriginField);
//                countryField=getActivity().findViewById(R.id.countryField);
//                dateOfBirthField=getActivity().findViewById(R.id.dateField);
////                Athlete ath=new Athlete(firstNameField.getText().toString(),
////                        lastNameField.getText().toString(),
////                        cityOfOriginField.getText().toString(),
////                        countryField.getText().toString(),
////                        dateOfBirthField.getText().toString(),0
////                );
//                Athlete athlete = new Athlete(1,"takhs","lastname","city","country",1,"11111");
                Athlete athlete = new Athlete("takhs","lastname","city","country",1,"11111");
                makeAthlete(athlete);
            }
        });

        firebtnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addTofireSote();
            }
        });

        createSport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Sport sport = new Sport(1,"ragby","s","solokariera");
                Sport sport = new Sport("ragby","s","solokariera");
                makeSport(sport);
            }
        });

        selectAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<SportAndAthlete> all = athleteDAO.getAthletesWithSportId();
                System.out.println(Arrays.toString(all.toArray()));
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
    public void makeSport(Sport sport){
        try{
            sportDAO.insert(sport);
            Toast.makeText(getActivity(), sport.toString(), Toast.LENGTH_SHORT).show();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void addTofireSote(){
        Sport sport = new Sport(1,"poker","travelaki","solo");
        Athlete athlete1= new Athlete(1,"takhs","lastname","alvania","alvania",2,"454/1/44/");
        Athlete athlete2= new Athlete(1,"kostas","lastnameeee","alvaniaoleeee","alvaniaoleeee",2,"454/1/22244/");
        Map<String, Object> match = new HashMap<>();
        match.put("date", "Ada");
        match.put("city", "Lovelace");
        match.put("country", 1815);
        match.put("Sport", sport);
        match.put("athlete1", athlete1);
        match.put("athlete2", athlete2);

        fireDB.collection("Matches")
                .add(match)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(getActivity(), "DocumentSnapshot added with ID: " + documentReference.getId(), Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getActivity(), "Error:"+e, Toast.LENGTH_SHORT).show();
                    }
                });
    }

}
