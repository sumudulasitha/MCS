package com.crowdsensing.sensordatacollector.data.remote;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.crowdsensing.sensordatacollector.data.Project;

@Database(entities = {Project.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    private static volatile AppDatabase INSTANCE;

    public static AppDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "sensor_data")
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    public abstract AppDao AppDao();
}
