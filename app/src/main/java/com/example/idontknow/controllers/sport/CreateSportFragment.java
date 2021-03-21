package com.example.idontknow.controllers.sport;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.idontknow.R;
import com.example.idontknow.room.Connections;
import com.example.idontknow.room.Sport;

public class CreateSportFragment extends Fragment {
    Button createSport;
    EditText sportNameField;
    RadioGroup genderGroup,typeGroup;
    Connections roomdb;
    private int id;
    private String name;
    private String gender;
    private String type;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }






    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        roomdb=Connections.getInstance(getContext());

// Inflate the layout for this fragment
        return inflater.inflate(R.layout.create_sport, container, false);
    }



    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        sportNameField=view.findViewById(R.id.sportName);
        createSport=view.findViewById(R.id.createSportBtn);
        genderGroup=view.findViewById(R.id.genderType);
        typeGroup=view.findViewById(R.id.sportType);

        if(getArguments()!=null){
            id=getArguments().getInt("id");
            switch(getArguments().getString("gender")){

                case "Male":
                    System.out.println(getArguments().getString("gender")+" "+getArguments().getString("type"));

                    ((RadioButton)view.findViewById(R.id.maleOption)).setChecked(true);
                    break;
                case "Female":
                    ((RadioButton)view.findViewById(R.id.femaleOption)).setChecked(true);
                    break;
                default:
                    break;
            }
            switch(getArguments().getString("type")){
                case "Solo":
                    ((RadioButton)view.findViewById(R.id.soloOption)).setChecked(true);
                    break;
                case "Team":
                    ((RadioButton)view.findViewById(R.id.teamOption)).setChecked(true);
                    break;
                default:
                    break;
            }

            sportNameField.setText(getArguments().getString("name"));
        }else{

        }


        createSport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int genderId=genderGroup.getCheckedRadioButtonId();
                int typeId=typeGroup.getCheckedRadioButtonId();


                String gender=((RadioButton)view.findViewById(genderId)).getText().toString();
                String type=((RadioButton)view.findViewById(typeId)).getText().toString();


                if(getArguments()==null){
                    Sport sport=new Sport(sportNameField.getText().toString(),gender,type);

                    System.out.println(getArguments());
                    roomdb.makeSport(sport);
                }else{
                    Sport sport=new Sport(id,sportNameField.getText().toString(),gender,type);


                    roomdb.updateSport(sport);
                }

                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.flContent, new SportFragment(),"SportFragment").addToBackStack("SportFragment").commit();

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
    //https://stackoverflow.com/questions/39892309/android-how-to-pick-an-image-from-gallery-and-save-it-for-later-use
}
