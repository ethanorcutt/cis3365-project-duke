package com.nicholsonplumbingtx.v2.database_connector;

import com.microsoft.sqlserver.jdbc.SQLServerException;
import com.nicholsonplumbingtx.v2.model.common.Status;
import com.nicholsonplumbingtx.v2.model.common.StatusBuilder;
import com.nicholsonplumbingtx.v2.model.common.Type;
import com.nicholsonplumbingtx.v2.model.common.TypeBuilder;
import com.nicholsonplumbingtx.v2.model.vehicle.Make;
import com.nicholsonplumbingtx.v2.model.vehicle.Model;
import com.nicholsonplumbingtx.v2.model.vehicle.Vehicle;
import com.nicholsonplumbingtx.v2.model.vehicle.VehicleBuilder;
import org.apache.commons.dbutils.DbUtils;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Ethan Orcutt on 11/6/2016.
 * Project D.U.K.E.
 */
public class VehicleDBConnection extends DBConnector
{
    public void createVehicle(Vehicle newVehicle)
    {
        PreparedStatement prep = null;
        Connection conn = null;

        String query = "INSERT INTO Vehicle(vehicle_status_id, vehicle_type_id, model_year," +
                "make_id, model_id, license_plate, description) VALUES(?, ?, ?, ?, ?, ?, ?)";

        try
        {
            conn = dS.getConnection();
            prep = conn.prepareStatement(query);

            prep.setInt(1, newVehicle.getVehicleStatusID());
            prep.setInt(2, newVehicle.getVehicleTypeID());
            prep.setInt(3, newVehicle.getModelYear());
            prep.setInt(4, newVehicle.getVehicleMake().getMakeID());
            prep.setInt(5, newVehicle.getVehicleModel().getModelID());
            prep.setString(6, newVehicle.getLicensePlate());
            prep.setString(7, newVehicle.getNotes());

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

    public Vehicle getVehicle(int selectedID)
    {
        ResultSet rs = null;
        Connection conn = null;
        PreparedStatement prep = null;
        Vehicle singleVehicle = null;

        String query = "SELECT v.vehicle_id, v.vehicle_status_id, v.vehicle_type_id, v.model_year, v.license_plate, " +
                "v.description, vS.title AS [Status], vT.title AS [Type], m.make_id, m.title AS [Make], mO.model_id, " +
                "mO.make_id, mO.title AS [Model] FROM Vehicle AS v " +
                "JOIN VehicleStatus AS vS ON v.vehicle_status_id = vs.vehicle_status_id " +
                "JOIN VehicleType AS vT ON v.vehicle_type_id = vt.vehicle_type_id " +
                "JOIN Make AS m ON v.make_id = m.make_id " +
                "JOIN Model AS mO ON v.model_id = mO.model_id WHERE vehicle_id = ?;";
        try
        {
            conn = dS.getConnection();
            prep = conn.prepareStatement(query);
            prep.setInt(1, selectedID);
            rs = prep.executeQuery();
            while (rs.next())
            {
                singleVehicle = new VehicleBuilder()
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
                        .createVehicle();
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
        return singleVehicle;
    }

    public void updateVehicle(Vehicle updatedVehicle)
    {
        Connection conn = null;
        PreparedStatement prep = null;

        String query = "UPDATE Vehicle SET vehicle_status_id = ?, vehicle_type_id = ?, model_year = ?, " +
                "make_id = ?, model_id = ?, license_plate = ?, description = ? WHERE vehicle_id = ?;";

        try
        {
            conn = dS.getConnection();
            prep = conn.prepareStatement(query);

            prep.setInt(1, updatedVehicle.getVehicleStatusID());
            prep.setInt(2, updatedVehicle.getVehicleTypeID());
            prep.setInt(3, updatedVehicle.getModelYear());
            prep.setInt(4, updatedVehicle.getVehicleMake().getMakeID());
            prep.setInt(5, updatedVehicle.getVehicleModel().getModelID());
            prep.setString(6, updatedVehicle.getLicensePlate());
            prep.setString(7, updatedVehicle.getNotes());
            prep.setInt(8, updatedVehicle.getVehicleID());

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

    public ArrayList<Vehicle> getVehicleList(String currentStatus)
    {
        ResultSet rs = null;
        Connection conn = null;
        PreparedStatement prep = null;
        ArrayList<Vehicle> vehicleList = new ArrayList<>();

        String query = "SELECT v.vehicle_id, v.vehicle_status_id, v.vehicle_type_id, v.model_year, v.license_plate, " +
                "v.description, vS.title AS [Status], vT.title AS [Type], m.make_id, m.title AS [Make], mO.model_id, " +
                "mO.make_id, mO.title AS [Model] FROM Vehicle AS v " +
                "JOIN VehicleStatus AS vS ON v.vehicle_status_id = vs.vehicle_status_id " +
                "JOIN VehicleType AS vT ON v.vehicle_type_id = vt.vehicle_type_id " +
                "JOIN Make AS m ON v.make_id = m.make_id " +
                "JOIN Model AS mO ON v.model_id = mO.model_id WHERE vS.title = ?;";
        try
        {
            conn = dS.getConnection();
            prep = conn.prepareStatement(query);
            prep.setString(1, currentStatus);
            rs = prep.executeQuery();
            while (rs.next())
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

    public ArrayList<Integer> getVehicleIDList()
    {
        ResultSet rs = null;
        Connection conn = null;
        PreparedStatement prep = null;
        ArrayList<Integer> vehicleList = new ArrayList<>();

        String query = "SELECT vehicle_id FROM Vehicle;";

        try
        {
            conn = dS.getConnection();
            prep = conn.prepareStatement(query);
            rs = prep.executeQuery();
            while (rs.next())
                vehicleList.add(rs.getInt("vehicle_id"));
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

    public ArrayList<Make> getVehicleMakeList()
    {
        ResultSet rs = null;
        Connection conn = null;
        PreparedStatement prep = null;
        ArrayList<Make> makeList = new ArrayList<>();

        String query = "SELECT * FROM Make";

        try
        {
            conn = dS.getConnection();
            prep = conn.prepareStatement(query);
            rs = prep.executeQuery();
            while (rs.next())
                makeList.add(new Make(rs.getInt("make_id"), rs.getString("title")));
        } catch (SQLException e)
        {
            e.printStackTrace();
        } finally
        {
            DbUtils.closeQuietly(rs);
            DbUtils.closeQuietly(prep);
            DbUtils.closeQuietly(conn);
        }
        return makeList;
    }

    public ArrayList<Model> getVehicleModelList(int selectedID)
    {
        ResultSet rs = null;
        Connection conn = null;
        PreparedStatement prep = null;
        ArrayList<Model> modelList = new ArrayList<>();

        String query = "SELECT * FROM Model WHERE make_id = ?";

        try
        {
            conn = dS.getConnection();
            prep = conn.prepareStatement(query);
            prep.setInt(1, selectedID);
            rs = prep.executeQuery();
            while (rs.next())
                modelList.add(new Model(rs.getInt("model_id"), rs.getInt("make_id"), rs.getString("title")));
        } catch (SQLException e)
        {
            e.printStackTrace();
        } finally
        {
            DbUtils.closeQuietly(rs);
            DbUtils.closeQuietly(prep);
            DbUtils.closeQuietly(conn);
        }
        return modelList;
    }

    public ArrayList<Status> getVehicleStatusList()
    {
        ResultSet rs = null;
        Connection conn = null;
        PreparedStatement prep = null;
        ArrayList<Status> statusList = new ArrayList<>();
        String idColumnName;

        String query = "SELECT * FROM VehicleStatus";

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

    public ArrayList<Type> getVehicleTypeList()
    {
        ResultSet rs = null;
        Connection conn = null;
        PreparedStatement prep = null;
        ArrayList<Type> typeList = new ArrayList<>();
        String idColumnName;

        String query = "SELECT * FROM VehicleType";

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