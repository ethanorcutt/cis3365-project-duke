package com.nicholsonplumbingtx.v2.controller.edit_form_controller.customer_screen;

import com.nicholsonplumbingtx.v2.controller.common_controller.Controller;
import com.nicholsonplumbingtx.v2.database_connector.ProjectDBConnection;
import com.nicholsonplumbingtx.v2.model.client.Client;
import com.nicholsonplumbingtx.v2.model.common.Status;
import com.nicholsonplumbingtx.v2.model.common.Type;
import com.nicholsonplumbingtx.v2.model.project.Project;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Date;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Created by Ethan Orcutt on 10/18/2016.
 * Project D.U.K.E.
 */

public class EditProjectController extends Controller implements Initializable
{
    /* Object that handles all the connection information to the database */
    private ProjectDBConnection eC;

    private Project selectedProject;

    /* Start New Project Window */

    @FXML private Button updateButton;
    @FXML private Button cancelButton;
    @FXML private ComboBox<Type> projectTypeComboBox;
    @FXML private ComboBox<Status> projectStatusComboBox;
    @FXML private ComboBox<Client> projectCustomerComboBox;
    @FXML private DatePicker startDatePicker;
    @FXML private DatePicker bidDatePicker;
    @FXML private TextField projectNameTF;
    @FXML private TextArea projectNotesArea;

    public EditProjectController()
    {

        updateButton = new Button();
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
                for(Client currentClient : clientList)
                    projectCustomerComboBox.getItems().add(currentClient);
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
    public void loadInformation(String selectedName)
    {
        selectedProject = eC.getProject(selectedName);

        LocalDate startDate = Instant.ofEpochMilli(selectedProject.getProjectStartDate().getTime())
                .atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate bidDate = Instant.ofEpochMilli(selectedProject.getProjectBidDate().getTime())
                .atZone(ZoneId.systemDefault()).toLocalDate();

        projectTypeComboBox.getSelectionModel().select(selectedProject.getProjectTypeID() - 1);
        projectStatusComboBox.getSelectionModel().select(selectedProject.getProjectStatusID() - 1);
        projectCustomerComboBox.getSelectionModel().select(selectedProject.getClientID() - 1);

        projectNameTF.setText(selectedProject.getProjectName());
        startDatePicker.setValue(startDate);
        bidDatePicker.setValue(bidDate);
        projectNotesArea.setText(selectedProject.getProjectNotes());
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
        selectedProject.setProjectStatusID(projectStatusComboBox.getSelectionModel().getSelectedItem().getStatusID());
        selectedProject.setProjectTypeID(projectTypeComboBox.getSelectionModel().getSelectedItem().getTypeID());
        selectedProject.setProjectName(projectNameTF.getText());
        selectedProject.setProjectStartDate(Date.valueOf(startDatePicker.getValue()));
        selectedProject.setProjectBidDate(Date.valueOf(bidDatePicker.getValue()));
        selectedProject.setClientID(projectCustomerComboBox.getSelectionModel().getSelectedItem().getClientID());
        selectedProject.setProjectNotes(projectNotesArea.getText());
    }

    /**
     * Created By: Ethan
     * Created On: 10/18/2016
     * Last Modified: 10/18/2016
     *
     */
    @FXML
    private void updateButtonAction()
    {
        Stage stage = (Stage) updateButton.getScene().getWindow();
        collectInformation();
        eC.updateProject(selectedProject);
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

