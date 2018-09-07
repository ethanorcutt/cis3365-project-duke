package com.nicholsonplumbingtx.v2.model;

/**
 * Created by Ethan Orcutt on 10/12/2016.
 */
public class Contact
{
    private String firstName;
    private String lastName;
    private String fullName;
    private String phoneNumber;
    private String email;

    public Contact(String firstName, String lastName, String fullName, String phoneNumber, String email)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.fullName = firstName +  " " + lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
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

    public void setFullName(String fullName)
    {
        this.fullName = fullName;
    }

    public String getPhoneNumber()
    {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber)
    {
        this.phoneNumber = phoneNumber;
    }

    public String getFormattedPhoneNumber()
    {
        return String.format("(%s) %s-%s",
                phoneNumber.substring(0, 3),
                phoneNumber.substring(3, 6),
                phoneNumber.substring(6, 10));
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }
}
