package com.nicholsonplumbingtx.v2.database_connector;

import com.nicholsonplumbingtx.v2.common.DBConnector;
import com.nicholsonplumbingtx.v2.model.Employee;
import com.nicholsonplumbingtx.v2.model.EmployeeBuilder;
import org.apache.commons.dbutils.DbUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Ethan Orcutt on 10/15/2016.
 */
public class EmployeeDBConnection extends DBConnector
{
    public int getEmployeeID(String clicked)
    {
        ResultSet rs = null;
        Connection conn = null;
        PreparedStatement prep = null;

        String query = "SELECT e.employee_id FROM Employee AS e WHERE e.first_name = ?;";

        int selectedID = 0;

        try
        {
            conn = dS.getConnection();
            prep = conn.prepareStatement(query);
            prep.setString(1, clicked.substring(0, clicked.indexOf(" ")));
            rs = prep.executeQuery();

            while(rs.next())
                selectedID = rs.getInt("employee_id");

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

    public Employee getEmployeeList(int selectedEmployee)
    {
        ResultSet rs = null;
        Connection conn = null;
        PreparedStatement prep = null;
        Employee singleEmployee = null;

        String query = "SELECT\n" + "  eS.title,\n" + "  eT.title,\n" + "  e.employee_id, e.employee_status_id, " +
                "e.employee_type_id,\n" + "  e.first_name, e.middle_initial, e.last_name, e.suffix,\n" + "  e.salary, " +
                "e.salary_frequency_id, e.address_line_one,\n" + "  e.address_line_two, e.city, e.region_id," +
                " e.country_id,\n" + "  e.postal_zip_code, e.phone_number, e.email\n" + "\n" + "FROM Employee AS e\n" +
                "JOIN EmployeeStatus AS eS ON e.employee_status_id = eS.employee_status_id\n" +
                "JOIN EmployeeType AS eT ON e.employee_type_id = eT.employee_type_id\n" +
                "WHERE e.employee_id = ?;";

        try
        {
            conn = dS.getConnection();
            prep = conn.prepareStatement(query);
            prep.setInt(1, selectedEmployee);
            rs = prep.executeQuery();

            while (rs.next())
            {
                singleEmployee = new EmployeeBuilder()
                        .setEmployeeID(rs.getInt("employee_id"))
                        .setEmployeeStatusID(rs.getInt("employee_status_id"))
                        .setEmployeeTypeID(rs.getInt("employee_type_id"))
                        .setFirstName(rs.getString("first_name"))
                        .setMiddleInitial(rs.getString("middle_initial"))
                        .setLastName(rs.getString("last_name"))
                        .setSuffix(rs.getString("suffix"))
                        .setSalary(rs.getDouble("salary"))
                        .setSalaryFrequencyID(rs.getInt("salary_frequency_id"))
                        .setAddressLineOne(rs.getString("address_line_one"))
                        .setAddressLineTwo(rs.getString("address_line_two"))
                        .setCity(rs.getString("city"))
                        .setRegionID(rs.getInt("region_id"))
                        .setCountryID(rs.getInt("country_id"))
                        .setZipCode(rs.getString("postal_zip_code"))
                        .setPhoneNumber(rs.getString("phone_number"))
                        .setEmail(rs.getString("email"))
                        .createEmployee();
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
        return singleEmployee;
    }

    public ArrayList<Employee> getEmployeeList(String employeeStatus, String employeeType)
    {
        ResultSet rs = null;
        Connection conn = null;
        PreparedStatement prep = null;
        ArrayList<Employee> employeeList = new ArrayList<>();

        String query = "SELECT\n" + "  eS.title,\n" + "  eT.title,\n" + "  e.employee_id, e.employee_status_id, " +
                "e.employee_type_id,\n" + "  e.first_name, e.middle_initial, e.last_name, e.suffix,\n" + "  e.salary, " +
                "e.salary_frequency_id, e.address_line_one,\n" + "  e.address_line_two, e.city, e.region_id," +
                " e.country_id,\n" + "  e.postal_zip_code, e.phone_number, e.email\n" + "\n" + "FROM Employee AS e\n" +
                "JOIN EmployeeStatus AS eS ON e.employee_status_id = eS.employee_status_id\n" +
                "JOIN EmployeeType AS eT ON e.employee_type_id = eT.employee_type_id\n" +
                "WHERE (eS.title = ? AND eT.title = ?)\n" + "ORDER BY first_name;";

        try
        {
            conn = dS.getConnection();
            prep = conn.prepareStatement(query);
            prep.setString(1, employeeStatus);
            prep.setString(2, employeeType);
            rs = prep.executeQuery();

            while (rs.next())
            {
                employeeList.add(new EmployeeBuilder()
                        .setEmployeeID(rs.getInt("employee_id"))
                        .setEmployeeStatusID(rs.getInt("employee_status_id"))
                        .setEmployeeTypeID(rs.getInt("employee_type_id"))
                        .setFirstName(rs.getString("first_name"))
                        .setMiddleInitial(rs.getString("middle_initial"))
                        .setLastName(rs.getString("last_name"))
                        .setSuffix(rs.getString("suffix"))
                        .setSalary(rs.getDouble("salary"))
                        .setSalaryFrequencyID(rs.getInt("salary_frequency_id"))
                        .setAddressLineOne(rs.getString("address_line_one"))
                        .setAddressLineTwo(rs.getString("address_line_two"))
                        .setCity(rs.getString("city"))
                        .setRegionID(rs.getInt("region_id"))
                        .setCountryID(rs.getInt("country_id"))
                        .setZipCode(rs.getString("postal_zip_code"))
                        .setPhoneNumber(rs.getString("phone_number"))
                        .setEmail(rs.getString("email"))
                        .createEmployee());
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
        return employeeList;
    }

    public ArrayList<String> getEmployeeStatusList()
    {
        ResultSet rs = null;
        Connection conn = null;
        PreparedStatement prep = null;
        ArrayList<String> employeeStatusList = new ArrayList<>();

        String query = "SELECT * FROM EmployeeStatus";

        try
        {
            conn = dS.getConnection();
            prep = conn.prepareStatement(query);
            rs = prep.executeQuery();

            while (rs.next())
                employeeStatusList.add(rs.getString("title"));
        } catch (SQLException e)
        {
            e.printStackTrace();
        } finally
        {
            DbUtils.closeQuietly(rs);
            DbUtils.closeQuietly(prep);
            DbUtils.closeQuietly(conn);
        }
        return employeeStatusList;
    }

    public ArrayList<String> getEmployeeTypeList()
    {
        ResultSet rs = null;
        Connection conn = null;
        PreparedStatement prep = null;
        ArrayList<String> employeeTypeList = new ArrayList<>();

        String query = "SELECT * FROM EmployeeType";

        try
        {
            conn = dS.getConnection();
            prep = conn.prepareStatement(query);
            rs = prep.executeQuery();

            while (rs.next())
                employeeTypeList.add(rs.getString("title"));
        } catch (SQLException e)
        {
            e.printStackTrace();
        } finally
        {
            DbUtils.closeQuietly(rs);
            DbUtils.closeQuietly(prep);
            DbUtils.closeQuietly(conn);
        }
        return employeeTypeList;
    }
}