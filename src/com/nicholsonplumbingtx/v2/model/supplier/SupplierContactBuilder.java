package com.nicholsonplumbingtx.v2.model.supplier;

import com.nicholsonplumbingtx.v2.model.common.Status;
import com.nicholsonplumbingtx.v2.model.common.Type;

public class SupplierContactBuilder {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private Status status;
    private Type type;
    private int supplierContactID;
    private int supplierID;
    private String companyName;

    public SupplierContactBuilder setFirstName(String firstName)
    {
        this.firstName = firstName;
        return this;
    }

    public SupplierContactBuilder setLastName(String lastName)
    {
        this.lastName = lastName;
        return this;
    }

    public SupplierContactBuilder setPhoneNumber(String phoneNumber)
    {
        this.phoneNumber = phoneNumber.replaceAll("[^a-zA-Z0-9]+", "");
        return this;
    }

    public SupplierContactBuilder setEmail(String email)
    {
        this.email = email;
        return this;
    }

    public SupplierContactBuilder setStatus(Status status)
    {
        this.status = status;
        return this;
    }

    public SupplierContactBuilder setType(Type type)
    {
        this.type = type;
        return this;
    }

    public SupplierContactBuilder setSupplierContactID(int supplierContactID)
    {
        this.supplierContactID = supplierContactID;
        return this;
    }

    public SupplierContactBuilder setSupplierID(int supplierID)
    {
        this.supplierID = supplierID;
        return this;
    }

    public SupplierContactBuilder setCompanyName(String companyName)
    {
        this.companyName = companyName;
        return this;
    }

    public SupplierContact createSupplierContact()
    {
        return new SupplierContact(firstName, lastName, phoneNumber, email, status, type, supplierContactID, supplierID, companyName);
    }
}