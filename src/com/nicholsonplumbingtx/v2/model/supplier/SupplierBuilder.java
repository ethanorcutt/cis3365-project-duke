package com.nicholsonplumbingtx.v2.model.supplier;

public class SupplierBuilder {
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

    public SupplierBuilder setSupplierID(int supplierID)
    {
        this.supplierID = supplierID;
        return this;
    }

    public SupplierBuilder setSupplierStatusID(int supplierStatusID)
    {
        this.supplierStatusID = supplierStatusID;
        return this;
    }

    public SupplierBuilder setCompanyName(String companyName)
    {
        this.companyName = companyName;
        return this;
    }

    public SupplierBuilder setAddressLineOne(String addressLineOne)
    {
        this.addressLineOne = addressLineOne;
        return this;
    }

    public SupplierBuilder setAddressLineTwo(String addressLineTwo)
    {
        this.addressLineTwo = addressLineTwo;
        return this;
    }

    public SupplierBuilder setCity(String city)
    {
        this.city = city;
        return this;
    }

    public SupplierBuilder setRegionID(int regionID)
    {
        this.regionID = regionID;
        return this;
    }

    public SupplierBuilder setCountryID(int countryID)
    {
        this.countryID = countryID;
        return this;
    }

    public SupplierBuilder setPostalCode(String postalCode)
    {
        this.postalCode = postalCode;
        return this;
    }

    public SupplierBuilder setPhoneNumber(String phoneNumber)
    {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public SupplierBuilder setEmail(String email)
    {
        this.email = email;
        return this;
    }

    public Supplier createSupplier()
    {
        return new Supplier(supplierID, supplierStatusID, companyName, addressLineOne, addressLineTwo, city, regionID, countryID, postalCode, phoneNumber, email);
    }
}