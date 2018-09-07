package com.nicholsonplumbingtx.v2.model.employee;

import com.nicholsonplumbingtx.v2.model.common.Status;
import com.nicholsonplumbingtx.v2.model.common.Type;

import java.sql.Date;

/**
 * Created by Ethan Orcutt on 11/13/2016.
 * Project D.U.K.E.
 */
public class EmployeeHistory
{
    private int employeeID;
    private Date entry_date;
    private String description;
    private Status employeeStatus;
    private Type employeeType;
    private double salary;
    private int salaryFrequency;

    public EmployeeHistory(int employeeID, Date entry_date, String description, Status employeeStatus, Type employeeType, double salary, int salaryFrequency)
    {
        this.employeeID = employeeID;
        this.entry_date = entry_date;
        this.description = description;
        this.employeeStatus = employeeStatus;
        this.employeeType = employeeType;
        this.salary = salary;
        this.salaryFrequency = salaryFrequency;
    }

    public int getEmployeeID()
    {
        return employeeID;
    }

    public void setEmployeeID(int employeeID)
    {
        this.employeeID = employeeID;
    }

    public Date getEntry_date()
    {
        return entry_date;
    }

    public void setEntry_date(Date entry_date)
    {
        this.entry_date = entry_date;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public Status getEmployeeStatus()
    {
        return employeeStatus;
    }

    public void setEmployeeStatus(Status employeeStatus)
    {
        this.employeeStatus = employeeStatus;
    }

    public Type getEmployeeType()
    {
        return employeeType;
    }

    public void setEmployeeType(Type employeeType)
    {
        this.employeeType = employeeType;
    }

    public double getSalary()
    {
        return salary;
    }

    public void setSalary(double salary)
    {
        this.salary = salary;
    }

    public int getSalaryFrequency()
    {
        return salaryFrequency;
    }

    public void setSalaryFrequency(int salaryFrequency)
    {
        this.salaryFrequency = salaryFrequency;
    }
}
