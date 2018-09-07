package simple;

public class ClientBuilder {
    private int clientID;
    private int statusID;
    private int typeID;
    private String firstName;
    private String lastName;
    private String companyName;
    private String clientNotes;

    public ClientBuilder setClientID(int clientID)
    {
        this.clientID = clientID;
        return this;
    }

    public ClientBuilder setStatusID(int statusID)
    {
        this.statusID = statusID;
        return this;
    }

    public ClientBuilder setTypeID(int typeID)
    {
        this.typeID = typeID;
        return this;
    }

    public ClientBuilder setFirstName(String firstName)
    {
        this.firstName = firstName;
        return this;
    }

    public ClientBuilder setLastName(String lastName)
    {
        this.lastName = lastName;
        return this;
    }

    public ClientBuilder setCompanyName(String companyName)
    {
        this.companyName = companyName;
        return this;
    }

    public ClientBuilder setClientNotes(String clientNotes)
    {
        this.clientNotes = clientNotes;
        return this;
    }

    public Client createClient()
    {
        return new Client(clientID, statusID, typeID, firstName, lastName, companyName, clientNotes);
    }
}