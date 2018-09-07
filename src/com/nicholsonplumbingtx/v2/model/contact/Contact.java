package com.nicholsonplumbingtx.v2.model.contact;

import com.nicholsonplumbingtx.v2.model.common.Status;
import com.nicholsonplumbingtx.v2.model.common.Type;

/**
 * Created by Ethan Orcutt on 10/12/2016.
 * Project D.U.K.E.
 */
public class Contact
{
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private Status status;
    private Type type;
    private String companyName;

    public Contact(String firstName, String lastName, String phoneNumber, String email, Status status, Type type, String companyName)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.status = status;
        this.type = type;
        this.companyName = companyName;
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
        return firstName + ' ' + lastName;
    }

    public String getPhoneNumber()
    {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber)
    {
        this.phoneNumber = phoneNumber.replaceAll("[^a-zA-Z0-9]+", "");
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

    public Status getStatus()
    {
        return status;
    }

    public void setStatus(Status status)
    {
        this.status = status;
    }

    public Type getType()
    {
        return type;
    }

    public void setType(Type type)
    {
        this.type = type;
    }

    public String getCompanyName()
    {
        return companyName;
    }

    public void setCompanyName(String companyName)
    {
        this.companyName = companyName;
    }
}
