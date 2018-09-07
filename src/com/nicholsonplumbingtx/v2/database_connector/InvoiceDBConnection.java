package com.nicholsonplumbingtx.v2.database_connector;

import com.microsoft.sqlserver.jdbc.SQLServerException;
import com.nicholsonplumbingtx.v2.model.change_order.ChangeOrder;
import com.nicholsonplumbingtx.v2.model.client.Client;
import com.nicholsonplumbingtx.v2.model.client.ClientBuilder;
import com.nicholsonplumbingtx.v2.model.common.Status;
import com.nicholsonplumbingtx.v2.model.common.StatusBuilder;
import com.nicholsonplumbingtx.v2.model.common.Type;
import com.nicholsonplumbingtx.v2.model.common.TypeBuilder;
import com.nicholsonplumbingtx.v2.model.invoice.Invoice;
import com.nicholsonplumbingtx.v2.model.invoice.InvoiceBuilder;
import com.nicholsonplumbingtx.v2.model.project.Project;
import com.nicholsonplumbingtx.v2.model.project.ProjectBuilder;
import org.apache.commons.dbutils.DbUtils;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Ethan Orcutt on 10/16/2016.
 * Project D.U.K.E.
 */
public class InvoiceDBConnection extends DBConnector
{
    public void createInvoice(Invoice newInvoice)
    {
        PreparedStatement prep = null;
        Connection conn = null;

        String query = "INSERT INTO Invoice(project_id, invoice_type_id, invoice_status_id," +
                "change_order_net_total, completed_sum, notes, total_due, total_still_due, date_created)" +
                "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try
        {
            conn = dS.getConnection();
            prep = conn.prepareStatement(query);

            prep.setInt(1, newInvoice.getProjectID());
            prep.setInt(2, newInvoice.getInvoiceTypeID());
            prep.setInt(3, newInvoice.getInvoiceStatusID());
            prep.setDouble(4, newInvoice.getChangeOrderNetTotal());
            prep.setDouble(5, newInvoice.getCompletedSum());
            prep.setString(6, newInvoice.getNotes());
            prep.setDouble(7, newInvoice.getTotalDue());
            prep.setDouble(8, newInvoice.getTotalStillDue());
            prep.setDate(9, newInvoice.getDateCreated());

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

    public void createChangeOrder(ChangeOrder newChangeOrder)
    {
        PreparedStatement prep = null;
        Connection conn = null;
        //TODO: Finish this Query.
        String query = "INSERT INTO ChangeOrder(change_order_status_id, invoice_id, material," +
                "sales_tax, direct_labor, indirect_costs, equipment_tools, overhead, sub_contracts," +
                "overhead_percentage, profit_percentage, bond_premium_percentage, service_percentage, final_total," +
                "exclusions, valid_days, contract_extension, notes, date_created, subtotal_one, subtotal_two," +
                "subtotal_three, order_add, order_deduct)" +
                "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try
        {
            conn = dS.getConnection();
            prep = conn.prepareStatement(query);

            prep.setInt(1, newChangeOrder.getChangeOrderStatusID());
            prep.setInt(2, newChangeOrder.getInvoiceID());
            prep.setDouble(3, newChangeOrder.getMaterial());
            prep.setDouble(4, newChangeOrder.getSalesTax());
            prep.setDouble(5, newChangeOrder.getDirectLabor());
            prep.setDouble(6, newChangeOrder.getIndirectCosts());
            prep.setDouble(7, newChangeOrder.getEquipmentToolsCosts());
            prep.setDouble(8, newChangeOrder.getOverheadCosts());
            prep.setDouble(9, newChangeOrder.getSubContracts());
            prep.setDouble(10, newChangeOrder.getOverheadCosts());
            prep.setDouble(11, newChangeOrder.getProfitPercentage());
            prep.setDouble(12, newChangeOrder.getBondPremiumPercentage());
            prep.setDouble(13, newChangeOrder.getServicePercentage());
            prep.setDouble(14, newChangeOrder.getFinalTotal());
            prep.setString(15, newChangeOrder.getExclusions());
            prep.setInt(16, newChangeOrder.getDaysValid());
            prep.setInt(17, newChangeOrder.getContractExtension());
            prep.setString(18, newChangeOrder.getNotes());
            prep.setDate(19, newChangeOrder.getDateCreated());
            prep.setDouble(20, newChangeOrder.getSubtotalF());
            prep.setDouble(21, newChangeOrder.getSubtotalJ());
            prep.setDouble(22, newChangeOrder.getSubtotalL());
            prep.setBoolean(23, newChangeOrder.isAdd());
            prep.setBoolean(24, newChangeOrder.isDeduct());

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

    public void updateInvoice(Invoice updatedInvoice)
    {
        Connection conn = null;
        PreparedStatement prep = null;

        String query = "UPDATE Invoice SET project_id = ?, invoice_type_id = ?, invoice_status_id = ?," +
                "change_order_net_total = ?, completed_sum = ?, notes = ?, total_due = ?, total_still_due = ?," +
                "date_created = ? WHERE invoice_id = ?;";

        try
        {
            conn = dS.getConnection();
            prep = conn.prepareStatement(query);

            prep.setInt(1, updatedInvoice.getProjectID());
            prep.setInt(2, updatedInvoice.getInvoiceTypeID());
            prep.setInt(3, updatedInvoice.getInvoiceStatusID());
            prep.setDouble(4, updatedInvoice.getChangeOrderNetTotal());
            prep.setDouble(5, updatedInvoice.getCompletedSum());
            prep.setString(6, updatedInvoice.getNotes());
            prep.setDouble(7, updatedInvoice.getTotalDue());
            prep.setDouble(8, updatedInvoice.getTotalStillDue());
            prep.setDate(9, updatedInvoice.getDateCreated());
            prep.setInt(10, updatedInvoice.getInvoiceID());

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
     * Created On: 10/29/2016
     * Last Modified: 10/29/2016
     *
     */
    public ArrayList<Client> getCustomerList()
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
            while(rs.next())
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
     * Created On: 10/16/2016
     * Last Modified: 10/16/2016
     *
     */
    public ArrayList<Project> getProjectList(int selectedID)
    {
        ResultSet rs = null;
        Connection conn = null;
        PreparedStatement prep = null;
        ArrayList<Project> projectList = new ArrayList<>();

        String query = "SELECT * FROM Project WHERE Project.client_id = ? ORDER BY project_name ASC;";

        try
        {
            conn = dS.getConnection();
            prep = conn.prepareStatement(query);
            prep.setInt(1, selectedID);
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

    public ArrayList<Invoice> getInvoiceList(int selectedID)
    {
        ResultSet rs = null;
        Connection conn = null;
        PreparedStatement prep = null;
        ArrayList<Invoice> invoiceList = new ArrayList<>();

        String query = "SELECT * FROM Invoice WHERE project_id = ? ORDER BY invoice_id;";

        try
        {
            conn = dS.getConnection();
            prep = conn.prepareStatement(query);
            prep.setInt(1, selectedID);
            rs = prep.executeQuery();
            while(rs.next())
            {
                invoiceList.add(new InvoiceBuilder()
                        .setInvoiceID(rs.getInt("invoice_id"))
                        .setProjectID(rs.getInt("project_id"))
                        .setInvoiceTypeID(rs.getInt("invoice_type_id"))
                        .setInvoiceStatusID(rs.getInt("invoice_status_id"))
                        .setChangeOrderNetTotal(rs.getDouble("change_order_net_total"))
                        .setCompletedSum(rs.getDouble("completed_sum"))
                        .setNotes(rs.getString("notes"))
                        .setTotalDue(rs.getDouble("total_due"))
                        .setTotalStillDue(rs.getDouble("total_still_due"))
                        .setDateCreated(rs.getDate("date_created"))
                        .createInvoice());
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
        return invoiceList;
    }

    /**
     * Created By: Ethan
     * Created On: 10/16/2016
     * Last Modified: 10/16/2016
     *
     */
    public ArrayList<Type> getInvoiceTypeList()
    {
        ResultSet rs = null;
        Connection conn = null;
        PreparedStatement prep = null;
        String idColumnName;
        ArrayList<Type> typeList = new ArrayList<>();

        String query = "SELECT * FROM InvoiceType";

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
     * Created On: 10/16/2016
     * Last Modified: 10/16/2016
     *
     */
    public ArrayList<Status> getInvoiceStatusList()
    {
        ResultSet rs = null;
        Connection conn = null;
        PreparedStatement prep = null;
        ArrayList<Status> statusList = new ArrayList<>();
        String idColumnName;

        String query = "SELECT * FROM InvoiceStatus";

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

    public ArrayList<Status> getChangeOrderStatusList()
    {
        ResultSet rs = null;
        Connection conn = null;
        PreparedStatement prep = null;
        ArrayList<Status> statusList = new ArrayList<>();
        String idColumnName;

        String query = "SELECT * FROM ChangeOrderStatus";

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

    public Project getProject(int projectID)
    {
        ResultSet rs = null;
        Connection conn = null;
        PreparedStatement prep = null;
        Project singleProject = null;

        String query = "SELECT\n" + "p.project_id, p.client_id, p.project_status_id,\n" + "p.project_type_id, p.project_name, p.project_bid_date, \n" + "p.project_start_date, p.project_notes,\n" + "  ps.title AS [Status], ps.description AS [Status Desc],\n" + "  pt.title AS [Type], pt.description AS [Type Desc]\n" + "\n" + "FROM Project AS p\n" + "JOIN ProjectStatus AS ps ON ps.project_status_id = p.project_status_id\n" + "JOIN ProjectType AS pt ON pt.project_type_id = p.project_type_id\n" + "WHERE p.project_id = ?;";
        try
        {
            conn = dS.getConnection();
            prep = conn.prepareStatement(query);
            prep.setInt(1, projectID);
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
}