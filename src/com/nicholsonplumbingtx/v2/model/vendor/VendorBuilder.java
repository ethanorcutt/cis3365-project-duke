package com.nicholsonplumbingtx.v2.model.vendor;

public class VendorBuilder {
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

    public VendorBuilder setVendorID(int vendorID)
    {
        this.vendorID = vendorID;
        return this;
    }

    public VendorBuilder setVendorStatusID(int vendorStatusID)
    {
        this.vendorStatusID = vendorStatusID;
        return this;
    }

    public VendorBuilder setVendorTypeID(int vendorTypeID)
    {
        this.vendorTypeID = vendorTypeID;
        return this;
    }

    public VendorBuilder setCompanyName(String companyName)
    {
        this.companyName = companyName;
        return this;
    }

    public VendorBuilder setAddressLineOne(String addressLineOne)
    {
        this.addressLineOne = addressLineOne;
        return this;
    }

    public VendorBuilder setAddressLineTwo(String addressLineTwo)
    {
        this.addressLineTwo = addressLineTwo;
        return this;
    }

    public VendorBuilder setCity(String city)
    {
        this.city = city;
        return this;
    }

    public VendorBuilder setRegionID(int regionID)
    {
        this.regionID = regionID;
        return this;
    }

    public VendorBuilder setCountryID(int countryID)
    {
        this.countryID = countryID;
        return this;
    }

    public VendorBuilder setPostalCode(String postalCode)
    {
        this.postalCode = postalCode;
        return this;
    }

    public VendorBuilder setNotes(String notes)
    {
        this.notes = notes;
        return this;
    }

    public Vendor createVendor()
    {
        return new Vendor(vendorID, vendorStatusID, vendorTypeID, companyName, addressLineOne, addressLineTwo, city, regionID, countryID, postalCode, notes);
    }
}