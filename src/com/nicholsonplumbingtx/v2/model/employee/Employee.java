package com.nicholsonplumbingtx.v2.model.employee;

/**
 * Created by Ethan Orcutt on 10/2/2016.
 * Project D.U.K.E.
 */
public class Employee
{
    private int employeeID;
    private int employeeStatusID;
    private int employeeTypeID;
    private String firstName;
    private String middleInitial;
    private String lastName;
    private final String fullName;
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

    public Employee(int employeeID, int employeeStatusID, int employeeTypeID, String firstName, String middleInitial, String lastName, String suffix, double salary, int salaryFrequencyID, String addressLineOne, String addressLineTwo, String city, int regionID, int countryID, String zipCode, String phoneNumber, String email, String notes)
    {
        this.employeeID = employeeID;
        this.employeeStatusID = employeeStatusID;
        this.employeeTypeID = employeeTypeID;
        this.firstName = firstName;
        this.middleInitial = middleInitial;
        this.lastName = lastName;
        this.fullName = firstName + " " + lastName;
        this.suffix = suffix;
        this.salary = salary;
        this.salaryFrequencyID = salaryFrequencyID;
        this.addressLineOne = addressLineOne;
        this.addressLineTwo = addressLineTwo;
        this.city = city;
        this.regionID = regionID;
        this.countryID = countryID;
        this.zipCode = zipCode;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.notes = notes;
    }

    public int getEmployeeID()
    {
        return employeeID;
    }

    public void setEmployeeID(int employeeID)
    {
        this.employeeID = employeeID;
    }

    public int getEmployeeStatusID()
    {
        return employeeStatusID;
    }

    public void setEmployeeStatusID(int employeeStatusID)
    {
        this.employeeStatusID = employeeStatusID;
    }

    public int getEmployeeTypeID()
    {
        return employeeTypeID;
    }

    public void setEmployeeTypeID(int employeeTypeID)
    {
        this.employeeTypeID = employeeTypeID;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public String getMiddleInitial()
    {
        return middleInitial;
    }

    public void setMiddleInitial(String middleInitial)
    {
        this.middleInitial = middleInitial;
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public String getFullName()
    {
        return fullName;
    }

    public String getSuffix()
    {
        return suffix;
    }

    public void setSuffix(String suffix)
    {
        this.suffix = suffix;
    }

    public double getSalary()
    {
        return salary;
    }

    public void setSalary(double salary)
    {
        this.salary = salary;
    }

    public int getSalaryFrequencyID()
    {
        return salaryFrequencyID;
    }

    public void setSalaryFrequencyID(int salaryFrequencyID)
    {
        this.salaryFrequencyID = salaryFrequencyID;
    }

    public String getAddressLineOne()
    {
        return addressLineOne;
    }

    public void setAddressLineOne(String addressLineOne)
    {
        this.addressLineOne = addressLineOne;
    }

    public String getAddressLineTwo()
    {
        return addressLineTwo;
    }

    public void setAddressLineTwo(String addressLineTwo)
    {
        this.addressLineTwo = addressLineTwo;
    }

    public String getCity()
    {
        return city;
    }

    public void setCity(String city)
    {
        this.city = city;
    }

    public int getRegionID()
    {
        return regionID;
    }

    public void setRegionID(int regionID)
    {
        this.regionID = regionID;
    }

    public int getCountryID()
    {
        return countryID;
    }

    public void setCountryID(int countryID)
    {
        this.countryID = countryID;
    }

    public String getZipCode()
    {
        return zipCode;
    }

    public void setZipCode(String zipCode)
    {
        this.zipCode = zipCode;
    }

    public String getPhoneNumber()
    {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber)
    {
        this.phoneNumber = phoneNumber.replaceAll("[^a-zA-Z0-9]+", "");
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getNotes()
    {
        return notes;
    }

    public void setNotes(String notes)
    {
        this.notes = notes;
    }

    public String getFormattedPhoneNumber()
    {
        return String.format("(%s) %s-%s",
                phoneNumber.substring(0, 3),
                phoneNumber.substring(3, 6),
                phoneNumber.substring(6, 10));
    }

    @Override
    public String toString()
    {
        return getFullName();
    }
}
