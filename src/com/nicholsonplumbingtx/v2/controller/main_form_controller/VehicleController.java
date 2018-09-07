package com.nicholsonplumbingtx.v2.controller.main_form_controller;

import com.nicholsonplumbingtx.v2.controller.common_controller.ScreenLoader;
import com.nicholsonplumbingtx.v2.database_connector.VehicleDBConnection;
import com.nicholsonplumbingtx.v2.model.common.Status;
import com.nicholsonplumbingtx.v2.model.vehicle.Make;
import com.nicholsonplumbingtx.v2.model.vehicle.Model;
import com.nicholsonplumbingtx.v2.model.vehicle.Vehicle;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * Created by Ethan Orcutt on 11/6/2016.
 * Project D.U.K.E.
 */
public class VehicleController extends ScreenLoader implements Initializable
{
    /* Object that handles all the connection information to the database */
    private VehicleDBConnection eC;

    private Vehicle selectedVehicle;

    @FXML private ComboBox<Integer> fleetNumberComboBox;
    @FXML private ComboBox<Integer> yearComboBox;
    @FXML private ComboBox<Make> makeComboBox;
    @FXML private ComboBox<Model> modelComboBox;
    @FXML private ComboBox<Status> statusComboBox;

    @FXML private TableView<Vehicle> vehicleLogTable;
    @FXML private TableColumn<Vehicle, String> fleetIDColumn;
    @FXML private TableColumn<Vehicle, String> yearColumn;
    @FXML private TableColumn<Vehicle, String> makeColumn;
    @FXML private TableColumn<Vehicle, String> modelColumn;
    @FXML private TableColumn<Vehicle, String> plateColumn;
    @FXML private TableColumn<Vehicle, String> statusColumn;
    @FXML private TableColumn<Vehicle, String> typeColumn;

    @FXML private TextField licensePlateField;
    @FXML private TextArea descriptionBox;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        establishConnection();
        loadMainItems();
        loadEventHandlers();
    }

    private void establishConnection()
    {
        try
        {
            eC = new VehicleDBConnection();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    private void loadMainItems()
    {
        loadVehicleTable(eC.getVehicleList("Active"));
        loadFleetNumberList(eC.getVehicleIDList());
        loadYearList();
        loadMakeList(eC.getVehicleMakeList());
        loadStatusList(eC.getVehicleStatusList());
    }

    private void loadEventHandlers()
    {
        vehicleLogTable.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> loadSelectedVehicle(vehicleLogTable.getSelectionModel().getSelectedItem()));
        makeComboBox.addEventHandler(ActionEvent.ACTION, event -> loadModelList(eC.getVehicleModelList(makeComboBox.getSelectionModel().getSelectedItem().getMakeID())));
        fleetNumberComboBox.addEventHandler(ActionEvent.ACTION, event -> loadSelectedVehicle(eC.getVehicle(fleetNumberComboBox.getSelectionModel().getSelectedItem().intValue())));
        statusComboBox.addEventHandler(ActionEvent.ACTION, event -> loadVehicleTable(eC.getVehicleList(statusComboBox.getSelectionModel().getSelectedItem().getStatusTitle())));
    }

    private void loadFleetNumberList(List<Integer> vehicles)
    {
        Task taskOne = new Task<Void>()
        {
            public Void call()
            {
                fleetNumberComboBox.getItems().addAll(vehicles);
                return null;
            }
        };
        new Thread(taskOne).start();
    }

    private void loadYearList()
    {
        Task taskOne = new Task<Void>()
        {
            public Void call()
            {
                //Stores the value of the current year.
                //This is used to run a for loop up to n+1 the current year
                //that a car could have been made. +1 because the car's
                //model could be of the next year.
                int year = Calendar.getInstance().get(Calendar.YEAR);
                for(int x = 1990; x <= year + 1; x++)
                    yearComboBox.getItems().add(x);
                return null;
            }
        };
        new Thread(taskOne).start();
    }

    private void loadMakeList(List<Make> makeList)
    {
        Task taskOne = new Task<Void>()
        {
            public Void call()
            {
                makeComboBox.getItems().addAll(makeList);
                return null;
            }
        };
        new Thread(taskOne).start();
    }

    private void loadModelList(List<Model> modelList)
    {
        Platform.runLater(() ->
        {
            modelComboBox.getItems().clear();
            modelComboBox.getItems().addAll(modelList);
        });
    }

    private void loadVehicleTable(List<Vehicle> vehicles)
    {
        Task taskOne = new Task<Void>()
        {
            public Void call()
            {
                fleetIDColumn.setCellValueFactory(new PropertyValueFactory<>("vehicleID"));
                yearColumn.setCellValueFactory(new PropertyValueFactory<>("modelYear"));
                makeColumn.setCellValueFactory(new PropertyValueFactory<>("vehicleMake"));
                modelColumn.setCellValueFactory(new PropertyValueFactory<>("vehicleModel"));
                plateColumn.setCellValueFactory(new PropertyValueFactory<>("licensePlate"));
                statusColumn.setCellValueFactory(new PropertyValueFactory<>("vehicleStatus"));
                typeColumn.setCellValueFactory(new PropertyValueFactory<>("vehicleType"));
                vehicleLogTable.setItems(getVehicleTableData(vehicles));
                return null;
            }
        };
        new Thread(taskOne).start();
    }

    private void loadStatusList(List<Status> statusList)
    {
        Task taskOne = new Task<Void>()
        {
            public Void call()
            {
                statusComboBox.getItems().addAll(statusList);
                return null;
            }
        };
        new Thread(taskOne).start();
    }

    private void loadSelectedVehicle(Vehicle selectedVehicle)
    {
        this.selectedVehicle = selectedVehicle;
        fleetNumberComboBox.setValue(selectedVehicle.getVehicleID());
        yearComboBox.setValue(selectedVehicle.getModelYear());
        makeComboBox.setValue(selectedVehicle.getVehicleMake());
        modelComboBox.setValue(selectedVehicle.getVehicleModel());
        licensePlateField.setText(selectedVehicle.getLicensePlate());
        descriptionBox.setText(selectedVehicle.getNotes());
    }

    private ObservableList<Vehicle> getVehicleTableData(List<Vehicle> contactList)
    {
        final ObservableList<Vehicle> data = FXCollections.observableArrayList();
        data.addAll(contactList);
        return data;
    }

    @FXML
    void createButtonAction()
    {
        openNewStageWindow(loadScreen("new_forms/newVehicle.fxml"));
    }

    @FXML
    void editButtonAction()
    {
        try
        {
            //TODO: Write a method using generics in order to pass any type of object.
            //TODO: Re-write the ScreenLoader to use generics. Possibly Controller too.
            openNewStageWindow(loadScreen("edit_forms/editVehicle.fxml", selectedVehicle.getVehicleID()));
        }
        catch(Exception e)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("An error has occurred!");
            alert.setContentText("No vehicle selected.");
            alert.showAndWait();
        }
    }

    @FXML
    void deleteActionButton()
    {
        try
        {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Dialog");
            alert.setHeaderText("Vehicle: " + selectedVehicle.getVehicleMake() + " " + selectedVehicle.getVehicleModel() + "\nPlate: " + selectedVehicle.getLicensePlate());
            alert.setContentText("Are you sure you want to delete this vehicle?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK)
            {
                selectedVehicle.setVehicleStatusID(2);
                eC.updateVehicle(selectedVehicle);
            }
        }
        catch(Exception e)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("An error has occurred!");
            alert.setContentText("No vehicle selected.");
            alert.showAndWait();
        }
    }
}
