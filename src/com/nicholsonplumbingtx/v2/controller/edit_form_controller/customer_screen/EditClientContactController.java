package com.nicholsonplumbingtx.v2.controller.edit_form_controller.customer_screen;

import com.nicholsonplumbingtx.v2.controller.common_controller.ContactController;
import com.nicholsonplumbingtx.v2.database_connector.CustomerDBConnection;
import com.nicholsonplumbingtx.v2.model.client.Client;
import com.nicholsonplumbingtx.v2.model.client.ClientContact;
import com.nicholsonplumbingtx.v2.model.common.Status;
import com.nicholsonplumbingtx.v2.model.common.Type;
import com.nicholsonplumbingtx.v2.model.contact.Contact;
import com.nicholsonplumbingtx.v2.model.invoice.Invoice;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Created by Ethan Orcutt on 10/30/2016.
 * Project D.U.K.E.
 */
public class EditClientContactController extends ContactController implements Initializable
{
    /* Object that handles all the connection information to the database */
    private CustomerDBConnection eC;

    private ClientContact selectedContact;

    @FXML private Button updateButton;
    @FXML private Button cancelButton;
    @FXML private ComboBox<Client> customerComboBox;
    @FXML private ComboBox<Status> statusComboBox;
    @FXML private ComboBox<Type> typeComboBox;
    @FXML private TextField fNameField;
    @FXML private TextField lNameField;
    @FXML private TextField phoneNumField;
    @FXML private TextField emailField;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        establishConnection();
        loadClientList(eC.getClientList());
        loadStatusList(eC.getContactStatusList());
        loadTypeList(eC.getContactTypeList());
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

    private void loadClientList(ArrayList<Client> clientList)
    {
        Task taskOne = new Task<Void>()
        {
            public Void call()
            {
                customerComboBox.getItems().addAll(clientList);
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
    public void loadInformation(Contact sC)
    {
        this.selectedContact = (ClientContact) sC;

        customerComboBox.setValue(eC.getClient(selectedContact.getCompanyName()));
        statusComboBox.setValue(selectedContact.getStatus());
        typeComboBox.setValue(selectedContact.getType());

        fNameField.setText(selectedContact.getFirstName());
        lNameField.setText(selectedContact.getLastName());
        phoneNumField.setText(selectedContact.getFormattedPhoneNumber());
        emailField.setText(selectedContact.getEmail());
    }

    @Override
    public void loadInformation(Invoice selectedInvoice, Client sC)
    {

    }

    protected void collectInformation()
    {
        selectedContact.setFirstName(fNameField.getText());
        selectedContact.setLastName(lNameField.getText());
        selectedContact.setPhoneNumber(phoneNumField.getText());
        selectedContact.setEmail(emailField.getText());
        selectedContact.setClientID(customerComboBox.getSelectionModel().getSelectedItem().getClientID());
        selectedContact.setStatus(statusComboBox.getSelectionModel().getSelectedItem());
        selectedContact.setType(typeComboBox.getSelectionModel().getSelectedItem());
    }

    @FXML
    private void updateButtonAction()
    {
        Stage stage = (Stage) updateButton.getScene().getWindow();
        collectInformation();
        eC.updateClientContact(selectedContact);
        stage.close();
    }

    @FXML
    private void cancelButtonAction()
    {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }
}
