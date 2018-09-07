package com.nicholsonplumbingtx.v2.database_connector;

import com.microsoft.sqlserver.jdbc.SQLServerException;
import com.nicholsonplumbingtx.v2.model.contact.Contact;
import com.nicholsonplumbingtx.v2.model.supplier.Supplier;
import com.nicholsonplumbingtx.v2.model.supplier.SupplierBuilder;
import com.nicholsonplumbingtx.v2.model.supplier.SupplierContact;
import org.apache.commons.dbutils.DbUtils;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;

/**
 * Created by Ethan Orcutt on 10/18/2016.
 * Project D.U.K.E.
 */
public class SupplierDBConnection extends DBConnector
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
     * @param newSupplier
     */
    public void createSupplier(Supplier newSupplier)
    {
        PreparedStatement prep = null;
        Connection conn = null;

        String query = "INSERT INTO Supplier(supplier_status_id, company_name, address_line_one, address_line_two," +
                "city, region_id, country_id, postal_zip_code, phone_number, email)" +
                "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try
        {
            conn = dS.getConnection();
            prep = conn.prepareStatement(query);

            prep.setInt(1, newSupplier.getSupplierStatusID());
            prep.setString(2, newSupplier.getCompanyName());
            prep.setString(3, newSupplier.getAddressLineOne());
            prep.setString(4, newSupplier.getAddressLineTwo());
            prep.setString(5, newSupplier.getCity());
            prep.setInt(6, newSupplier.getRegionID());
            prep.setInt(7, newSupplier.getCountryID());
            prep.setString(8, newSupplier.getPostalCode());
            prep.setString(9, newSupplier.getPhoneNumber());
            prep.setString(10, newSupplier.getEmail());

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

    /**
     * Created By: Ethan
     * Created On: 10/2/2016
     * Last Modified: 10/2/2016
     * <p>
     * DESCRIPTION:
     *
     * @param newContact
     */
    public void createNewSupplierContact(SupplierContact newContact, Date dateCreated)
    {
        PreparedStatement prep = null;
        Connection conn = null;

        String query = "INSERT INTO SupplierContact(supplier_id, first_name, last_name, phone_number, email)" +
                "VALUES(?, ?, ?, ?, ?)";

        String queryTwo = "INSERT INTO SupplierAssignedContact(supplier_contact_id, contact_type_id, contact_status_id, " +
                "date_assigned) VALUES(?,?,?,?);";

        try
        {
            conn = dS.getConnection();
            prep = conn.prepareStatement(query);
            prep.setInt(1, newContact.getSupplierID());
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
            System.out.println("ERROR: Supplier Contact already exists.");
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

    public void updateSupplierContact(SupplierContact updatedContact)
    {
        Connection conn = null;
        PreparedStatement prep = null;

        String query = "UPDATE SupplierContact SET supplier_id = ?, first_name = ?," +
                "last_name = ?, phone_number = ?, email = ? WHERE supplier_contact_id = ?;";

        String queryTwo = "UPDATE SupplierAssignedContact SET contact_type_id = ?, contact_status_id = ? WHERE supplier_contact_id = ?;";

        try
        {
            conn = dS.getConnection();
            prep = conn.prepareStatement(query);

            prep.setInt(1, updatedContact.getSupplierID());
            prep.setString(2, updatedContact.getFirstName());
            prep.setString(3, updatedContact.getLastName());
            prep.setString(4, updatedContact.getPhoneNumber());
            prep.setString(5, updatedContact.getEmail());
            prep.setInt(6, updatedContact.getSupplierContactID());
            prep.executeUpdate();

            prep = conn.prepareStatement(queryTwo);
            prep.setInt(1, updatedContact.getType().getTypeID());
            prep.setInt(2, updatedContact.getStatus().getStatusID());
            prep.setInt(3, updatedContact.getSupplierContactID());
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

        String query = "SELECT cc.supplier_contact_id FROM SupplierContact AS cc WHERE cc.first_name = ? AND cc.last_name = ?;";
        try
        {
            conn = dS.getConnection();
            prep = conn.prepareStatement(query);
            prep.setString(1, contact.getFirstName());
            prep.setString(2, contact.getLastName());
            rs = prep.executeQuery();

            while(rs.next())
                contactID = rs.getInt("supplier_contact_id");
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

    public Supplier getSupplier(String supplierName)
    {
        ResultSet rs = null;
        Connection conn = null;
        PreparedStatement prep = null;
        Supplier supplier =  null;

        String query = "SELECT * FROM Supplier WHERE company_name = ?;";

        try
        {
            conn = dS.getConnection();
            prep = conn.prepareStatement(query);
            prep.setString(1, supplierName);
            rs = prep.executeQuery();

            while (rs.next())
            {
                supplier = (new SupplierBuilder()
                        .setSupplierID(rs.getInt("supplier_id"))
                        .setSupplierStatusID(rs.getInt("supplier_status_id"))
                        .setCompanyName(rs.getString("company_name"))
                        .setAddressLineOne(rs.getString("address_line_one"))
                        .setAddressLineTwo(rs.getString("address_line_two"))
                        .setCity(rs.getString("city"))
                        .setRegionID(rs.getInt("region_id"))
                        .setCountryID(rs.getInt("country_id"))
                        .setPostalCode(rs.getString("postal_zip_code"))
                        .setPhoneNumber(rs.getString("phone_number"))
                        .setEmail(rs.getString("email"))
                        .createSupplier());
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
        return supplier;
    }

    public ArrayList<Supplier> getSupplierList()
    {
        ResultSet rs = null;
        Connection conn = null;
        PreparedStatement prep = null;
        ArrayList<Supplier> supplierList = new ArrayList<>();

        String query = "SELECT * FROM Supplier ORDER BY company_name;";

        try
        {
            conn = dS.getConnection();
            prep = conn.prepareStatement(query);
            rs = prep.executeQuery();
            while(rs.next())
            {
                supplierList.add(new SupplierBuilder()
                        .setSupplierID(rs.getInt("supplier_id"))
                        .setSupplierStatusID(rs.getInt("supplier_status_id"))
                        .setCompanyName(rs.getString("company_name"))
                        .setAddressLineOne(rs.getString("address_line_one"))
                        .setAddressLineTwo(rs.getString("address_line_two"))
                        .setCity(rs.getString("city"))
                        .setRegionID(rs.getInt("region_id"))
                        .setCountryID(rs.getInt("country_id"))
                        .setPostalCode(rs.getString("postal_zip_code"))
                        .setPhoneNumber(rs.getString("phone_number"))
                        .setEmail(rs.getString("email"))
                        .createSupplier());
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
        return supplierList;
    }
}