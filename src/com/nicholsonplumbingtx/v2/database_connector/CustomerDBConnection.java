package com.nicholsonplumbingtx.v2.database_connector;

import com.microsoft.sqlserver.jdbc.SQLServerException;
import com.nicholsonplumbingtx.v2.model.client.Client;
import com.nicholsonplumbingtx.v2.model.client.ClientBuilder;
import com.nicholsonplumbingtx.v2.model.client.ClientContact;
import com.nicholsonplumbingtx.v2.model.client.ClientContactBuilder;
import com.nicholsonplumbingtx.v2.model.common.*;
import com.nicholsonplumbingtx.v2.model.contact.Contact;
import com.nicholsonplumbingtx.v2.model.invoice.Invoice;
import com.nicholsonplumbingtx.v2.model.invoice.InvoiceBuilder;
import com.nicholsonplumbingtx.v2.model.project.Project;
import com.nicholsonplumbingtx.v2.model.project.ProjectBuilder;
import com.nicholsonplumbingtx.v2.model.project.ProjectContactBuilder;
import org.apache.commons.dbutils.DbUtils;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;

/**
 * Created by Ethan Orcutt on 9/12/2016.
 * Project D.U.K.E.
 */
public class CustomerDBConnection extends DBConnector
{
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
    public void createClient(Client newClient)
    {
        PreparedStatement prep = null;
        Connection conn = null;

        String query = "INSERT INTO Client(client_status_id, client_type_id, first_name, last_name, company_name," +
                "notes, address_line_one, address_line_two, city, region_id, country_id, postal_zip_code," +
                "phone_number) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

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
            prep.setString(7, newClient.getAddressLineOne());
            prep.setString(8, newClient.getAddressLineTwo());
            prep.setString(9, newClient.getCity());
            prep.setInt(10, newClient.getRegion());
            prep.setInt(11, newClient.getCountry());
            prep.setString(12, newClient.getPostal());
            prep.setString(13, newClient.getPhoneNumber());

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
    public void createNewClientContact(ClientContact newContact, Date dateCreated)
    {
        PreparedStatement prep = null;
        Connection conn = null;

        String query = "INSERT INTO ClientContact(client_id, first_name, last_name, phone_number, email)" +
                "VALUES(?, ?, ?, ?, ?);";

        String queryTwo = "INSERT INTO ClientAssignedContact(client_contact_id, contact_type_id, contact_status_id, " +
                "date_assigned) VALUES(?,?,?,?);";

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

            prep = conn.prepareStatement(queryTwo);
            prep.setInt(1, getContactID(newContact));
            prep.setInt(2, newContact.getType().getTypeID());
            prep.setInt(3, newContact.getStatus().getStatusID());
            prep.setDate(4, dateCreated);
            prep.executeUpdate();
        }
        catch (SQLServerException ex)
        {
            System.out.println(ex.getMessage());
            System.out.println("ERROR: Client Contact already exists.");
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

    private int getContactID(Contact contact)
    {
        ResultSet rs;
        PreparedStatement prep = null;
        Connection conn = null;
        int contactID = 0;

        String query = "SELECT cc.client_contact_id FROM ClientContact AS cc WHERE cc.first_name = ? AND cc.last_name = ?;";
        try
        {
            conn = dS.getConnection();
            prep = conn.prepareStatement(query);
            prep.setString(1, contact.getFirstName());
            prep.setString(2, contact.getLastName());
            rs = prep.executeQuery();

            while(rs.next())
                contactID = rs.getInt("client_contact_id");
        }
        catch (SQLServerException ex)
        {
            System.out.println(ex.getMessage());
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
        return contactID;
    }

    public void updateClient(Client updatedClient)
    {
        Connection conn = null;
        PreparedStatement prep = null;

        String query = "UPDATE Client SET client_status_id = ?, client_type_id = ?, first_name = ?, last_name = ?," +
                "company_name = ?, phone_number = ?, notes = ?, country_id = ?, address_line_one = ?," +
                "address_line_two = ?, city = ?, postal_zip_code = ?, region_id = ? WHERE client_id = ?;";

        try
        {
            conn = dS.getConnection();
            prep = conn.prepareStatement(query);

            prep.setInt(1, updatedClient.getStatusID());
            prep.setInt(2, updatedClient.getTypeID());
            prep.setString(3, updatedClient.getFirstName());
            prep.setString(4, updatedClient.getLastName());
            prep.setString(5, updatedClient.getCompanyName());
            prep.setString(6, updatedClient.getPhoneNumber());
            prep.setString(7, updatedClient.getClientNotes());
            prep.setInt(8, updatedClient.getCountry());
            prep.setString(9, updatedClient.getAddressLineOne());
            prep.setString(10, updatedClient.getAddressLineTwo());
            prep.setString(11, updatedClient.getCity());
            prep.setString(12, updatedClient.getPostal());
            prep.setInt(13, updatedClient.getRegion());
            prep.setInt(14, updatedClient.getClientID());

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

    public void updateClientContact(ClientContact updatedContact)
    {
        Connection conn = null;
        PreparedStatement prep = null;

        String query = "UPDATE ClientContact SET client_id = ?, first_name = ?," +
                "last_name = ?, phone_number = ?, email = ? WHERE client_contact_id = ?;";

        String queryTwo = "UPDATE ClientAssignedContact SET contact_type_id = ?, contact_status_id = ? WHERE client_contact_id = ?;";

        try
        {
            conn = dS.getConnection();
            prep = conn.prepareStatement(query);

            prep.setInt(1, updatedContact.getClientID());
            prep.setString(2, updatedContact.getFirstName());
            prep.setString(3, updatedContact.getLastName());
            prep.setString(4, updatedContact.getPhoneNumber());
            prep.setString(5, updatedContact.getEmail());
            prep.setInt(6, updatedContact.getClientContactID());
            prep.executeUpdate();

            prep = conn.prepareStatement(queryTwo);
            prep.setInt(1, updatedContact.getType().getTypeID());
            prep.setInt(2, updatedContact.getStatus().getStatusID());
            prep.setInt(3, updatedContact.getClientContactID());
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
     * Created On: 10/2/2016
     * Last Modified: 10/12/2016
     * <p>
     * DESCRIPTION:
     *
     * @param clicked
     */
    public int getSelectedID(String clicked, String parent)
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
        } catch (SQLException e)
        {
            e.printStackTrace();
        } finally
        {
            DbUtils.closeQuietly(rs);
            DbUtils.closeQuietly(prep);
            DbUtils.closeQuietly(conn);
        }
        return selectedID;
    }

    /**
     * Created By: Ethan
     * Created On: 10/10/2016
     * Last Modified: 10/10/2016
     * <p>
     * DESCRIPTION:
     *
     */
    public ArrayList<Status> getClientStatusList()
    {
        ResultSet rs = null;
        Connection conn = null;
        PreparedStatement prep = null;
        ArrayList<Status> statusList = new ArrayList<>();
        String idColumnName;

        String query = "SELECT * FROM ClientStatus";

        try
        {
            conn = dS.getConnection();
            prep = conn.prepareStatement(query);
            rs = prep.executeQuery();
            idColumnName = rs.getMetaData().getColumnName(1);

            while(rs.next())
            {
                statusList.add(new StatusBuilder()
                        .setStatusID(rs.getInt(idColumnName))
                        .setStatusTitle(rs.getString("title"))
                        .setStatusDescription(rs.getString("description"))
                        .createStatus());
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
        return statusList;
    }

    /**
     * Created By: Ethan
     * Created On: 10/12/2016
     * Last Modified: 10/12/2016
     *
     * @return
     */
    public ArrayList<String> getClientProjects(String cName)
    {
        ResultSet rs = null;
        Connection conn = null;
        PreparedStatement prep = null;
        ArrayList<String> projectList = new ArrayList<>();

        String query = "SELECT p.project_name FROM Project AS p JOIN Client AS c ON p.client_id = c.client_id WHERE c.company_name = ? ORDER BY project_name;";

        try
        {
            conn = dS.getConnection();
            prep = conn.prepareStatement(query);
            prep.setString(1, cName);
            rs = prep.executeQuery();
            while(rs.next())
                projectList.add(rs.getString("project_name"));
        } catch (SQLException e)
        {
            e.printStackTrace();
        } finally
        {
            DbUtils.closeQuietly(rs);
            DbUtils.closeQuietly(prep);
            DbUtils.closeQuietly(conn);
        }
        return projectList;
    }

    public ArrayList<Project> getClientProjectList(String cName)
    {
        ResultSet rs = null;
        Connection conn = null;
        PreparedStatement prep = null;
        ArrayList<Project> projectList = new ArrayList<>();

        String query = "SELECT * FROM Project AS p JOIN Client AS c ON p.client_id = c.client_id WHERE c.company_name = ? ORDER BY project_name;";

        try
        {
            conn = dS.getConnection();
            prep = conn.prepareStatement(query);
            prep.setString(1, cName);
            rs = prep.executeQuery();
            while(rs.next())
            {
                projectList.add(new ProjectBuilder()
                        .setProjectID(rs.getInt("project_id"))
                        .setClientID(rs.getInt("client_id"))
                        .setProjectStatusID(rs.getInt("project_status_id"))
                        .setProjectTypeID(rs.getInt("project_type_id"))
                        .setProjectName(rs.getString("project_name"))
                        .setProjectBidDate(rs.getDate("project_bid_date"))
                        .setProjectStartDate(rs.getDate("project_start_date"))
                        .setProjectNotes(rs.getString("project_notes"))
                        .createProject());
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
        return projectList;
    }

    /**
     * Created By: Ethan
     * Created On: 10/18/2016
     * Last Modified: 10/18/2016
     *
     */
    public Client getClient(String clientName)
    {
        ResultSet rs = null;
        Connection conn = null;
        PreparedStatement prep = null;
        Client client =  null;

        String query = "SELECT * FROM Client WHERE company_name = ?;";

        try
        {
            conn = dS.getConnection();
            prep = conn.prepareStatement(query);
            prep.setString(1, clientName);
            rs = prep.executeQuery();

            while (rs.next())
            {
                client = (new ClientBuilder()
                        .setClientID(rs.getInt("client_id"))
                        .setStatusID(rs.getInt("client_status_id"))
                        .setTypeID(rs.getInt("client_type_id"))
                        .setFirstName(rs.getString("first_name"))
                        .setLastName(rs.getString("last_name"))
                        .setCompanyName(rs.getString("company_name"))
                        .setClientNotes(rs.getString("notes"))
                        .setAddressLineOne(rs.getString("address_line_one"))
                        .setAddressLineTwo(rs.getString("address_line_two"))
                        .setCity(rs.getString("city"))
                        .setRegion(rs.getInt("region_id"))
                        .setCountry(rs.getInt("country_id"))
                        .setPostal(rs.getString("postal_zip_code"))
                        .setPhoneNumber(rs.getString("phone_number"))
                        .createClient());
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
        return client;
    }

    /**
     * Created By: Ethan
     * Created On: 10/12/2016
     * Last Modified: 10/12/2016

     * @param clicked
     * @return
     */
    public Project getProject(String clicked)
    {
        ResultSet rs = null;
        Connection conn = null;
        PreparedStatement prep = null;
        Project singleProject = null;

        String query = "SELECT\n" + "p.project_id, p.client_id, p.project_status_id,\n" + "p.project_type_id, p.project_name, p.project_bid_date, \n" + "p.project_start_date, p.project_notes,\n" + "  ps.title AS [Status], ps.description AS [Status Desc],\n" + "  pt.title AS [Type], pt.description AS [Type Desc]\n" + "\n" + "FROM Project AS p\n" + "JOIN ProjectStatus AS ps ON ps.project_status_id = p.project_status_id\n" + "JOIN ProjectType AS pt ON pt.project_type_id = p.project_type_id\n" + "WHERE p.project_name = ?;";
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
                        .setProjectBidDate(rs.getDate("project_bid_date"))
                        .setProjectStartDate(rs.getDate("project_start_date"))
                        .setProjectNotes(rs.getString("project_notes"))
                        .setProjectStatus(new Status(rs.getInt("project_status_id"), rs.getString("Status"), rs.getString("Status Desc")))
                        .setProjectType(new Type(rs.getInt("project_type_id"), rs.getString("Type"), rs.getString("Type Desc")))
                        .createProject());
            }
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
        return singleProject;
    }

    /**
     * Created By: Ethan
     * Created On: 10/18/2016
     * Last Modified: 10/18/2016
     *
     */
    public ArrayList<Client> getClientList()
    {
        ResultSet rs = null;
        Connection conn = null;
        PreparedStatement prep = null;
        ArrayList<Client> clientList = new ArrayList<>();

        String query = "SELECT * FROM Client ORDER BY company_name;";

        try
        {
            conn = dS.getConnection();
            prep = conn.prepareStatement(query);
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
                        .setAddressLineOne(rs.getString("address_line_one"))
                        .setAddressLineTwo(rs.getString("address_line_two"))
                        .setCity(rs.getString("city"))
                        .setRegion(rs.getInt("region_id"))
                        .setCountry(rs.getInt("country_id"))
                        .setPostal(rs.getString("postal_zip_code"))
                        .setPhoneNumber(rs.getString("phone_number"))
                        .createClient());
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
        return clientList;
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
    public ArrayList<Client> getClientList(String clientStatus)
    {
        ResultSet rs = null;
        Connection conn = null;
        PreparedStatement prep = null;
        ArrayList<Client> clientList = new ArrayList<>();

        String query = "SELECT c.client_id, c.client_status_id, c.client_type_id," +
                "c.first_name, c.last_name, c.company_name, c.notes FROM Client AS c " +
                "JOIN ClientStatus AS cS ON cS.client_status_id = c.client_status_id " +
                "WHERE cS.title = ? ORDER BY c.company_name;";

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
     * Created On: 10/15/2016
     * Last Modified: 10/15/2016
     *
     * @param clicked
     * @return
     */
    public void setClientNotes(String clicked, String updatedNotes)
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
                prep.setString(4, clicked.substring(clicked.indexOf(" ") + 1, clicked.length()));
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
    public void setProjectNotes(String clicked, String updatedNotes)
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
    public ArrayList<Invoice> getInvoiceList(int selectedID, String parent)
    {
        ResultSet rs = null;
        Connection conn = null;
        PreparedStatement prep = null;
        ArrayList<Invoice> invoiceList = new ArrayList<>();
        String query = null;

        if(parent.equals("Client"))
        {
            query = "SELECT\n" + "p.project_name, i.invoice_id, i.project_id,\n" + "i.invoice_type_id, i.invoice_status_id, i.change_order_net_total,\n" + "i.completed_sum, i.notes, i.total_due, i.total_still_due,\n" + "i.date_created, invStat.title AS [Status], invStat.description AS [Status Desc],\n" + "invTyp.title AS [Type], invTyp.description AS [Type Desc]\n" + "\n" + "FROM Client AS c JOIN Project AS p ON c.client_id = p.client_id\n" + "JOIN Invoice AS i ON p.project_id = i.project_id\n" + "JOIN InvoiceStatus AS invStat ON invStat.invoice_status_id = i.invoice_status_id\n" + "JOIN InvoiceType AS invTyp ON invTyp.invoice_type_id = i.invoice_type_id\n" + "WHERE c.client_id = ?;";
        }
        else if(parent.equals("Project"))
        {
            query = "SELECT\n" + "p.project_name, i.invoice_id, i.project_id,\n" + "i.invoice_type_id, i.invoice_status_id, i.change_order_net_total,\n" + "i.completed_sum, i.notes, i.total_due, i.total_still_due,\n" + "i.date_created, invStat.title AS [Status], invStat.description AS [Status Desc],\n" + "invTyp.title AS [Type], invTyp.description AS [Type Desc]\n" + "\n" + "FROM Project AS p\n" + "JOIN Invoice as i ON p.project_id = i.project_id\n" + "JOIN InvoiceStatus as invStat On invStat.invoice_status_id = i.invoice_status_id\n" + "JOIN InvoiceType as invTyp ON invTyp.invoice_type_id = i.invoice_type_id\n" + "WHERE p.project_id = ?;";
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
                        .setInvoiceID(rs.getInt("invoice_id"))
                        .setProjectID(rs.getInt("project_id"))
                        .setProjectName(rs.getString("project_name"))
                        .setInvoiceTypeID(rs.getInt("invoice_type_id"))
                        .setInvoiceStatus(new Status(rs.getInt("invoice_status_id"), rs.getString("Status"), rs.getString("Status Desc")))
                        .setInvoiceType(new Type(rs.getInt("invoice_type_id"), rs.getString("Type"), rs.getString("Type Desc")))
                        .setInvoiceStatusID(rs.getInt("invoice_status_id"))
                        .setChangeOrderNetTotal(rs.getDouble("change_order_net_total"))
                        .setCompletedSum(rs.getDouble("completed_sum"))
                        .setNotes(rs.getString("notes"))
                        .setTotalDue(rs.getDouble("total_due"))
                        .setTotalStillDue(rs.getDouble("total_still_due"))
                        .setDateCreated(rs.getDate("date_created"))
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
    public ArrayList<Contact> getContactList(int selectedID, String parent)
    {
        ResultSet rs = null;
        Connection conn = null;
        PreparedStatement prep = null;
        ArrayList<Contact> contactList = new ArrayList<>();
        String query = null;

        if(parent.equals("Client"))
        {
            query = "SELECT cC.client_id, cC.email, cC.first_name, cC.last_name, cC.client_contact_id, " +
                    "cC.phone_number, c.company_name, cS.contact_status_id, cS.title AS [Status], cS.description AS [Status Desc]," +
                    "cT.contact_type_id, cT.title AS [Type], cT.description AS [Type Desc] FROM Client AS c JOIN ClientContact AS cC " +
                    "ON c.client_id = cC.client_id\n" + "JOIN ClientAssignedContact AS cac ON cac.client_contact_id = cC.client_contact_id\n" + "JOIN ContactStatus AS cS ON cS.contact_status_id = cac.contact_status_id\n" + "JOIN ContactType AS cT ON cT.contact_type_id = cac.contact_type_id\n" + "\n" + "WHERE cC.client_id = ?\n" + "ORDER BY cC.first_name;";
        }
        else if(parent.equals("Project"))
        {
            query = "SELECT\n" + "  pC.project_contact_id,\n" + "  pC.project_id,\n" + "  pC.first_name,\n" + "  pC.last_name,\n" + "  pC.phone_number,\n" + "  pC.email,\n" + "  p.project_name,\n" + "  cS.contact_status_id,\n" + "  cS.title AS [Status],\n" + "  cS.description AS [Status Desc],\n" + "  cT.contact_type_id,\n" + "  cT.title AS [Type],\n" + "  cT.description AS [Type Desc]\n" + "\n" + "FROM Project AS p\n" + "JOIN ProjectContact AS pC ON p.project_id = pC.project_id\n" + "JOIN ProjectAssignedContact AS pac ON pac.project_contact_id = pC.project_contact_id\n" + "  JOIN ContactStatus AS cS ON cS.contact_status_id = pac.contact_status_id\n" + "  JOIN ContactType AS cT ON cT.contact_type_id = pac.contact_type_id\n" + "\n" + "WHERE p.project_id = ?\n" + "ORDER BY pC.first_name;";
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
                    contactList.add(new ClientContactBuilder()
                            .setClientContactID(rs.getInt("client_contact_id"))
                            .setClientID(rs.getInt("client_id"))
                            .setFirstName(rs.getString("first_name"))
                            .setLastName(rs.getString("last_name"))
                            .setPhoneNumber(rs.getString("phone_number"))
                            .setEmail(rs.getString("email"))
                            .setCompanyName(rs.getString("company_name"))
                            .setType(new Type(rs.getInt("contact_type_id"), rs.getString("Type"), rs.getString("Type Desc")))
                            .setStatus(new Status(rs.getInt("contact_status_id"), rs.getString("Status"), rs.getString("Status Desc")))
                            .createClientContact());
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
                            .setCompanyName(rs.getString("project_name"))
                            .setType(new Type(rs.getInt("contact_type_id"), rs.getString("Type"), rs.getString("Type Desc")))
                            .setStatus(new Status(rs.getInt("contact_status_id"), rs.getString("Status"), rs.getString("Status Desc")))
                            .createProjectContact());
                }
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
        return contactList;
    }

    /**
     * Created By: Ethan
     * Created On: 10/18/2016
     * Last Modified: 10/18/2016
     *
     */
    public ArrayList<Country> getCountryList()
    {
        ResultSet rs = null;
        Connection conn = null;
        PreparedStatement prep = null;
        ArrayList<Country> countryList = new ArrayList<>();

        String query = "SELECT * FROM Country ORDER BY country_name;";

        try
        {
            conn = dS.getConnection();
            prep = conn.prepareStatement(query);
            rs = prep.executeQuery();
            while(rs.next())
            {
                countryList.add(new CountryBuilder()
                        .setCountryID(rs.getInt("country_id"))
                        .setCountryName(rs.getString("country_name"))
                        .setCountryCode(rs.getString("country_code"))
                        .createCountry());
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
        return countryList;
    }

    /**
     * Created By: Ethan
     * Created On: 10/18/2016
     * Last Modified: 10/18/2016
     *
     */
    public ArrayList<Region> getRegionList(int selectedID)
    {
        ResultSet rs = null;
        Connection conn = null;
        PreparedStatement prep = null;
        ArrayList<Region> regionList = new ArrayList<>();

        String query = "SELECT * FROM Region WHERE country_id = ? ORDER BY NAME;";

        try
        {
            conn = dS.getConnection();
            prep = conn.prepareStatement(query);
            prep.setInt(1, selectedID);
            rs = prep.executeQuery();
            while(rs.next())
            {
                regionList.add(new RegionBuilder()
                        .setRegionID(rs.getInt("region_id"))
                        .setRegionName(rs.getString("NAME"))
                        .setRegionCode(rs.getString("code"))
                        .createRegion());
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
        return regionList;
    }

    /**
     * Created By: Ethan
     * Created On: 10/18/2016
     * Last Modified: 10/18/2016
     *
     */
    public Country getCountryName(int countryID)
    {
        ResultSet rs = null;
        Connection conn = null;
        PreparedStatement prep = null;
        Country countryName = null;

        String query = "SELECT * FROM Country WHERE country_id = ?;";

        try
        {
            conn = dS.getConnection();
            prep = conn.prepareStatement(query);
            prep.setInt(1, countryID);
            rs = prep.executeQuery();
            while(rs.next()) {
                countryName = new CountryBuilder()
                        .setCountryID(rs.getInt("country_id"))
                        .setCountryName(rs.getString("country_name"))
                        .setCountryCode(rs.getString("country_code"))
                        .createCountry();
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
        return countryName;
    }

    /**
     * Created By: Ethan
     * Created On: 10/18/2016
     * Last Modified: 10/18/2016
     *
     */
    public Region getRegionName(int regionID)
    {
        ResultSet rs = null;
        Connection conn = null;
        PreparedStatement prep = null;
        Region regionName = null;

        String query = "SELECT * FROM Region WHERE region_id = ?;";

        try
        {
            conn = dS.getConnection();
            prep = conn.prepareStatement(query);
            prep.setInt(1, regionID);
            rs = prep.executeQuery();
            while(rs.next())
            {
                regionName = new RegionBuilder()
                        .setRegionID(rs.getInt("region_id"))
                        .setRegionName(rs.getString("NAME"))
                        .setRegionCode(rs.getString("code"))
                        .createRegion();
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
        return regionName;
    }
}