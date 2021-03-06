package com.nicholsonplumbingtx.v2.controller.new_form_controller.customer_screen;

import com.nicholsonplumbingtx.v2.controller.common_controller.Controller;
import com.nicholsonplumbingtx.v2.database_connector.VendorDBConnection;
import com.nicholsonplumbingtx.v2.model.client.Client;
import com.nicholsonplumbingtx.v2.model.common.Status;
import com.nicholsonplumbingtx.v2.model.common.Type;
import com.nicholsonplumbingtx.v2.model.project.Project;
import com.nicholsonplumbingtx.v2.model.vendor.Vendor;
import com.nicholsonplumbingtx.v2.model.vendor.VendorContact;
import com.nicholsonplumbingtx.v2.model.vendor.VendorContactBuilder;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

/**
 * Created by Ethan Orcutt on 10/18/2016.
 * Project D.U.K.E.
 */
public class NewVendorContactController extends Controller implements Initializable
{
    /* Object that handles all the connection information to the database */
    private VendorDBConnection eC;

    private VendorContact createdContact;

    @FXML private Button createButton;
    @FXML private Button cancelButton;
    @FXML private ComboBox<Vendor> vendorComboBox;
    @FXML private ComboBox<Status> statusComboBox;
    @FXML private ComboBox<Type> typeComboBox;
    @FXML private TextField fNameField;
    @FXML private TextField lNameField;
    @FXML private TextField phoneNumField;
    @FXML private TextField emailField;

    public NewVendorContactController()
    {

        createButton = new Button();
        cancelButton = new Button();
        vendorComboBox = new ComboBox<>();
        statusComboBox = new ComboBox<>();
        typeComboBox = new ComboBox<>();
        fNameField = new TextField();
        lNameField = new TextField();
        phoneNumField = new TextField();
        emailField = new TextField();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        establishConnection();
        loadVendorList(eC.getVendorList());
        loadStatusList(eC.getContactStatusList());
        loadTypeList(eC.getContactTypeList());
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
            eC = new VendorDBConnection();
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
    private void loadVendorList(ArrayList<Vendor> vendorList)
    {
        Task taskOne = new Task<Void>()
        {
            public Void call()
            {
                vendorComboBox.getItems().addAll(vendorList);
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

    private void loadTypeList(ArrayList<Type> typeList)
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
        createdContact = new VendorContactBuilder()
                .setFirstName(fNameField.getText())
                .setLastName(lNameField.getText())
                .setPhoneNumber(phoneNumField.getText())
                .setEmail(emailField.getText())
                .setVendorID(vendorComboBox.getSelectionModel().getSelectedItem().getVendorID())
                .setStatus(statusComboBox.getSelectionModel().getSelectedItem())
                .setType(typeComboBox.getSelectionModel().getSelectedItem())
                .createVendorContact();
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
        Date tmp = new Date();
        tmp.getTime();

        Stage stage = (Stage) createButton.getScene().getWindow();
        collectInformation();
        eC.createNewVendorContact(createdContact, convertJavaDateToSqlDate(tmp));
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
