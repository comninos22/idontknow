package com.example.idontknow.controllers;

import android.app.Activity;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.idontknow.R;
import com.example.idontknow.room.Athlete;
import com.example.idontknow.room.AthleteDAO;
import com.example.idontknow.room.Connections;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;

import java.util.List;

public class FirstFragment extends Fragment {
    Connections roomdb;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        roomdb=Connections.getInstance(getActivity().getApplicationContext());
        View v=inflater.inflate(R.layout.fragment_first, container, false);

// Inflate the layout for this fragment
        return v;
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        // Setup any handles to view objects here
        // EditText etFoo = (EditText) view.findViewById(R.id.etFoo);
        List<Athlete> list=roomdb.getAthletes();
        LinearLayout l=view.findViewById(R.id.flexView2);
        for(Athlete ath : list){
            System.out.println(ath);
            Button b=new Button(this.getContext());
            b.setText(ath.getFirstName());
            l.addView(b);
        }

    }
    public void floatingBullcrap(){
        FloatingActionMenu materialDesignFAM;
        FloatingActionButton floatingActionInsert, floatingActionUpdate, floatingActionDelete;
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
}