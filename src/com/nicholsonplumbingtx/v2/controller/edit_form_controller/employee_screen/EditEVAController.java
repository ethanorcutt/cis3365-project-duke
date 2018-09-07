package com.nicholsonplumbingtx.v2.controller.edit_form_controller.employee_screen;

import com.nicholsonplumbingtx.v2.database_connector.EmployeeDBConnection;
import com.nicholsonplumbingtx.v2.model.employee.Employee;
import com.nicholsonplumbingtx.v2.model.vehicle.Vehicle;
import javafx.concurrent.Task;
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
 * Created by Ethan Orcutt on 11/9/2016.
 * Project D.U.K.E.
 */
public class EditEVAController implements Initializable
{
    /* Object that handles all the connection information to the database */
    private EmployeeDBConnection eC;

    private Employee selectedEmployee;
    private Vehicle selectedVehicle;

    @FXML private ComboBox<Vehicle> vehicleComboBox;
    @FXML private Button submitButton;
    @FXML private TextArea notesBox;
    @FXML private TextField employeeTextField;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        establishConnection();
        loadVehicleList(eC.getVehicleList());
    }

    private void establishConnection()
    {
        try
        {
            eC = new EmployeeDBConnection();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    private void loadVehicleList(List<Vehicle> vehicleList)
    {
        Task taskOne = new Task<Void>()
        {
            public Void call()
            {
                vehicleComboBox.getItems().addAll(vehicleList);
                return null;
            }
        };
        new Thread(taskOne).start();
    }

    public void loadInformation(Employee sE, Vehicle sV)
    {
        this.selectedEmployee = sE;
        this.selectedVehicle = sV;

        employeeTextField.setText(selectedEmployee.toString());
        vehicleComboBox.setValue(selectedVehicle);
    }

    private void collectInformation()
    {
        selectedVehicle.setVehicleID(vehicleComboBox.getSelectionModel().getSelectedItem().getVehicleID());
    }

    @FXML
    void submitButtonAction()
    {
        Stage stage = (Stage) submitButton.getScene().getWindow();
        collectInformation();
        //eC.updateEPAssignment(selectedEmployee, selectedProject);
        stage.close();
    }
}
