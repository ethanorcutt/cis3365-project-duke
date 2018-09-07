package com.nicholsonplumbingtx.v2.model.project;


import com.nicholsonplumbingtx.v2.model.common.Status;
import com.nicholsonplumbingtx.v2.model.common.Type;
import com.nicholsonplumbingtx.v2.model.contact.Contact;

/**
 * Created by Ethan Orcutt on 10/12/2016.
 * Project D.U.K.E.
 */
public class ProjectContact extends Contact
{
    private int projectContactID;
    private int projectID;

    public ProjectContact(String firstName, String lastName, String phoneNumber, String email, Status status, Type type, int projectContactID, int projectID, String companyName)
    {
        super(firstName, lastName, phoneNumber, email, status, type, companyName);
        this.projectContactID = projectContactID;
        this.projectID = projectID;
    }

    public int getProjectContactID()
    {
        return projectContactID;
    }

    public void setProjectContactID(int projectContactID)
    {
        this.projectContactID = projectContactID;
    }

    public int getProjectID()
    {
        return projectID;
    }

    public void setProjectID(int projectID)
    {
        this.projectID = projectID;
    }
}
