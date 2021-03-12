package com.example.idontknow.application;

import android.content.Context;

import com.example.idontknow.room.FireDB;
import com.google.firebase.firestore.FirebaseFirestore;

public class GlobalReference {
    private static GlobalReference localInstance;
    private Context context;
    private GlobalReference(){

    }
    public static GlobalReference getInstance(){
        synchronized (GlobalReference.class){
            if(localInstance==null){
                localInstance=new GlobalReference();
            }
            return localInstance;
        }
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }
}
