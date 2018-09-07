package com.nicholsonplumbingtx.v2.controller.edit_form_controller.employee_screen;

import com.nicholsonplumbingtx.v2.database_connector.EmployeeDBConnection;
import com.nicholsonplumbingtx.v2.model.employee.Employee;
import com.nicholsonplumbingtx.v2.model.project.Project;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by Ethan Orcutt on 11/9/2016.
 * Project D.U.K.E.
 */
public class EditEPAController implements Initializable
{
    /* Object that handles all the connection information to the database */
    private EmployeeDBConnection eC;

    private Employee selectedEmployee;
    private Project selectedProject;

    @FXML private TextField employeeTextField;
    @FXML private ComboBox<Project> projectComboBox;
    @FXML private Button submitButton;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        establishConnection();
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

    private void loadProjectList(List<Project> projects)
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

    public void loadInformation(Employee sE, Project sP)
    {
        this.selectedEmployee = sE;
        this.selectedProject = sP;

        employeeTextField.setText(selectedEmployee.toString());
        projectComboBox.setValue(selectedProject);
    }

    private void collectInformation()
    {
        selectedProject.setProjectID(projectComboBox.getSelectionModel().getSelectedItem().getProjectID());
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
