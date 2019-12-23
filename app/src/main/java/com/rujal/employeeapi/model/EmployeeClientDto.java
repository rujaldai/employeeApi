package com.rujal.employeeapi.model;

public class EmployeeClientDto {
    String name;
    float salary;
    int age;
    int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getSalary() {
        return salary;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public EmployeeClientDto(String name, float salary, int age) {
        this.name = name;
        this.salary = salary;
        this.age = age;
    }

    public EmployeeClientDto(String name, float salary, int age, int id) {
        this.name = name;
        this.salary = salary;
        this.age = age;
        this.id = id;
    }
}
