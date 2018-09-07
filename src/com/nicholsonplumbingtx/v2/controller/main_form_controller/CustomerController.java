package com.nicholsonplumbingtx.v2.controller.main_form_controller;

import com.nicholsonplumbingtx.v2.controller.common_controller.ContactController;
import com.nicholsonplumbingtx.v2.controller.common_controller.ScreenLoader;
import com.nicholsonplumbingtx.v2.database_connector.CustomerDBConnection;
import com.nicholsonplumbingtx.v2.model.client.Client;
import com.nicholsonplumbingtx.v2.model.common.Status;
import com.nicholsonplumbingtx.v2.model.contact.Contact;
import com.nicholsonplumbingtx.v2.model.invoice.Invoice;
import com.nicholsonplumbingtx.v2.model.project.Project;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
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
 * Project D.U.K.E.
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
    @FXML private ComboBox<Status> customerStatus;
    @FXML private TreeView<String> customerTreeView;

    /* Customers Tab - New Menu Buttons */
    @FXML private MenuItem newProjectMenuItem;
    @FXML private MenuItem newContactMenuItem;
    @FXML private MenuItem newInvoiceMenuItem;
    @FXML private MenuItem newCustomerMenuItem;
    @FXML private MenuItem newChangeOrderMenuItem;
    @FXML private MenuItem newJDSMenuItem;

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

    @FXML private Label labelOne;
    @FXML private Label labelTwo;
    @FXML private Label labelThree;
    @FXML private Label infoFieldOne;
    @FXML private Label infoFieldTwo;
    @FXML private Label infoFieldThree;

    public CustomerController()
    {

        projectNameLabel = new Label();
        contractorNameLabel = new Label();
        customerStatus = new ComboBox<>();
        customerTreeView = new TreeView<>();
        newProjectMenuItem = new MenuItem();
        newContactMenuItem = new MenuItem();
        newInvoiceMenuItem = new MenuItem();
        newCustomerMenuItem = new MenuItem();
        customersEdit = new Button();
        customerContactTable = new TableView<>();
        contactTableName = new TableColumn<>();
        contactTablePhone = new TableColumn<>();
        contactTableEmail = new TableColumn<>();
        customerInvoiceTable = new TableView<>();
        invoiceTableProject = new TableColumn<>();
        invoiceTableAmount = new TableColumn<>();
        invoiceTableDaysOutstanding = new TableColumn<>();
        cPNotesBox = new TextArea();
        customerVBox = new VBox();
        customerVBox.setVisible(true);
    }

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
        projectNameLabel.setVisible(false);
        contractorNameLabel.setVisible(false);
        labelOne.setVisible(false);
        labelTwo.setVisible(false);
        labelThree.setVisible(false);
        infoFieldOne.setVisible(false);
        infoFieldTwo.setVisible(false);
        infoFieldThree.setVisible(false);
        loadClientList(eC.getClientList("Active"));
        loadClientStatus(eC.getClientStatusList());
        updateLabels();
        establishEventHandlers();
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
        newCustomerMenuItem.setOnAction(event -> openNewStageWindow(loadScreen("new_forms/newClient.fxml")));
        newProjectMenuItem.setOnAction(event -> openNewStageWindow(loadScreen("new_forms/newProject.fxml")));
        newContactMenuItem.setOnAction(event -> openNewStageWindow(loadScreen("new_forms/newContactSB.fxml")));
        newInvoiceMenuItem.setOnAction(event -> openNewStageWindow(loadScreen("new_forms/newInvoice.fxml")));
        newJDSMenuItem.setOnAction(event -> openNewStageWindow(loadScreen("new_forms/newJobDataSheet.fxml")));

        newChangeOrderMenuItem.setOnAction(event ->
        {
            TreeItem<String> selectedItem = customerTreeView.getSelectionModel().getSelectedItem();

            if(selectedItem == null)
                openNewStageWindow(loadScreen("new_forms/newChangeOrderSBOne.fxml"));
            else if(checkIfParent(selectedItem))
                openNewStageWindow(loadScreen("new_forms/newChangeOrderSBTwo.fxml",
                        eC.getClient(selectedItem.getValue())));
            else
                openNewStageWindow(loadScreen("new_forms/newChangeOrder.fxml",
                        eC.getClient(selectedItem.getParent().getValue()), eC.getProject(selectedItem.getValue())));
        });

        customersEdit.setOnAction(event ->
        {
            Contact selectedContact = customerContactTable.getSelectionModel().getSelectedItem();
            Invoice selectedInvoice = customerInvoiceTable.getSelectionModel().getSelectedItem();
            TreeItem<String> selectedItem = customerTreeView.getSelectionModel().getSelectedItem();

            if(selectedContact != null)
            {
                if(selectedItem != null && !checkIfParent(selectedItem))
                    openNewStageWindow(loadScreen("edit_forms/editProjectContact.fxml", selectedContact));
                else
                    openNewStageWindow(loadScreen("edit_forms/editClientContact.fxml", selectedContact));
                customerContactTable.getSelectionModel().clearSelection();
            }
            else if(selectedInvoice != null)
            {
                openNewStageWindow(loadScreen("edit_forms/editInvoice.fxml", selectedInvoice, eC.getClient(selectedItem.getValue())));
                customerInvoiceTable.getSelectionModel().clearSelection();
            }
            else if(selectedItem != null)
            {
                if(checkIfParent(selectedItem))
                    openNewStageWindow(loadScreen("edit_forms/editClient.fxml", selectedItem.getValue()));
                else
                    openNewStageWindow(loadScreen("edit_forms/editProject.fxml", selectedItem.getValue()));
            }
            else
            {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning Dialog");
                alert.setHeaderText("");
                alert.setContentText("Nothing has been selected.");
                alert.showAndWait();
            }
        });

        cPNotesBox.focusedProperty().addListener((observable, oldValue, newValue) -> saveNotesBox());
    }

    private boolean checkIfParent(TreeItem<String> newItem)
    {
        boolean isParent = false;

        if (newItem.isLeaf() && !(newItem.getParent().getValue().equals("Customers")))
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
                invoiceTableAmount.setCellValueFactory(new PropertyValueFactory<>("totalDue"));
                invoiceTableDaysOutstanding.setCellValueFactory(new PropertyValueFactory<>("dateCreated"));
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
    private void loadClientStatus(ArrayList<Status> customerStatusList)
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
                //TODO: Follow Below Notes:
                //For now, this event is limited to only responding when a mouse is clicked.
                //Maybe improve in a future revision.
                //KeyEvent's don't work.
                customerTreeView.addEventHandler(MouseEvent.MOUSE_CLICKED, event ->
                {
                    TreeItem<String> newItem = customerTreeView.getSelectionModel().getSelectedItem();

                    if(newItem == null) {}
                    else if (newItem.isLeaf() && !(newItem.getParent().getValue().equals("Customers")))
                    {
                        Project selectedProject = eC.getProject(newItem.getValue());

                        contractorNameLabel.setText(newItem.getParent().getValue());
                        projectNameLabel.setText(newItem.getValue());

                        projectNameLabel.setVisible(true);
                        contractorNameLabel.setVisible(true);

                        loadContactTable(eC.getContactList(selectedProject.getProjectID(), "Project"));
                        loadInvoiceTable(eC.getInvoiceList(selectedProject.getProjectID(), "Project"));
                        loadNotes(null, eC.getProject(newItem.getValue()));

                        labelOne.setText("Start Date:");
                        labelTwo.setText("Status:");
                        labelThree.setText("Type:");
                        infoFieldOne.setText(selectedProject.getProjectStartDate().toLocalDate().toString());
                        infoFieldTwo.setText(selectedProject.getProjectStatus().getStatusTitle());
                        infoFieldThree.setText(selectedProject.getProjectType().getTypeTitle());
                    }
                    else if (newItem.getParent().getValue().equals("Customers"))
                    {
                        labelOne.setVisible(false);
                        infoFieldOne.setVisible(false);
                        infoFieldTwo.setVisible(false);
                        infoFieldThree.setVisible(false);

                        Client selectedClient = eC.getClient(newItem.getValue());

                        contractorNameLabel.setText(newItem.getValue());
                        projectNameLabel.setText("No Project Selected");
                        projectNameLabel.setVisible(true);
                        contractorNameLabel.setVisible(true);
                        loadContactTable(eC.getContactList(selectedClient.getClientID(), "Client"));
                        loadInvoiceTable(eC.getInvoiceList(selectedClient.getClientID(), "Client"));
                        loadNotes(eC.getClient(newItem.getValue()), null);

                        labelOne.setText("Phone #:");
                        labelTwo.setText("Address 1:");
                        labelThree.setText("Address 2:");
                        infoFieldOne.setText(selectedClient.getFormattedPhoneNumber());

                        infoFieldTwo.setText(selectedClient.getAddressLineOne());
                        if(selectedClient.getAddressLineTwo() != null)
                            infoFieldThree.setText(selectedClient.getAddressLineTwo());
                        else
                            infoFieldThree.setText("N/A");

                        infoFieldThree.setText(selectedClient.getCity() + ", " + eC.getRegionName(selectedClient.getRegion()).getRegionCode() + " " + selectedClient.getPostal());
                    }
                    labelOne.setVisible(true);
                    labelTwo.setVisible(true);
                    labelThree.setVisible(true);
                    infoFieldOne.setVisible(true);
                    infoFieldTwo.setVisible(true);
                    infoFieldThree.setVisible(true);
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
        customerStatus.setOnAction(event -> loadClientList(eC.getClientList(customerStatus.getValue().getStatusTitle())));
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
    private void makeNewTreeItem(String title, TreeItem<String> parent)
    {
        TreeItem<String> item = new TreeItem<>(title);
        item.setExpanded(false);
        parent.getChildren().add(item);
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

    protected Parent loadScreen(String resource, Contact selectedContact)
    {
        try
        {
            FXMLLoader myLoader = new FXMLLoader(getClass().getClassLoader().getResource("resources/" + resource));
            Parent loadScreen = myLoader.load();
            ContactController masterController = myLoader.getController();
            masterController.loadInformation(selectedContact);
            return loadScreen;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }

        return null;
    }

    protected Parent loadScreen(String resource, Invoice selectedInvoice, Client selectedClient)
    {
        try
        {
            FXMLLoader myLoader = new FXMLLoader(getClass().getClassLoader().getResource("resources/" + resource));
            Parent loadScreen = myLoader.load();
            ContactController masterController = myLoader.getController();
            masterController.loadInformation(selectedInvoice, selectedClient);
            return loadScreen;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }

        return null;
    }
}