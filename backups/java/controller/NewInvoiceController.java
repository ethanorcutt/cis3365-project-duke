package com.nicholsonplumbingtx.v2.controller;

import com.nicholsonplumbingtx.v2.common.ScreenLoader;
import com.nicholsonplumbingtx.v2.database_connector.InvoiceDBConnection;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Created by Ethan Orcutt on 10/16/2016.
 */
public class NewInvoiceController extends ScreenLoader implements Initializable
{
    /* Object that handles all the connection information to the database */
    private InvoiceDBConnection eC;

    /* Start New Invoice Window */

    @FXML private Button saveButton;
    @FXML private Button cancelButton;
    @FXML private ComboBox<String> customerComboBox;
    @FXML private ComboBox<String> projectComboBox;
    @FXML private ComboBox<String> invoiceTypeComboBox;
    @FXML private ComboBox<String> invoiceStatusComboBox;
    @FXML private TextField newInvoiceID;
    @FXML private TextField changeOrderSum;
    @FXML private TextField invoiceCompletedSum;
    @FXML private TextField totalDueNow;
    @FXML private TextField totalStillDue;
    @FXML private TextArea newInvoiceNotes;
    @FXML private DatePicker invoiceDate;

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
        customerComboBox.addEventHandler(ActionEvent.ACTION, event ->
        {
            String selectedCustomerName = customerComboBox.getSelectionModel().getSelectedItem();
            int selectedCustomerID = eC.getSelectedID(selectedCustomerName);
            loadProjectList(eC.getProjectList(selectedCustomerID));
        });
    }

    /**
     * Created By: Ethan
     * Created On: 10/16/2016
     * Last Modified: 10/16/2016
     *
     */
    private void loadCustomerList(ArrayList<String> customerList)
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
    private void loadProjectList(ArrayList<String> projectList)
    {
        Task taskOne = new Task<Void>()
        {
            public Void call()
            {
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
    private void loadInvoiceStatusList(ArrayList<String> statusList)
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
    private void loadInvoiceTypeList(ArrayList<String> typeList)
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

    /**
     * Created By: Ethan
     * Created On: 10/16/2016
     * Last Modified: 10/16/2016
     *
     */
    @FXML
    private void saveButtonAction()
    {
        Stage stage = (Stage) saveButton.getScene().getWindow();
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
