package com.nicholsonplumbingtx.v2.model.vendor;

import com.nicholsonplumbingtx.v2.model.common.Status;
import com.nicholsonplumbingtx.v2.model.common.Type;

public class VendorContactBuilder {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private Status status;
    private Type type;
    private int vendorContactID;
    private int vendorID;
    private String companyName;

    public VendorContactBuilder setFirstName(String firstName)
    {
        this.firstName = firstName;
        return this;
    }

    public VendorContactBuilder setLastName(String lastName)
    {
        this.lastName = lastName;
        return this;
    }

    public VendorContactBuilder setPhoneNumber(String phoneNumber)
    {
        this.phoneNumber = phoneNumber.replaceAll("[^a-zA-Z0-9]+", "");
        return this;
    }

    public VendorContactBuilder setEmail(String email)
    {
        this.email = email;
        return this;
    }

    public VendorContactBuilder setStatus(Status status)
    {
        this.status = status;
        return this;
    }

    public VendorContactBuilder setType(Type type)
    {
        this.type = type;
        return this;
    }

    public VendorContactBuilder setVendorContactID(int vendorContactID)
    {
        this.vendorContactID = vendorContactID;
        return this;
    }

    public VendorContactBuilder setVendorID(int vendorID)
    {
        this.vendorID = vendorID;
        return this;
    }

    public VendorContactBuilder setCompanyName(String companyName)
    {
        this.companyName = companyName;
        return this;
    }

    public VendorContact createVendorContact()
    {
        return new VendorContact(firstName, lastName, phoneNumber, email, status, type, vendorContactID, vendorID, companyName);
    }
}