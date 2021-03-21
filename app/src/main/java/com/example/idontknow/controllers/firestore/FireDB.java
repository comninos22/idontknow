package com.example.idontknow.controllers.firestore;

import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import com.example.idontknow.room.SoloMatch;
import com.example.idontknow.room.TeamMatch;
import com.example.idontknow.utils.IPromiseLinkedList;
import com.example.idontknow.utils.PromiseLinkedList;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.LinkedList;

public class FireDB {
    private  static FireDB localInstance;
    private FirebaseFirestore firebaseInstance;
    private FireDB(){
        firebaseInstance=FirebaseFirestore.getInstance();
    }
    public static FireDB getInstance(){
        synchronized (FireDB.class){
            if(localInstance==null){
                localInstance=new FireDB();
            }
            return localInstance;
        }
    }
    public void addSoloMatch(SoloMatch soloMatch){
        firebaseInstance.collection("SoloMatches")
                .add(soloMatch)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        System.out.println("Document created succesfuly :D");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        System.out.println("Seems like something went terribly wrong :\\ \' more info: "+e.getMessage());
                    }
                });
    }
    public void getSoloMatch(PromiseLinkedList callback){
        TeamMatch match;
        firebaseInstance.collection("SoloMatches")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    private static final String TAG = "";

                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            LinkedList<SoloMatch> matches=new LinkedList<SoloMatch>();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                matches.add(document.toObject(SoloMatch.class));
                                Log.d(TAG, document.getId() + " => " + document.getData());
                            }
                            callback.methodToCallBack(matches);
                        } else {
                            Log.w(TAG, "Error getting documents.", task.getException());
                        }
                    }

                });

    }
    public void getTeamMatch(IPromiseLinkedList callback){
        TeamMatch match;
        firebaseInstance.collection("TeamMatches")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    private static final String TAG = "";

                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            LinkedList<TeamMatch> matches=new LinkedList<TeamMatch>();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                TeamMatch match=document.toObject(TeamMatch.class);
                                match.setId(document.getId());
                                matches.add(match);
                                Log.d(TAG, document.getId() + " => " + document.getData());
                            }
                            callback.methodToCallBack(matches);
                        } else {
                            Log.w(TAG, "Error getting documents.", task.getException());
                        }
                    }

                });

    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    public void addTeamMatch(TeamMatch match){

        firebaseInstance.collection("TeamMatches")
                .add(match)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });
    }
    public void deleteTeamMatch(String id){
        firebaseInstance.collection("TeamMatches").document(id).delete();

}


    private TeamMatch retrieve(){
        return null;
    }
}
