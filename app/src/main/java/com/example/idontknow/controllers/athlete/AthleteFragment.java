package com.example.idontknow.controllers.athlete;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.os.Build;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class AthleteFragment extends Fragment{
    Connections roomdb;
    FloatingActionMenu materialDesignFAM;
    FloatingActionButton floatingActionInsert, floatingActionDelete;
    Button deleteAllBtn;
    LinkedList<ConstraintLayout> allPreviews = new LinkedList<ConstraintLayout>();
    List<SportAndAthlete> athleteList;
    LinearLayout contentArea;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.athlete_fragment_view, container, false);
        // Gets the MapView from the XML layout and creates it


        roomdb = Connections.getInstance(getActivity().getApplicationContext());
        List<String> countries=roomdb.getCountries();
        while(!countries.isEmpty()){
            System.out.println(countries.remove(0));
        }

// Inflate the layout for this fragment
        return v;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {


        // Setup any handles to view objects here
        // EditText etFoo = (EditText) view.findViewById(R.id.etFoo);
        athleteList = roomdb.getAthletesWithSport();
        contentArea = view.findViewById(R.id.flexView2);
        deleteAllBtn=getActivity().findViewById(R.id.deleteAllButton);
        floatingBullcrap();
        // get your outer relative layout


// inflate content layout and add it to the relative layout as second child
// add as second child, therefore pass index 1 (0,1,...)
        contentArea.removeAllViews();
        LayoutInflater layoutInflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        for (SportAndAthlete ath : athleteList) {
            int baseId = 2000000;
            ConstraintLayout newView = (ConstraintLayout) layoutInflater.inflate(R.layout.athlete_preview, (ViewGroup) view, false);
            ImageHandler handler = new ImageHandler(getContext());

            ImageView v = (ImageView) newView.getChildAt(0);
            //Glide.with(this).load("https://static2.car.gr/13402009_0_z.jpg").into(v);
            ((ImageView) newView.getChildAt(0)).setImageBitmap(handler.loadImageFromStorage(ath.getAthlete().getImgUrl()));


//pbs.twimg.com/profile_images/949787136030539782/LnRrYf6e.jpg
            ((TextView) newView.getChildAt(1)).setText(ath.getAthlete().getFirstName() + " " + ath.getAthlete().getLastName());

            ((TextView) newView.getChildAt(2)).setText(ath.getAthlete().getCountry());

            ((TextView) newView.getChildAt(3)).setText(ath.getSport().getName());
            ((TextView) newView.getChildAt(4)).setText(Integer.toString(ath.getAthlete().getId()));

            contentArea.addView(newView, 0);
            allPreviews.add(newView);
            newView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AthleteOverviewFragment overview=new AthleteOverviewFragment();
                    Bundle argBundle=new Bundle();
                    argBundle.putInt("id",ath.getAthlete().getId());
                    argBundle.putString("firstName",ath.getAthlete().getFirstName());
                    argBundle.putString("lastName",ath.getAthlete().getLastName());
                    argBundle.putString("cityOfOrigin",ath.getAthlete().getCityOfOrigin());
                    argBundle.putString("country",ath.getAthlete().getCountry());
                    argBundle.putString("dateOfBirth",ath.getAthlete().getDateOfBirth());
                    argBundle.putString("imgUrl",ath.getAthlete().getImgUrl());
                    argBundle.putString("sportName",ath.getSport().getName());
                    argBundle.putInt("sportId",ath.getSport().getSid());
                    overview.setArguments(argBundle);
                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.flContent, overview,"overviewAthlete").addToBackStack("overviewAthlete").commit();

                }
            });

        }

    }


    public void floatingBullcrap() {

        materialDesignFAM = (FloatingActionMenu) getActivity().findViewById(R.id.material_design_android_floating_action_menu);
        floatingActionInsert = (FloatingActionButton) getActivity().findViewById(R.id.material_design_floating_action_menu_insert);
        floatingActionDelete = (FloatingActionButton) getActivity().findViewById(R.id.material_design_floating_action_menu_delete);
        floatingActionDelete.setLabelText("Delete athletes");
        floatingActionInsert.setLabelText("Create an athlete");





        materialDesignFAM.setOnMenuButtonClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                handleFAM();

                System.out.println("ahahahahah");
            }
        });
        floatingActionInsert.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.flContent, new CreateAthleteFragment(),"CreateAthleteFragment").addToBackStack("CreateAthleteFragment").commit();
            }
        });


        floatingActionDelete.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                deleteAllBtn.setVisibility(View.VISIBLE);
                LinkedList<ConstraintLayout> temp = (LinkedList<ConstraintLayout>) allPreviews.clone();
                while (!temp.isEmpty()) {
                    ((CheckBox) temp.poll().getChildAt(5)).setVisibility(View.VISIBLE);
                }
                deleteAllBtn.setOnClickListener(new View.OnClickListener(){

                    @Override
                    public void onClick(View v) {

                        materialDesignFAM.close(true);
                        LinkedList<ConstraintLayout> temp = (LinkedList<ConstraintLayout>) allPreviews.clone();


                        while (!temp.isEmpty()) {
                            ConstraintLayout element=(temp.pollFirst());
                            CheckBox checkBox=(CheckBox)element.getChildAt(5);
                            TextView hiddenId=(TextView)element.getChildAt(4);
                            if(checkBox.isChecked()){
                                System.out.println();

                                for(SportAndAthlete ath: athleteList){
                                    if(ath.getAthlete().getId()==Integer.parseInt(hiddenId.getText().toString())){

                                        roomdb.deleteAthlete(ath.getAthlete());
                                        checkBox.setChecked(false);
                                        contentArea.removeView(element);

                                        break;
                                    }
                                }
                            }
                        }
                        handleFAM();
                    }
                });
            }
        });
    }
    public void handleFAM(){
        if (materialDesignFAM.isOpened()) {
            LinkedList<ConstraintLayout> temp = (LinkedList<ConstraintLayout>) allPreviews.clone();
            while (!temp.isEmpty()) {
                ((CheckBox) temp.poll().getChildAt(5)).setVisibility(View.INVISIBLE);
            }
            materialDesignFAM.close(true);
            deleteAllBtn.setVisibility(View.INVISIBLE);
        } else{
            materialDesignFAM.open(true);

        }

    }
    @Override
    public void onResume() {
        super.onResume();
        materialDesignFAM.showMenu(true);
        Toast toast = Toast.makeText(getActivity().getApplicationContext(), "resumed", Toast.LENGTH_SHORT);


    }

    @Override
    public void onPause() {
        super.onPause();
        materialDesignFAM.hideMenu(true);
        Toast toast = Toast.makeText(getActivity().getApplicationContext(), "paused", Toast.LENGTH_SHORT);
        toast.show();
    }




}