package com.crowdsensing.sensordatacollector.view.subscribedprojects;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.crowdsensing.sensordatacollector.R;
import com.crowdsensing.sensordatacollector.data.Project;
import com.crowdsensing.sensordatacollector.data.Subscription;
import com.crowdsensing.sensordatacollector.data.remote.SendData;
import com.crowdsensing.sensordatacollector.utils.Util;

public class SubscribeProjectActivity extends AppCompatActivity {

    private static final int REQUEST_READ_PHONE_STATE = 1;
    private Toolbar toolbar;

    private TextView projectNameTextView;
    private TextView startDateTextView;
    private TextView endDateTextView;
    private TextView startTimeTextView;
    private TextView endTimeTextView;

    private SendData sendData;
    Project mProject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subscribe_project);

        sendData = new SendData(this);

        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(getResources().getColor(android.R.color.white));
        toolbar.setTitle("Subscribe Project");
        setSupportActionBar(toolbar);

        projectNameTextView = findViewById(R.id.projectNameTextView);
        startDateTextView = findViewById(R.id.startDateTextView);
        endDateTextView = findViewById(R.id.endDateTextView);
        startTimeTextView = findViewById(R.id.startTimeTextView);
        endTimeTextView = findViewById(R.id.endTimeTextView);

        mProject = getIntent().getParcelableExtra("project");
        setData();
    }

    private void setData() {
        if (mProject != null) {
            projectNameTextView.setText(mProject.name);
            startDateTextView.setText(Util.getDate(mProject.startDate));
            endDateTextView.setText(Util.getDate(mProject.endDate));
            startTimeTextView.setText(mProject.startTime);
            endTimeTextView.setText(mProject.endTime);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.actionSubscribe:
                subscribeProject();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void subscribeProject() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_PHONE_STATE}, REQUEST_READ_PHONE_STATE);
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                sendData.subscribeProject(new Subscription(mProject.id, Build.getSerial()));
            } else {
                sendData.subscribeProject(new Subscription(mProject.id, Build.SERIAL));
            }
            finish();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_subscribe, menu);
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        switch (requestCode) {
            case REQUEST_READ_PHONE_STATE: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // permissions granted.
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
                            return;
                        }
                        sendData.subscribeProject(new Subscription(mProject.id, Build.getSerial()));
                    } else {
                        sendData.subscribeProject(new Subscription(mProject.id, Build.SERIAL));
                    }
                    finish();
                } //permissions list of don't granted permission
            }

        }
    }
}
