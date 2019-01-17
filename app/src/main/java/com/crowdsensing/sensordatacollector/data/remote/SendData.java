package com.crowdsensing.sensordatacollector.data.remote;

import android.content.Context;

import com.crowdsensing.sensordatacollector.data.Project;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class SendData {

    private DatabaseReference mDatabase;
    private Context context;

    //    private static SendData INSTANCE;
    public SendData(Context context) {
//        if(INSTANCE == null){
//            INSTANCE = new SendData();
        this.context = context;
//        mDatabase = FirebaseDatabase.getInstance().getReference();

//        }
    }

//    public static SendData getInstance(Context context){
//        return INSTANCE;
//    }

    public void sendProject(Project project) {

        if(project != null) {
            mDatabase.child("project").setValue(project);
            mDatabase.push();
        }
    }
}
