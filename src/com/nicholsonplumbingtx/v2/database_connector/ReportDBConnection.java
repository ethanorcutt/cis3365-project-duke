package com.nicholsonplumbingtx.v2.database_connector;

import com.nicholsonplumbingtx.v2.controller.common_controller.ScreenLoader;
import com.nicholsonplumbingtx.v2.controller.main_form_controller.ReportViewerController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import org.apache.commons.dbutils.DbUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ethan Orcutt on 11/6/2016.
 * Project D.U.K.E.
 */
public class ReportDBConnection extends DBConnector
{
    private List<String> columnsList;
    private List<String[]> data;

    public void runReport(String query)
    {
        ResultSet rs = null;
        Connection conn = null;
        PreparedStatement prep = null;

        try
        {
            conn = dS.getConnection();
            prep = conn.prepareStatement(query);
            rs = prep.executeQuery();
            buildReport(rs);
            tmp.openNewStageWindow(loadScreen("main_forms/reportViewer.fxml", columnsList, data));
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        finally
        {
            DbUtils.closeQuietly(rs);
            DbUtils.closeQuietly(prep);
            DbUtils.closeQuietly(conn);
        }
    }

    private void buildReport(ResultSet rs)
    {
        columnsList = new ArrayList<>();
        data = new ArrayList<>();

        try
        {
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnCount = rsmd.getColumnCount();

            for(int i = 1; i <= columnCount; i++)
                columnsList.add(rsmd.getColumnName(i));

            while(rs.next())
            {
                String[] row = new String[columnCount];
                for (int iCol = 1; iCol <= columnCount; iCol++)
                {
                    Object obj = rs.getObject(iCol);
                    row[iCol - 1] = (obj == null) ? null:obj.toString();
                }
                data.add(row);
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    private ScreenLoader tmp = new ScreenLoader()
    {
        @Override
        public void openNewStageWindow(Parent value)
        {
            super.openNewStageWindow(value);
        }
    };

    public Parent loadScreen(String resource, List<String> cNames, List<String[]> data)
    {
        try
        {
            FXMLLoader myLoader = new FXMLLoader(getClass().getClassLoader().getResource("resources/" + resource));
            Parent loadScreen = myLoader.load();
            ReportViewerController masterController = myLoader.getController();
            masterController.loadInformation(cNames, data);
            return loadScreen;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        return null;
    }
}