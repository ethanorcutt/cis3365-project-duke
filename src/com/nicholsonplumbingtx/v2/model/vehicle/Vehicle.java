package com.nicholsonplumbingtx.v2.model.vehicle;

/**
 * Created by Ethan Orcutt on 10/2/2016.
 * Project D.U.K.E.
 */
public class Vehicle
{
    private int vehicleID;
    private int vehicleStatusID;
    private int vehicleTypeID;
    private int modelYear;
    private Make vehicleMake;
    private Model vehicleModel;
    private String licensePlate;
    private String notes;
    private String vehicleStatus;
    private String vehicleType;

    public Vehicle(int vehicleID, int vehicleStatusID, int vehicleTypeID, int modelYear, Make vehicleMake, Model vehicleModel, String licensePlate, String notes, String vehicleStatus, String vehicleType)
    {
        this.vehicleID = vehicleID;
        this.vehicleStatusID = vehicleStatusID;
        this.vehicleTypeID = vehicleTypeID;
        this.modelYear = modelYear;
        this.vehicleMake = vehicleMake;
        this.vehicleModel = vehicleModel;
        this.licensePlate = licensePlate;
        this.notes = notes;
        this.vehicleStatus = vehicleStatus;
        this.vehicleType = vehicleType;
    }

    public int getVehicleID()
    {
        return vehicleID;
    }

    public void setVehicleID(int vehicleID)
    {
        this.vehicleID = vehicleID;
    }

    public int getVehicleStatusID()
    {
        return vehicleStatusID;
    }

    public void setVehicleStatusID(int vehicleStatusID)
    {
        this.vehicleStatusID = vehicleStatusID;
    }

    public int getVehicleTypeID()
    {
        return vehicleTypeID;
    }

    public void setVehicleTypeID(int vehicleTypeID)
    {
        this.vehicleTypeID = vehicleTypeID;
    }

    public int getModelYear()
    {
        return modelYear;
    }

    public void setModelYear(int modelYear)
    {
        this.modelYear = modelYear;
    }

    public Make getVehicleMake()
    {
        return vehicleMake;
    }

    public void setVehicleMake(Make vehicleMake)
    {
        this.vehicleMake = vehicleMake;
    }

    public Model getVehicleModel()
    {
        return vehicleModel;
    }

    public void setVehicleModel(Model vehicleModel)
    {
        this.vehicleModel = vehicleModel;
    }

    public String getLicensePlate()
    {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate)
    {
        if(licensePlate.contains(" "))
            this.licensePlate = licensePlate;
        else
            this.licensePlate = licensePlate.substring(0, 3) + " " + licensePlate.substring(3, licensePlate.length());
    }

    public String getNotes()
    {
        return notes;
    }

    public void setNotes(String notes)
    {
        this.notes = notes;
    }

    public String getVehicleStatus()
    {
        return vehicleStatus;
    }

    public void setVehicleStatus(String vehicleStatus)
    {
        this.vehicleStatus = vehicleStatus;
    }

    public String getVehicleType()
    {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType)
    {
        this.vehicleType = vehicleType;
    }

    @Override
    public String toString()
    {
        return getVehicleMake() + " " + getVehicleModel() + " " + getLicensePlate();
    }
}