package com.rujal.employeeapi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.rujal.employeeapi.api.EmployeeAPI;
import com.rujal.employeeapi.model.Employee;
import com.rujal.employeeapi.model.EmployeeClientDto;
import com.rujal.employeeapi.url.URL;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UpdateDeleteActivity extends AppCompatActivity {

    EditText etName, etAge, etSalary, etEmployee;
    Button btnSearch, btnUpdate, btnDelete;
    Retrofit retrofit;
    EmployeeAPI employeeAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_delete);


        etName = findViewById(R.id.etEName);
        etAge = findViewById(R.id.etEAge);
        etSalary = findViewById(R.id.etESalary);
        etEmployee = findViewById(R.id.etEmployee);

        btnSearch = findViewById(R.id.btnSearch);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);


        btnSearch.setOnClickListener(i -> loadData());

        btnUpdate.setOnClickListener(i -> updateEmployee());
        btnDelete.setOnClickListener(i -> deleteEmployee());

    }

    private void createInstance() {
        retrofit = new Retrofit.Builder()
                .baseUrl(URL.base_url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        employeeAPI = retrofit.create(EmployeeAPI.class);

    }


    private void loadData() {
        createInstance();
        Call<Employee> listCall = employeeAPI.getEmployeeByID(Integer.parseInt(etEmployee.getText().toString()));


        listCall.enqueue(new Callback<Employee>() {
            @Override
            public void onResponse(Call<Employee> call, Response<Employee> response) {
                etName.setText(response.body().getEmployee_name());
                etAge.setText(String.valueOf(response.body().getEmployee_age()));
                etSalary.setText(String.valueOf(response.body().getEmployee_salary()));
            }

            @Override
            public void onFailure(Call<Employee> call, Throwable t) {
                Toast.makeText(UpdateDeleteActivity.this, "Error", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void updateEmployee(){
        createInstance();
        EmployeeClientDto employee = new EmployeeClientDto(
                etName.getText().toString(),
                Float.parseFloat( etSalary.getText().toString()),
                Integer.parseInt(etAge.getText().toString())

        );
        Call<Void> voidCall = employeeAPI.updateEmployee(Integer.parseInt(etEmployee.getText().toString()),employee);

        voidCall.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Toast.makeText(UpdateDeleteActivity.this, "Updated Successfully", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(UpdateDeleteActivity.this, "Error", Toast.LENGTH_SHORT).show();

            }
        });

    }

    private void deleteEmployee(){
        createInstance();
        Call<Void> voidCall = employeeAPI.deleteEmployee(Integer.parseInt(etEmployee.getText().toString()));

        voidCall.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Toast.makeText(UpdateDeleteActivity.this, "Successfully deleted", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(UpdateDeleteActivity.this, "Error" + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
