package com.nicholsonplumbingtx.v2.model.employee;

public class EmployeeBuilder {
    private int employeeID;
    private int employeeStatusID;
    private int employeeTypeID;
    private String firstName;
    private String middleInitial;
    private String lastName;
    private String fullName;
    private String suffix;
    private double salary;
    private int salaryFrequencyID;
    private String addressLineOne;
    private String addressLineTwo;
    private String city;
    private int regionID;
    private int countryID;
    private String zipCode;
    private String phoneNumber;
    private String email;
    private String notes;

    public EmployeeBuilder setEmployeeID(int employeeID)
    {
        this.employeeID = employeeID;
        return this;
    }

    public EmployeeBuilder setEmployeeStatusID(int employeeStatusID)
    {
        this.employeeStatusID = employeeStatusID;
        return this;
    }

    public EmployeeBuilder setEmployeeTypeID(int employeeTypeID)
    {
        this.employeeTypeID = employeeTypeID;
        return this;
    }

    public EmployeeBuilder setFirstName(String firstName)
    {
        this.firstName = firstName;
        return this;
    }

    public EmployeeBuilder setMiddleInitial(String middleInitial)
    {
        this.middleInitial = middleInitial;
        return this;
    }

    public EmployeeBuilder setLastName(String lastName)
    {
        this.lastName = lastName;
        return this;
    }

    public EmployeeBuilder setSuffix(String suffix)
    {
        this.suffix = suffix;
        return this;
    }

    public EmployeeBuilder setSalary(double salary)
    {
        this.salary = salary;
        return this;
    }

    public EmployeeBuilder setSalaryFrequencyID(int salaryFrequencyID)
    {
        this.salaryFrequencyID = salaryFrequencyID;
        return this;
    }

    public EmployeeBuilder setAddressLineOne(String addressLineOne)
    {
        this.addressLineOne = addressLineOne;
        return this;
    }

    public EmployeeBuilder setAddressLineTwo(String addressLineTwo)
    {
        this.addressLineTwo = addressLineTwo;
        return this;
    }

    public EmployeeBuilder setCity(String city)
    {
        this.city = city;
        return this;
    }

    public EmployeeBuilder setRegionID(int regionID)
    {
        this.regionID = regionID;
        return this;
    }

    public EmployeeBuilder setCountryID(int countryID)
    {
        this.countryID = countryID;
        return this;
    }

    public EmployeeBuilder setZipCode(String zipCode)
    {
        this.zipCode = zipCode;
        return this;
    }

    public EmployeeBuilder setPhoneNumber(String phoneNumber)
    {
        this.phoneNumber = phoneNumber.replaceAll("[^a-zA-Z0-9]+", "");
        return this;
    }

    public EmployeeBuilder setEmail(String email)
    {
        this.email = email;
        return this;
    }

    public EmployeeBuilder setNotes(String notes)
    {
        this.notes = notes;
        return this;
    }

    public Employee createEmployee()
    {
        return new Employee(employeeID, employeeStatusID, employeeTypeID, firstName, middleInitial, lastName, suffix, salary, salaryFrequencyID, addressLineOne, addressLineTwo, city, regionID, countryID, zipCode, phoneNumber, email, notes);
    }
}