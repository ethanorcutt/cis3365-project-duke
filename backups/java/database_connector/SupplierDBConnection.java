package com.nicholsonplumbingtx.v2.database_connector;

import com.nicholsonplumbingtx.v2.common.DBConnector;
import org.apache.commons.dbutils.DbUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Ethan Orcutt on 10/18/2016.
 */
public class SupplierDBConnection extends DBConnector
{
    public ArrayList<String> getSupplierList()
    {
        ResultSet rs = null;
        Connection conn = null;
        PreparedStatement prep = null;
        ArrayList<String> supplierList = new ArrayList<>();

        String query = "SELECT * FROM Supplier ORDER BY company_name;";

        try
        {
            conn = dS.getConnection();
            prep = conn.prepareStatement(query);
            rs = prep.executeQuery();
            while(rs.next())
                supplierList.add(rs.getString("company_name"));
        } catch (SQLException e)
        {
            e.printStackTrace();
        } finally
        {
            DbUtils.closeQuietly(rs);
            DbUtils.closeQuietly(prep);
            DbUtils.closeQuietly(conn);
        }
        return supplierList;
    }
}