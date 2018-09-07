package com.nicholsonplumbingtx.v2.controller;

import com.nicholsonplumbingtx.v2.database_connector.CustomerDBConnection;
import com.nicholsonplumbingtx.v2.model.Client;
import com.nicholsonplumbingtx.v2.model.ClientBuilder;
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
 */
public class NewClientController implements Initializable
{
    /* Object that handles all the connection information to the database */
    private CustomerDBConnection eC;

    private Client createdClient;

    @FXML private Button createButton;
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
    @FXML private ComboBox<String> stateComboBox;
    @FXML private ComboBox<String> countryComboBox;
    @FXML private ComboBox<String> statusComboBox;
    @FXML private ComboBox<String> typeComboBox;

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
        loadTypeList(eC.getClientTypeList());
    }

    private void loadEventHandlers()
    {
        countryComboBox.addEventHandler(ActionEvent.ACTION, event ->
        {
            String selectedCountryName = countryComboBox.getSelectionModel().getSelectedItem();
            int selectedCountryID = eC.getSelectedCountryID(selectedCountryName);
            loadRegionList(eC.getRegionList(selectedCountryID));
        });
    }

    private void loadCountryList(ArrayList<String> countryList)
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

    private void loadRegionList(ArrayList<String> regionList)
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

    private void loadStatusList(ArrayList<String> statusList)
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

    private void loadTypeList(ArrayList<String> typeList)
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

    private void collectClientInformation()
    {
        createdClient = new ClientBuilder().createClient();

        createdClient.setStatusID(statusComboBox.getSelectionModel().getSelectedIndex() + 1);
        createdClient.setTypeID(typeComboBox.getSelectionModel().getSelectedIndex() + 1);
        createdClient.setFirstName(fNameField.getText());
        createdClient.setLastName(lNameField.getText());
        createdClient.setCompanyName(companyNameField.getText());
        createdClient.setPhoneNumber(phoneNumberField.getText());
        createdClient.setAddressLineOne(addressOneField.getText());
        createdClient.setAddressLineTwo(addressTwoField.getText());
        createdClient.setCity(cityField.getText());
        createdClient.setPostal(zipField.getText());
        createdClient.setClientNotes(customerNotesArea.getText());
        createdClient.setCountry(countryComboBox.getSelectionModel().getSelectedIndex() + 1);
        createdClient.setRegion(stateComboBox.getSelectionModel().getSelectedIndex() + 1);
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
        eC.createClient(createdClient);
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
