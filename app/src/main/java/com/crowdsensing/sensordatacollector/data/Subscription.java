package com.crowdsensing.sensordatacollector.data;

public class Subscription {

    public String id;
    public String projectId;
    public String userId;

    public Subscription(String projectId, String userId) {
        this.projectId = projectId;
        this.userId = userId;
    }
}
