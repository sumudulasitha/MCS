package com.crowdsensing.sensordatacollector.data.remote;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.crowdsensing.sensordatacollector.data.Project;
import com.crowdsensing.sensordatacollector.data.ProjectSave;

import java.util.List;

@Dao
public interface AppDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertProject(ProjectSave project);

    @Query("SELECT * FROM ProjectSave")
    List<ProjectSave> getProjects();

    @Delete
    void deleteProject(ProjectSave project);
}
