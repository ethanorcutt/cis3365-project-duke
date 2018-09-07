package com.nicholsonplumbingtx.v2.model.supplier;

/**
 * Created by Ethan Orcutt on 10/2/2016.
 * Project D.U.K.E.
 */
public class Supplier
{
    private int supplierID;
    private int supplierStatusID;
    private String companyName;
    private String addressLineOne;
    private String addressLineTwo;
    private String city;
    private int regionID;
    private int countryID;
    private String postalCode;
    private String phoneNumber;
    private String email;

    public Supplier(int supplierID, int supplierStatusID, String companyName, String addressLineOne, String addressLineTwo, String city, int regionID, int countryID, String postalCode, String phoneNumber, String email)
    {
        this.supplierID = supplierID;
        this.supplierStatusID = supplierStatusID;
        this.companyName = companyName;
        this.addressLineOne = addressLineOne;
        this.addressLineTwo = addressLineTwo;
        this.city = city;
        this.regionID = regionID;
        this.countryID = countryID;
        this.postalCode = postalCode;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public int getSupplierID()
    {
        return supplierID;
    }

    public void setSupplierID(int supplierID)
    {
        this.supplierID = supplierID;
    }

    public int getSupplierStatusID()
    {
        return supplierStatusID;
    }

    public void setSupplierStatusID(int supplierStatusID)
    {
        this.supplierStatusID = supplierStatusID;
    }

    public String getCompanyName()
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
