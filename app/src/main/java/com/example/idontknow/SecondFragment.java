package com.example.idontknow;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;

public class SecondFragment extends Fragment {
    ImageView imageView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
// Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second, container, false);
    }
    public void onViewCreated(View view, Bundle savedInstanceState) {
        imageView=view.findViewById(R.id.psofaXristoTwra);
        LinearLayout l=view.findViewById(R.id.flexView2);


        Glide.with(this).load("https://scontent.fath5-1.fna.fbcdn.net/v/t1.0-9/108183241_3122576387837373_2478768094608141340_n.jpg?_nc_cat=106&ccb=1-3&_nc_sid=09cbfe&_nc_ohc=l0-Lrp0-W2AAX_4n8ek&_nc_ht=scontent.fath5-1.fna&oh=0d0528e21df41b8dc8a1513e3050122b&oe=606E2B3D").into(imageView);

    }
}