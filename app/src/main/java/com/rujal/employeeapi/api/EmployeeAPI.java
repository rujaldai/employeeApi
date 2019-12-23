package com.rujal.employeeapi.api;

import com.rujal.employeeapi.model.Employee;
import com.rujal.employeeapi.model.EmployeeClientDto;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface EmployeeAPI {

    @GET("employees")
    Call<List<Employee>> getAllEmployees();

    @GET("employee/{empID}")
    Call<Employee> getEmployeeByID(@Path("empID") int empID);

    @POST("create")
    Call<EmployeeClientDto> registerEmployee(@Body EmployeeClientDto emp);

    @PUT("update/{empID}")
    Call<Void> updateEmployee(@Path("empID") int empId, @Body EmployeeClientDto emp);

    @DELETE("delete/{empID}")
    Call<Void> deleteEmployee(@Path("empID") int empId);
}
