package simple;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import org.apache.commons.dbutils.DbUtils;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;

/**
 * Created by Ethan Orcutt on 9/12/2016.
 */
public class EstablishConnection
{
    private final SQLServerDataSource dS = new SQLServerDataSource();

    protected EstablishConnection()
    {
        dS.setPortNumber(1433);
        dS.setServerName("localhost");
        dS.setDatabaseName("DukeDB");
        dS.setUser("dukeConnector");
        dS.setPassword("test1234");
    }

    /**
     * Created By: Ethan
     * Created On: 10/2/2016
     * Last Modified: 10/2/2016
     * <p>
     * DESCRIPTION:
     * - This method sends newly created client records to the database.
     * <p>
     * EXCEPTIONS:
     * - SQLServerException
     * - If the client already exists within the database, the user needs to adjust their input.
     * <p>
     * COMPLETE:
     * - Catches the error if the client already exists within the database.
     * <p>
     * TO-DO:
     * - Update the client creation form that there was an error with inserting the new client.
     *
     * @param newClient
     */
    protected void createNewClient(Client newClient)
    {
        PreparedStatement prep = null;
        Connection conn = null;

        String query = "INSERT INTO Client(client_status_id, client_type_id," + "first_name, last_name, company_name, notes)" + "VALUES(?, ?, ?, ?, ?, ?)";

        try
        {
            conn = dS.getConnection();
            prep = conn.prepareStatement(query);
            prep.setInt(1, newClient.getStatusID());
            prep.setInt(2, newClient.getTypeID());
            prep.setString(3, newClient.getFirstName());
            prep.setString(4, newClient.getLastName());
            prep.setString(5, newClient.getCompanyName());
            prep.setString(6, newClient.getClientNotes());
            prep.executeUpdate();
        }
        catch (SQLServerException ex)
        {
            JOptionPane.showMessageDialog(null, ex.getClass().getSimpleName() + ": " + ex.getMessage());
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        finally
        {
            DbUtils.closeQuietly(prep);
            DbUtils.closeQuietly(conn);
        }
    }

    /**
     * Created By: Ethan
     * Created On: 10/2/2016
     * Last Modified: 10/2/2016
     * <p>
     * DESCRIPTION:
     *
     * @param newContact
     */
    protected void createNewLocationContact(LocationContact newContact)
    {
        PreparedStatement prep = null;
        Connection conn = null;

        String query = "INSERT INTO LocationContact(location_id, first_name, last_name, phone_number, email)" +
                "VALUES(?, ?, ?, ?, ?)";

        try
        {
            conn = dS.getConnection();
            prep = conn.prepareStatement(query);
            prep.setInt(1, newContact.getClientID());
            prep.setString(2, newContact.getFirstName());
            prep.setString(3, newContact.getLastName());
            prep.setString(4, newContact.getPhoneNumber());
            prep.setString(5, newContact.getEmail());
            prep.executeUpdate();
        }
        catch (SQLServerException ex)
        {
            System.out.println("ERROR: Location Contact already exists.");
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        finally
        {
            DbUtils.closeQuietly(prep);
            DbUtils.closeQuietly(conn);
        }
    }

    /**
     * Created By: Ethan
     * Created On: 10/2/2016
     * Last Modified: 10/12/2016
     * <p>
     * DESCRIPTION:
     *
     * @param clicked
     */
    protected int getSelectedID(String clicked, String parent)
    {
        ResultSet rs = null;
        Connection conn = null;
        PreparedStatement prep = null;
        String query = null;

        if (parent.equals("Client"))
            query = "SELECT c.client_id FROM Client AS c WHERE c.company_name = ?;";
        else if (parent.equals("Project"))
            query = "SELECT p.project_id FROM Project AS p WHERE p.project_name = ?;";

        int selectedID = 0;

        try
        {
            conn = dS.getConnection();
            prep = conn.prepareStatement(query);
            prep.setString(1, clicked);
            rs = prep.executeQuery();

            while(rs.next())
            {
                if (parent.equals("Client"))
                {
                    selectedID = rs.getInt("client_id");
                } else if (parent.equals("Project"))
                {
                    selectedID = rs.getInt("project_id");
                }
            }

            return selectedID;
        } catch (SQLException e)
        {
            e.printStackTrace();
        } finally
        {
            DbUtils.closeQuietly(rs);
            DbUtils.closeQuietly(prep);
            DbUtils.closeQuietly(conn);
        }
        return 0;
    }

    /**
     * Created By: Ethan
     * Created On: 10/10/2016
     * Last Modified: 10/10/2016
     * <p>
     * DESCRIPTION:
     *
     */
    protected ArrayList<String> getClientStatusList()
    {
        ResultSet rs = null;
        Connection conn = null;
        PreparedStatement prep = null;
        ArrayList<String> clientStatusList = new ArrayList<>();

        String query = "SELECT * FROM ClientStatus";

        try
        {
            conn = dS.getConnection();
            prep = conn.prepareStatement(query);
            rs = prep.executeQuery();

            while (rs.next())
            {
                clientStatusList.add(rs.getString(2));
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
        } finally
        {
            DbUtils.closeQuietly(rs);
            DbUtils.closeQuietly(prep);
            DbUtils.closeQuietly(conn);
        }
        return clientStatusList;
    }

    /**
     * Created By: Ethan
     * Created On: 10/12/2016
     * Last Modified: 10/12/2016
     *
     * @return
     */
    protected ArrayList<String> getClientProjects(String cName)
    {
        ResultSet rs = null;
        Connection conn = null;
        PreparedStatement prep = null;
        ArrayList<String> projectList = new ArrayList<>();

        String query = "SELECT p.project_name FROM Client AS c JOIN Project AS p ON c.client_id = p.client_id WHERE c.company_name = ?;";

        try
        {
            conn = dS.getConnection();
            prep = conn.prepareStatement(query);
            prep.setString(1, cName);
            rs = prep.executeQuery();
            while(rs.next())
                projectList.add(rs.getString("project_name"));

            return projectList;
        } catch (SQLException e)
        {
            e.printStackTrace();
        } finally
        {
            DbUtils.closeQuietly(rs);
            DbUtils.closeQuietly(prep);
            DbUtils.closeQuietly(conn);
        }
        return null;
    }

    /**
     * Created By: Ethan
     * Created On: 10/12/2016
     * Last Modified: 10/12/2016
     * <p>
     * DESCRIPTION:
     * - Connects to the database, grabs all client records and creates Client objects.
     * All objects are stored within an ArrayList and then returned.
     *
     * @return
     */
    protected ArrayList<Client> getClientList(String clientStatus)
    {
        ResultSet rs = null;
        Connection conn = null;
        PreparedStatement prep = null;
        ArrayList<Client> clientList = new ArrayList<>();

        String query = "SELECT c.client_id, c.client_status_id, c.client_type_id," +
                "c.first_name, c.last_name, c.company_name, c.notes FROM Client AS c " +
                "JOIN ClientStatus AS cS ON cS.client_status_id = c.client_status_id " +
                "WHERE cS.title = ?;";

        try
        {
            conn = dS.getConnection();
            prep = conn.prepareStatement(query);
            prep.setString(1, clientStatus);
            rs = prep.executeQuery();

            while (rs.next())
            {
                clientList.add(new ClientBuilder()
                        .setClientID(rs.getInt("client_id"))
                        .setStatusID(rs.getInt("client_status_id"))
                        .setTypeID(rs.getInt("client_type_id"))
                        .setFirstName(rs.getString("first_name"))
                        .setLastName(rs.getString("last_name"))
                        .setCompanyName(rs.getString("company_name"))
                        .setClientNotes(rs.getString("notes"))
                        .createClient());
            }
            return clientList;
        } catch (SQLException e)
        {
            System.out.println(e.getMessage());
            System.exit(1);
            e.printStackTrace();
        } finally
        {
            DbUtils.closeQuietly(rs);
            DbUtils.closeQuietly(prep);
            DbUtils.closeQuietly(conn);
        }
        return clientList;
    }

    /**
     * Created By: Ethan
     * Created On: 10/12/2016
     * Last Modified: 10/12/2016
     *
     * @param clicked
     * @return
     */
    protected Client getClientNotes(String clicked)
    {
        ResultSet rs = null;
        Connection conn = null;
        PreparedStatement prep = null;
        Client singleClient = null;

        String query = "SELECT\n" + "  c.client_id,\n" + "  c.client_status_id,\n" + "  c.client_type_id,\n" +
                "  c.first_name,\n" + "  c.last_name,\n" + "  c.company_name,\n" + "  c.notes\n" + "\n" +
                "FROM Client AS c\n" + "WHERE c.company_name = ?;";
        try
        {
            conn = dS.getConnection();
            prep = conn.prepareStatement(query);
            prep.setString(1, clicked);
            rs = prep.executeQuery();

            while (rs.next())
            {
                singleClient = (new ClientBuilder()
                        .setClientID(rs.getInt("client_id"))
                        .setStatusID(rs.getInt("client_status_id"))
                        .setTypeID(rs.getInt("client_type_id"))
                        .setFirstName(rs.getString("first_name"))
                        .setLastName(rs.getString("last_name"))
                        .setCompanyName(rs.getString("company_name"))
                        .setClientNotes(rs.getString("notes"))
                        .createClient());
            }
            return singleClient;
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
            System.exit(1);
            e.printStackTrace();
        }
        finally
        {
            DbUtils.closeQuietly(rs);
            DbUtils.closeQuietly(prep);
            DbUtils.closeQuietly(conn);
        }
        return null;
    }

    /**
     * Created By: Ethan
     * Created On: 10/12/2016
     * Last Modified: 10/12/2016

     * @param clicked
     * @return
     */
    protected Project getProjectNotes(String clicked)
    {
        ResultSet rs = null;
        Connection conn = null;
        PreparedStatement prep = null;
        Project singleProject = null;

        String query = "SELECT\n" + "  p.project_id,\n" + "  p.client_id,\n" + "  p.project_status_id,\n" +
                "  p.project_type_id,\n" + "  p.project_name,\n" + "  p.project_bid_date,\n" +
                "  p.project_start_date,\n" + "  p.project_notes\n" + "\n" + "FROM Project AS p\n" +
                "WHERE p.project_name = ?;";
        try
        {
            conn = dS.getConnection();
            prep = conn.prepareStatement(query);
            prep.setString(1, clicked);
            rs = prep.executeQuery();

            while (rs.next())
            {
                singleProject = (new ProjectBuilder()
                        .setProjectID(rs.getInt("project_id"))
                        .setClientID(rs.getInt("client_id"))
                        .setProjectStatusID(rs.getInt("project_status_id"))
                        .setProjectTypeID(rs.getInt("project_type_id"))
                        .setProjectName(rs.getString("project_name"))
                        .setProjectBidDate(rs.getString("project_bid_date"))
                        .setProjectStartDate(rs.getString("project_start_date"))
                        .setProjectNotes(rs.getString("project_notes"))
                        .createProject());
            }
            return singleProject;
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
            System.exit(1);
            e.printStackTrace();
        }
        finally
        {
            DbUtils.closeQuietly(rs);
            DbUtils.closeQuietly(prep);
            DbUtils.closeQuietly(conn);
        }
        return null;
    }

    /**
     * Created By: Ethan
     * Created On: 10/15/2016
     * Last Modified: 10/15/2016
     *
     * @param clicked
     * @return
     */
    protected void setClientNotes(String clicked, String updatedNotes)
    {
        Connection conn = null;
        PreparedStatement prep = null;

        String query = "UPDATE Client SET notes = ? WHERE (company_name = ?)" +
                "OR (first_name = ? AND last_name = ?);";

        try
        {
            conn = dS.getConnection();
            prep = conn.prepareStatement(query);
            prep.setString(1, updatedNotes);
            prep.setString(2, clicked);
            if(clicked.contains(" "))
            {
                prep.setString(3, clicked.substring(0, clicked.indexOf(" ")));
                prep.setString(4, clicked.substring(clicked.indexOf(" "), clicked.length() - 1));
            }

            prep.executeUpdate();
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
            System.exit(1);
            e.printStackTrace();
        }
        finally
        {
            DbUtils.closeQuietly(prep);
            DbUtils.closeQuietly(conn);
        }
    }

    /**
     * Created By: Ethan
     * Created On: 10/15/2016
     * Last Modified: 10/15/2016

     * @param clicked
     * @return
     */
    protected void setProjectNotes(String clicked, String updatedNotes)
    {
        Connection conn = null;
        PreparedStatement prep = null;

        String query = "UPDATE Project SET project_notes = ? WHERE project_name = ?;";

        try
        {
            conn = dS.getConnection();
            prep = conn.prepareStatement(query);
            prep.setString(1, updatedNotes);
            prep.setString(2, clicked);

            prep.executeUpdate();
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
            System.exit(1);
            e.printStackTrace();
        }
        finally
        {
            DbUtils.closeQuietly(prep);
            DbUtils.closeQuietly(conn);
        }
    }

    /**
     * Created By: Ethan
     * Created On: 10/12/2016
     * Last Modified: 10/12/2016
     * <p>
     * DESCRIPTION:
     *
     * @param selectedID
     * @param parent
     */
    protected ArrayList<Invoice> getInvoiceList(int selectedID, String parent)
    {
        ResultSet rs = null;
        Connection conn = null;
        PreparedStatement prep = null;
        ArrayList<Invoice> invoiceList = new ArrayList<>();
        String query = null;

        if(parent.equals("Client"))
        {
            query = "SELECT\n" + "  p.project_name,\n" + "  i.invoice_id,\n" + "  i.project_id,\n" +
                    "i.invoice_type_id,\n" + "  i.invoice_status_id,\n" + "  i.change_order_net_total,\n" +
                    "  i.completed_sum,\n" + "  i.notes,\n" + "  i.total_due,\n" + "  i.total_still_due,\n" +
                    "  i.date_created\n" + "\n" + "FROM Client AS c JOIN Project AS p ON c.client_id = p.client_id\n" +
                    "JOIN Invoice as i ON p.project_id = i.project_id\n" + "WHERE c.client_id = ?;";
        }
        else if(parent.equals("Project"))
        {
            query = "SELECT\n" + "  p.project_name,\n" + "  i.invoice_id,\n" + "  i.project_id,\n" +
                    "  i.invoice_type_id,\n" + "  i.invoice_status_id,\n" + "  i.change_order_net_total,\n" +
                    "  i.completed_sum,\n" + "  i.notes,\n" + "  i.total_due,\n" + "  i.total_still_due,\n" +
                    "  i.date_created\n" + "\n" + "FROM Project AS p\n" +
                    "JOIN Invoice as i ON p.project_id = i.project_id\n" + "WHERE p.project_id = ?;";
        }
        try
        {
            conn = dS.getConnection();
            prep = conn.prepareStatement(query);
            prep.setInt(1, selectedID);
            rs = prep.executeQuery();

            while (rs.next())
            {
                invoiceList.add(new InvoiceBuilder()
                        .setProjectName(rs.getString("project_name"))
                        .setInvoiceID(rs.getInt("invoice_id"))
                        .setProjectID(rs.getInt("project_id"))
                        .setInvoiceTypeID(rs.getInt("invoice_type_id"))
                        .setInvoiceStatusID(rs.getInt("invoice_status_id"))
                        .setChangeOrderNetTotal(rs.getDouble("change_order_net_total"))
                        .setCompletedSum(rs.getDouble("completed_sum"))
                        .setNotes(rs.getString("notes"))
                        .setTotalDue(rs.getDouble("total_due"))
                        .setTotalStillDue(rs.getDouble("total_still_due"))
                        .setDateCreated(rs.getString("date_created"))
                        .createInvoice());
            }
        }
        catch (SQLException e)
        {
            System.out.println("ERROR: " + e.getMessage());
        }
        finally
        {
            DbUtils.closeQuietly(rs);
            DbUtils.closeQuietly(prep);
            DbUtils.closeQuietly(conn);
        }
        return invoiceList;
    }

    /**
     * Created By: Ethan
     * Created On: 10/12/2016
     * Last Modified: 10/12/2016
     * <p>
     * DESCRIPTION:
     *
     * @param selectedID
     * @param parent
     */
    protected ArrayList<Contact> getContactList(int selectedID, String parent)
    {
        ResultSet rs = null;
        Connection conn = null;
        PreparedStatement prep = null;
        ArrayList<Contact> contactList = new ArrayList<>();
        String query = null;

        if(parent.equals("Client"))
        {
            query = "SELECT lC.client_id, lC.email, lC.first_name, lC.last_name, lC.location_contact_id," +
                    "lC.phone_number FROM Client AS c JOIN LocationContact AS lC ON c.client_id = lC.client_id " +
                    "WHERE lC.client_id = ?;";
        }
        else if(parent.equals("Project"))
        {
            query = "SELECT\n" + "  pC.project_contact_id,\n" + "  pC.project_id,\n" + "  pC.first_name,\n" +
                    "  pC.last_name,\n" + "  pC.phone_number,\n" + "  pC.email\n" + "\n" + "FROM Project AS p\n" +
                    "JOIN ProjectContact AS pC ON p.project_id = pC.project_id\n" + "WHERE p.project_id = ?;";
        }
        try
        {
            conn = dS.getConnection();
            prep = conn.prepareStatement(query);
            prep.setInt(1, selectedID);
            rs = prep.executeQuery();

            while (rs.next())
            {
                if(parent.equals("Client"))
                {
                    contactList.add(new LocationContactBuilder()
                            .setLocationContactID(rs.getInt("location_contact_id"))
                            .setClientID(rs.getInt("client_id"))
                            .setFirstName(rs.getString("first_name"))
                            .setLastName(rs.getString("last_name"))
                            .setPhoneNumber(rs.getString("phone_number"))
                            .setEmail(rs.getString("email"))
                            .createLocationContact());
                }
                else if(parent.equals("Project"))
                {
                    contactList.add(new ProjectContactBuilder()
                            .setProjectContactID(rs.getInt("project_contact_id"))
                            .setProjectID(rs.getInt("project_id"))
                            .setFirstName(rs.getString("first_name"))
                            .setLastName(rs.getString("last_name"))
                            .setPhoneNumber(rs.getString("phone_number"))
                            .setEmail(rs.getString("email"))
                            .createProjectContact());
                }
            }

            return contactList;
        } catch (SQLException e)
        {
            e.printStackTrace();
        } finally
        {
            DbUtils.closeQuietly(rs);
            DbUtils.closeQuietly(prep);
            DbUtils.closeQuietly(conn);
        }
        return null;
    }
}