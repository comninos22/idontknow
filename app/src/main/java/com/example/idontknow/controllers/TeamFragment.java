package com.example.idontknow.controllers;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.idontknow.R;
import com.example.idontknow.application.GlobalReference;
import com.example.idontknow.utils.PromiseLinkedList;
import com.example.idontknow.room.Athlete;
import com.example.idontknow.room.Connections;
import com.example.idontknow.room.FireDB;
import com.example.idontknow.room.SoloMatch;
import com.example.idontknow.room.Sport;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;

import java.util.LinkedList;
import java.util.List;

public class TeamFragment extends Fragment {

    FloatingActionMenu materialDesignFAM;
    FloatingActionButton floatingActionInsert, floatingActionUpdate, floatingActionDelete;

    //Button selectButton;
    final LinkedList list = new LinkedList();
    GlobalReference globals=GlobalReference.getInstance();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
// Inflate the layout for this fragment
        return inflater.inflate(R.layout.athlete_fragment_view, container, false);
    }
    public void onViewCreated(View view, Bundle savedInstanceState) {

        globals.setContext(getActivity().getApplicationContext());


        LinearLayout l=view.findViewById(R.id.flexView2);
        Connections roomdb=Connections.getInstance(getActivity().getApplicationContext());


        FireDB.getInstance().getSoloMatch(new PromiseLinkedList(){
            @Override
            public void methodToCallBack(LinkedList fromCallback) {
                System.out.println(fromCallback.size());
                LinkedList<SoloMatch> s=(LinkedList<SoloMatch>) fromCallback.clone();
                while(!s.isEmpty()){
                    Button b=new Button(GlobalReference.getInstance().getContext());
                    b.setText(s.poll().getDate());
                    l.addView(b);
                }
                list.addAll(s);
                }
        });

        //Glide.with(this).load("https://static2.car.gr/13402009_0_z.jpg").into(imageView);
       // selectButton=view.findViewById(R.id.button);
//        selectButton.setOnClickListener(new View.OnClickListener() {
//            @RequiresApi(api = Build.VERSION_CODES.N)
//            @Override
//            public void onClick(View v) {
//                Athlete athlete1= new Athlete("takhs","lastname","alvania","alvania",2,"454/1/44/","imgurl");
//                Athlete athlete2= new Athlete("kostas","lastnameeee","alvaniaoleeee","alvaniaoleeee",2,"454/1/22244/","imgurl");
//                roomdb.makeSport(new Sport(2,"football","male","team"));
//                roomdb.makeAthlete(athlete1);
//                roomdb.makeAthlete(athlete2);
//                List<Athlete> list=roomdb.getAthletes();
//                for(Athlete ath : list){
//                    System.out.println(ath);
//
//
//                }
//            }
//        });
    }
    public void floatingBullcrap(){

        materialDesignFAM = (FloatingActionMenu) getActivity().findViewById(R.id.material_design_android_floating_action_menu);
        floatingActionInsert = (FloatingActionButton) getActivity().findViewById(R.id.material_design_floating_action_menu_insert);
        floatingActionUpdate = (FloatingActionButton) getActivity().findViewById(R.id.material_design_floating_action_menu_update);
        floatingActionDelete = (FloatingActionButton) getActivity().findViewById(R.id.material_design_floating_action_menu_delete);



        floatingActionInsert.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Toast toast = Toast. makeText(getActivity().getApplicationContext(), "This is a message displayed in a Toast", Toast. LENGTH_SHORT);
                toast.show();

            }
        });
        floatingActionUpdate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //TODO something when floating action menu second item clicked

            }
        });
        floatingActionDelete.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //TODO something when floating action menu third item clicked

            }
        });
    }
    @Override
    public void onResume(){
        super.onResume();

        Toast toast = Toast. makeText(getActivity().getApplicationContext(), "resumed", Toast. LENGTH_SHORT);
        toast.show();
    }
    @Override
    public void onPause() {
        super.onPause();

        Toast toast = Toast.makeText(getActivity().getApplicationContext(), "paused", Toast.LENGTH_SHORT);
        toast.show();
    }
}