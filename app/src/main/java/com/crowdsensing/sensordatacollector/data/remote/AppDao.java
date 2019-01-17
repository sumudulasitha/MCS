package com.crowdsensing.sensordatacollector.data.remote;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.crowdsensing.sensordatacollector.data.Project;

import java.util.List;

@Dao
public interface AppDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertProject(Project project);

    @Query("SELECT * FROM Project")
    List<Project> getProjects();

    @Delete
    void deleteProject(Project project);
}
