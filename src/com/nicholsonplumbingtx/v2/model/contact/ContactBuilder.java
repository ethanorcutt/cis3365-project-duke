package com.nicholsonplumbingtx.v2.model.contact;

import com.nicholsonplumbingtx.v2.model.common.Status;
import com.nicholsonplumbingtx.v2.model.common.Type;

public class ContactBuilder {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private Status status;
    private Type type;
    private String companyName;

    public ContactBuilder setFirstName(String firstName)
    {
        this.firstName = firstName;
        return this;
    }

    public ContactBuilder setLastName(String lastName)
    {
        this.lastName = lastName;
        return this;
    }

    public ContactBuilder setPhoneNumber(String phoneNumber)
    {
        this.phoneNumber = phoneNumber.replaceAll("[^a-zA-Z0-9]+", "");
        return this;
    }

    public ContactBuilder setEmail(String email)
    {
        this.email = email;
        return this;
    }

    public ContactBuilder setStatus(Status status)
    {
        this.status = status;
        return this;
    }

    public ContactBuilder setType(Type type)
    {
        this.type = type;
        return this;
    }

    public ContactBuilder setCompanyName(String companyName)
    {
        this.companyName = companyName;
        return this;
    }

    public Contact createContact()
    {
        return new Contact(firstName, lastName, phoneNumber, email, status, type, companyName);
    }
}