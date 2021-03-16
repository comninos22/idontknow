package com.example.idontknow.controllers.team;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.idontknow.R;
import com.example.idontknow.room.Connections;
import com.example.idontknow.room.Sport;
import com.example.idontknow.room.Team;
import com.example.idontknow.utils.ImageHandler;

import java.util.LinkedList;
import java.util.List;

import static android.app.Activity.RESULT_OK;

public class CreateTeamFragment extends Fragment {
    Button createTeam,selectImageFromGallery;
    EditText teamNameField,headquartersField,countryField,sportNameField,establishedField,stadiumField;
    String localImagePath;
    Connections roomdb;
    Spinner spinner;
    Sport selectedSport;
    private static final int REQUEST_WRITE_PERMISSION = 786;


    private int id;
    private String teamName;
    private String stadiumName;
    private String headquarters;
    private String country;
    private String sportName;
    private String established;
    private String imgURL;
    private Button editButton;
    private int sportId;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        System.out.println(sportName);
    }






    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        roomdb=Connections.getInstance(getContext());
        Sport sport=new Sport("wtf","wtf","wtf");
        roomdb.makeSport(sport);
// Inflate the layout for this fragment
        return inflater.inflate(R.layout.create_team, container, false);
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        createTeam=view.findViewById(R.id.createSportBtn);
        teamNameField=view.findViewById(R.id.teamNameField);
        stadiumField=view.findViewById(R.id.stadiumNameField);
        establishedField=view.findViewById(R.id.teamEstablishedField);
        headquartersField=view.findViewById(R.id.sportName);
        countryField=view.findViewById(R.id.teamCountryField);
        selectImageFromGallery=view.findViewById(R.id.selectTeamImage);
        spinner=view.findViewById(R.id.spinnerTeam);
        if (getArguments() != null) {
            id = getArguments().getInt("id");
            stadiumField.setText(getArguments().getString("stadiumName"));
            teamNameField.setText(getArguments().getString("name"));
            headquartersField.setText(getArguments().getString("headquarters"));
            establishedField.setText( getArguments().getString("established"));
            countryField.setText( getArguments().getString("country"));
            sportNameField.setText( getArguments().getString("sportName"));
            selectedSport=new Sport();
            selectedSport.setSid(getArguments().getInt("sportId")) ;
            imgURL = getArguments().getString("imgUrl");
            ((Button)view.findViewById(R.id.createSportBtn)).setText("Edit Team");
        }
        List<Sport> sportsList=roomdb.getSports();
        List<String> stringList=new LinkedList();
        for(Sport s:sportsList){
            stringList.add(s.getName());
        }
        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(getActivity(), R.layout.spinner_item, stringList);
        adaptador.setDropDownViewResource(R.layout.spinner_item);
        spinner.setAdapter(adaptador);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedSport=(Sport)sportsList.toArray()[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        selectImageFromGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(Intent.createChooser(intent,"Select Picture"), 9000);
            }
        });
        createTeam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(getArguments()==null){
                    Team team=new Team(teamNameField.getText().toString(),
                            stadiumField.getText().toString(),
                            headquartersField.getText().toString(),
                            countryField.getText().toString(),
                            selectedSport.getSid(),
                            establishedField.getText().toString()
                            ,localImagePath);
                    System.out.println(getArguments());

                    System.out.println(team.toString());
                    roomdb.makeTeam(team);
                }else{
                    if(localImagePath==null){
                        localImagePath=imgURL;
                    }
                    Team team=new Team(teamNameField.getText().toString(),
                            stadiumField.getText().toString(),
                            headquartersField.getText().toString(),
                            countryField.getText().toString(),
                            selectedSport.getSid(),
                            establishedField.getText().toString()
                            ,localImagePath);
                    System.out.println(team);
                    roomdb.updateTeam(team);
                }

                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.flContent, new TeamFragment(),"TeamFragment").addToBackStack("TeamFragment").commit();

            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == REQUEST_WRITE_PERMISSION && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

        }
    }
    @Override
     public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 9000 && resultCode == RESULT_OK && null != data) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};

            Cursor cursor = getActivity().getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();
            ImageHandler handler=new ImageHandler(getContext());
            ;
            //localImagePath=handler.saveToInternalStorage();

            localImagePath=handler.saveToInternalStorage(handler.loadImageFromStorage(picturePath));

        }

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
