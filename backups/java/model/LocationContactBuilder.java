package com.nicholsonplumbingtx.v2.model;

public class LocationContactBuilder {
    private String firstName;
    private String lastName;
    private String fullName;
    private String phoneNumber;
    private String formattedPhoneNumber;
    private String email;
    private int locationContactID;
    private int clientID;

    public LocationContactBuilder setFirstName(String firstName)
    {
        this.firstName = firstName;
        return this;
    }

    public LocationContactBuilder setLastName(String lastName)
    {
        this.lastName = lastName;
        return this;
    }

    public LocationContactBuilder setFullName(String fullName)
    {
        this.fullName = fullName;
        return this;
    }

    public LocationContactBuilder setPhoneNumber(String phoneNumber)
    {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public LocationContactBuilder setEmail(String email)
    {
        this.email = email;
        return this;
    }

    public LocationContactBuilder setLocationContactID(int locationContactID)
    {
        this.locationContactID = locationContactID;
        return this;
    }

    public LocationContactBuilder setClientID(int clientID)
    {
        this.clientID = clientID;
        return this;
    }

    public LocationContact createLocationContact()
    {
        return new LocationContact(firstName, lastName, fullName, phoneNumber, email, locationContactID, clientID);
    }
}