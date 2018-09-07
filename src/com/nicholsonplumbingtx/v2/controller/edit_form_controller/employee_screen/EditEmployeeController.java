package com.nicholsonplumbingtx.v2.controller.edit_form_controller.employee_screen;

import com.nicholsonplumbingtx.v2.controller.common_controller.Controller;
import com.nicholsonplumbingtx.v2.database_connector.EmployeeDBConnection;
import com.nicholsonplumbingtx.v2.model.client.Client;
import com.nicholsonplumbingtx.v2.model.common.*;
import com.nicholsonplumbingtx.v2.model.employee.Employee;
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
 * Created by Ethan Orcutt on 11/2/2016.
 * Project D.U.K.E.
 */
public class EditEmployeeController extends Controller implements Initializable
{
    /* Object that handles all the connection information to the database */
    private EmployeeDBConnection eC;

    private Employee selectedEmployee;

    @FXML private Button updateButton;
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
        selectedEmployee = eC.getEmployee(selectedName);

        loadRegionList(eC.getRegionList(selectedEmployee.getCountryID()));
        countryComboBox.setValue(eC.getCountryName(selectedEmployee.getCountryID()));
        stateComboBox.setValue(eC.getRegionName(selectedEmployee.getRegionID()));
        statusComboBox.getSelectionModel().select(selectedEmployee.getEmployeeStatusID() - 1);
        typeComboBox.getSelectionModel().select(selectedEmployee.getEmployeeTypeID() - 1);
        salaryFrequencyComboBox.getSelectionModel().select(selectedEmployee.getSalaryFrequencyID() - 1);

        fNameField.setText(selectedEmployee.getFirstName());
        middleInitialField.setText(selectedEmployee.getMiddleInitial());
        lNameField.setText(selectedEmployee.getLastName());
        suffixField.setText(selectedEmployee.getSuffix());
        salaryField.setText(selectedEmployee.getSalary() + "");
        addressOneField.setText(selectedEmployee.getAddressLineOne());
        addressTwoField.setText(selectedEmployee.getAddressLineTwo());
        cityField.setText(selectedEmployee.getCity());
        zipField.setText(selectedEmployee.getZipCode());
        phoneNumberField.setText(selectedEmployee.getFormattedPhoneNumber());
        emailField.setText(selectedEmployee.getEmail());
        //TODO: Add Employee Notes back to the employee screen and add notes to the employee table in the database.
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
        selectedEmployee.setEmployeeStatusID(statusComboBox.getSelectionModel().getSelectedItem().getStatusID());
        selectedEmployee.setEmployeeTypeID(typeComboBox.getSelectionModel().getSelectedItem().getTypeID());
        selectedEmployee.setFirstName(fNameField.getText());
        selectedEmployee.setMiddleInitial(middleInitialField.getText());
        selectedEmployee.setLastName(lNameField.getText());
        selectedEmployee.setSuffix(suffixField.getText());
        selectedEmployee.setSalary(Double.parseDouble(salaryField.getText()));
        selectedEmployee.setSalaryFrequencyID(salaryFrequencyComboBox.getSelectionModel().getSelectedItem().getSalaryFrequencyID());
        selectedEmployee.setAddressLineOne(addressOneField.getText());
        selectedEmployee.setAddressLineTwo(addressTwoField.getText());
        selectedEmployee.setCity(cityField.getText());
        selectedEmployee.setRegionID(stateComboBox.getSelectionModel().getSelectedItem().getRegionID());
        selectedEmployee.setCountryID(countryComboBox.getSelectionModel().getSelectedItem().getCountryID());
        selectedEmployee.setZipCode(zipField.getText());
        selectedEmployee.setPhoneNumber(phoneNumberField.getText());
        selectedEmployee.setEmail(emailField.getText());
    }

    @FXML
    void updateButtonAction()
    {
        Stage stage = (Stage) updateButton.getScene().getWindow();
        collectInformation();
        eC.updateEmployee(selectedEmployee);
        stage.close();
    }

    @FXML
    void cancelButtonAction()
    {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }
}
