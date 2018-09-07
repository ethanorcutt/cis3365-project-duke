package com.nicholsonplumbingtx.v2.controller.main_form_controller.switchboard_controller;

import com.nicholsonplumbingtx.v2.controller.common_controller.Controller;
import com.nicholsonplumbingtx.v2.database_connector.CustomerDBConnection;
import com.nicholsonplumbingtx.v2.model.client.Client;
import com.nicholsonplumbingtx.v2.model.project.Project;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
public class ChangeOrderSBTwoController extends Controller implements Initializable
{
    private CustomerDBConnection eC;

    private Client selectedClient;
    private Project selectedProject;

    @FXML private Button submitButton;
    @FXML private ComboBox<Project> projectComboBox;

    public ChangeOrderSBTwoController()
    {
        submitButton = new Button();
        projectComboBox = new ComboBox<>();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        establishConnection();
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

    @Override
    protected void loadInformation(int vehicleID) {

    }

    @Override
    public void loadInformation(String selectedName) {}

    protected void loadInformation(Client selectedClient)
    {
        this.selectedClient = selectedClient;
        loadProjectList(eC.getClientProjectList(selectedClient.getCompanyName()));
    }

    @Override
    public void loadInformation(Client selectedClient, Project selectedProject)
    {

    }

    protected void collectInformation()
    {
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

    protected void openNewStageWindow(Parent value)
    {
        Stage myDialog = new Stage();

        Group root = new Group(value);
        Scene scene = new Scene(root);

        myDialog.setScene(scene);
        myDialog.sizeToScene();
        myDialog.show();
    }

    protected Parent loadScreen(String resource, Client client, Project project)
    {
        try
        {
            FXMLLoader myLoader = new FXMLLoader(getClass().getClassLoader().getResource("resources/" + resource));
            Parent loadScreen = myLoader.load();
            Controller masterController = myLoader.getController();
            masterController.loadInformation(client, project);
            return loadScreen;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }

        return null;
    }
}
