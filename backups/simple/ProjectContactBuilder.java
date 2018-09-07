package simple;

public class ProjectContactBuilder {
    private String firstName;
    private String lastName;
    private String fullName;
    private String phoneNumber;
    private String formattedPhoneNumber;
    private String email;
    private int projectContactID;
    private int projectID;

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

    public ProjectContactBuilder setFullName(String fullName)
    {
        this.fullName = fullName;
        return this;
    }

    public ProjectContactBuilder setPhoneNumber(String phoneNumber)
    {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public ProjectContactBuilder setFormattedPhoneNumber(String formattedPhoneNumber)
    {
        this.formattedPhoneNumber = formattedPhoneNumber;
        return this;
    }

    public ProjectContactBuilder setEmail(String email)
    {
        this.email = email;
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

    public ProjectContact createProjectContact()
    {
        return new ProjectContact(firstName, lastName, fullName, phoneNumber, formattedPhoneNumber, email, projectContactID, projectID);
    }
}