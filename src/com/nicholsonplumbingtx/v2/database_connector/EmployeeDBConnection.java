package com.nicholsonplumbingtx.v2.database_connector;

import com.microsoft.sqlserver.jdbc.SQLServerException;
import com.nicholsonplumbingtx.v2.model.common.*;
import com.nicholsonplumbingtx.v2.model.employee.Employee;
import com.nicholsonplumbingtx.v2.model.employee.EmployeeBuilder;
import com.nicholsonplumbingtx.v2.model.employee.EmployeeHistory;
import com.nicholsonplumbingtx.v2.model.employee.EmployeeHistoryBuilder;
import com.nicholsonplumbingtx.v2.model.project.Project;
import com.nicholsonplumbingtx.v2.model.project.ProjectBuilder;
import com.nicholsonplumbingtx.v2.model.vehicle.Make;
import com.nicholsonplumbingtx.v2.model.vehicle.Model;
import com.nicholsonplumbingtx.v2.model.vehicle.Vehicle;
import com.nicholsonplumbingtx.v2.model.vehicle.VehicleBuilder;
import org.apache.commons.dbutils.DbUtils;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;

/**
 * Created by Ethan Orcutt on 10/15/2016.
 * Project D.U.K.E.
 */
public class EmployeeDBConnection extends DBConnector
{
    public void createEmployee(Employee newEmployee)
    {
        PreparedStatement prep = null;
        Connection conn = null;

        String query = "INSERT INTO Employee(employee_status_id, employee_type_id, first_name, middle_initial," +
                "last_name, suffix, salary, salary_frequency_id, address_line_one, address_line_two, city, region_id," +
                "country_id, postal_zip_code, phone_number, email)" +
                "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?,?, ?, ?, ?, ?, ?, ?)";

        try
        {
            conn = dS.getConnection();
            prep = conn.prepareStatement(query);

            prep.setInt(1, newEmployee.getEmployeeStatusID());
            prep.setInt(2, newEmployee.getEmployeeTypeID());
            prep.setString(3, newEmployee.getFirstName());
            prep.setString(4, newEmployee.getMiddleInitial());
            prep.setString(5, newEmployee.getLastName());
            prep.setString(6, newEmployee.getSuffix());
            prep.setDouble(7, newEmployee.getSalary());
            prep.setInt(8, newEmployee.getSalaryFrequencyID());
            prep.setString(9, newEmployee.getAddressLineOne());
            prep.setString(10, newEmployee.getAddressLineTwo());
            prep.setString(11, newEmployee.getCity());
            prep.setInt(12, newEmployee.getRegionID());
            prep.setInt(13, newEmployee.getCountryID());
            prep.setString(14, newEmployee.getZipCode());
            prep.setString(15, newEmployee.getPhoneNumber());
            prep.setString(16, newEmployee.getEmail());

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

    public void updateEmployee(Employee updatedEmployee)
    {
        Connection conn = null;
        PreparedStatement prep = null;

        String query = "UPDATE Employee SET employee_status_id = ?, employee_type_id = ?, first_name = ?," +
                "last_name = ?, middle_initial = ?, suffix = ?, salary = ?, salary_frequency_id = ?, " +
                "address_line_one = ?, address_line_two = ?, city = ?, postal_zip_code = ?, region_id = ?, " +
                "country_id = ?, phone_number = ?, email = ? WHERE employee_id = ?;";

        try
        {
            conn = dS.getConnection();
            prep = conn.prepareStatement(query);

            prep.setInt(1, updatedEmployee.getEmployeeStatusID());
            prep.setInt(2, updatedEmployee.getEmployeeTypeID());
            prep.setString(3, updatedEmployee.getFirstName());
            prep.setString(4, updatedEmployee.getLastName());
            prep.setString(5, updatedEmployee.getMiddleInitial());
            prep.setString(6, updatedEmployee.getSuffix());
            prep.setDouble(7, updatedEmployee.getSalary());
            prep.setInt(8, updatedEmployee.getSalaryFrequencyID());
            prep.setString(9, updatedEmployee.getAddressLineOne());
            prep.setString(10, updatedEmployee.getAddressLineTwo());
            prep.setString(11, updatedEmployee.getCity());
            prep.setString(12, updatedEmployee.getZipCode());
            prep.setInt(13, updatedEmployee.getRegionID());
            prep.setInt(14, updatedEmployee.getCountryID());
            prep.setString(15, updatedEmployee.getPhoneNumber());
            prep.setString(16, updatedEmployee.getEmail());
            prep.setInt(17, updatedEmployee.getEmployeeID());

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

    public Employee getEmployee(int selectedEmployee)
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

    public Employee getEmployee(String empName)
    {
        ResultSet rs = null;
        Connection conn = null;
        PreparedStatement prep = null;
        Employee singleEmployee = null;

        String query = "SELECT * FROM Employee WHERE (first_name = ? AND last_name = ?)";

        try
        {
            conn = dS.getConnection();
            prep = conn.prepareStatement(query);
            prep.setString(1, empName.substring(0, empName.indexOf(" ")));
            prep.setString(2, empName.substring(empName.indexOf(" ") + 1, empName.length()));
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

    public ArrayList<Employee> getEmployeeList()
    {
        ResultSet rs = null;
        Connection conn = null;
        PreparedStatement prep = null;
        ArrayList<Employee> employeeList = new ArrayList<>();

        String query = "SELECT * FROM Employee WHERE employee_status_id = '1' ORDER BY first_name";

        try
        {
            conn = dS.getConnection();
            prep = conn.prepareStatement(query);
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

    public ArrayList<Project> getProjectList()
    {
        ResultSet rs = null;
        Connection conn = null;
        PreparedStatement prep = null;
        ArrayList<Project> projectList = new ArrayList<>();

        String query = "SELECT * FROM Project ORDER BY project_name ASC;";

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

    public ArrayList<Vehicle> getVehicleList()
    {
        ResultSet rs = null;
        Connection conn = null;
        PreparedStatement prep = null;
        ArrayList<Vehicle> vehicleList = new ArrayList<>();

        String query = "SELECT\n" + "  v.vehicle_id, v.vehicle_status_id, v.vehicle_type_id,\n" + "  v.model_year, v.license_plate, v.description,\n" + "  m.make_id, m.title AS [Make], mO.model_id, mO.make_id, mO.title AS [Model],\n" + "  vS.title AS [Status], vT.title AS [Type]\n" + "\n" + "FROM Vehicle AS v\n" + "JOIN EmployeeVehicle AS eV ON eV.vehicle_id = v.vehicle_id\n" + "JOIN Employee AS e ON e.employee_id = eV.employee_id\n" + "JOIN Make AS m ON v.make_id = m.make_id\n" + "JOIN Model AS mO ON v.model_id = mO.model_id\n" + "JOIN VehicleStatus AS vS ON v.vehicle_status_id = vs.vehicle_status_id\n" + "JOIN VehicleType AS vT ON v.vehicle_type_id = vt.vehicle_type_id\n" + "ORDER BY vehicle_id;";

        try
        {
            conn = dS.getConnection();
            prep = conn.prepareStatement(query);
            rs = prep.executeQuery();
            while(rs.next())
            {
                vehicleList.add(new VehicleBuilder()
                        .setVehicleID(rs.getInt("vehicle_id"))
                        .setVehicleStatusID(rs.getInt("vehicle_status_id"))
                        .setVehicleTypeID(rs.getInt("vehicle_type_id"))
                        .setVehicleStatus(rs.getString("Status"))
                        .setVehicleType(rs.getString("Type"))
                        .setModelYear(rs.getInt("model_year"))
                        .setVehicleModel(new Model(rs.getInt("model_id"), rs.getInt("make_id"), rs.getString("Model")))
                        .setVehicleMake(new Make(rs.getInt("make_id"), rs.getString("Make")))
                        .setNotes(rs.getString("description"))
                        .setLicensePlate(rs.getString("license_plate"))
                        .createVehicle());
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
        return vehicleList;
    }

    public ArrayList<Employee> getEmployeeList(String employeeStatus)
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
                "WHERE eS.title = ? ORDER BY first_name;";

        try
        {
            conn = dS.getConnection();
            prep = conn.prepareStatement(query);
            prep.setString(1, employeeStatus);
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

    public ArrayList<Status> getEmployeeStatusList()
    {
        ResultSet rs = null;
        Connection conn = null;
        PreparedStatement prep = null;
        ArrayList<Status> employeeStatusList = new ArrayList<>();
        String idColumnName;

        String query = "SELECT * FROM EmployeeStatus";

        try
        {
            conn = dS.getConnection();
            prep = conn.prepareStatement(query);
            rs = prep.executeQuery();
            idColumnName = rs.getMetaData().getColumnName(1);

            while (rs.next())
            {
                employeeStatusList.add(new StatusBuilder()
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
        return employeeStatusList;
    }

    public ArrayList<Type> getEmployeeTypeList()
    {
        ResultSet rs = null;
        Connection conn = null;
        PreparedStatement prep = null;
        ArrayList<Type> employeeTypeList = new ArrayList<>();
        String idColumnName;

        String query = "SELECT * FROM EmployeeType";

        try
        {
            conn = dS.getConnection();
            prep = conn.prepareStatement(query);
            rs = prep.executeQuery();
            idColumnName = rs.getMetaData().getColumnName(1);

            while(rs.next())
            {
                employeeTypeList.add(new TypeBuilder()
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
        return employeeTypeList;
    }

    public ArrayList<SalaryFrequency> getSalaryFrequencyList()
    {
        ResultSet rs = null;
        Connection conn = null;
        PreparedStatement prep = null;
        ArrayList<SalaryFrequency> salaryFrequencyList = new ArrayList<>();
        String idColumnName;

        String query = "SELECT * FROM SalaryFrequency";

        try
        {
            conn = dS.getConnection();
            prep = conn.prepareStatement(query);
            rs = prep.executeQuery();
            idColumnName = rs.getMetaData().getColumnName(1);

            while(rs.next())
            {
                salaryFrequencyList.add(new SalaryFrequencyBuilder()
                        .setSalaryFrequencyID(rs.getInt(idColumnName))
                        .setTitle(rs.getString("title"))
                        .setDescription(rs.getString("description"))
                        .createSalaryFrequency());
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
        return salaryFrequencyList;
    }

    public ArrayList<EmployeeHistory> getEmployeeHistoryLog(int employeeID)
    {
        ResultSet rs = null;
        Connection conn = null;
        PreparedStatement prep = null;
        ArrayList<EmployeeHistory> historylog = new ArrayList<>();

        String query = "SELECT\n" + "\teh.description,\n" + "\teh.entry_date,\n" + "\teh.salary,\n" + "\teh.salary_frequency_id,\n" + "\tes.employee_status_id,\n" + "\tes.title AS [Status], \n" + "\tes.description AS [Status Desc],\n" + "\tet.employee_type_id, \n" + "\tet.title AS [Type], \n" + "\tet.description AS [Type Desc]\n" + "\n" + "FROM EmployeeHistory AS eh\n" + "JOIN Employee AS e ON e.employee_id = eh.employee_id\n" + "JOIN EmployeeStatus AS es ON es.employee_status_id = e.employee_status_id\n" + "JOIN EmployeeType AS et ON et.employee_type_id = e.employee_type_id\n" + "WHERE eh.employee_id = ?;";

        try
        {
            conn = dS.getConnection();
            prep = conn.prepareStatement(query);
            prep.setInt(1, employeeID);
            rs = prep.executeQuery();

            while (rs.next())
            {
                historylog.add(new EmployeeHistoryBuilder()
                        .setEmployeeStatus(new Status(rs.getInt("employee_status_id"), rs.getString("Status"), rs.getString("Status Desc")))
                        .setEmployeeType(new Type(rs.getInt("employee_type_id"), rs.getString("Type"), rs.getString("Type Desc")))
                        .setDescription(rs.getString("description"))
                        .setEntry_date(rs.getDate("entry_date"))
                        .setSalary(rs.getDouble("salary"))
                        .setSalaryFrequency(rs.getInt("salary_frequency_id"))
                        .createEmployeeHistory());
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
        return historylog;
    }

    public void assignProject(Employee emp, Project proj, Date dateCreated)
    {
        PreparedStatement prep = null;
        Connection conn = null;

        String query = "INSERT INTO ProjectEmployee(project_id, employee_id, first_name, last_name, date_assigned)" +
                "VALUES(?,?,?,?,?)";

        try
        {
            conn = dS.getConnection();
            prep = conn.prepareStatement(query);

            prep.setInt(1, proj.getProjectID());
            prep.setInt(2, emp.getEmployeeID());
            prep.setString(3, emp.getFirstName());
            prep.setString(4, emp.getLastName());
            prep.setDate(5, dateCreated);

            prep.executeUpdate();
        }
        catch (Exception ex)
        {
            JOptionPane.showMessageDialog(null, ex.getClass().getSimpleName() + ": " + ex.getMessage());
        }
        finally
        {
            DbUtils.closeQuietly(prep);
            DbUtils.closeQuietly(conn);
        }
    }

    public void assignVehicle(Employee emp, Vehicle vehic, Date dateAssigned, String notes)
    {
        PreparedStatement prep = null;
        Connection conn = null;

        String query = "INSERT INTO EmployeeVehicle(employee_id, vehicle_id, date_assigned, notes)" +
                "VALUES(?,?,?,?)";

        try
        {
            conn = dS.getConnection();
            prep = conn.prepareStatement(query);

            prep.setInt(1, emp.getEmployeeID());
            prep.setInt(2, vehic.getVehicleID());
            prep.setDate(3, dateAssigned);
            prep.setString(4, notes);

            prep.executeUpdate();
        }
        catch (Exception ex)
        {
            JOptionPane.showMessageDialog(null, ex.getClass().getSimpleName() + ": " + ex.getMessage());
        }
        finally
        {
            DbUtils.closeQuietly(prep);
            DbUtils.closeQuietly(conn);
        }
    }

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

    public ArrayList<Project> getAssignedProjects(int employeeID)
    {
        ResultSet rs = null;
        Connection conn = null;
        PreparedStatement prep = null;
        ArrayList<Project> projectList = new ArrayList<>();

        String query = "SELECT\n" + "  p.project_id, p.client_id, p.project_status_id, p.project_type_id, " +
                "p.project_name, p.project_bid_date, p.project_start_date, p.project_notes, pS.title AS [Status], " +
                "pT.title AS [Type] FROM Employee AS e JOIN ProjectEmployee AS pE ON pE.employee_id = e.employee_id " +
                "JOIN Project AS p ON p.project_id = pE.project_id JOIN ProjectStatus AS pS ON " +
                "p.project_status_id = pS.project_status_id JOIN ProjectType AS pT ON " +
                "p.project_type_id = pT.project_type_id WHERE (e.employee_id = ? AND p.project_status_id = 1) ORDER BY p.project_name;";

        try
        {
            conn = dS.getConnection();
            prep = conn.prepareStatement(query);
            prep.setInt(1, employeeID);
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
                        .setProjectStatus(new Status(rs.getInt("project_status_id"), rs.getString("Status"), rs.getString("Status Desc")))
                        .setProjectType(new Type(rs.getInt("project_type_id"), rs.getString("Type"), rs.getString("Type Desc")))
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

    public ArrayList<Vehicle> getAssignedVehicles(int employeeID)
    {
        ResultSet rs = null;
        Connection conn = null;
        PreparedStatement prep = null;
        ArrayList<Vehicle> vehicleList = new ArrayList<>();

        String query = "SELECT\n" + "  v.vehicle_id, v.vehicle_status_id, v.vehicle_type_id,\n" + "  v.model_year, v.license_plate, v.description,\n" + "  m.make_id, m.title AS [Make], mO.model_id, mO.make_id, mO.title as [Model],\n" + "  vS.title AS [Status], vT.title AS [Type]\n" + "\n" + "FROM Vehicle AS v\n" + "JOIN EmployeeVehicle AS eV ON eV.vehicle_id = v.vehicle_id\n" + "JOIN Employee AS e ON e.employee_id = eV.employee_id\n" + "JOIN Make AS m ON v.make_id = m.make_id\n" + "JOIN Model AS mO ON v.model_id = mO.model_id\n" + "JOIN VehicleStatus AS vS ON v.vehicle_status_id = vs.vehicle_status_id\n" + "JOIN VehicleType AS vT ON v.vehicle_type_id = vt.vehicle_type_id\n" + "\n" + "WHERE e.employee_id = ?\n" + "ORDER BY vehicle_id;";
        try
        {
            conn = dS.getConnection();
            prep = conn.prepareStatement(query);
            prep.setInt(1, employeeID);
            rs = prep.executeQuery();
            while(rs.next())
            {
                vehicleList.add(new VehicleBuilder()
                        .setVehicleID(rs.getInt("vehicle_id"))
                        .setVehicleStatusID(rs.getInt("vehicle_status_id"))
                        .setVehicleTypeID(rs.getInt("vehicle_type_id"))
                        .setVehicleStatus(rs.getString("Status"))
                        .setVehicleType(rs.getString("Type"))
                        .setModelYear(rs.getInt("model_year"))
                        .setVehicleModel(new Model(rs.getInt("model_id"), rs.getInt("make_id"), rs.getString("Model")))
                        .setVehicleMake(new Make(rs.getInt("make_id"), rs.getString("Make")))
                        .setNotes(rs.getString("description"))
                        .setLicensePlate(rs.getString("license_plate"))
                        .createVehicle());
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
        return vehicleList;
    }
}