package com.example.idontknow.controllers.athlete;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.idontknow.R;
import com.example.idontknow.utils.ImageHandler;

/**
 * A simple {@link Fragment} subclass.

 * create an instance of this fragment.
 */
public class AthleteOverviewFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER



    // TODO: Rename and change types of parameters
    private int id;
    private String firstName;
    private String lastName;
    private String cityOfOrigin;
    private String country;
    private String dateOfBirth;
    private String sportName;
    private String imgUrl;
    private Button editButton;
    public AthleteOverviewFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            id = getArguments().getInt("id");
            firstName = getArguments().getString("firstName");
            lastName = getArguments().getString("lastName");
            cityOfOrigin = getArguments().getString("cityOfOrigin");
            country = getArguments().getString("country");
            dateOfBirth = getArguments().getString("dateOfBirth");
            sportName = getArguments().getString("sportName");
            imgUrl = getArguments().getString("imgUrl");

        }
        System.out.println(sportName);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.athlete_overview_fragment, container, false);
    }
    public void onViewCreated(View view, Bundle savedInstanceState) {
        editButton=view.findViewById(R.id.athEditButton);
        ImageView img=view.findViewById(R.id.athleteImage);
        ImageHandler handler=new ImageHandler(getContext());
        img.setImageBitmap(handler.loadImageFromStorage(imgUrl));
        TextView firstNameField,lastNameField,cityOfOriginField,countryField,dateOfBirthField,sportNameField;
        firstNameField=view.findViewById(R.id.teamNameField);
        lastNameField=view.findViewById(R.id.athLastNameField);
        cityOfOriginField=view.findViewById(R.id.athCityOfOriginField);
        countryField=view.findViewById(R.id.athCountryField);
        dateOfBirthField=view.findViewById(R.id.athDateOfBirthField);
        sportNameField=view.findViewById(R.id.athSportNameField);

        firstNameField.setText("First name: "+firstName);
        lastNameField.setText("Last name: "+lastName);
        cityOfOriginField.setText("City: "+cityOfOrigin);
        countryField.setText("Country: "+country);
        dateOfBirthField.setText("Date of birth: "+dateOfBirth);
        sportNameField.setText("Sport: "+sportName);

        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle argBundle=new Bundle();
                argBundle.putInt("id",id);
                argBundle.putString("firstName",firstName);
                argBundle.putString("lastName",lastName);
                argBundle.putString("cityOfOrigin",cityOfOrigin);
                argBundle.putString("country",country);
                argBundle.putString("dateOfBirth",dateOfBirth);
                argBundle.putString("sportName",sportName);
                argBundle.putString("imgUrl",imgUrl);
                CreateAthleteFragment editAthlete=new CreateAthleteFragment();

                editAthlete.setArguments(argBundle);
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.flContent, editAthlete,"editAthlete").addToBackStack("editAthlete").commit();
            }
        });
    }
}