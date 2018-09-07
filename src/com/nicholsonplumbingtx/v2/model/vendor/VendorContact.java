package com.nicholsonplumbingtx.v2.model.vendor;

import com.nicholsonplumbingtx.v2.model.common.Status;
import com.nicholsonplumbingtx.v2.model.common.Type;
import com.nicholsonplumbingtx.v2.model.contact.Contact;

/**
 * Created by Ethan Orcutt on 10/30/2016.
 * Project D.U.K.E.
 */
public class VendorContact extends Contact
{
    private int vendorContactID;
    private int vendorID;

    public VendorContact(String firstName, String lastName, String phoneNumber, String email, Status status, Type type, int vendorContactID, int vendorID, String companyName)
    {
        super(firstName, lastName, phoneNumber, email, status, type, companyName);
        this.vendorContactID = vendorContactID;
        this.vendorID = vendorID;
    }

    public int getVendorContactID()
    {
        return vendorContactID;
    }

    public void setVendorContactID(int vendorContactID)
    {
        this.vendorContactID = vendorContactID;
    }

    public int getVendorID()
    {
        return vendorID;
    }

    public void setVendorID(int vendorID)
    {
        this.vendorID = vendorID;
    }
}
