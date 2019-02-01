package com.crowdsensing.sensordatacollector.data;

import android.os.Parcel;
import android.os.Parcelable;

public class Project implements Parcelable {

    public String id;
    public String name;
    public long startDate;
    public long endDate;
    public String startTime;
    public String endTime;
    public String sensorList;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.name);
        dest.writeLong(this.startDate);
        dest.writeLong(this.endDate);
        dest.writeString(this.startTime);
        dest.writeString(this.endTime);
        dest.writeString(this.sensorList);
    }

    public Project() {
    }

    protected Project(Parcel in) {
        this.id = in.readString();
        this.name = in.readString();
        this.startDate = in.readLong();
        this.endDate = in.readLong();
        this.startTime = in.readString();
        this.endTime = in.readString();
        this.sensorList = in.readString();
    }

    public static final Parcelable.Creator<Project> CREATOR = new Parcelable.Creator<Project>() {
        @Override
        public Project createFromParcel(Parcel source) {
            return new Project(source);
        }

        @Override
        public Project[] newArray(int size) {
            return new Project[size];
        }
    };
}
