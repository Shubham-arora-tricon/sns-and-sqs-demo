package com.triconinfotech.aws.sqs.entity;

import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;

@Entity
@Component
public class Employee {

    @Id
    private int empId;
    private String empName;
    private long salary;
    private String department;
    private Timestamp timestamp;

    public Employee() {
    }

    public Employee(int empId, String empName, long salary, String department, Timestamp timestamp) {
        this.empId = empId;
        this.empName = empName;
        this.salary = salary;
        this.department = department;
        this.timestamp = timestamp;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public int getEmpId() {
        return empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public long getSalary() {
        return salary;
    }

    public void setSalary(long salary) {
        this.salary = salary;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "empId='" + empId + '\'' +
                ", empName='" + empName + '\'' +
                ", salary=" + salary +
                ", department='" + department + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}
