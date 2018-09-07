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
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Created by Ethan Orcutt on 10/18/2016.
 */

public class NewProjectController implements Initializable
{
    /* Object that handles all the connection information to the database */
    private ProjectDBConnection eC;

    private Project createdProject;

    /* Start New Project Window */

    @FXML private Button createButton;
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
        createdProject = new ProjectBuilder().createProject();

        createdProject.setProjectStatusID(projectStatusComboBox.getSelectionModel().getSelectedIndex() + 1);
        createdProject.setProjectTypeID(projectTypeComboBox.getSelectionModel().getSelectedIndex() + 1);
        createdProject.setProjectName(projectNameTF.getText());
        createdProject.setProjectStartDate(Date.valueOf(startDatePicker.getValue()));
        createdProject.setProjectBidDate(Date.valueOf(bidDatePicker.getValue()));
        createdProject.setClientID(eC.getSelectedID(projectCustomerComboBox.getSelectionModel().getSelectedItem()));
        createdProject.setProjectNotes(projectNotesArea.getText());
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
        collectClientInformation();
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

