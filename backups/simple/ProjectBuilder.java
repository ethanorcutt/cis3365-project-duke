package simple;

public class ProjectBuilder {
    private int projectID;
    private int projectStatusID;
    private int projectTypeID;
    private String projectName;
    private String projectBidDate;
    private String projectStartDate;
    private String projectNotes;
    private int clientID;

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

    public ProjectBuilder setProjectBidDate(String projectBidDate)
    {
        this.projectBidDate = projectBidDate;
        return this;
    }

    public ProjectBuilder setProjectStartDate(String projectStartDate)
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

    public Project createProject()
    {
        return new Project(projectID, projectStatusID, projectTypeID, projectName, projectBidDate, projectStartDate, projectNotes, clientID);
    }
}