package com.nicholsonplumbingtx.v2.model.supplier;

import com.nicholsonplumbingtx.v2.model.common.Status;
import com.nicholsonplumbingtx.v2.model.common.Type;
import com.nicholsonplumbingtx.v2.model.contact.Contact;

/**
 * Created by Ethan Orcutt on 10/30/2016.
 * Project D.U.K.E.
 */
public class SupplierContact extends Contact
{
    private int supplierContactID;
    private int supplierID;

    public SupplierContact(String firstName, String lastName, String phoneNumber, String email, Status status, Type type, int supplierContactID, int supplierID, String companyName)
    {
        super(firstName, lastName, phoneNumber, email, status, type, companyName);
        this.supplierContactID = supplierContactID;
        this.supplierID = supplierID;
    }

    public int getSupplierContactID()
    {
        return supplierContactID;
    }

    public void setSupplierContactID(int supplierContactID)
    {
        this.supplierContactID = supplierContactID;
    }

    public int getSupplierID()
    {
        return supplierID;
    }

    public void setSupplierID(int supplierID)
    {
        this.supplierID = supplierID;
    }
}
