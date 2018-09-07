package com.nicholsonplumbingtx.v2.controller.new_form_controller.customer_screen;

import com.nicholsonplumbingtx.v2.controller.common_controller.Controller;
import com.nicholsonplumbingtx.v2.database_connector.InvoiceDBConnection;
import com.nicholsonplumbingtx.v2.model.client.Client;
import com.nicholsonplumbingtx.v2.model.common.Status;
import com.nicholsonplumbingtx.v2.model.common.Type;
import com.nicholsonplumbingtx.v2.model.invoice.Invoice;
import com.nicholsonplumbingtx.v2.model.invoice.InvoiceBuilder;
import com.nicholsonplumbingtx.v2.model.project.Project;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Created by Ethan Orcutt on 10/16/2016.
 * Project D.U.K.E.
 */
public class NewInvoiceController extends Controller implements Initializable
{
    /* Object that handles all the connection information to the database */
    private InvoiceDBConnection eC;

    private Invoice createdInvoice;

    /* Start New Invoice Window */

    @FXML private Button createButton;
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

    public NewInvoiceController()
    {

        createButton = new Button();
        cancelButton = new Button();
        customerComboBox = new ComboBox<>();
        projectComboBox = new ComboBox<>();
        invoiceTypeComboBox = new ComboBox<>();
        invoiceStatusComboBox = new ComboBox<>();
        changeOrderSum = new TextField();
        completedSum = new TextField();
        totalDue = new TextField();
        totalStillDue = new TextField();
        newInvoiceNotes = new TextArea();
        dateCreated = new DatePicker();
    }

    /* End New Invoice Window */

    /**
     * Created By: Ethan
     * Created On: 10/16/2016
     * Last Modified: 10/16/2016
     *
     */
    public void initialize(URL location, ResourceBundle resources)
    {
        establishConnection();
        mainInvoiceScreen();
    }

    /**
     * Created By: Ethan
     * Created On: 10/16/2016
     * Last Modified: 10/16/2016
     *
     */
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

    /**
     * Created By: Ethan
     * Created On: 10/16/2016
     * Last Modified: 10/16/2016
     *
     */
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
    protected void loadInformation(int vehicleID)
    {

    }

    @Override
    public void loadInformation(String selectedName) {

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
        createdInvoice = new InvoiceBuilder()
                .setInvoiceTypeID(invoiceTypeComboBox.getSelectionModel().getSelectedItem().getTypeID())
                .setInvoiceStatusID(invoiceStatusComboBox.getSelectionModel().getSelectedItem().getStatusID())
                .setProjectID(projectComboBox.getSelectionModel().getSelectedItem().getProjectID())
                .setChangeOrderNetTotal(Double.parseDouble(changeOrderSum.getText()))
                .setCompletedSum(Double.parseDouble(completedSum.getText()))
                .setNotes(newInvoiceNotes.getText())
                .setTotalDue(Double.parseDouble(totalDue.getText()))
                .setTotalStillDue(Double.parseDouble(totalStillDue.getText()))
                .setDateCreated(Date.valueOf(dateCreated.getValue()))
                .createInvoice();
    }

    /**
     * Created By: Ethan
     * Created On: 10/16/2016
     * Last Modified: 10/16/2016
     *
     */
    @FXML
    private void createButtonAction()
    {
        Stage stage = (Stage) createButton.getScene().getWindow();
        collectInformation();
        eC.createInvoice(createdInvoice);
        stage.close();
    }

    /**
     * Created By: Ethan
     * Created On: 10/16/2016
     * Last Modified: 10/16/2016
     *
     */
    @FXML
    private void cancelButtonAction()
    {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }
}
