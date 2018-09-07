package com.nicholsonplumbingtx.v2.model.client;

import com.nicholsonplumbingtx.v2.model.common.Status;
import com.nicholsonplumbingtx.v2.model.common.Type;

public class ClientContactBuilder {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private Status status;
    private Type type;
    private int clientContactID;
    private int clientID;
    private String companyName;

    public ClientContactBuilder setFirstName(String firstName)
    {
        this.firstName = firstName;
        return this;
    }

    public ClientContactBuilder setLastName(String lastName)
    {
        this.lastName = lastName;
        return this;
    }

    public ClientContactBuilder setPhoneNumber(String phoneNumber)
    {
        this.phoneNumber = phoneNumber.replaceAll("[^a-zA-Z0-9]+", "");
        return this;
    }

    public ClientContactBuilder setEmail(String email)
    {
        this.email = email;
        return this;
    }

    public ClientContactBuilder setStatus(Status status)
    {
        this.status = status;
        return this;
    }

    public ClientContactBuilder setType(Type type)
    {
        this.type = type;
        return this;
    }

    public ClientContactBuilder setClientContactID(int clientContactID)
    {
        this.clientContactID = clientContactID;
        return this;
    }

    public ClientContactBuilder setClientID(int clientID)
    {
        this.clientID = clientID;
        return this;
    }

    public ClientContactBuilder setCompanyName(String companyName)
    {
        this.companyName = companyName;
        return this;
    }

    public ClientContact createClientContact()
    {
        return new ClientContact(firstName, lastName, phoneNumber, email, status, type, clientContactID, clientID, companyName);
    }
}