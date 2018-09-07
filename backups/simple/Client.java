package simple;

/**
 * Created by Ethan Orcutt on 10/2/2016.
 */
public class Client
{
    private int clientID;
    private int statusID;
    private int typeID;
    private String firstName;
    private String lastName;
    private String companyName;
    private String clientNotes;

    public Client(int clientID, int statusID, int typeID, String firstName, String lastName,
                    String companyName, String clientNotes)
    {
        this.clientID = clientID;
        this.statusID = statusID;
        this.typeID = typeID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.companyName = companyName;
        this.clientNotes = clientNotes;
    }

    public int getClientID()
    {
        return clientID;
    }

    public void setClientID(int clientID)
    {
        this.clientID = clientID;
    }

    public int getStatusID()
    {
        return statusID;
    }

    public void setStatusID(int statusID)
    {
        this.statusID = statusID;
    }

    public int getTypeID()
    {
        return typeID;
    }

    public void setTypeID(int typeID)
    {
        this.typeID = typeID;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public String getCompanyName()
    {
        return companyName;
    }

    public void setCompanyName(String companyName)
    {
        this.companyName = companyName;
    }

    public String getClientNotes()
    {
        return clientNotes;
    }

    public void setClientNotes(String clientNotes)
    {
        this.clientNotes = clientNotes;
    }
}
