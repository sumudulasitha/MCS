package com.crowdsensing.sensordatacollector.utils;

import android.content.Context;
import android.widget.Toast;

import com.crowdsensing.sensordatacollector.R;
import com.crowdsensing.sensordatacollector.data.Sensor;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Util {

    public static List<Sensor> getAllSensors(Context context){

        String[] sensorIds = context.getResources().getStringArray(R.array.action_ids);
        String[] sensorNames = context.getResources().getStringArray(R.array.action_names);

        List<Sensor> sensorList = new ArrayList<>();
        for (int i = 0; i < sensorIds.length; i++) {
            Sensor sensor = new Sensor();
            sensor.id = sensorIds[i];
            sensor.name = sensorNames[i];

            sensorList.add(sensor);
        }
        return sensorList;
    }

    public static long getMilliseconds(String date){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date dateString = null;
        try {
            dateString = sdf.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dateString != null ? dateString.getTime() : 0;
    }

    public static String getSensorListAsString(List<Sensor> sensorList, Context context){
        StringBuilder sb = new StringBuilder();
        if(sensorList != null && sensorList.size() > 0) {
            for (Sensor sensor : sensorList) {
                sb.append(sensor.id + ",");
            }
        }else {
            Toast.makeText(context, "Please select at least one sensor", Toast.LENGTH_SHORT).show();
        }
        return sb.toString();
    }
}
