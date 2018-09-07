package com.nicholsonplumbingtx.v2.controller.new_form_controller.employee_screen;

import com.nicholsonplumbingtx.v2.controller.common_controller.Controller;
import com.nicholsonplumbingtx.v2.database_connector.EmployeeDBConnection;
import com.nicholsonplumbingtx.v2.model.client.Client;
import com.nicholsonplumbingtx.v2.model.common.*;
import com.nicholsonplumbingtx.v2.model.employee.Employee;
import com.nicholsonplumbingtx.v2.model.employee.EmployeeBuilder;
import com.nicholsonplumbingtx.v2.model.project.Project;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Created by Ethan Orcutt on 10/31/2016.
 * Project D.U.K.E.
 */
public class NewEmployeeController extends Controller implements Initializable
{
    /* Object that handles all the connection information to the database */
    private EmployeeDBConnection eC;

    private Employee createdEmployee;

    @FXML private Button createButton;
    @FXML private Button cancelButton;
    @FXML private TextField fNameField;
    @FXML private TextField emailField;
    @FXML private TextField lNameField;
    @FXML private TextField addressTwoField;
    @FXML private TextField addressOneField;
    @FXML private TextField cityField;
    @FXML private TextField zipField;
    @FXML private ComboBox<Region> stateComboBox;
    @FXML private ComboBox<Country> countryComboBox;
    @FXML private TextField suffixField;
    @FXML private ComboBox<Status> statusComboBox;
    @FXML private ComboBox<Type> typeComboBox;
    @FXML private TextField salaryField;
    @FXML private TextField phoneNumberField;
    @FXML private TextField middleInitialField;
    @FXML private ComboBox<SalaryFrequency> salaryFrequencyComboBox;

    public NewEmployeeController()
    {

        createButton = new Button();
        cancelButton = new Button();
        fNameField = new TextField();
        emailField = new TextField();
        lNameField = new TextField();
        addressTwoField = new TextField();
        addressOneField = new TextField();
        cityField = new TextField();
        zipField = new TextField();
        stateComboBox = new ComboBox<>();
        countryComboBox = new ComboBox<>();
        suffixField = new TextField();
        statusComboBox = new ComboBox<>();
        typeComboBox = new ComboBox<>();
        salaryField = new TextField();
        phoneNumberField = new TextField();
        middleInitialField = new TextField();
        salaryFrequencyComboBox = new ComboBox<>();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        establishConnection();
        loadCountryList(eC.getCountryList());
        loadStatusList(eC.getEmployeeStatusList());
        loadTypeList(eC.getEmployeeTypeList());
        loadSalaryFrequencyList(eC.getSalaryFrequencyList());
        loadEventHandlers();
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

    private void loadEventHandlers()
    {
        countryComboBox.addEventHandler(ActionEvent.ACTION, event -> loadRegionList(eC.getRegionList(countryComboBox.getSelectionModel().getSelectedItem().getCountryID())));
    }

    private void loadStatusList(ArrayList<Status> statusList)
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

    private void loadTypeList(ArrayList<Type> typeList)
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

    private void loadSalaryFrequencyList(ArrayList<SalaryFrequency> salaryFrequencies)
    {
        Task taskOne = new Task<Void>()
        {
            public Void call()
            {
                salaryFrequencyComboBox.getItems().addAll(salaryFrequencies);
                return null;
            }
        };
        new Thread(taskOne).start();
    }

    private void loadCountryList(ArrayList<Country> countryList)
    {
        Task taskOne = new Task<Void>()
        {
            public Void call()
            {
                countryComboBox.getItems().addAll(countryList);
                return null;
            }
        };
        new Thread(taskOne).start();
    }

    private void loadRegionList(ArrayList<Region> regionList)
    {
        Task taskOne = new Task<Void>()
        {
            public Void call()
            {
                stateComboBox.getItems().clear();
                stateComboBox.getItems().addAll(regionList);
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
        createdEmployee = new EmployeeBuilder()
                .setEmployeeStatusID(statusComboBox.getSelectionModel().getSelectedItem().getStatusID())
                .setEmployeeTypeID(typeComboBox.getSelectionModel().getSelectedItem().getTypeID())
                .setFirstName(fNameField.getText())
                .setLastName(lNameField.getText())
                .setPhoneNumber(phoneNumberField.getText())
                .setAddressLineOne(addressOneField.getText())
                .setAddressLineTwo(addressTwoField.getText())
                .setCity(cityField.getText())
                .setZipCode(zipField.getText())
                .setEmail(emailField.getText())
                .setCountryID(countryComboBox.getSelectionModel().getSelectedItem().getCountryID())
                .setRegionID(stateComboBox.getSelectionModel().getSelectedItem().getRegionID())
                .setSuffix(suffixField.getText())
                .setSalary(Double.parseDouble(salaryField.getText()))
                .setMiddleInitial(middleInitialField.getText())
                .setSalaryFrequencyID(salaryFrequencyComboBox.getSelectionModel().getSelectedItem().getSalaryFrequencyID())
                .createEmployee();
    }

    /**
     * Created By: Ethan
     * Created On: 10/18/2016
     * Last Modified: 10/18/2016
     *
     */
    @FXML
    private void createButtonAction()
    {
        Stage stage = (Stage) createButton.getScene().getWindow();
        collectInformation();
        eC.createEmployee(createdEmployee);
        stage.close();
    }

    /**
     * Created By: Ethan
     * Created On: 10/18/2016
     * Last Modified: 10/18/2016
     *
     */
    @FXML
    private void cancelButtonAction()
    {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }
}