package com.nicholsonplumbingtx.v2.controller.main_form_controller.switchboard_controller;

import com.nicholsonplumbingtx.v2.controller.common_controller.ScreenLoader;
import com.nicholsonplumbingtx.v2.database_connector.CustomerDBConnection;
import com.nicholsonplumbingtx.v2.model.client.Client;
import com.nicholsonplumbingtx.v2.model.project.Project;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Created by Ethan Orcutt on 11/1/2016.
 * Project D.U.K.E.
 */
public class ChangeOrderSBOneController extends ScreenLoader implements Initializable
{
    private CustomerDBConnection eC;

    private Client selectedClient;
    private Project selectedProject;

    @FXML private ComboBox<Client> customerComboBox;
    @FXML private Button submitButton;
    @FXML private ComboBox<Project> projectComboBox;

    public ChangeOrderSBOneController()
    {
        customerComboBox = new ComboBox<>();
        submitButton = new Button();
        projectComboBox = new ComboBox<>();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        loadEventHandlers();
        establishConnection();
        loadClientList(eC.getClientList());
    }

    private void establishConnection()
    {
        try
        {
            eC = new CustomerDBConnection();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    private void loadEventHandlers()
    {
        customerComboBox.addEventHandler(ActionEvent.ACTION, event -> loadProjectList(eC.getClientProjectList(customerComboBox.getSelectionModel().getSelectedItem().getCompanyName())));
    }

    private void loadClientList(ArrayList<Client> clients)
    {
        Task taskOne = new Task<Void>()
        {
            public Void call()
            {
                customerComboBox.getItems().addAll(clients);
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
                projectComboBox.getItems().clear();
                projectComboBox.getItems().addAll(projects);
                return null;
            }
        };
        new Thread(taskOne).start();
    }

    private void collectInformation()
    {
        selectedClient = customerComboBox.getSelectionModel().getSelectedItem();
        selectedProject = projectComboBox.getSelectionModel().getSelectedItem();
    }

    @FXML
    void submitButtonAction()
    {
        Stage stage = (Stage) submitButton.getScene().getWindow();
        collectInformation();
        openNewStageWindow(loadScreen("new_forms/newChangeOrder.fxml", selectedClient, selectedProject));
        stage.close();
    }
}
