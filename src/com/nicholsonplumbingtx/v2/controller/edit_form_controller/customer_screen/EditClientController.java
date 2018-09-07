package com.nicholsonplumbingtx.v2.controller.edit_form_controller.customer_screen;

import com.nicholsonplumbingtx.v2.controller.common_controller.Controller;
import com.nicholsonplumbingtx.v2.database_connector.CustomerDBConnection;
import com.nicholsonplumbingtx.v2.model.client.Client;
import com.nicholsonplumbingtx.v2.model.common.Country;
import com.nicholsonplumbingtx.v2.model.common.Region;
import com.nicholsonplumbingtx.v2.model.common.Status;
import com.nicholsonplumbingtx.v2.model.project.Project;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Created by Ethan Orcutt on 10/18/2016.
 * Project D.U.K.E.
 */
public class EditClientController extends Controller implements Initializable
{
    /* Object that handles all the connection information to the database */
    private CustomerDBConnection eC;

    private Client selectedClient;

    @FXML private Button updateButton;
    @FXML private Button cancelButton;
    @FXML private TextField fNameField;
    @FXML private TextArea customerNotesArea;
    @FXML private TextField lNameField;
    @FXML private TextField companyNameField;
    @FXML private TextField phoneNumberField;
    @FXML private TextField addressOneField;
    @FXML private TextField addressTwoField;
    @FXML private TextField cityField;
    @FXML private TextField zipField;
    @FXML private ComboBox<Region> stateComboBox;
    @FXML private ComboBox<Country> countryComboBox;
    @FXML private ComboBox<Status> statusComboBox;

    public EditClientController()
    {

        updateButton = new Button();
        cancelButton = new Button();
        fNameField = new TextField();
        customerNotesArea = new TextArea();
        lNameField = new TextField();
        companyNameField = new TextField();
        phoneNumberField = new TextField();
        addressOneField = new TextField();
        addressTwoField = new TextField();
        cityField = new TextField();
        zipField = new TextField();
        stateComboBox = new ComboBox<>();
        countryComboBox = new ComboBox<>();
        statusComboBox = new ComboBox<>();
    }

    /**
     * Created By: Ethan
     * Created On: 10/18/2016
     * Last Modified: 10/18/2016
     *
     */
    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        establishConnection();
        loadEventHandlers();
        loadCountryList(eC.getCountryList());
        loadStatusList(eC.getClientStatusList());
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
            eC = new CustomerDBConnection();
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

    @Override
    protected void loadInformation(int vehicleID)
    {

    }

    @Override
    public void loadInformation(String customerName)
    {
        selectedClient = eC.getClient(customerName);

        loadRegionList(eC.getRegionList(selectedClient.getCountry()));
        countryComboBox.setValue(eC.getCountryName(selectedClient.getCountry()));
        stateComboBox.setValue(eC.getRegionName(selectedClient.getRegion()));
        statusComboBox.getSelectionModel().select(selectedClient.getStatusID() - 1);

        fNameField.setText(selectedClient.getFirstName());
        lNameField.setText(selectedClient.getLastName());
        companyNameField.setText(selectedClient.getCompanyName());
        phoneNumberField.setText(selectedClient.getFormattedPhoneNumber());
        addressOneField.setText(selectedClient.getAddressLineOne());
        addressTwoField.setText(selectedClient.getAddressLineTwo());
        cityField.setText(selectedClient.getCity());
        zipField.setText(selectedClient.getPostal());
        customerNotesArea.setText(selectedClient.getClientNotes());
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
        selectedClient.setStatusID(statusComboBox.getSelectionModel().getSelectedItem().getStatusID());
        selectedClient.setFirstName(fNameField.getText());
        selectedClient.setLastName(lNameField.getText());
        selectedClient.setCompanyName(companyNameField.getText());
        selectedClient.setPhoneNumber(phoneNumberField.getText());
        selectedClient.setAddressLineOne(addressOneField.getText());
        selectedClient.setAddressLineTwo(addressTwoField.getText());
        selectedClient.setCity(cityField.getText());
        selectedClient.setPostal(zipField.getText());
        selectedClient.setClientNotes(customerNotesArea.getText());
        selectedClient.setCountry(countryComboBox.getSelectionModel().getSelectedItem().getCountryID());
        selectedClient.setRegion(stateComboBox.getSelectionModel().getSelectedItem().getRegionID());
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
        eC.updateClient(selectedClient);
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
