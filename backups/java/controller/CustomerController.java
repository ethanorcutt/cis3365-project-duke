package com.nicholsonplumbingtx.v2.controller;

import com.nicholsonplumbingtx.v2.common.ScreenLoader;
import com.nicholsonplumbingtx.v2.database_connector.CustomerDBConnection;
import com.nicholsonplumbingtx.v2.model.Client;
import com.nicholsonplumbingtx.v2.model.Contact;
import com.nicholsonplumbingtx.v2.model.Invoice;
import com.nicholsonplumbingtx.v2.model.Project;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

/**
 * Created by Ethan Orcutt on 10/15/2016.
 */
public class CustomerController extends ScreenLoader implements Initializable
{
    /* Object that handles all the connection information to the database */
    private CustomerDBConnection eC;

    /* Start Customers Tab */

    /* Customers Tab - Holds the TreeView & Status Box in place  */
    @FXML private VBox customerVBox;

    /* Customers Tab - Customer TreeView & Placeholders for Information */
    @FXML private Label projectNameLabel;
    @FXML private Label contractorNameLabel;
    @FXML private ComboBox<String> customerStatus;
    @FXML private TreeView<String> customerTreeView;

    /* Customers Tab - New Menu Buttons */
    @FXML private MenuItem newProjectMenuItem;
    @FXML private MenuItem newContactMenuItem;
    @FXML private MenuItem newInvoiceMenuItem;
    @FXML private MenuItem newCustomerMenuItem;

    /* Customers Tab - Edit Button */
    @FXML private Button customersEdit;

    /* Customers Tab - Contacts Table */
    @FXML private TableView<Contact> customerContactTable;
    @FXML private TableColumn<Contact, String> contactTableName;
    @FXML private TableColumn<Contact, String> contactTablePhone;
    @FXML private TableColumn<Contact, String> contactTableEmail;

    /* Customers Tab - Invoice Table */
    @FXML private TableView<Invoice> customerInvoiceTable;
    @FXML private TableColumn<Invoice, String> invoiceTableProject;
    @FXML private TableColumn<Invoice, String> invoiceTableAmount;
    @FXML private TableColumn<Invoice, String> invoiceTableDaysOutstanding;

    /* Customers Tab - Holds the notes for a Customer or a Project */
    @FXML private TextArea cPNotesBox;

    /* End Customers Tab */

    /**
     * Created By: Ethan
     * Created On: 9/25/2016
     * Last Modified: 9/25/2016
     *
     * @param url
     * @param rbd
     */
    public void initialize(URL url, ResourceBundle rbd)
    {
        establishConnection();
        mainCustomersScreen();
    }

    /**
     * Created By: Ethan
     * Created On: 9/25/2016
     * Last Modified: 9/25/2016
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

    /**
     * Created By: Ethan
     * Created On: 9/25/2016
     * Last Modified: 10/15/2016
     *
     * This method houses all of the method calls that are necessary to maintain the
     * Customers tab.
     */
    private void mainCustomersScreen()
    {
        loadClientList(eC.getClientList("Active"));
        loadClientStatus(eC.getClientStatusList());
        loadCustomerVBox();
        updateLabels();
        establishEventHandlers();
    }

    /**
     * Created By: Ethan
     * Created On: 9/25/2016
     * Last Modified: 9/25/2016
     *
     */
    private void loadCustomerVBox()
    {
        customerVBox = new VBox();
        customerVBox.setVisible(true);
    }

    /**
     * Created By: Ethan
     * Created On: 9/25/2016
     * Last Modified: 10/2/2016
     *
     * Creates all the EventHandlers for the "New Menu Items" list.
     *
     * Completed:
     *    - Add new customers dynamically.
     *       - When the new customer button is clicked, a new client is created and sent to the database.
     *         From there, the Client List is reloaded.
     */
    private void establishEventHandlers()
    {
        newCustomerMenuItem.setOnAction(event -> openNewStageWindow(loadScreen("/new_forms/newClient.fxml")));

        newProjectMenuItem.setOnAction(event -> openNewStageWindow(loadScreen("/new_forms/newProject.fxml")));

        newContactMenuItem.setOnAction(event -> openNewStageWindow(loadScreen("/new_forms/newContactSB.fxml")));

        newInvoiceMenuItem.setOnAction(event -> openNewStageWindow(loadScreen("/new_forms/newInvoice.fxml")));

        customersEdit.setOnAction(event ->
        {
            TreeItem<String> selectedItem = customerTreeView.getSelectionModel().getSelectedItem();

            if(checkIfParent(selectedItem))
                openNewStageWindow(loadScreen("/edit_forms/editClient.fxml", selectedItem.getValue()));
            else
                openNewStageWindow(loadScreen("/edit_forms/editProject.fxml", selectedItem.getValue()));
        });

        cPNotesBox.focusedProperty().addListener((observable, oldValue, newValue) -> saveNotesBox());
    }

    private boolean checkIfParent(TreeItem<String> newItem)
    {
        boolean isParent = false;

        if (newItem == null) {}
        else if (newItem.isLeaf() && !(newItem.getParent().getValue().equals("Customers")))
            isParent = false;
        else if (newItem.getParent().getValue().equals("Customers"))
            isParent = true;

        return isParent;
    }

    /**
     * Created By: Ethan
     * Created On: 9/25/2016
     * Last Modified: 10/2/2016
     */
    private void saveNotesBox()
    {
        Task taskOne = new Task<Void>()
        {
            public Void call()
            {
                TreeItem<String> newItem = customerTreeView.getSelectionModel().getSelectedItem();

                if(newItem.isLeaf() && !(Objects.equals(newItem.getParent().getValue(), "Customers")))
                    eC.setProjectNotes(newItem.getValue(), cPNotesBox.getText());
                else
                    eC.setClientNotes(newItem.getValue(), cPNotesBox.getText());
                return null;
            }
        };
        new Thread(taskOne).start();
    }

    /**
     * Created By: Ethan
     * Created On: 9/25/2016
     * Last Modified: 10/10/2016
     *
     */
    private void loadClientList(ArrayList<Client> clientList)
    {
        loadInitialTreeItems(clientList);

        Task taskOne = new Task<Void>()
        {
            public Void call()
            {
                ArrayList<String> projectList;
                int clientNode = 0;
                for(Client cName : clientList)
                {
                    projectList = eC.getClientProjects(cName.getCompanyName());
                    for(String pName : projectList)
                        makeNewTreeItem(pName, customerTreeView.getTreeItem(clientNode));
                    clientNode++;
                }
                return null;
            }
        };
        new Thread(taskOne).start();
    }

    /**
     * Created By: Ethan
     * Created On: 9/25/2016
     * Last Modified: 10/2/2016
     *
     * TO-DO:
     *    - Add a way to dynamically add rows.
     *
     * COMPLETED:
     *    - Need to create the table.
     */
    private void loadContactTable(ArrayList<Contact> contactList)
    {
        Task taskOne = new Task<Void>()
        {
            public Void call()
            {
                contactTableName.setCellValueFactory(new PropertyValueFactory<>("fullName"));
                contactTablePhone.setCellValueFactory(new PropertyValueFactory<>("formattedPhoneNumber"));
                contactTableEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
                contactTableEmail.setMinWidth(175);
                customerContactTable.setItems(getContactTableData(contactList));
                return null;
            }
        };
        new Thread(taskOne).start();
    }

    /**
     * Created By: Ethan
     * Created On: 10/2/2016
     * Last Modified: 10/2/2016
     *
     * TO-DO:
     *    - Add a way to dynamically add rows.
     *
     * COMPLETED:
     *    - Need to create the table.
     */
    private void loadInvoiceTable(ArrayList<Invoice> invoiceList)
    {
        Task taskOne = new Task<Void>()
        {
            public Void call()
            {
                invoiceTableProject.setCellValueFactory(new PropertyValueFactory<>("projectName"));
                invoiceTableAmount.setCellValueFactory(new PropertyValueFactory<>("invoiceAmount"));
                invoiceTableDaysOutstanding.setCellValueFactory(new PropertyValueFactory<>("daysOutstanding"));
                customerInvoiceTable.setItems(getInvoiceTableData(invoiceList));
                return null;
            }
        };
        new Thread(taskOne).start();
    }

    /**
     * Created By: Ethan
     * Created On: 9/25/2016
     * Last Modified: 10/15/2016
     *
     */
    private void loadClientStatus(ArrayList<String> customerStatusList)
    {
        Task taskOne = new Task<Void>()
        {
            public Void call()
            {
                customerStatus.getItems().addAll(customerStatusList);
                customerStatus.setValue(customerStatus.getItems().get(0));
                return null;
            }
        };
        new Thread(taskOne).start();
    }

    /**
     * Created By: Ethan
     * Created On: 10/12/2016
     * Last Modified: 10/15/2016
     */
    private void loadNotes(Client singleClient, Project singleProject)
    {
        Task taskOne = new Task<Void>()
        {
            public Void call()
            {
                if (singleClient != null) cPNotesBox.setText(singleClient.getClientNotes());
                else if (singleProject != null) cPNotesBox.setText(singleProject.getProjectNotes());
                else cPNotesBox.setText("No Notes Available");
                return null;
            }
        };
        new Thread(taskOne).start();
    }

    /**
     * Created By: Ethan
     * Created On: 9/25/2016
     * Last Modified: 9/26/2016
     *
     * This method makes sure that the Contractor and Project Name labels are kept up
     * to speed on the current selection that the user has made in the TreeView.
     *
     * TO-DO:
     *    - Implement the Active/Inactive status
     *      - Redraw the TreeView when a different status is selected.
     *
     * COMPLETED:
     *    - Make it so that if a TreeItem does not have any leaves, that the labels will
     *      not update to "Customers" root label. It's hidden for a reason.
     */
    private void updateLabels()
    {
        Task taskOne = new Task<Void>()
        {
            public Void call()
            {
                //For now, this event is limited to only responding when a mouse is clicked.
                //Maybe improve in a future revision.
                //KeyEvent's don't work.
                customerTreeView.addEventHandler(MouseEvent.MOUSE_CLICKED, event ->
                {
                    TreeItem<String> newItem = customerTreeView.getSelectionModel().getSelectedItem();

                    if (newItem == null) {}
                    else if (newItem.isLeaf() && !(newItem.getParent().getValue().equals("Customers")))
                    {
                        int projectID = eC.getSelectedID(newItem.getValue(), "Project");
                        contractorNameLabel.setText(newItem.getParent().getValue());
                        projectNameLabel.setText(newItem.getValue());
                        loadContactTable(eC.getContactList(projectID, "Project"));
                        loadInvoiceTable(eC.getInvoiceList(projectID, "Project"));
                        loadNotes(null, eC.getProject(newItem.getValue()));
                    }
                    else if (newItem.getParent().getValue().equals("Customers"))
                    {
                        int clientID = eC.getSelectedID(newItem.getValue(), "Client");
                        contractorNameLabel.setText(newItem.getValue());
                        projectNameLabel.setText("No Project Selected");
                        loadContactTable(eC.getContactList(clientID, "Client"));
                        loadInvoiceTable(eC.getInvoiceList(clientID, "Client"));
                        loadNotes(eC.getClient(newItem.getValue()), null);
                    }
                });
                return null;
            }
        };
        new Thread(taskOne).start();
    }

    /**
     * Created By: Ethan
     * Created On: 9/25/2016
     * Last Modified: 10/15/2016
     */
    @FXML
    private void updateClientStatus()
    {
        customerStatus.setOnAction(event -> loadClientList(eC.getClientList(customerStatus.getValue())));
    }

    /**
     * Created By: Ethan
     * Created On: 10/2/2016
     * Last Modified: 10/2/2016
     *
     * Gathers the Data from the database and then creates the records.
     * All records are then returned to the setupContactsTable() for display.
     *
     * TO-DO:
     *    - Transition to ContactBuilder
     */
    private ObservableList<Contact> getContactTableData(ArrayList<Contact> contactList)
    {
        final ObservableList<Contact> data = FXCollections.observableArrayList();
        data.addAll(contactList);
        return data;
    }

    /**
     * Created By: Ethan
     * Created On: 10/2/2016
     * Last Modified: 10/10/2016
     *
     * Gathers the Data from the database and then creates the records.
     * All records are then returned to the setupContactsTable() for display.
     */
    private ObservableList<Invoice> getInvoiceTableData(ArrayList<Invoice> invoiceList)
    {
        final ObservableList<Invoice> data = FXCollections.observableArrayList();
        data.addAll(invoiceList);
        return data;
    }

    /**
     * Created By: Ethan
     * Created On: 9/25/2016
     * Last Modified: 9/25/2016
     *
     * @param title
     * @param parent
     * @return
     */
    private TreeItem<String> makeNewTreeItem(String title, TreeItem<String> parent)
    {
        TreeItem<String> item = new TreeItem<>(title);
        item.setExpanded(false);
        parent.getChildren().add(item);
        return item;
    }

    /**
     * Created By: Ethan
     * Created On: 9/25/2016
     * Last Modified: 9/25/2016
     *
     * @param customerList
     */
    private void loadInitialTreeItems(ArrayList<Client> customerList)
    {
        TreeItem<String> treeBranch = new TreeItem<>("Customers");
        treeBranch.setExpanded(true);

        for (Client clientName : customerList)
            makeNewTreeItem(clientName.getCompanyName(), treeBranch);

        customerTreeView.setRoot(treeBranch);
        customerTreeView.setShowRoot(false);
    }
}