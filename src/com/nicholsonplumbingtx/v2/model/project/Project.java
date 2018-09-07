package com.nicholsonplumbingtx.v2.model.project;

import com.nicholsonplumbingtx.v2.model.common.Status;
import com.nicholsonplumbingtx.v2.model.common.Type;

import java.sql.Date;

/**
 * Created by Ethan Orcutt on 10/2/2016.
 * Project D.U.K.E.
 */
public class Project
{
    private int projectID;
    private int projectStatusID;
    private int projectTypeID;
    private String projectName;
    private Date projectBidDate;
    private Date projectStartDate;
    private String projectNotes;
    private int clientID;
    private Status projectStatus;
    private Type projectType;

    public Project(int projectID, int projectStatusID, int projectTypeID, String projectName, Date projectBidDate, Date projectStartDate, String projectNotes, int clientID, Status projectStatus, Type projectType)
    {
        this.projectID = projectID;
        this.projectStatusID = projectStatusID;
        this.projectTypeID = projectTypeID;
        this.projectName = projectName;
        this.projectBidDate = projectBidDate;
        this.projectStartDate = projectStartDate;
        this.projectNotes = projectNotes;
        this.clientID = clientID;
        this.projectStatus = projectStatus;
        this.projectType = projectType;
    }

    public int getProjectID()
    {
        return projectID;
    }

    public void setProjectID(int projectID)
    {
        this.projectID = projectID;
    }

    public int getProjectStatusID()
    {
        return projectStatusID;
    }

    public void setProjectStatusID(int projectStatusID)
    {
        this.projectStatusID = projectStatusID;
    }

    public int getProjectTypeID()
    {
        return projectTypeID;
    }

    public void setProjectTypeID(int projectTypeID)
    {
        this.projectTypeID = projectTypeID;
    }

    public String getProjectName()
    {
        return projectName;
    }

    public void setProjectName(String projectName)
    {
        this.projectName = projectName;
    }

    public Date getProjectBidDate()
    {
        return projectBidDate;
    }

    public void setProjectBidDate(Date projectBidDate)
    {
        this.projectBidDate = projectBidDate;
    }

    public Date getProjectStartDate()
    {
        return projectStartDate;
    }

    public void setProjectStartDate(Date projectStartDate)
    {
        this.projectStartDate = projectStartDate;
    }

    public String getProjectNotes()
    {
        return projectNotes;
    }

    public void setProjectNotes(String projectNotes)
    {
        this.projectNotes = projectNotes;
    }

    public int getClientID()
    {
        return clientID;
    }

    public void setClientID(int clientID)
    {
        this.clientID = clientID;
    }

    public Status getProjectStatus()
    {
        return projectStatus;
    }

    public void setProjectStatus(Status projectStatus)
    {
        this.projectStatus = projectStatus;
    }

    public Type getProjectType()
    {
        return projectType;
    }

    public void setProjectType(Type projectType)
    {
        this.projectType = projectType;
    }

    @Override
    public String toString()
    {
        return getProjectName();
    }
}
