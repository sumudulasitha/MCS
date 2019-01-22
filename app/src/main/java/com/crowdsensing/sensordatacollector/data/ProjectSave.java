package com.crowdsensing.sensordatacollector.data;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import java.util.Date;
import java.util.List;

@Entity
public class ProjectSave {

    @PrimaryKey(autoGenerate = true)
    public int id;
    public String name;
    @Ignore
    public List<Integer> sensors;
    @Ignore
    public Date startDate;
    @Ignore
    public Date endDate;
    public String startTime;
    public String endTime;
    public String sensorList;
}
