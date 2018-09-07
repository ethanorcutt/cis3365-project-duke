package com.nicholsonplumbingtx.v2.model.employee;

import com.nicholsonplumbingtx.v2.model.common.Status;
import com.nicholsonplumbingtx.v2.model.common.Type;

import java.sql.Date;

public class EmployeeHistoryBuilder {
    private int employeeID;
    private Date entry_date;
    private String description;
    private Status employeeStatus;
    private Type employeeType;
    private double salary;
    private int salaryFrequency;

    public EmployeeHistoryBuilder setEmployeeID(int employeeID)
    {
        this.employeeID = employeeID;
        return this;
    }

    public EmployeeHistoryBuilder setEntry_date(Date entry_date)
    {
        this.entry_date = entry_date;
        return this;
    }

    public EmployeeHistoryBuilder setDescription(String description)
    {
        this.description = description;
        return this;
    }

    public EmployeeHistoryBuilder setEmployeeStatus(Status employeeStatus)
    {
        this.employeeStatus = employeeStatus;
        return this;
    }

    public EmployeeHistoryBuilder setEmployeeType(Type employeeType)
    {
        this.employeeType = employeeType;
        return this;
    }

    public EmployeeHistoryBuilder setSalary(double salary)
    {
        this.salary = salary;
        return this;
    }

    public EmployeeHistoryBuilder setSalaryFrequency(int salaryFrequency)
    {
        this.salaryFrequency = salaryFrequency;
        return this;
    }

    public EmployeeHistory createEmployeeHistory()
    {
        return new EmployeeHistory(employeeID, entry_date, description, employeeStatus, employeeType, salary, salaryFrequency);
    }
}