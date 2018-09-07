package com.nicholsonplumbingtx.v2.database_connector;

import com.microsoft.sqlserver.jdbc.SQLServerException;
import com.nicholsonplumbingtx.v2.model.client.Client;
import com.nicholsonplumbingtx.v2.model.client.ClientBuilder;
import com.nicholsonplumbingtx.v2.model.common.Status;
import com.nicholsonplumbingtx.v2.model.common.StatusBuilder;
import com.nicholsonplumbingtx.v2.model.common.Type;
import com.nicholsonplumbingtx.v2.model.common.TypeBuilder;
import com.nicholsonplumbingtx.v2.model.contact.Contact;
import com.nicholsonplumbingtx.v2.model.project.Project;
import com.nicholsonplumbingtx.v2.model.project.ProjectBuilder;
import com.nicholsonplumbingtx.v2.model.project.ProjectContact;
import org.apache.commons.dbutils.DbUtils;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;

/**
 * Created by Ethan Orcutt on 10/18/2016.
 * Project D.U.K.E.
 */
public class ProjectDBConnection extends DBConnector
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
     * @param newProject
     */
    public void createProject(Project newProject)
    {
        PreparedStatement prep = null;
        Connection conn = null;

        String query = "INSERT INTO Project(project_status_id, project_type_id, project_name, project_start_date," +
                "project_bid_date, client_id, project_notes) VALUES(?, ?, ?, ?, ?, ?, ?)";

        try
        {
            conn = dS.getConnection();
            prep = conn.prepareStatement(query);

            prep.setInt(1, newProject.getProjectStatusID());
            prep.setInt(2, newProject.getProjectTypeID());
            prep.setString(3, newProject.getProjectName());
            prep.setDate(4, newProject.getProjectStartDate());
            prep.setDate(5, newProject.getProjectBidDate());
            prep.setInt(6, newProject.getClientID());
            prep.setString(7, newProject.getProjectNotes());

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

    public void updateProject(Project updatedProject)
    {
        Connection conn = null;
        PreparedStatement prep = null;

        String query = "UPDATE Project SET project_status_id = ?, project_type_id = ?, project_name = ?," +
                "project_start_date = ?, project_bid_date = ?, client_id = ?, project_notes = ?  WHERE project_id = ?;";

        try
        {
            conn = dS.getConnection();
            prep = conn.prepareStatement(query);

            prep.setInt(1, updatedProject.getProjectStatusID());
            prep.setInt(2, updatedProject.getProjectTypeID());
            prep.setString(3, updatedProject.getProjectName());
            prep.setDate(4, updatedProject.getProjectStartDate());
            prep.setDate(5, updatedProject.getProjectBidDate());
            prep.setInt(6, updatedProject.getClientID());
            prep.setString(7, updatedProject.getProjectNotes());
            prep.setInt(8, updatedProject.getProjectID());

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
     * Last Modified: 10/2/2016
     * <p>
     * DESCRIPTION:
     *
     * @param newContact
     */
    public void createNewProjectContact(ProjectContact newContact, Date dateCreated)
    {
        PreparedStatement prep = null;
        Connection conn = null;

        String query = "INSERT INTO ProjectContact(project_id, first_name, last_name, phone_number, email)" +
                "VALUES(?, ?, ?, ?, ?)";

        String queryTwo = "INSERT INTO ProjectAssignedContact(project_contact_id, contact_type_id, contact_status_id, " +
                "date_assigned) VALUES(?,?,?,?);";

        try
        {
            conn = dS.getConnection();
            prep = conn.prepareStatement(query);
            prep.setInt(1, newContact.getProjectID());
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
            System.out.println("ERROR: Project Contact already exists.");
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

    public void updateProjectContact(ProjectContact updatedContact)
    {
        Connection conn = null;
        PreparedStatement prep = null;

        String query = "UPDATE ProjectContact SET project_id = ?, first_name = ?," +
                "last_name = ?, phone_number = ?, email = ? WHERE project_contact_id = ?;";

        String queryTwo = "UPDATE ProjectAssignedContact SET contact_type_id = ?, contact_status_id = ? WHERE project_contact_id = ?;";

        try
        {
            conn = dS.getConnection();
            prep = conn.prepareStatement(query);

            prep.setInt(1, updatedContact.getProjectID());
            prep.setString(2, updatedContact.getFirstName());
            prep.setString(3, updatedContact.getLastName());
            prep.setString(4, updatedContact.getPhoneNumber());
            prep.setString(5, updatedContact.getEmail());
            prep.setInt(6, updatedContact.getProjectContactID());
            prep.executeUpdate();

            prep = conn.prepareStatement(queryTwo);
            prep.setInt(1, updatedContact.getType().getTypeID());
            prep.setInt(2, updatedContact.getStatus().getStatusID());
            prep.setInt(3, updatedContact.getProjectContactID());
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

    private int getContactID(Contact contact)
    {
        ResultSet rs;
        PreparedStatement prep = null;
        Connection conn = null;
        int contactID = 0;

        String query = "SELECT cc.project_contact_id FROM ProjectContact AS cc WHERE cc.first_name = ? AND cc.last_name = ?;";
        try
        {
            conn = dS.getConnection();
            prep = conn.prepareStatement(query);
            prep.setString(1, contact.getFirstName());
            prep.setString(2, contact.getLastName());
            rs = prep.executeQuery();

            while(rs.next())
                contactID = rs.getInt("project_contact_id");
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

        String query = "SELECT * FROM Project WHERE project_name = ?;";
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
     * Created On: 10/2/2016
     * Last Modified: 10/12/2016
     * <p>
     * DESCRIPTION:
     *
     * @param clicked
     */
    public int getSelectedID(String clicked)
    {
        ResultSet rs = null;
        Connection conn = null;
        PreparedStatement prep = null;

        String query = "SELECT c.client_id FROM Client AS c WHERE c.company_name = ?;";

        int selectedID = 0;

        try
        {
            conn = dS.getConnection();
            prep = conn.prepareStatement(query);
            prep.setString(1, clicked);
            rs = prep.executeQuery();

            while(rs.next())
                selectedID = rs.getInt("client_id");
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
     * Created On: 10/18/2016
     * Last Modified: 10/18/2016
     *
     */
    public ArrayList<Project> getProjectList()
    {
        ResultSet rs = null;
        Connection conn = null;
        PreparedStatement prep = null;
        ArrayList<Project> projectList = new ArrayList<>();

        String query = "SELECT * FROM Project ORDER BY project_name;";

        try
        {
            conn = dS.getConnection();
            prep = conn.prepareStatement(query);
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
    public ArrayList<Type> getProjectTypeList()
    {
        ResultSet rs = null;
        Connection conn = null;
        PreparedStatement prep = null;
        ArrayList<Type> typeList = new ArrayList<>();
        String idColumnName;

        String query = "SELECT * FROM ProjectType";

        try
        {
            conn = dS.getConnection();
            prep = conn.prepareStatement(query);
            rs = prep.executeQuery();
            idColumnName = rs.getMetaData().getColumnName(1);

            while(rs.next())
            {
                typeList.add(new TypeBuilder()
                        .setTypeID(rs.getInt(idColumnName))
                        .setTypeTitle(rs.getString("title"))
                        .setTypeDescription(rs.getString("description"))
                        .createType());
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
        return typeList;
    }

    /**
     * Created By: Ethan
     * Created On: 10/18/2016
     * Last Modified: 10/18/2016
     *
     */
    public ArrayList<Status> getProjectStatusList()
    {
        ResultSet rs = null;
        Connection conn = null;
        PreparedStatement prep = null;
        ArrayList<Status> statusList = new ArrayList<>();
        String idColumnName;

        String query = "SELECT * FROM ProjectStatus";

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

    public ArrayList<Project> getClientProjectList(int projectID)
    {
        ResultSet rs = null;
        Connection conn = null;
        PreparedStatement prep = null;
        ArrayList<Project> projectList = new ArrayList<>();

        String query = "SELECT * FROM Project AS p JOIN Client AS c ON c.client_id = p.client_id WHERE c.client_id = (SELECT p.client_id FROM Project AS p WHERE p.project_id = ?) ORDER BY project_name;";

        try
        {
            conn = dS.getConnection();
            prep = conn.prepareStatement(query);
            prep.setInt(1, projectID);
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
}
