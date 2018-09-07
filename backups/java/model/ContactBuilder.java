package com.nicholsonplumbingtx.v2.model;

public class ContactBuilder
{
    private String firstName;
    private String lastName;
    private String fullName;
    private String phoneNumber;
    private String email;

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

    public ContactBuilder setFullName(String fullName)
    {
        this.fullName = fullName;
        return this;
    }

    public ContactBuilder setPhoneNumber(String phoneNumber)
    {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public ContactBuilder setEmail(String email)
    {
        this.email = email;
        return this;
    }

    public Contact createContact()
    {
        return new Contact(firstName, lastName, fullName, phoneNumber, email);
    }
}