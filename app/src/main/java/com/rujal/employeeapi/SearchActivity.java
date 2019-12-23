package com.rujal.employeeapi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.rujal.employeeapi.api.EmployeeAPI;
import com.rujal.employeeapi.model.Employee;
import com.rujal.employeeapi.url.URL;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SearchActivity extends AppCompatActivity {
    EditText etEmpNo;
    Button btnSearch;
    TextView tvData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        etEmpNo=findViewById(R.id.etEmpNo);
        btnSearch=findViewById(R.id.btnSearch);
        tvData=findViewById(R.id.tvData);

        btnSearch.setOnClickListener(i -> loadData());

    }
    private void loadData(){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL.base_url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        EmployeeAPI employeeAPI = retrofit.create(EmployeeAPI.class);
        try {
            Call<Employee> listCall = employeeAPI.getEmployeeByID(Integer.parseInt(etEmpNo.getText().toString()));

            //Asynchronous call
            listCall.enqueue(new Callback<Employee>() {
                @Override
                public void onResponse(Call<Employee> call, Response<Employee> response) {
                    Employee responseEmployee = response.body();
                    System.out.println(responseEmployee.toString());
                    Toast.makeText(SearchActivity.this, responseEmployee.toString(), Toast.LENGTH_SHORT).show();
                    String content = "";
                    tvData.setText("");

                    content += "Id :" + responseEmployee.getId() + "\n";
                    content += "Name :" + responseEmployee.getEmployee_name() + "\n";
                    content += "Age :" + responseEmployee.getEmployee_age() + "\n";
                    content += "Salary :" + responseEmployee.getEmployee_salary() + "\n";

                    tvData.setText(content);
                }

                @Override
                public void onFailure(Call<Employee> call, Throwable t) {
                    Toast.makeText(SearchActivity.this, "Error " + t.getMessage(), Toast.LENGTH_SHORT).show();

                }
            });
        } catch (NumberFormatException ex) {
            System.out.println("Number should be put");
        }



    }
}
