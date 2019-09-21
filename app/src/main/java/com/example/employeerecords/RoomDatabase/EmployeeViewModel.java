package com.example.employeerecords.RoomDatabase;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class EmployeeViewModel extends AndroidViewModel {
   private EmployeeRepository mEmployeeRepository;
  private LiveData<List<EmployeeEntity>> mEmployeeData;
    public EmployeeViewModel(@NonNull Application application) {
        super(application);
        mEmployeeRepository = new EmployeeRepository(application);
        mEmployeeData = mEmployeeRepository.getAllEmployees();
    }
    public void insert(EmployeeEntity employeeEntity){
        mEmployeeRepository.insert(employeeEntity);
    }

    public void delete(EmployeeEntity employeeEntity){
        mEmployeeRepository.delete(employeeEntity);
    }

    public LiveData<List<EmployeeEntity>> getAllEmployeeData(){
       return mEmployeeData;
    }
}
