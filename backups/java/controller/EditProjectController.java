package com.nicholsonplumbingtx.v2.controller;

import com.nicholsonplumbingtx.v2.database_connector.ProjectDBConnection;
import com.nicholsonplumbingtx.v2.model.Project;
import com.nicholsonplumbingtx.v2.model.ProjectBuilder;
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
 */

public class EditProjectController extends Controller implements Initializable
{
    /* Object that handles all the connection information to the database */
    private ProjectDBConnection eC;

    private Project selectedProject;

    /* Start New Project Window */

    @FXML private Button updateButton;
    @FXML private Button cancelButton;
    @FXML private ComboBox<String> projectTypeComboBox;
    @FXML private ComboBox<String> projectStatusComboBox;
    @FXML private ComboBox<String> projectCustomerComboBox;
    @FXML private DatePicker startDatePicker;
    @FXML private DatePicker bidDatePicker;
    @FXML private TextField projectNameTF;
    @FXML private TextArea projectNotesArea;

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

    @Override
    public void loadCustomerInformation(String selectedName)
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

    /**
     * Created By: Ethan
     * Created On: 10/18/2016
     * Last Modified: 10/18/2016
     *
     */
    private void loadClientList(ArrayList<String> projectList)
    {
        Task taskOne = new Task<Void>()
        {
            public Void call()
            {
                projectCustomerComboBox.getItems().addAll(projectList);
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
    private void loadProjectStatusList(ArrayList<String> statusList)
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
    private void loadProjectTypeList(ArrayList<String> typeList)
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

    private void collectClientInformation()
    {
        selectedProject = new ProjectBuilder().createProject();

        selectedProject.setProjectStatusID(projectStatusComboBox.getSelectionModel().getSelectedIndex() + 1);
        selectedProject.setProjectTypeID(projectTypeComboBox.getSelectionModel().getSelectedIndex() + 1);
        selectedProject.setProjectName(projectNameTF.getText());
        selectedProject.setProjectStartDate(Date.valueOf(startDatePicker.getValue()));
        selectedProject.setProjectBidDate(Date.valueOf(bidDatePicker.getValue()));
        selectedProject.setClientID(eC.getSelectedID(projectCustomerComboBox.getSelectionModel().getSelectedItem()));
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
        collectClientInformation();
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

