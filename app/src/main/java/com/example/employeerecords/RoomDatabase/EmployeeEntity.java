package com.example.employeerecords.RoomDatabase;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "EmployeeDatabase")
public class EmployeeEntity {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    private String emp_id;

    @ColumnInfo(name = "name")
    private String emp_name;

    @ColumnInfo(name = "department")
    private String emp_dep;

    public EmployeeEntity(@NonNull String  emp_id, String emp_name, String emp_dep) {

        this.emp_id = emp_id;
        this.emp_name = emp_name;
        this.emp_dep = emp_dep;
    }

    public String getEmp_name() {
        return emp_name;
    }

    public void setEmp_name(String emp_name) {
        this.emp_name = emp_name;
    }
@NonNull
    public String getEmp_id() {
        return emp_id;
    }


    public void setEmp_id( @NonNull String emp_id) {
        this.emp_id = emp_id;
    }

    public String getEmp_dep() {
        return emp_dep;
    }

    public void setEmp_dep(String emp_dep) {
        this.emp_dep = emp_dep;
    }
}
