package com.example.employeerecords.RoomDatabase;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class EmployeeRepository {
    private EmployeeDao employeeDao;
    private LiveData<List<EmployeeEntity>> mAllEmployees;
    EmployeeRepository(Application application){
        EmployeeRoomDatabase db = EmployeeRoomDatabase.getDatabase(application);
        employeeDao = db.employeeDao();
        mAllEmployees = employeeDao.getEmployeeData();
    }
    //gets the details of all the employees
    public LiveData<List<EmployeeEntity>> getAllEmployees(){
        return mAllEmployees;
    }

    //insert a employee data
    public void insert (EmployeeEntity employeeEntity){
        new insertAsyncTask(employeeDao).execute(employeeEntity);
    }

    private static class insertAsyncTask extends AsyncTask<EmployeeEntity,Void,Void> {

      private EmployeeDao mEmployeeDao;

      insertAsyncTask(EmployeeDao employeeDao){
          mEmployeeDao = employeeDao;
      }
        @Override
        protected Void doInBackground(final EmployeeEntity... employeeEntities) {
          mEmployeeDao.insertEmployee(employeeEntities[0]);
            return null;
        }
    }
//delete an employee data
    public void delete (EmployeeEntity employeeEntity){
        new deleteAsynctask(employeeDao).execute(employeeEntity);
    }
    private static class deleteAsynctask extends AsyncTask<EmployeeEntity,Void,Void>{
        private EmployeeDao mEmployeeDao;

        deleteAsynctask(EmployeeDao employeeDao){
            mEmployeeDao = employeeDao;
        }

        @Override
        protected Void doInBackground(final EmployeeEntity... employeeEntities) {
            mEmployeeDao.deleteEmployee(employeeEntities[0]);
            return null;
        }
    }
}
