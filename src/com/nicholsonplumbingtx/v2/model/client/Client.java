package com.nicholsonplumbingtx.v2.model.client;

/**
 * Created by Ethan Orcutt on 10/2/2016.
 * Project D.U.K.E.
 */
public class Client
{
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

    public Client(int clientID, int statusID, int typeID, String firstName, String lastName, String companyName, String clientNotes, String phoneNumber, String addressLineOne, String addressLineTwo, String city, String postal, int region, int country)
    {
        this.clientID = clientID;
        this.statusID = statusID;
        this.typeID = typeID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.companyName = companyName;
        this.clientNotes = clientNotes;
        this.phoneNumber = phoneNumber;
        this.addressLineOne = addressLineOne;
        this.addressLineTwo = addressLineTwo;
        this.city = city;
        this.postal = postal;
        this.region = region;
        this.country = country;
    }

    public int getClientID()
    {
        return clientID;
    }

    public void setClientID(int clientID)
    {
        this.clientID = clientID;
    }

    public int getStatusID()
    {
        return statusID;
    }

    public void setStatusID(int statusID)
    {
        this.statusID = statusID;
    }

    public int getTypeID()
    {
        return typeID;
    }

    public void setTypeID(int typeID)
    {
        this.typeID = typeID;
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

    public String getCompanyName()
    {
        return companyName;
    }

    public void setCompanyName(String companyName)
    {
        this.companyName = companyName;
    }

    public String getClientNotes()
    {
        return clientNotes;
    }

    public void setClientNotes(String clientNotes)
    {
        this.clientNotes = clientNotes;
    }

    public String getPhoneNumber()
    {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber)
    {
        this.phoneNumber = phoneNumber.replaceAll("[^a-zA-Z0-9]+", "");
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

    public String getPostal()
    {
        return postal;
    }

    public void setPostal(String postal)
    {
        this.postal = postal;
    }

    public int getRegion()
    {
        return region;
    }

    public void setRegion(int region)
    {
        this.region = region;
    }

    public int getCountry()
    {
        return country;
    }

    public void setCountry(int country)
    {
        this.country = country;
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
        return getCompanyName();
    }
}
