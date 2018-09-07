package com.nicholsonplumbingtx.v2.controller.new_form_controller.customer_screen;

import com.nicholsonplumbingtx.v2.controller.common_controller.Controller;
import com.nicholsonplumbingtx.v2.database_connector.ProjectDBConnection;
import com.nicholsonplumbingtx.v2.model.client.Client;
import com.nicholsonplumbingtx.v2.model.common.Status;
import com.nicholsonplumbingtx.v2.model.common.Type;
import com.nicholsonplumbingtx.v2.model.project.Project;
import com.nicholsonplumbingtx.v2.model.project.ProjectBuilder;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Created by Ethan Orcutt on 10/18/2016.
 * Project D.U.K.E.
 */

public class NewProjectController extends Controller implements Initializable
{
    /* Object that handles all the connection information to the database */
    private ProjectDBConnection eC;

    private Project createdProject;

    /* Start New Project Window */

    @FXML private Button createButton;
    @FXML private Button cancelButton;
    @FXML private ComboBox<Type> projectTypeComboBox;
    @FXML private ComboBox<Status> projectStatusComboBox;
    @FXML private ComboBox<Client> projectCustomerComboBox;
    @FXML private DatePicker startDatePicker;
    @FXML private DatePicker bidDatePicker;
    @FXML private TextField projectNameTF;
    @FXML private TextArea projectNotesArea;

    public NewProjectController()
    {

        createButton = new Button();
        cancelButton = new Button();
        projectTypeComboBox = new ComboBox<>();
        projectStatusComboBox = new ComboBox<>();
        projectCustomerComboBox = new ComboBox<>();
        startDatePicker = new DatePicker();
        bidDatePicker = new DatePicker();
        projectNameTF = new TextField();
        projectNotesArea = new TextArea();
    }

    /* End New Project Window */

    /**
     * Created By: Ethan
     * Created On: 10/18/2016
     * Last Modified: 10/18/2016
     *
     */
    public void initialize(URL location, ResourceBundle resources)
    {
        establishConnection();
        loadClientList(eC.getClientList());
        loadProjectStatusList(eC.getProjectStatusList());
        loadProjectTypeList(eC.getProjectTypeList());
    }

    /**
     * Created By: Ethan
     * Created On: 10/18/2016
     * Last Modified: 10/18/2016
     *
     */
    private void establishConnection()
    {
        try
        {
            eC = new ProjectDBConnection();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Created By: Ethan
     * Created On: 10/18/2016
     * Last Modified: 10/18/2016
     *
     */
    private void loadClientList(ArrayList<Client> clientList)
    {
        Task taskOne = new Task<Void>()
        {
            public Void call()
            {
                projectCustomerComboBox.getItems().addAll(clientList);
                return null;
            }
        };
        new Thread(taskOne).start();
    }

    /**
     * Created By: Ethan
     * Created On: 10/18/2016
     * Last Modified: 10/18/2016
     *
     */
    private void loadProjectStatusList(ArrayList<Status> statusList)
    {
        Task taskOne = new Task<Void>()
        {
            public Void call()
            {
                projectStatusComboBox.getItems().addAll(statusList);
                return null;
            }
        };
        new Thread(taskOne).start();
    }

    /**
     * Created By: Ethan
     * Created On: 10/18/2016
     * Last Modified: 10/18/2016
     *
     */
    private void loadProjectTypeList(ArrayList<Type> typeList)
    {
        Task taskOne = new Task<Void>()
        {
            public Void call()
            {
                projectTypeComboBox.getItems().addAll(typeList);
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
    public void loadInformation(String selectedName) {}

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
        createdProject = new ProjectBuilder()
                .setProjectStatusID(projectStatusComboBox.getSelectionModel().getSelectedItem().getStatusID())
                .setProjectTypeID(projectTypeComboBox.getSelectionModel().getSelectedItem().getTypeID())
                .setProjectName(projectNameTF.getText())
                .setProjectStartDate(Date.valueOf(startDatePicker.getValue()))
                .setProjectBidDate(Date.valueOf(bidDatePicker.getValue()))
                .setClientID(projectCustomerComboBox.getSelectionModel().getSelectedItem().getClientID())
                .setProjectNotes(projectNotesArea.getText())
                .createProject();
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
        eC.createProject(createdProject);
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

