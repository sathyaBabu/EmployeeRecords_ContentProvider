package com.example.employeerecords.RoomDatabase;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.employeerecords.R;

import java.util.List;


public class EmployeeRecyclerAdapter extends RecyclerView.Adapter<EmployeeRecyclerAdapter.MyViewHolder>{
    private Context mcontext;
   private  List<EmployeeEntity> employeeList;

    public EmployeeRecyclerAdapter(Context context){

        mcontext = context;

    }
public void setData(List<EmployeeEntity> empList){
        employeeList = empList;
        notifyDataSetChanged();
}
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(mcontext).inflate(R.layout.employee_row,parent,false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        if (employeeList != null) {
            EmployeeEntity current = employeeList.get(position);
            holder.emp_id.setText(current.getEmp_id());
            holder.emp_name.setText(current.getEmp_name());
            holder.emp_dep.setText(current.getEmp_dep());
        } else {
            // Covers the case of data not being ready yet.
            holder.emp_id.setText("No data");
            holder.emp_name.setText("No data");
            holder.emp_dep.setText("No data");
        }
    }

    @Override
    public int getItemCount() {
        if(employeeList != null) {
            return employeeList.size();
        }
        else {
            return 0;
        }
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView emp_id, emp_name, emp_dep;

        private MyViewHolder(View itemView) {
            super(itemView);
            emp_id = (TextView) itemView.findViewById(R.id.emp_id);
            emp_name = (TextView) itemView.findViewById(R.id.emp_name);
            emp_dep = (TextView) itemView.findViewById(R.id.emp_dep);
        }
    }
}