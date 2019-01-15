package com.crowdsensing.sensordatacollector.data;

import java.util.Date;
import java.util.List;

public class Project {

    public int id;
    public String name;
    public List<Integer> sensors;
    public Date startDate;
    public Date endDate;
    public String startTime;
    public String endTime;
}
