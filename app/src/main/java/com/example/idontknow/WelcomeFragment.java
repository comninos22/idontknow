package com.example.idontknow;

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
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.example.idontknow.room.Athlete;
import com.example.idontknow.room.AthleteDAO;
import com.example.idontknow.room.Connections;
import com.example.idontknow.room.Sport;
import com.example.idontknow.room.SportAndAthlete;
import com.example.idontknow.room.SportDAO;
import com.example.idontknow.room.Team;
import com.example.idontknow.room.TeamMatch;
import com.example.idontknow.room.TeamMatchResult;
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
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class WelcomeFragment extends Fragment {
    AthleteDAO athleteDAO;
    SportDAO sportDAO;
    Button create,createSport,selectAll,firebtnCreate,selectFromFirebaseBtn;
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
        selectFromFirebaseBtn=v.findViewById(R.id.getDataFromFirebase);

        create.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                Sport sport2 = new Sport(2,"football","male","team");
                Team team1= new Team(1,"ASDASDASD","toumpa","the","gr",2,"454/1/44/","imgurl");
                Team team2= new Team(2,"QWEQWEQWE","karaiskaki","ath","gr",2,"454/1/22244/","imgurl");
                TeamMatch match1=new TeamMatch(team1,team2,sport2,"12/2/4242","axladoxori serrwn","ellada");
                addTeamMatch(match1);
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

        firebtnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addTofireSote();
            }
        });
        selectFromFirebaseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                System.out.println("asd");
                getMatches();
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
        Sport sport1 = new Sport(1,"poker","travelaki","solo");
        Athlete athlete1= new Athlete(1,"takhs","lastname","alvania","alvania",2,"454/1/44/","imgurl");
        Athlete athlete2= new Athlete(1,"kostas","lastnameeee","alvaniaoleeee","alvaniaoleeee",2,"454/1/22244/","imgurl");
        Map<String, Object> soloMatch = new HashMap<>();
        soloMatch.put("date", "Ada");
        soloMatch.put("winner", athlete1.getFirstName());
        soloMatch.put("winnerPerformance", "10s");
        soloMatch.put("city", "Lovelace");
        soloMatch.put("country", 1815);
        soloMatch.put("Sport", sport1);
        soloMatch.put("athlete1", athlete1);
        soloMatch.put("athlete2", athlete2);



        fireDB.collection("SoloMatches")
                .add(soloMatch)
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

        Sport sport2 = new Sport(2,"football","male","team");
        Team team1= new Team(1,"PAOK","toumpa","the","gr",2,"454/1/44/","imgurl");
        Team team2= new Team(2,"OSFP","karaiskaki","ath","gr",2,"454/1/22244/","imgurl");
        Map<String, Object> match = new HashMap<>();
        Map<String, Object> score = new HashMap<>();
        List team1ScoreTimes=new LinkedList();
        team1ScoreTimes.add(20);
        team1ScoreTimes.add(30);
        List team2ScoreTimes=new LinkedList();
        team2ScoreTimes.add(20);
        team2ScoreTimes.add(30);

        score.put(team1.getTeamName(),team1ScoreTimes);
        score.put(team2.getTeamName(),team2ScoreTimes);

        match.put("date", "Ada");
        match.put("winnerTeam", team1.getTeamName());
        match.put("winnerScore", 2);
        match.put("loserTeam", team2.getTeamName());
        match.put("loserScore", 1);
        match.put("score", score);
        match.put("city", "Lovelace");
        match.put("country", "1815");
        match.put("Sport", sport2);
        match.put("team1", team1);
        match.put("team2", team2);

        fireDB.collection("TeamMatches")
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
    @RequiresApi(api = Build.VERSION_CODES.N)
    public void addTeamMatch(TeamMatch match){

        fireDB.collection("TeamMatches")
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
                               // System.out.println(document.getData().toString());
                               Log.d(TAG, document.getId() + " => " + document.getData());
                            }
                        } else {
                            Log.w(TAG, "Error getting documents.", task.getException());
                        }
                    }
                });
    }
}
