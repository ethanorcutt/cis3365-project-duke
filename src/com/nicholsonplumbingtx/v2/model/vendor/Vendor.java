package com.nicholsonplumbingtx.v2.model.vendor;

/**
 * Created by Ethan Orcutt on 10/30/2016.
 * Project D.U.K.E.
 */
public class Vendor
{
    private int vendorID;
    private int vendorStatusID;
    private int vendorTypeID;
    private String companyName;
    private String addressLineOne;
    private String addressLineTwo;
    private String city;
    private int regionID;
    private int countryID;
    private String postalCode;
    private String notes;

    public Vendor(int vendorID, int vendorStatusID, int vendorTypeID, String companyName, String addressLineOne, String addressLineTwo, String city, int regionID, int countryID, String postalCode, String notes)
    {
        this.vendorID = vendorID;
        this.vendorStatusID = vendorStatusID;
        this.vendorTypeID = vendorTypeID;
        this.companyName = companyName;
        this.addressLineOne = addressLineOne;
        this.addressLineTwo = addressLineTwo;
        this.city = city;
        this.regionID = regionID;
        this.countryID = countryID;
        this.postalCode = postalCode;
        this.notes = notes;
    }

    public int getVendorID()
    {
        return vendorID;
    }

    public void setVendorID(int vendorID)
    {
        this.vendorID = vendorID;
    }

    public int getVendorStatusID()
    {
        return vendorStatusID;
    }

    public void setVendorStatusID(int vendorStatusID)
    {
        this.vendorStatusID = vendorStatusID;
    }

    public int getVendorTypeID()
    {
        return vendorTypeID;
    }

    public void setVendorTypeID(int vendorTypeID)
    {
        this.vendorTypeID = vendorTypeID;
    }

    private String getCompanyName()
    {
        return companyName;
    }

    public void setCompanyName(String companyName)
    {
        this.companyName = companyName;
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

    public String getPostalCode()
    {
        return postalCode;
    }

    public void setPostalCode(String postalCode)
    {
        this.postalCode = postalCode;
    }

    public String getNotes()
    {
        return notes;
    }

    public void setNotes(String notes)
    {
        this.notes = notes;
    }

    @Override
    public String toString()
    {
        return getCompanyName();
    }
}
