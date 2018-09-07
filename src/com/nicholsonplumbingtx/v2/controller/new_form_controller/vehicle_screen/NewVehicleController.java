package com.nicholsonplumbingtx.v2.controller.new_form_controller.vehicle_screen;

import com.nicholsonplumbingtx.v2.controller.common_controller.Controller;
import com.nicholsonplumbingtx.v2.database_connector.VehicleDBConnection;
import com.nicholsonplumbingtx.v2.model.client.Client;
import com.nicholsonplumbingtx.v2.model.common.Status;
import com.nicholsonplumbingtx.v2.model.common.Type;
import com.nicholsonplumbingtx.v2.model.project.Project;
import com.nicholsonplumbingtx.v2.model.vehicle.Make;
import com.nicholsonplumbingtx.v2.model.vehicle.Model;
import com.nicholsonplumbingtx.v2.model.vehicle.Vehicle;
import com.nicholsonplumbingtx.v2.model.vehicle.VehicleBuilder;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by ethanorcutt on 11/8/16.
 */
public class NewVehicleController extends Controller implements Initializable
{
    /* Object that handles all the connection information to the database */
    private VehicleDBConnection eC;

    private Vehicle createdVehicle;

    @FXML private Button cancelButton;
    @FXML private Button createButton;
    @FXML private ComboBox<Make> makeComboBox;
    @FXML private ComboBox<Model> modelComboBox;
    @FXML private TextField yearField;
    @FXML private TextField plateField;
    @FXML private ComboBox<Status> statusComboBox;
    @FXML private ComboBox<Type> typeComboBox;
    @FXML private TextArea notesArea;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        establishConnection();
        loadEventHandlers();
        loadMakeList(eC.getVehicleMakeList());
        loadVehicleStatusList(eC.getVehicleStatusList());
        loadVehicleTypeList(eC.getVehicleTypeList());
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

    private void loadEventHandlers()
    {
        makeComboBox.addEventHandler(ActionEvent.ACTION, event -> loadModelList(eC.getVehicleModelList(makeComboBox.getSelectionModel().getSelectedItem().getMakeID())));
    }

    private void loadMakeList(List<Make> makes)
    {
        Task taskOne = new Task<Void>()
        {
            public Void call()
            {
                makeComboBox.getItems().addAll(makes);
                return null;
            }
        };
        new Thread(taskOne).start();
    }

    private void loadVehicleStatusList(List<Status> statusList)
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

    private void loadVehicleTypeList(List<Type> typeList)
    {
        Task taskOne = new Task<Void>()
        {
            public Void call()
            {
                typeComboBox.getItems().addAll(typeList);
                return null;
            }
        };
        new Thread(taskOne).start();
    }

    private void loadModelList(List<Model> models)
    {
        Task taskOne = new Task<Void>()
        {
            public Void call()
            {
                modelComboBox.getItems().clear();
                modelComboBox.getItems().addAll(models);
                return null;
            }
        };
        new Thread(taskOne).start();
    }

    @Override
    protected void loadInformation(int vehicleID)
    {

    }

    @Override
    public void loadInformation(String selectedName)
    {

    }

    @Override
    protected void loadInformation(Client selectedClient)
    {

    }

    @Override
    public void loadInformation(Client selectedClient, Project selectedProject)
    {

    }

    @Override
    protected void collectInformation()
    {
        createdVehicle = new VehicleBuilder()
                .setModelYear(Integer.parseInt(yearField.getText()))
                .setVehicleMake(makeComboBox.getSelectionModel().getSelectedItem())
                .setVehicleModel(modelComboBox.getSelectionModel().getSelectedItem())
                .setNotes(notesArea.getText())
                .setLicensePlate(plateField.getText())
                .setVehicleStatusID(statusComboBox.getSelectionModel().getSelectedItem().getStatusID())
                .setVehicleTypeID(typeComboBox.getSelectionModel().getSelectedItem().getTypeID())
                .createVehicle();
    }

    @FXML
    private void createButtonAction()
    {
        Stage stage = (Stage) createButton.getScene().getWindow();
        collectInformation();
        eC.createVehicle(createdVehicle);
        stage.close();
    }

    @FXML
    private void cancelButtonAction()
    {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }
}
