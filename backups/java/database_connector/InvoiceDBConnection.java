package com.nicholsonplumbingtx.v2.database_connector;

import com.nicholsonplumbingtx.v2.common.DBConnector;
import org.apache.commons.dbutils.DbUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Ethan Orcutt on 10/16/2016.
 */
public class InvoiceDBConnection extends DBConnector
{
    /**
     * Created By: Ethan
     * Created On: 10/29/2016
     * Last Modified: 10/29/2016
     *
     */
    public ArrayList<String> getCustomerList()
    {
        ResultSet rs = null;
        Connection conn = null;
        PreparedStatement prep = null;
        ArrayList<String> projectList = new ArrayList<>();

        String query = "SELECT * FROM Client ORDER BY company_name ASC;";

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
     * Created On: 10/2/2016
     * Last Modified: 10/28/2016
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
     * Created On: 10/16/2016
     * Last Modified: 10/16/2016
     *
     */
    public ArrayList<String> getProjectList(int selectedID)
    {
        ResultSet rs = null;
        Connection conn = null;
        PreparedStatement prep = null;
        ArrayList<String> projectList = new ArrayList<>();

        String query = "SELECT * FROM Project WHERE Project.client_id = ? ORDER BY project_name ASC;";

        try
        {
            conn = dS.getConnection();
            prep = conn.prepareStatement(query);
            prep.setInt(1, selectedID);
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
     * Created On: 10/16/2016
     * Last Modified: 10/16/2016
     *
     */
    public ArrayList<String> getInvoiceTypeList()
    {
        ResultSet rs = null;
        Connection conn = null;
        PreparedStatement prep = null;
        ArrayList<String> projectList = new ArrayList<>();

        String query = "SELECT * FROM InvoiceType";

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
     * Created On: 10/16/2016
     * Last Modified: 10/16/2016
     *
     */
    public ArrayList<String> getInvoiceStatusList()
    {
        ResultSet rs = null;
        Connection conn = null;
        PreparedStatement prep = null;
        ArrayList<String> projectList = new ArrayList<>();

        String query = "SELECT * FROM InvoiceStatus";

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
