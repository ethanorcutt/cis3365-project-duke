package com.nicholsonplumbingtx.v2.controller.edit_form_controller.customer_screen;

import com.nicholsonplumbingtx.v2.controller.common_controller.ContactController;
import com.nicholsonplumbingtx.v2.database_connector.SupplierDBConnection;
import com.nicholsonplumbingtx.v2.model.client.Client;
import com.nicholsonplumbingtx.v2.model.common.Status;
import com.nicholsonplumbingtx.v2.model.common.Type;
import com.nicholsonplumbingtx.v2.model.contact.Contact;
import com.nicholsonplumbingtx.v2.model.invoice.Invoice;
import com.nicholsonplumbingtx.v2.model.supplier.Supplier;
import com.nicholsonplumbingtx.v2.model.supplier.SupplierContact;
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
public class EditSupplierContactController extends ContactController implements Initializable
{
    /* Object that handles all the connection information to the database */
    private SupplierDBConnection eC;

    private SupplierContact selectedContact;

    @FXML
    private Button updateButton;
    @FXML private Button cancelButton;
    @FXML private TextField fNameField;
    @FXML private TextField lNameField;
    @FXML private TextField phoneNumField;
    @FXML private TextField emailField;
    @FXML private ComboBox<Supplier> supplierComboBox;
    @FXML private ComboBox<Status> statusComboBox;
    @FXML private ComboBox<Type> typeComboBox;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        establishConnection();
        loadSupplierList(eC.getSupplierList());
        loadStatusList(eC.getContactStatusList());
        loadTypeList(eC.getContactTypeList());
    }

    private void establishConnection()
    {
        try
        {
            eC = new SupplierDBConnection();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    private void loadSupplierList(ArrayList<Supplier> suppliers)
    {
        Task taskOne = new Task<Void>()
        {
            public Void call()
            {
                supplierComboBox.getItems().addAll(suppliers);
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
        this.selectedContact = (SupplierContact) sC;

        supplierComboBox.setValue(eC.getSupplier(selectedContact.getCompanyName()));
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
        selectedContact.setSupplierID(supplierComboBox.getSelectionModel().getSelectedItem().getSupplierID());
        selectedContact.setStatus(statusComboBox.getSelectionModel().getSelectedItem());
        selectedContact.setType(typeComboBox.getSelectionModel().getSelectedItem());
    }

    @FXML
    private void updateButtonAction()
    {
        Stage stage = (Stage) updateButton.getScene().getWindow();
        collectInformation();
        eC.updateSupplierContact(selectedContact);
        stage.close();
    }

    @FXML
    private void cancelButtonAction()
    {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }
}
