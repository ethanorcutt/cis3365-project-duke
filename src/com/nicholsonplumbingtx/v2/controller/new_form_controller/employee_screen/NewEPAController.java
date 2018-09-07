package com.nicholsonplumbingtx.v2.controller.new_form_controller.employee_screen;

import com.nicholsonplumbingtx.v2.controller.common_controller.Controller;
import com.nicholsonplumbingtx.v2.database_connector.EmployeeDBConnection;
import com.nicholsonplumbingtx.v2.model.client.Client;
import com.nicholsonplumbingtx.v2.model.employee.Employee;
import com.nicholsonplumbingtx.v2.model.project.Project;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

/**
 * Created by Ethan Orcutt on 11/2/2016.
 * Project D.U.K.E.
 */
public class NewEPAController extends Controller implements Initializable
{
    /* Object that handles all the connection information to the database */
    private EmployeeDBConnection eC;

    private Employee selectedEmployee;
    private Project selectedProject;
    private Date dateCreated = new Date();

    @FXML private ComboBox<Employee> employeeComboBox;
    @FXML private ComboBox<Project> projectComboBox;
    @FXML private Button submitButton;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        establishConnection();
        loadEmployeeList(eC.getEmployeeList());
        loadProjectList(eC.getProjectList());
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

    private void loadEmployeeList(ArrayList<Employee> employees)
    {
        Task taskOne = new Task<Void>()
        {
            public Void call()
            {
                employeeComboBox.getItems().addAll(employees);
                return null;
            }
        };
        new Thread(taskOne).start();
    }

    private void loadProjectList(ArrayList<Project> projects)
    {
        Task taskOne = new Task<Void>()
        {
            public Void call()
            {
                projectComboBox.getItems().addAll(projects);
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
        selectedEmployee = employeeComboBox.getSelectionModel().getSelectedItem();
        selectedProject = projectComboBox.getSelectionModel().getSelectedItem();

    }

    @FXML
    void submitButtonAction()
    {
        Stage stage = (Stage) submitButton.getScene().getWindow();
        collectInformation();
        eC.assignProject(selectedEmployee, selectedProject, convertJavaDateToSqlDate(dateCreated));
        stage.close();
    }
}
