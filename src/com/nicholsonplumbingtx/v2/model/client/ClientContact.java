package com.nicholsonplumbingtx.v2.model.client;

import com.nicholsonplumbingtx.v2.model.common.Status;
import com.nicholsonplumbingtx.v2.model.common.Type;
import com.nicholsonplumbingtx.v2.model.contact.Contact;

/**
 * Created by Ethan Orcutt on 10/2/2016.
 * Project D.U.K.E.
 */
public class ClientContact extends Contact
{
    private int clientContactID;
    private int clientID;

    public ClientContact(String firstName, String lastName, String phoneNumber, String email, Status status, Type type, int clientContactID, int clientID, String companyName)
    {
        super(firstName, lastName, phoneNumber, email, status, type, companyName);
        this.clientContactID = clientContactID;
        this.clientID = clientID;
    }

    public int getClientContactID()
    {
        return clientContactID;
    }

    public void setClientContactID(int clientContactID)
    {
        this.clientContactID = clientContactID;
    }

    public int getClientID()
    {
        return clientID;
    }

    public void setClientID(int clientID)
    {
        this.clientID = clientID;
    }
}