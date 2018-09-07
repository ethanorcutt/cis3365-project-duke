package com.nicholsonplumbingtx.v2.database_connector;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.nicholsonplumbingtx.v2.model.common.Status;
import com.nicholsonplumbingtx.v2.model.common.StatusBuilder;
import com.nicholsonplumbingtx.v2.model.common.Type;
import com.nicholsonplumbingtx.v2.model.common.TypeBuilder;
import org.apache.commons.dbutils.DbUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Ethan Orcutt on 10/15/2016.
 * Project D.U.K.E.
 */
public abstract class DBConnector
{
    final SQLServerDataSource dS = new SQLServerDataSource();

    DBConnector()
    {
        dS.setPortNumber(1433);
        dS.setServerName("localhost");
        dS.setDatabaseName("DukeDB");
        dS.setUser("dukeConnector");
        dS.setPassword("test1234");
    }

    public ArrayList<Status> getContactStatusList()
    {
        ResultSet rs = null;
        Connection conn = null;
        PreparedStatement prep = null;
        ArrayList<Status> statusList = new ArrayList<>();
        String idColumnName;

        String query = "SELECT * FROM ContactStatus";

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

    public ArrayList<Type> getContactTypeList()
    {
        ResultSet rs = null;
        Connection conn = null;
        PreparedStatement prep = null;
        ArrayList<Type> typeList = new ArrayList<>();
        String idColumnName;

        String query = "SELECT * FROM ContactType";

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
}