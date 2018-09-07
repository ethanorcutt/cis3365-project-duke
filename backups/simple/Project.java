package simple;

/**
 * Created by Ethan Orcutt on 10/2/2016.
 */
public class Project
{
    private int projectID;
    private int projectStatusID;
    private int projectTypeID;
    private String projectName;
    private String projectBidDate;
    private String projectStartDate;
    private String projectNotes;
    private int clientID;

    public Project(int projectID, int projectStatusID, int projectTypeID, String projectName, String projectBidDate, String projectStartDate, String projectNotes, int clientID)
    {
        this.projectID = projectID;
        this.projectStatusID = projectStatusID;
        this.projectTypeID = projectTypeID;
        this.projectName = projectName;
        this.projectBidDate = projectBidDate;
        this.projectStartDate = projectStartDate;
        this.projectNotes = projectNotes;
        this.clientID = clientID;
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

    public String getProjectBidDate()
    {
        return projectBidDate;
    }

    public void setProjectBidDate(String projectBidDate)
    {
        this.projectBidDate = projectBidDate;
    }

    public String getProjectStartDate()
    {
        return projectStartDate;
    }

    public void setProjectStartDate(String projectStartDate)
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
}
