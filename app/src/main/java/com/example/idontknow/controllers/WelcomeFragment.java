package com.example.idontknow.controllers;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.example.idontknow.R;
import com.example.idontknow.room.Athlete;
import com.example.idontknow.room.AthleteDAO;
import com.example.idontknow.room.Connections;
import com.example.idontknow.room.SoloMatch;
import com.example.idontknow.room.Sport;
import com.example.idontknow.room.SportAndAthlete;
import com.example.idontknow.room.SportDAO;
import com.example.idontknow.room.Team;
import com.example.idontknow.room.TeamMatch;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class WelcomeFragment extends Fragment {
    AthleteDAO athleteDAO;
    SportDAO sportDAO;
    Button create,createSport,selectAll,firebtnCreate,selectFromFirebaseBtn;
    TextView firstNameField,lastNameField,cityOfOriginField,dateOfBirthField,countryField;
    FirebaseFirestore fireDB = FirebaseFirestore.getInstance();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Toast toast = Toast. makeText(getActivity().getApplicationContext(), "This is a message displayed in a Toast", Toast. LENGTH_SHORT);
        toast.show();
        View v=(View) inflater.inflate(R.layout.fragment_welcome, container, false);
        athleteDAO= Connections.getInstance(getActivity().getApplicationContext()).getDatabase().getAthleteDAO();
        sportDAO= Connections.getInstance(getActivity().getApplicationContext()).getDatabase().getSportDAO();

        create=  v.findViewById(R.id.createBtn);


        create.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                Sport sport2 = new Sport(2,"football","male","team");
                Team team1= new Team(1,"ASDASDASD","toumpa","the","gr",2,"454/1/44/","imgurl");
                Team team2= new Team(2,"QWEQWEQWE","karaiskaki","ath","gr",2,"454/1/22244/","imgurl");
                TeamMatch match1=new TeamMatch(team1,team2,sport2,"12/2/4242","axladoxori serrwn","ellada");
                LinkedList<Athlete> athletes=new LinkedList<Athlete>();
                athletes.add(new Athlete("takhs","lastname","city","country",1,"11111","imgurl"));
                athletes.add(new Athlete("takhs","lastname","city","country",1,"11111","imgurl"));
                athletes.add(new Athlete("takhs","lastname","city","country",1,"11111","imgurl"));
                athletes.add(new Athlete("takhs","lastname","city","country",1,"11111","imgurl"));
                athletes.add(new Athlete("takhs","lastname","city","country",1,"11111","imgurl"));
                athletes.add(new Athlete("takhs","lastname","city","country",1,"11111","imgurl"));
                SoloMatch match3=new SoloMatch(sport2,"asasd","asdasd","asdasd",athletes);
                addTeamMatch(match3);
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
                Athlete athlete = new Athlete("takhs","lastname","city","country",1,"11111","imgurl");
                makeAthlete(athlete);
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




    @RequiresApi(api = Build.VERSION_CODES.N)
    public void addTeamMatch(SoloMatch match){

        fireDB.collection("SoloMatches")
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

    public void getMatches(){
        fireDB.collection("TeamMatches")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    private static final String TAG = "";

                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                TeamMatch match=document.toObject(TeamMatch.class);
                                System.out.println("AAAAAAA: "+match);
                               Log.d(TAG, document.getId() + " => " + document.getData());
                            }
                        } else {
                            Log.w(TAG, "Error getting documents.", task.getException());
                        }
                    }
                });
    }
}
