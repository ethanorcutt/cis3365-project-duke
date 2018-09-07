package com.nicholsonplumbingtx.v2.database_connector;

import com.microsoft.sqlserver.jdbc.SQLServerException;
import com.nicholsonplumbingtx.v2.common.DBConnector;
import com.nicholsonplumbingtx.v2.model.*;
import org.apache.commons.dbutils.DbUtils;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Ethan Orcutt on 9/12/2016.
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
    public void createNewLocationContact(LocationContact newContact)
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
     * Created On: 10/2/2016
     * Last Modified: 10/12/2016
     * <p>
     * DESCRIPTION:
     *
     * @param clicked
     */
    public int getSelectedCountryID(String clicked)
    {
        ResultSet rs = null;
        Connection conn = null;
        PreparedStatement prep = null;
        int selectedID = 0;

        String query = "SELECT c.country_id FROM Country AS c WHERE c.country_name = ?;";

        try
        {
            conn = dS.getConnection();
            prep = conn.prepareStatement(query);
            prep.setString(1, clicked);
            rs = prep.executeQuery();

            while(rs.next())
                selectedID = rs.getInt("country_id");
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
    public ArrayList<String> getClientStatusList()
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
                clientStatusList.add(rs.getString("title"));
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
     * Created On: 10/10/2016
     * Last Modified: 10/10/2016
     * <p>
     * DESCRIPTION:
     *
     */
    public ArrayList<String> getClientTypeList()
    {
        ResultSet rs = null;
        Connection conn = null;
        PreparedStatement prep = null;
        ArrayList<String> clientTypeList = new ArrayList<>();

        String query = "SELECT * FROM ClientType";

        try
        {
            conn = dS.getConnection();
            prep = conn.prepareStatement(query);
            rs = prep.executeQuery();

            while (rs.next())
                clientTypeList.add(rs.getString("title"));
        } catch (SQLException e)
        {
            e.printStackTrace();
        } finally
        {
            DbUtils.closeQuietly(rs);
            DbUtils.closeQuietly(prep);
            DbUtils.closeQuietly(conn);
        }
        return clientTypeList;
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

        String query = "SELECT p.project_name FROM Client AS c " +
                "JOIN Project AS p ON c.client_id = p.client_id WHERE c.company_name = ? ORDER BY c.company_name;";

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
                        .setProjectBidDate(rs.getDate("project_bid_date"))
                        .setProjectStartDate(rs.getDate("project_start_date"))
                        .setProjectNotes(rs.getString("project_notes"))
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
    public ArrayList<String> getClientList()
    {
        ResultSet rs = null;
        Connection conn = null;
        PreparedStatement prep = null;
        ArrayList<String> clientList = new ArrayList<>();

        String query = "SELECT * FROM Client ORDER BY company_name;";

        try
        {
            conn = dS.getConnection();
            prep = conn.prepareStatement(query);
            rs = prep.executeQuery();
            while(rs.next())
                clientList.add(rs.getString("company_name"));
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
    public ArrayList<Contact> getContactList(int selectedID, String parent)
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
                    "WHERE lC.client_id = ? ORDER BY lC.first_name;";
        }
        else if(parent.equals("Project"))
        {
            query = "SELECT\n" + "pC.project_contact_id, pC.project_id, pC.first_name,\n" +
                    "pC.last_name, pC.phone_number, pC.email\n" + "FROM Project AS p\n" +
                    "JOIN ProjectContact AS pC ON p.project_id = pC.project_id WHERE p.project_id = ?\n" +
                    "ORDER BY pC.first_name;";
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
    public ArrayList<String> getCountryList()
    {
        ResultSet rs = null;
        Connection conn = null;
        PreparedStatement prep = null;
        ArrayList<String> countryList = new ArrayList<>();

        String query = "SELECT * FROM Country;";

        try
        {
            conn = dS.getConnection();
            prep = conn.prepareStatement(query);
            rs = prep.executeQuery();
            while(rs.next())
                countryList.add(rs.getString("country_name"));
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
    public ArrayList<String> getRegionList(int selectedID)
    {
        ResultSet rs = null;
        Connection conn = null;
        PreparedStatement prep = null;
        ArrayList<String> regionList = new ArrayList<>();

        String query = "SELECT * FROM Region WHERE country_id = ?;";

        try
        {
            conn = dS.getConnection();
            prep = conn.prepareStatement(query);
            prep.setInt(1, selectedID);
            rs = prep.executeQuery();
            while(rs.next())
                regionList.add(rs.getString("NAME"));
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
    public String getCountryName(int countryID)
    {
        ResultSet rs = null;
        Connection conn = null;
        PreparedStatement prep = null;
        String countryName = "";

        String query = "SELECT country_name FROM Country WHERE country_id = ?;";

        try
        {
            conn = dS.getConnection();
            prep = conn.prepareStatement(query);
            prep.setInt(1, countryID);
            rs = prep.executeQuery();
            while(rs.next())
                countryName = rs.getString("country_name");
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
    public String getRegionName(int countryID)
    {
        ResultSet rs = null;
        Connection conn = null;
        PreparedStatement prep = null;
        String regionName = "";

        String query = "SELECT NAME FROM Region WHERE region_id = ?;";

        try
        {
            conn = dS.getConnection();
            prep = conn.prepareStatement(query);
            prep.setInt(1, countryID);
            rs = prep.executeQuery();
            while(rs.next())
                regionName = rs.getString("name");
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