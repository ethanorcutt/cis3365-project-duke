package simple;

/**
 * Created by Ethan Orcutt on 10/12/2016.
 */
public class ProjectContact extends Contact
{
    private int projectContactID;
    private int projectID;

    public ProjectContact(String firstName, String lastName, String fullName, String phoneNumber, String formattedPhoneNumber, String email, int projectContactID, int projectID)
    {
        super(firstName, lastName, fullName, phoneNumber, formattedPhoneNumber, email);
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
