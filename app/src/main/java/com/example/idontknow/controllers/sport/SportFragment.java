package com.example.idontknow.controllers.sport;

import android.content.Context;
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

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import com.example.idontknow.R;
import com.example.idontknow.controllers.athlete.AthleteOverviewFragment;
import com.example.idontknow.controllers.athlete.CreateAthleteFragment;
import com.example.idontknow.room.Connections;
import com.example.idontknow.room.Sport;
import com.example.idontknow.room.SportAndAthlete;
import com.example.idontknow.room.SportAndTeam;
import com.example.idontknow.utils.ImageHandler;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;

import java.util.LinkedList;
import java.util.List;


public class SportFragment extends Fragment {

    FloatingActionMenu materialDesignFAM;
    FloatingActionButton floatingActionInsert, floatingActionUpdate, floatingActionDelete;
    Connections roomdb;
    List<Sport> sportList;
    LinearLayout contentArea;
    Button deleteAllBtn;
    LinkedList<ConstraintLayout> allPreviews = new LinkedList<ConstraintLayout>();

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        floatingBullcrap();
        sportList = roomdb.getSports();
        contentArea = view.findViewById(R.id.sportLayout);
        deleteAllBtn = getActivity().findViewById(R.id.deleteAllButton);

        // get your outer relative layout


// inflate content layout and add it to the relative layout as second child
// add as second child, therefore pass index 1 (0,1,...)

        contentArea.removeAllViews();
        LayoutInflater layoutInflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        for (Sport sport : sportList) {
            ConstraintLayout newView = (ConstraintLayout) layoutInflater.inflate(R.layout.sport_preview, (ViewGroup) view, false);
            ImageHandler handler = new ImageHandler(getContext());


            //Glide.with(this).load("https://static2.car.gr/13402009_0_z.jpg").into(v);


//pbs.twimg.com/profile_images/949787136030539782/LnRrYf6e.jpg
            ((TextView) newView.getChildAt(0)).setText(sport.getName());

            ((TextView) newView.getChildAt(1)).setText(sport.getGender());

            ((TextView) newView.getChildAt(2)).setText(sport.getType());
            ((TextView) newView.getChildAt(3)).setText(Integer.toString(sport.getSid()));

            contentArea.addView(newView, 0);
            allPreviews.add(newView);
            newView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    CreateSportFragment overview=new CreateSportFragment();
                    Bundle argBundle=new Bundle();
                    argBundle.putInt("id",sport.getSid());
                    argBundle.putString("name",sport.getName());
                    argBundle.putString("gender",sport.getGender());
                    argBundle.putString("type",sport.getType());

                    overview.setArguments(argBundle);
                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.flContent, overview,"overviewAthlete").addToBackStack("overviewAthlete").commit();

                }
            });
        }


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        roomdb = Connections.getInstance(getContext());
// Inflate the layout for this fragment
        return inflater.inflate(R.layout.sport_fragment_view, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();
        Toast toast = Toast.makeText(getActivity().getApplicationContext(), "resumed", Toast.LENGTH_SHORT);
        toast.show();
    }

    @Override
    public void onPause() {
        super.onPause();
        Toast toast = Toast.makeText(getActivity().getApplicationContext(), "paused", Toast.LENGTH_SHORT);
        toast.show();
    }

    public void floatingBullcrap() {
        materialDesignFAM = (FloatingActionMenu) getActivity().findViewById(R.id.material_design_android_floating_action_menu);
        floatingActionInsert = (FloatingActionButton) getActivity().findViewById(R.id.material_design_floating_action_menu_insert);
        floatingActionDelete = (FloatingActionButton) getActivity().findViewById(R.id.material_design_floating_action_menu_delete);


        floatingActionInsert.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.flContent, new CreateSportFragment(),"CreateSportFragment").addToBackStack("CreateSportFragment").commit();

            }
        });

        floatingActionDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteAllBtn.setVisibility(View.VISIBLE);
                LinkedList<ConstraintLayout> temp = (LinkedList<ConstraintLayout>) allPreviews.clone();
                while (!temp.isEmpty()) {
                    ((CheckBox) temp.poll().getChildAt(4)).setVisibility(View.VISIBLE);
                }
                deleteAllBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        materialDesignFAM.close(true);
                        LinkedList<ConstraintLayout> temp = (LinkedList<ConstraintLayout>) allPreviews.clone();
                        while (!temp.isEmpty()) {
                            ConstraintLayout element = (temp.pollFirst());
                            CheckBox checkBox = (CheckBox) element.getChildAt(4);
                            TextView hiddenId = (TextView) element.getChildAt(3);
                            if (checkBox.isChecked()) {

                                for (Sport sport : sportList) {
                                    if (sport.getSid() == Integer.parseInt(hiddenId.getText().toString())) {
                                        roomdb.deleteSport(sport);
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

    public void handleFAM() {
        if (materialDesignFAM.isOpened()) {
            LinkedList<ConstraintLayout> temp = (LinkedList<ConstraintLayout>) allPreviews.clone();
            while (!temp.isEmpty()) {
                ((CheckBox) temp.poll().getChildAt(4)).setVisibility(View.INVISIBLE);
            }
            materialDesignFAM.close(true);
            deleteAllBtn.setVisibility(View.INVISIBLE);
        } else {
            materialDesignFAM.open(true);
        }

    }
}