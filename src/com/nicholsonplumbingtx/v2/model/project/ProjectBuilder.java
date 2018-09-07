package com.nicholsonplumbingtx.v2.model.project;

import com.nicholsonplumbingtx.v2.model.common.Status;
import com.nicholsonplumbingtx.v2.model.common.Type;

import java.sql.Date;

public class ProjectBuilder {
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

    public ProjectBuilder setProjectID(int projectID)
    {
        this.projectID = projectID;
        return this;
    }

    public ProjectBuilder setProjectStatusID(int projectStatusID)
    {
        this.projectStatusID = projectStatusID;
        return this;
    }

    public ProjectBuilder setProjectTypeID(int projectTypeID)
    {
        this.projectTypeID = projectTypeID;
        return this;
    }

    public ProjectBuilder setProjectName(String projectName)
    {
        this.projectName = projectName;
        return this;
    }

    public ProjectBuilder setProjectBidDate(Date projectBidDate)
    {
        this.projectBidDate = projectBidDate;
        return this;
    }

    public ProjectBuilder setProjectStartDate(Date projectStartDate)
    {
        this.projectStartDate = projectStartDate;
        return this;
    }

    public ProjectBuilder setProjectNotes(String projectNotes)
    {
        this.projectNotes = projectNotes;
        return this;
    }

    public ProjectBuilder setClientID(int clientID)
    {
        this.clientID = clientID;
        return this;
    }

    public ProjectBuilder setProjectStatus(Status projectStatus)
    {
        this.projectStatus = projectStatus;
        return this;
    }

    public ProjectBuilder setProjectType(Type projectType)
    {
        this.projectType = projectType;
        return this;
    }

    public Project createProject()
    {
        return new Project(projectID, projectStatusID, projectTypeID, projectName, projectBidDate, projectStartDate, projectNotes, clientID, projectStatus, projectType);
    }
}