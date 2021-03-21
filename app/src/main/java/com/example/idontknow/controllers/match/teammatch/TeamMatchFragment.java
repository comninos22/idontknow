package com.example.idontknow.controllers.match.teammatch;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import com.example.idontknow.R;
import com.example.idontknow.controllers.sport.CreateSportFragment;
import com.example.idontknow.room.Connections;
import com.example.idontknow.controllers.firestore.FireDB;
import com.example.idontknow.room.Sport;
import com.example.idontknow.room.Team;
import com.example.idontknow.room.TeamMatch;
import com.example.idontknow.room.TeamScore;
import com.example.idontknow.utils.ImageHandler;
import com.example.idontknow.utils.PromiseLinkedList;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class TeamMatchFragment extends Fragment {
    FloatingActionMenu materialDesignFAM;
    FloatingActionButton floatingActionInsert, floatingActionUpdate, floatingActionDelete;
    Connections roomdb;
    Button deleteAllBtn;
    List<TeamMatch> teamMatchList;
    FireDB fireDB;
    LinearLayout contentArea;
    @RequiresApi(api = Build.VERSION_CODES.N)
    public void onViewCreated(View view, Bundle savedInstanceState) {
        floatingBullcrap();
        Sport sport2 = new Sport(2,"football","male","team");
        Team team1= new Team(1,"OSFP","toumpa","the","gr",2,"454/1/44/","imgurl");
        Team team2= new Team(2,"PAOK","karaiskaki","ath","gr",2,"454/1/22244/","imgurl");
        TeamMatch match1=new TeamMatch(team1,team2,sport2,"12/2/4242","axladoxori serrwn","ellada");
        fireDB.addTeamMatch(match1);
        // floatingBullcrap();
        fireDB.getTeamMatch(new PromiseLinkedList() {
            @Override
            public void methodToCallBack(LinkedList l) {
                System.out.println(l);
                afterCallback(l,view);
                teamMatchList = l;
            }
        });


        // get your outer relative layout


// inflate content layout and add it to the relative layout as second child
// add as second child, therefore pass index 1 (0,1,...)




    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    public void afterCallback(List<TeamMatch> teamMatchList, View view){
        contentArea = view.findViewById(R.id.teamMatchLayout);
        deleteAllBtn = getActivity().findViewById(R.id.deleteAllButton);
        contentArea.removeAllViews();
        LayoutInflater layoutInflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        for (TeamMatch teamMatches : teamMatchList) {

            ConstraintLayout newView = (ConstraintLayout) layoutInflater.inflate(R.layout.team_match_preview, (ViewGroup) view, false);

            ((TextView) newView.getChildAt(0)).setText(teamMatches.getTeam1().getTeamName());

            ((TextView) newView.getChildAt(2)).setText(teamMatches.getTeam2().getTeamName());

            ((TextView) newView.getChildAt(3)).setText(((TeamScore) teamMatches.getPerformance()).getTeam1NumOfGoals() + "-" + ((TeamScore) teamMatches.getPerformance()).getTeam2NumOfGoals());

           ((TextView) newView.getChildAt(4)).setText((teamMatches.getId()));

            contentArea.addView(newView, 0);
            //allPreviews.add(newView);
            newView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    TeamMatchOverviewFragment overview = new TeamMatchOverviewFragment();
                    Bundle argBundle = new Bundle();
                    argBundle.putString("id",teamMatches.getId());
                    argBundle.putString("country",teamMatches.getCountry());
                    argBundle.putString("city",teamMatches.getCity());
                    argBundle.putString("date",teamMatches.getDate());
                    argBundle.putString("sport",teamMatches.getSport().getName());
                    argBundle.putString("participant1",teamMatches.getTeam1().getTeamName());
                    argBundle.putString("participant2",teamMatches.getTeam2().getTeamName());
                    Object[] objects1=teamMatches.getPerformance().getTeam1score().toArray();
                    int[] array1=new int[objects1.length];
                    for(int i=0;i<array1.length;i++){
                        array1[i]=(int)objects1[i];
                    }
                    argBundle.putIntArray("performance1",array1);
                    Object[] objects2=teamMatches.getPerformance().getTeam2score().toArray();
                    int[] array2=new int[objects2.length];
                    for(int i=0;i<array2.length;i++){
                        array2[i]=(int)objects2[i];
                    }
                    argBundle.putIntArray("performance2",array2);
                    argBundle.putBoolean("draw",teamMatches.getPerformance().isDraw());
                    overview.setArguments(argBundle);
                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.flContent, overview, "overviewAthlete").addToBackStack("overviewAthlete").commit();

                }
            });
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fireDB = FireDB.getInstance();
// Inflate the layout for this fragment
        return inflater.inflate(R.layout.team_match_fragment_view, container, false);
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
                for(int i=0;i<contentArea.getChildCount();i++){
                    ((ConstraintLayout) contentArea.getChildAt(i)).getChildAt(5).setVisibility(View.VISIBLE);

                }

                deleteAllBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        materialDesignFAM.close(true);

                        LinkedList<ConstraintLayout> temp=new LinkedList<ConstraintLayout>();
                        for(int i=0;i<contentArea.getChildCount();i++){
                            ConstraintLayout element = (ConstraintLayout)(contentArea.getChildAt(i));
                            CheckBox checkBox = (CheckBox) element.getChildAt(5);
                            TextView hiddenId = (TextView) element.getChildAt(4);
                            if (checkBox.isChecked()) {

                                for (TeamMatch match : teamMatchList) {
                                    System.out.println(match.getId());
                                    if (match.getId().equals(hiddenId.getText().toString())) {
                                        fireDB.deleteTeamMatch(match.getId());
                                        checkBox.setChecked(false);
                                        temp.add(element);

                                    }
                                }
                            }
                        }
                        while(!temp.isEmpty()){
                            contentArea.removeView(temp.poll());
                        }

                        handleFAM();
                    }
                });
            }
        });
    }

    public void handleFAM() {
        if (materialDesignFAM.isOpened()) {
            for(int i=0;i<contentArea.getChildCount();i++){
                ((ConstraintLayout) contentArea.getChildAt(i)).getChildAt(5).setVisibility(View.INVISIBLE);

            }
            materialDesignFAM.close(true);
            deleteAllBtn.setVisibility(View.INVISIBLE);
        } else {
            materialDesignFAM.open(true);
        }

    }
}
