package com.example.idontknow.controllers;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;

import android.os.StrictMode;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import com.example.idontknow.R;
import com.example.idontknow.room.Athlete;
import com.example.idontknow.room.Connections;
import com.example.idontknow.room.SportAndAthlete;
import com.example.idontknow.utils.ImageHandler;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;

import java.io.IOException;
import java.net.URL;
import java.util.List;

public class AthleteFragment extends Fragment {
    Connections roomdb;
    FloatingActionMenu materialDesignFAM;
    FloatingActionButton floatingActionInsert, floatingActionUpdate, floatingActionDelete;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        roomdb=Connections.getInstance(getActivity().getApplicationContext());
        View v=inflater.inflate(R.layout.team_fragment_view, container, false);

// Inflate the layout for this fragment
        return v;
    }
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        // Setup any handles to view objects here
        // EditText etFoo = (EditText) view.findViewById(R.id.etFoo);
        List<SportAndAthlete> list=roomdb.getAthletesWithSport();
        LinearLayout l=view.findViewById(R.id.flexView2);

        floatingBullcrap();
        // get your outer relative layout



// inflate content layout and add it to the relative layout as second child
// add as second child, therefore pass index 1 (0,1,...)
        l.removeAllViews();
        LayoutInflater layoutInflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        for(SportAndAthlete ath: list){
            ConstraintLayout newView=(ConstraintLayout)layoutInflater.inflate(R.layout.athlete_preview, (ViewGroup) view,false);
            ImageHandler handler=new ImageHandler(getContext());



            ((ImageView) newView.getChildAt(0)).setImageBitmap(handler.loadImageFromStorage(ath.getAthlete().getImgUrl()));

            ((TextView) newView.getChildAt(1)).setText(ath.getAthlete().getFirstName()+" "+ath.getAthlete().getLastName());

            ((TextView) newView.getChildAt(2)).setText(ath.getAthlete().getCountry());

            ((TextView) newView.getChildAt(3)).setText(ath.getSport().getName());

            l.addView(newView, 0 );





        }

    }





    public void floatingBullcrap(){

        materialDesignFAM = (FloatingActionMenu) getActivity().findViewById(R.id.material_design_android_floating_action_menu);
        floatingActionInsert = (FloatingActionButton) getActivity().findViewById(R.id.material_design_floating_action_menu_insert);
        floatingActionUpdate = (FloatingActionButton) getActivity().findViewById(R.id.material_design_floating_action_menu_update);
        floatingActionDelete = (FloatingActionButton) getActivity().findViewById(R.id.material_design_floating_action_menu_delete);
        floatingActionDelete.setLabelText("Delete athletes");
        floatingActionInsert.setLabelText("Create an athlete");
        floatingActionUpdate.setLabelText("Edit an athlete");

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
        materialDesignFAM.showMenu(true);
        Toast toast = Toast. makeText(getActivity().getApplicationContext(), "resumed", Toast. LENGTH_SHORT);
        toast.show();
    }
    @Override
    public void onPause() {
        super.onPause();
        materialDesignFAM.hideMenu(true);
        Toast toast = Toast.makeText(getActivity().getApplicationContext(), "paused", Toast.LENGTH_SHORT);
        toast.show();
    }
}