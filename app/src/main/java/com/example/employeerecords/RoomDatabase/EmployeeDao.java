package com.example.employeerecords.RoomDatabase;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface EmployeeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertEmployee(EmployeeEntity employeeEntity);

    @Delete
    void deleteEmployee(EmployeeEntity employeeEntity);

    @Query("SELECT * FROM EmployeeDatabase")
    LiveData<List<EmployeeEntity>> getEmployeeData();
}
