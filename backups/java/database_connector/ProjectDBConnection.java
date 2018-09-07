package com.nicholsonplumbingtx.v2.database_connector;

import com.microsoft.sqlserver.jdbc.SQLServerException;
import com.nicholsonplumbingtx.v2.common.DBConnector;
import com.nicholsonplumbingtx.v2.model.Project;
import com.nicholsonplumbingtx.v2.model.ProjectBuilder;
import org.apache.commons.dbutils.DbUtils;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Ethan Orcutt on 10/18/2016.
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
    public ArrayList<String> getClientList()
    {
        ResultSet rs = null;
        Connection conn = null;
        PreparedStatement prep = null;
        ArrayList<String> projectList = new ArrayList<>();

        String query = "SELECT * FROM Client ORDER BY company_name;";

        try
        {
            conn = dS.getConnection();
            prep = conn.prepareStatement(query);
            rs = prep.executeQuery();
            while(rs.next())
                projectList.add(rs.getString("company_name"));
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
    public ArrayList<String> getProjectList()
    {
        ResultSet rs = null;
        Connection conn = null;
        PreparedStatement prep = null;
        ArrayList<String> projectList = new ArrayList<>();

        String query = "SELECT * FROM Project ORDER BY project_name;";

        try
        {
            conn = dS.getConnection();
            prep = conn.prepareStatement(query);
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
    public ArrayList<String> getProjectTypeList()
    {
        ResultSet rs = null;
        Connection conn = null;
        PreparedStatement prep = null;
        ArrayList<String> projectList = new ArrayList<>();

        String query = "SELECT * FROM ProjectType";

        try
        {
            conn = dS.getConnection();
            prep = conn.prepareStatement(query);
            rs = prep.executeQuery();
            while(rs.next())
                projectList.add(rs.getString("title"));
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
    public ArrayList<String> getProjectStatusList()
    {
        ResultSet rs = null;
        Connection conn = null;
        PreparedStatement prep = null;
        ArrayList<String> projectList = new ArrayList<>();

        String query = "SELECT * FROM ProjectStatus";

        try
        {
            conn = dS.getConnection();
            prep = conn.prepareStatement(query);
            rs = prep.executeQuery();
            while(rs.next())
                projectList.add(rs.getString("title"));
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
