package com.nicholsonplumbingtx.v2.database_connector;

import com.microsoft.sqlserver.jdbc.SQLServerException;
import com.nicholsonplumbingtx.v2.model.contact.Contact;
import com.nicholsonplumbingtx.v2.model.vendor.Vendor;
import com.nicholsonplumbingtx.v2.model.vendor.VendorBuilder;
import com.nicholsonplumbingtx.v2.model.vendor.VendorContact;
import org.apache.commons.dbutils.DbUtils;

import java.sql.*;
import java.util.ArrayList;

/**
 * Created by Ethan Orcutt on 10/18/2016.
 * Project D.U.K.E.
 */
public class VendorDBConnection extends DBConnector
{
    public ArrayList<Vendor> getVendorList()
    {
        ResultSet rs = null;
        Connection conn = null;
        PreparedStatement prep = null;
        ArrayList<Vendor> vendorList = new ArrayList<>();

        String query = "SELECT * FROM Vendor ORDER BY vendor_name;";

        try
        {
            conn = dS.getConnection();
            prep = conn.prepareStatement(query);
            rs = prep.executeQuery();
            while(rs.next())
            {
                vendorList.add(new VendorBuilder()
                        .setVendorID(rs.getInt("vendor_id"))
                        .setVendorStatusID(rs.getInt("vendor_status_id"))
                        .setVendorTypeID(rs.getInt("vendor_type_id"))
                        .setCompanyName(rs.getString("vendor_name"))
                        .setAddressLineOne(rs.getString("address_line_one"))
                        .setAddressLineTwo(rs.getString("address_line_two"))
                        .setNotes(rs.getString("notes"))
                        .createVendor());
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
        return vendorList;
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
    public void createNewVendorContact(VendorContact newContact, Date dateCreated)
    {
        PreparedStatement prep = null;
        Connection conn = null;

        String query = "INSERT INTO VendorContact(vendor_id, first_name, last_name, phone_number, email)" +
                "VALUES(?, ?, ?, ?, ?)";

        String queryTwo = "INSERT INTO VendorAssignedContact(vendor_contact_id, contact_type_id, contact_status_id, " +
                "date_assigned) VALUES(?,?,?,?);";

        try
        {
            conn = dS.getConnection();
            prep = conn.prepareStatement(query);
            prep.setInt(1, newContact.getVendorID());
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
            System.out.println("ERROR: Vendor Contact already exists.");
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
    }

    private int getContactID(Contact contact)
    {
        ResultSet rs;
        PreparedStatement prep = null;
        Connection conn = null;
        int contactID = 0;

        String query = "SELECT cc.vendor_contact_id FROM VendorContact AS cc WHERE cc.first_name = ? AND cc.last_name = ?;";
        try
        {
            conn = dS.getConnection();
            prep = conn.prepareStatement(query);
            prep.setString(1, contact.getFirstName());
            prep.setString(2, contact.getLastName());
            rs = prep.executeQuery();

            while(rs.next())
                contactID = rs.getInt("vendor_contact_id");
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

    public void updateVendorContact(VendorContact updatedContact)
    {
        Connection conn = null;
        PreparedStatement prep = null;

        String query = "UPDATE VendorContact SET vendor_id = ?, first_name = ?," +
                "last_name = ?, phone_number = ?, email = ? WHERE vendor_contact_id = ?;";

        String queryTwo = "UPDATE VendorAssignedContact SET contact_type_id = ?, contact_status_id = ? WHERE vendor_contact_id = ?;";

        try
        {
            conn = dS.getConnection();
            prep = conn.prepareStatement(query);

            prep.setInt(1, updatedContact.getVendorID());
            prep.setString(2, updatedContact.getFirstName());
            prep.setString(3, updatedContact.getLastName());
            prep.setString(4, updatedContact.getPhoneNumber());
            prep.setString(5, updatedContact.getEmail());
            prep.setInt(6, updatedContact.getVendorContactID());
            prep.executeUpdate();

            prep = conn.prepareStatement(queryTwo);
            prep.setInt(1, updatedContact.getType().getTypeID());
            prep.setInt(2, updatedContact.getStatus().getStatusID());
            prep.setInt(3, updatedContact.getVendorContactID());
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

    public Vendor getVendor(String clicked)
    {
        ResultSet rs = null;
        Connection conn = null;
        PreparedStatement prep = null;
        Vendor singleVendor = null;

        String query = "SELECT * FROM Vendor WHERE company_name = ?;";
        try
        {
            conn = dS.getConnection();
            prep = conn.prepareStatement(query);
            prep.setString(1, clicked);
            rs = prep.executeQuery();

            while (rs.next())
            {
                singleVendor = (new VendorBuilder()
                        .setVendorID(rs.getInt("vendor_id"))
                        .setVendorStatusID(rs.getInt("vendor_status_id"))
                        .setVendorTypeID(rs.getInt("vendor_type_id"))
                        .setCompanyName(rs.getString("vendor_name"))
                        .setAddressLineOne(rs.getString("address_line_one"))
                        .setAddressLineTwo(rs.getString("address_line_two"))
                        .setNotes(rs.getString("notes"))
                        .createVendor());
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
        return singleVendor;
    }
}
