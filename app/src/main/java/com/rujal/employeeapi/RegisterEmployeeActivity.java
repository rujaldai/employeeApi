package com.rujal.employeeapi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.rujal.employeeapi.api.EmployeeAPI;
import com.rujal.employeeapi.model.EmployeeClientDto;
import com.rujal.employeeapi.url.URL;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegisterEmployeeActivity extends AppCompatActivity {
    Button btnRegister;
    EditText etName,etSalary,etAge;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_employee);

        etName=findViewById(R.id.etName);
        etSalary=findViewById(R.id.etSalary);
        etAge=findViewById(R.id.etAge);
        btnRegister=findViewById(R.id.btnRegister);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register();
            }
        });

    }

    private void register(){
        String name = etName.getText().toString();
        float salary = Float.parseFloat(etSalary.getText().toString());
        int age = Integer.parseInt(etAge.getText().toString());

        EmployeeClientDto employee = new EmployeeClientDto(name, salary, age);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL.base_url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        EmployeeAPI employeeAPI = retrofit.create(EmployeeAPI.class);
        Call<EmployeeClientDto> call = employeeAPI.registerEmployee(employee);

        call.enqueue(new Callback<EmployeeClientDto>() {
            @Override
            public void onResponse(Call<EmployeeClientDto> call, Response<EmployeeClientDto> response) {
                Toast.makeText(RegisterEmployeeActivity.this, "You have been successfully registered with id :: " + response.body().getId(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<EmployeeClientDto> call, Throwable t) {
                Toast.makeText(RegisterEmployeeActivity.this, "Error", Toast.LENGTH_SHORT).show();
            }
        });
    }
}