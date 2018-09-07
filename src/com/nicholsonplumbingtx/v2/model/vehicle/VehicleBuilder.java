package com.nicholsonplumbingtx.v2.model.vehicle;

public class VehicleBuilder {
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

    public VehicleBuilder setVehicleID(int vehicleID)
    {
        this.vehicleID = vehicleID;
        return this;
    }

    public VehicleBuilder setVehicleStatusID(int vehicleStatusID)
    {
        this.vehicleStatusID = vehicleStatusID;
        return this;
    }

    public VehicleBuilder setVehicleTypeID(int vehicleTypeID)
    {
        this.vehicleTypeID = vehicleTypeID;
        return this;
    }

    public VehicleBuilder setModelYear(int modelYear)
    {
        this.modelYear = modelYear;
        return this;
    }

    public VehicleBuilder setVehicleMake(Make vehicleMake)
    {
        this.vehicleMake = vehicleMake;
        return this;
    }

    public VehicleBuilder setVehicleModel(Model vehicleModel)
    {
        this.vehicleModel = vehicleModel;
        return this;
    }

    public VehicleBuilder setLicensePlate(String licensePlate)
    {
        if(licensePlate.contains(" "))
            this.licensePlate = licensePlate;
        else
            this.licensePlate = licensePlate.substring(0, 3) + " " + licensePlate.substring(3, licensePlate.length());

        return this;
    }

    public VehicleBuilder setNotes(String notes)
    {
        this.notes = notes;
        return this;
    }

    public VehicleBuilder setVehicleStatus(String vehicleStatus)
    {
        this.vehicleStatus = vehicleStatus;
        return this;
    }

    public VehicleBuilder setVehicleType(String vehicleType)
    {
        this.vehicleType = vehicleType;
        return this;
    }

    public Vehicle createVehicle()
    {
        return new Vehicle(vehicleID, vehicleStatusID, vehicleTypeID, modelYear, vehicleMake, vehicleModel, licensePlate, notes, vehicleStatus, vehicleType);
    }
}