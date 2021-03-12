package com.example.idontknow;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.idontknow.application.GlobalReference;
import com.example.idontknow.callbacks.PromiseLinkedList;
import com.example.idontknow.room.Athlete;
import com.example.idontknow.room.Connections;
import com.example.idontknow.room.FireDB;
import com.example.idontknow.room.SoloMatch;
import com.example.idontknow.room.Sport;

import java.util.LinkedList;
import java.util.List;

public class SecondFragment extends Fragment {
    ImageView imageView;
    Button selectButton;
    final LinkedList list = new LinkedList();
    GlobalReference globals=GlobalReference.getInstance();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
// Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second, container, false);
    }
    public void onViewCreated(View view, Bundle savedInstanceState) {

        globals.setContext(getActivity().getApplicationContext());

        imageView=view.findViewById(R.id.psofaXristoTwra);
        LinearLayout l=view.findViewById(R.id.flexView2);
        Connections roomdb=Connections.getInstance(getActivity().getApplicationContext());


        FireDB.getInstance().getSoloMatch(new PromiseLinkedList(){
            @Override
            public void methodToCallBack(LinkedList fromCallback) {
                System.out.println(fromCallback.size());
                LinkedList<SoloMatch> s=(LinkedList<SoloMatch>) fromCallback.clone();
                while(!s.isEmpty()){
                    Button b=new Button(GlobalReference.getInstance().getContext());
                    b.setText(s.poll().getDate());
                    l.addView(b);
                }
                list.addAll(s);
                }
        });

        Glide.with(this).load("https://scontent.fath5-1.fna.fbcdn.net/v/t1.0-9/108183241_3122576387837373_2478768094608141340_n.jpg?_nc_cat=106&ccb=1-3&_nc_sid=09cbfe&_nc_ohc=l0-Lrp0-W2AAX_4n8ek&_nc_ht=scontent.fath5-1.fna&oh=0d0528e21df41b8dc8a1513e3050122b&oe=606E2B3D").into(imageView);
        selectButton=view.findViewById(R.id.button);
        selectButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                System.out.println("eimai o antwnis kai eimai mia aderfi :\\");
                Athlete athlete1= new Athlete("takhs","lastname","alvania","alvania",2,"454/1/44/","imgurl");
                Athlete athlete2= new Athlete("kostas","lastnameeee","alvaniaoleeee","alvaniaoleeee",2,"454/1/22244/","imgurl");
                roomdb.makeSport(new Sport(2,"football","male","team"));
                roomdb.makeAthlete(athlete1);
                roomdb.makeAthlete(athlete2);
                List<Athlete> list=roomdb.getAthletes();
                for(Athlete ath : list){
                    System.out.println(ath);


                }
            }
        });
    }

}