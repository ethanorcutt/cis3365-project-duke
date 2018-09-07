package com.nicholsonplumbingtx.v2.model;

public class ClientBuilder {
    private int clientID;
    private int statusID;
    private int typeID;
    private String firstName;
    private String lastName;
    private String companyName;
    private String clientNotes;
    private String phoneNumber;
    private String addressLineOne;
    private String addressLineTwo;
    private String city;
    private String postal;
    private int region;
    private int country;

    public ClientBuilder setClientID(int clientID)
    {
        this.clientID = clientID;
        return this;
    }

    public ClientBuilder setStatusID(int statusID)
    {
        this.statusID = statusID;
        return this;
    }

    public ClientBuilder setTypeID(int typeID)
    {
        this.typeID = typeID;
        return this;
    }

    public ClientBuilder setFirstName(String firstName)
    {
        this.firstName = firstName;
        return this;
    }

    public ClientBuilder setLastName(String lastName)
    {
        this.lastName = lastName;
        return this;
    }

    public ClientBuilder setCompanyName(String companyName)
    {
        this.companyName = companyName;
        return this;
    }

    public ClientBuilder setClientNotes(String clientNotes)
    {
        this.clientNotes = clientNotes;
        return this;
    }

    public ClientBuilder setPhoneNumber(String phoneNumber)
    {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public ClientBuilder setAddressLineOne(String addressLineOne)
    {
        this.addressLineOne = addressLineOne;
        return this;
    }

    public ClientBuilder setAddressLineTwo(String addressLineTwo)
    {
        this.addressLineTwo = addressLineTwo;
        return this;
    }

    public ClientBuilder setCity(String city)
    {
        this.city = city;
        return this;
    }

    public ClientBuilder setPostal(String postal)
    {
        this.postal = postal;
        return this;
    }

    public ClientBuilder setRegion(int region)
    {
        this.region = region;
        return this;
    }

    public ClientBuilder setCountry(int country)
    {
        this.country = country;
        return this;
    }

    public Client createClient()
    {
        return new Client(clientID, statusID, typeID, firstName, lastName, companyName, clientNotes, phoneNumber, addressLineOne, addressLineTwo, city, postal, region, country);
    }
}