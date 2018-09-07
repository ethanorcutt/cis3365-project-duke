package com.nicholsonplumbingtx.v2.model.project;

import com.nicholsonplumbingtx.v2.model.common.Status;
import com.nicholsonplumbingtx.v2.model.common.Type;

public class ProjectContactBuilder {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private Status status;
    private Type type;
    private int projectContactID;
    private int projectID;
    private String companyName;

    public ProjectContactBuilder setFirstName(String firstName)
    {
        this.firstName = firstName;
        return this;
    }

    public ProjectContactBuilder setLastName(String lastName)
    {
        this.lastName = lastName;
        return this;
    }

    public ProjectContactBuilder setPhoneNumber(String phoneNumber)
    {
        this.phoneNumber = phoneNumber.replaceAll("[^a-zA-Z0-9]+", "");
        return this;
    }

    public ProjectContactBuilder setEmail(String email)
    {
        this.email = email;
        return this;
    }

    public ProjectContactBuilder setStatus(Status status)
    {
        this.status = status;
        return this;
    }

    public ProjectContactBuilder setType(Type type)
    {
        this.type = type;
        return this;
    }

    public ProjectContactBuilder setProjectContactID(int projectContactID)
    {
        this.projectContactID = projectContactID;
        return this;
    }

    public ProjectContactBuilder setProjectID(int projectID)
    {
        this.projectID = projectID;
        return this;
    }

    public ProjectContactBuilder setCompanyName(String companyName)
    {
        this.companyName = companyName;
        return this;
    }

    public ProjectContact createProjectContact()
    {
        return new ProjectContact(firstName, lastName, phoneNumber, email, status, type, projectContactID, projectID, companyName);
    }
}