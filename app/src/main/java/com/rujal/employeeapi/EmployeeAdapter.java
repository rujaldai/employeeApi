package com.rujal.employeeapi;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rujal.employeeapi.model.Employee;

import java.util.List;

public class EmployeeAdapter extends RecyclerView.Adapter<EmployeeAdapter.EmployeeViewHolder> {

    private List<Employee> employeeList;

    public EmployeeAdapter(List<Employee> employeeList) {
        this.employeeList = employeeList;

    }

    @NonNull
    @Override
    public EmployeeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_employee_details, parent, false);
        return new EmployeeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EmployeeViewHolder holder, int position) {
        Employee employee = employeeList.get(position);
        holder.tvid.setText(String.valueOf(employee.getId()));
        holder.tvname.setText(employee.getEmployee_name());
        holder.tvage.setText(String.valueOf(employee.getEmployee_age()));
        holder.tvsalary.setText(String.valueOf(employee.getEmployee_salary()));
        holder.tvprofile.setText(employee.getProfile_image());
    }

    @Override
    public int getItemCount() {
        return employeeList.size();
    }


    public class EmployeeViewHolder extends RecyclerView.ViewHolder {
        TextView tvid, tvname, tvage, tvsalary, tvprofile;

        public EmployeeViewHolder(@NonNull View itemView) {
            super(itemView);

            tvid = itemView.findViewById(R.id.tvId);
            tvname = itemView.findViewById(R.id.tvName);
            tvage = itemView.findViewById(R.id.tvAge);
            tvsalary = itemView.findViewById(R.id.tvSalary);
            tvprofile = itemView.findViewById(R.id.tvProfile);

        }

    }

}
