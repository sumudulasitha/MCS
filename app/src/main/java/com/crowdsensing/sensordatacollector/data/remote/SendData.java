package com.crowdsensing.sensordatacollector.data.remote;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.crowdsensing.sensordatacollector.data.Project;
import com.crowdsensing.sensordatacollector.data.Subscription;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class SendData {

    private DatabaseReference mDatabase;
    private OnProjectsListReceived onProjectsListReceived;
//    private Context context;

    public interface OnProjectsListReceived{
        void onProjectListReceived(List<Project> projectList);
    }

    public void setOnProjectListReceived(OnProjectsListReceived onProjectListReceived){
        this.onProjectsListReceived = onProjectListReceived;
    }
//        private static SendData INSTANCE;
    public SendData(Context context) {
//        if(INSTANCE == null){
//            INSTANCE = new SendData(context);
//            this.context = context;
            mDatabase = FirebaseDatabase.getInstance().getReference();

//        }
    }

    public void sendProject(Project project) {

        if(project != null) {
            String key = mDatabase.child("project").push().getKey();
            project.id = key;

            Map<String, Object> child = new HashMap<>();
            child.put("/project/"+ key, project);

            mDatabase.updateChildren(child);
        }
    }

    public void getProjects(){

        final List<Project> projectList = new ArrayList<>();

        mDatabase.child("project").addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot snapShot: dataSnapshot.getChildren()) {
                    projectList.add(snapShot.getValue(Project.class));
                }

                onProjectsListReceived.onProjectListReceived(projectList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void subscribeProject(Subscription subscription){
        if(subscription != null) {
            String key = mDatabase.child("subscription").push().getKey();
            Log.i("Key", key);
            subscription.id = key;

            Map<String, Object> child = new HashMap<>();
            child.put("/subscription/"+ key, subscription);

            mDatabase.updateChildren(child);
        }
    }

    public void removeListener(){
//        mDatabase.removeEventListener();
    }
}
