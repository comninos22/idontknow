package com.example.idontknow.controllers;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.idontknow.R;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;


public class SportFragment extends Fragment {

    FloatingActionMenu materialDesignFAM;
    FloatingActionButton floatingActionInsert, floatingActionUpdate, floatingActionDelete;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
// Inflate the layout for this fragment
        return inflater.inflate(R.layout.sport_fragment_view, container, false);
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
}