package com.crowdsensing.sensordatacollector.utils;

import android.content.Context;

import com.crowdsensing.sensordatacollector.R;
import com.crowdsensing.sensordatacollector.data.Sensor;

import java.util.ArrayList;
import java.util.List;

public class Util {

    public static List<Sensor> getAllSensors(Context context){

        String[] sensorIds = context.getResources().getStringArray(R.array.sensor_ids);
        String[] sensorNames = context.getResources().getStringArray(R.array.sensor_names);

        List<Sensor> sensorList = new ArrayList<>();
        for (int i = 0; i < sensorIds.length; i++) {
            Sensor sensor = new Sensor();
            sensor.id = sensorIds[i];
            sensor.name = sensorNames[i];

            sensorList.add(sensor);
        }
        return sensorList;
    }
}
