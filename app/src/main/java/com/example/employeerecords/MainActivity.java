package com.example.employeerecords;

import androidx.appcompat.app.AppCompatActivity;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import android.widget.Button;
import android.widget.EditText;


import com.example.employeerecords.RoomDatabase.EmployeeEntity;
import com.example.employeerecords.RoomDatabase.EmployeeRecyclerAdapter;
import com.example.employeerecords.RoomDatabase.EmployeeViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private EmployeeViewModel employeeViewModel;
    public EditText emp_name, emp_id, emp_dep;
    public Button create, delete, slice;
    public RecyclerView empData;
    public EmployeeRecyclerAdapter empAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        emp_name = findViewById(R.id.et_emp_name);
        emp_id = findViewById(R.id.et_emp_id);
        emp_dep = findViewById(R.id.et_emp_dep);
        empData = findViewById(R.id.emp_rec_data);

        //Buttons
        create = findViewById(R.id.bt_create);
        delete = findViewById(R.id.bt_delete);
        slice = findViewById(R.id.bt_slice);

        create.setOnClickListener(this);
        delete.setOnClickListener(this);
        slice.setOnClickListener(this);

        employeeViewModel = ViewModelProviders.of(this).get(EmployeeViewModel.class);

        //recycler view related code
          empAdapter = new EmployeeRecyclerAdapter(this);
          empData.setAdapter(empAdapter);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        empData.setLayoutManager(llm);


        //this arguement giving error that main activity shoud implement life cycle owner
//        solved it by replacing app compat implementation depenency with
        //    implementation 'androidx.appcompat:appcompat:1.1.0-rc01'
            employeeViewModel.getAllEmployeeData().observe( this, new Observer<List<EmployeeEntity>>() {
                @Override
                public void onChanged(List<EmployeeEntity> employeeEntities) {
                    Log.d("tag"," Data : "+employeeEntities.get(0).getEmp_name());
                    empAdapter.setData(employeeEntities);
                }
            });

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){

            case R.id.bt_create :
                //create the employee record
              //  Toast.makeText(mcontext, "create employee record", Toast.LENGTH_SHORT).show();

                employeeViewModel.insert(new EmployeeEntity(emp_id.getText().toString(),emp_name.getText().toString(),emp_dep.getText().toString()));
              break;
            case R.id.bt_delete :
                //create the employee record
              //  Toast.makeText(mcontext, "delete employee record", Toast.LENGTH_SHORT).show();
                employeeViewModel.delete(new EmployeeEntity(emp_id.getText().toString(),emp_name.getText().toString(),emp_dep.getText().toString()));
               break;
            case R.id.bt_slice :

                String uri = "slice-content://com.example.employeerecords/EmployeeSliceProvider/hello";
                Intent sliceIntent = new Intent (Intent.ACTION_VIEW, Uri.parse(uri));
                startActivity(sliceIntent);
                break;

        }

    }
}
