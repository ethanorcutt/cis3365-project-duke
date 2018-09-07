package com.nicholsonplumbingtx.v2.controller.edit_form_controller.customer_screen;

import com.nicholsonplumbingtx.v2.controller.common_controller.ContactController;
import com.nicholsonplumbingtx.v2.database_connector.InvoiceDBConnection;
import com.nicholsonplumbingtx.v2.model.client.Client;
import com.nicholsonplumbingtx.v2.model.common.Status;
import com.nicholsonplumbingtx.v2.model.common.Type;
import com.nicholsonplumbingtx.v2.model.contact.Contact;
import com.nicholsonplumbingtx.v2.model.invoice.Invoice;
import com.nicholsonplumbingtx.v2.model.project.Project;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Created by Ethan Orcutt on 11/9/2016.
 * Project D.U.K.E.
 */
public class EditInvoiceController extends ContactController implements Initializable
{
    /* Object that handles all the connection information to the database */
    private InvoiceDBConnection eC;

    private Invoice selectedInvoice;

    @FXML private Button updateButton;
    @FXML private Button cancelButton;
    @FXML private ComboBox<Client> customerComboBox;
    @FXML private ComboBox<Project> projectComboBox;
    @FXML private ComboBox<Type> invoiceTypeComboBox;
    @FXML private ComboBox<Status> invoiceStatusComboBox;
    @FXML private TextField changeOrderSum;
    @FXML private TextField completedSum;
    @FXML private TextField totalDue;
    @FXML private TextField totalStillDue;
    @FXML private TextArea newInvoiceNotes;
    @FXML private DatePicker dateCreated;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        establishConnection();
        mainInvoiceScreen();
    }

    private void establishConnection()
    {
        try
        {
            eC = new InvoiceDBConnection();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    private void mainInvoiceScreen()
    {
        loadCustomerList(eC.getCustomerList());
        loadInvoiceStatusList(eC.getInvoiceStatusList());
        loadInvoiceTypeList(eC.getInvoiceTypeList());
        loadEventHandlers();
    }

    private void loadEventHandlers()
    {
        customerComboBox.addEventHandler(ActionEvent.ACTION, event -> loadProjectList(eC.getProjectList(customerComboBox.getSelectionModel().getSelectedItem().getClientID())));
    }

    /**
     * Created By: Ethan
     * Created On: 10/16/2016
     * Last Modified: 10/16/2016
     *
     */
    private void loadCustomerList(ArrayList<Client> customerList)
    {
        Task taskOne = new Task<Void>()
        {
            public Void call()
            {
                customerComboBox.getItems().addAll(customerList);
                return null;
            }
        };
        new Thread(taskOne).start();
    }

    /**
     * Created By: Ethan
     * Created On: 10/16/2016
     * Last Modified: 10/16/2016
     *
     */
    private void loadProjectList(ArrayList<Project> projectList)
    {
        Task taskOne = new Task<Void>()
        {
            public Void call()
            {
                projectComboBox.getItems().clear();
                projectComboBox.getItems().addAll(projectList);
                return null;
            }
        };
        new Thread(taskOne).start();
    }

    /**
     * Created By: Ethan
     * Created On: 10/16/2016
     * Last Modified: 10/16/2016
     *
     */
    private void loadInvoiceStatusList(ArrayList<Status> statusList)
    {
        Task taskOne = new Task<Void>()
        {
            public Void call()
            {
                invoiceStatusComboBox.getItems().addAll(statusList);
                return null;
            }
        };
        new Thread(taskOne).start();
    }

    /**
     * Created By: Ethan
     * Created On: 10/16/2016
     * Last Modified: 10/16/2016
     *
     */
    private void loadInvoiceTypeList(ArrayList<Type> typeList)
    {
        Task taskOne = new Task<Void>()
        {
            public Void call()
            {
                invoiceTypeComboBox.getItems().addAll(typeList);
                return null;
            }
        };
        new Thread(taskOne).start();
    }

    @Override
    public void loadInformation(Contact selectedContact)
    {

    }


    public void loadInformation(Invoice sI, Client sC)
    {
        this.selectedInvoice = sI;

        customerComboBox.setValue(sC);
        projectComboBox.setValue(eC.getProject(selectedInvoice.getProjectID()));
        invoiceStatusComboBox.setValue(selectedInvoice.getInvoiceStatus());
        invoiceTypeComboBox.setValue(selectedInvoice.getInvoiceType());

        changeOrderSum.setText(selectedInvoice.getChangeOrderNetTotal() + "");
        completedSum.setText(selectedInvoice.getCompletedSum() + "");
        totalDue.setText(selectedInvoice.getTotalDue() + "");
        totalStillDue.setText(selectedInvoice.getTotalStillDue() + "");
        dateCreated.setValue(selectedInvoice.getDateCreated().toLocalDate());
        newInvoiceNotes.setText(selectedInvoice.getNotes());

        loadProjectList(eC.getProjectList(sC.getClientID()));
    }

    private void collectInformation()
    {
        LocalDate locald = dateCreated.getValue();
        Date date = Date.valueOf(locald);

        selectedInvoice.setProjectID(projectComboBox.getSelectionModel().getSelectedItem().getProjectID());
        selectedInvoice.setInvoiceType(invoiceTypeComboBox.getSelectionModel().getSelectedItem());
        selectedInvoice.setInvoiceStatus(invoiceStatusComboBox.getSelectionModel().getSelectedItem());
        selectedInvoice.setChangeOrderNetTotal(Double.parseDouble(changeOrderSum.getText()));
        selectedInvoice.setCompletedSum(Double.parseDouble(completedSum.getText()));
        selectedInvoice.setTotalDue(Double.parseDouble(totalDue.getText()));
        selectedInvoice.setTotalStillDue(Double.parseDouble(totalStillDue.getText()));
        selectedInvoice.setNotes(newInvoiceNotes.getText());
        selectedInvoice.setDateCreated(convertJavaDateToSqlDate(date));
    }

    @FXML
    void updateButtonAction()
    {
        Stage stage = (Stage) updateButton.getScene().getWindow();
        collectInformation();
        eC.updateInvoice(selectedInvoice);
        stage.close();
    }

    @FXML
    void cancelButtonAction()
    {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }
}
