package com.crowdsensing.sensordatacollector.view.myprojects;

import android.hardware.SensorManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.crowdsensing.sensordatacollector.R;
import com.crowdsensing.sensordatacollector.data.Sensor;
import com.crowdsensing.sensordatacollector.utils.Util;

import java.util.ArrayList;
import java.util.List;

public class CreateProjectActivity extends AppCompatActivity implements SensorListAdapter.OnCheckedListener
{

    public static final String ACTION_DISPLAY_SENSORS = "ACTION_DISPLAY_SENSORS";

    private SensorManager mSensorManager;
    private List<Sensor> selectedSensorList;
    List<Sensor> sensorList;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT_WATCH)
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensors);

        sensorList = Util.getAllSensors(this);
        selectedSensorList = new ArrayList<>();
        // Create Sensor Instance
//        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
//        List<Sensor> sensorList = mSensorManager.getSensorList(Sensor.TYPE_ALL);

        //StringBuilder sensorText = new StringBuilder();
//        ArrayList<String> sensorNames = new ArrayList<String>();
//        for (Sensor currentSensor : sensorList )
//        {
//            com.crowdsensing.sensordatacollector.data.Sensor sensor = new com.crowdsensing.sensordatacollector.data.Sensor();
//            sensorNames.add(currentSensor.getStringType());
            //sensorText.append(currentSensor.getName()).append(System.getProperty("line.separator"));
//        }

        //TextView sensorTextView = (TextView) findViewById(R.id.sensor_list);
        //sensorTextView.setText(sensorText);
        // initiate a ListView
        ListView listView = findViewById(R.id.listView);
        // set the adapter to fill the data in ListView
        SensorListAdapter customAdapter = new SensorListAdapter(getApplicationContext(), sensorList);
        customAdapter.setOnCheckedListener(this);
        listView.setAdapter(customAdapter);

        //CheckedTextView checkedTextView = (CheckedTextView) findViewById(R.id.checkedTextView);
    }

    @Override
    public void onItemChecked(int position, boolean isChecked) {
        if(isChecked){
            selectedSensorList.add(sensorList.get(position));
        }else {
            selectedSensorList.remove(sensorList.get(position));
        }
    }
}
