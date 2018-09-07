package simple;

/**
 * Created by Ethan Orcutt on 10/2/2016.
 */
public class LocationContact extends Contact
{
    private int locationContactID;
    private int clientID;

    public LocationContact(String firstName, String lastName, String fullName, String phoneNumber, String formattedPhoneNumber, String email, int locationContactID, int clientID)
    {
        super(firstName, lastName, fullName, phoneNumber, formattedPhoneNumber, email);
        this.locationContactID = locationContactID;
        this.clientID = clientID;
    }

    public int getLocationContactID()
    {
        return locationContactID;
    }

    public void setLocationContactID(int locationContactID)
    {
        this.locationContactID = locationContactID;
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
