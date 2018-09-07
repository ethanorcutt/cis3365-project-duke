package simple;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.net.URL;

public class MainController extends ScreenLoader implements Initializable
{
    private EstablishConnection eC;

    /* Start Customers Tab */

    /* Customers Tab - Holds the TreeView & Status Box in place.  */
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

    /* Customers Tab - Holds the notes for a Customer or a Project. */
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
            eC = new EstablishConnection();
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
        setClientList(eC.getClientList("Active"));
        setClientStatus(eC.getClientStatusList());
        setCustomerVBox();
        updateLabels();
        establishEventHandlers();
    }

    /**
     * Created By: Ethan
     * Created On: 9/25/2016
     * Last Modified: 9/25/2016
     *
     */
    private void setCustomerVBox()
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

        /*eC.createNewClient(new ClientBuilder()
                .setStatusID(1)
                .setTypeID(1)
                .setFirstName("Ethan")
                .setLastName("Orcutt")
                .setCompanyName("Nicholson Plumbing")
                .setClientNotes("This is a test string.")
                .createClient()
        );

        setClientList(eC.getClientList("Active"));*/

        newCustomerMenuItem.addEventHandler(ActionEvent.ACTION,
                event -> openNewStageWindow(loadScreen("clientScreen", "newClient.fxml")));

        newProjectMenuItem.addEventHandler(ActionEvent.ACTION,
                event -> openNewStageWindow(loadScreen("projectScreen", "newProject.fxml")));

        /*eC.createNewLocationContact(new LocationContactBuilder()
                .setLocationID(1)
                .setFirstName("Renee")
                .setLastName("Orcutt")
                .setPhoneNumber("8325636263")
                .setEmail("brorcutt@comcast.net")
                .createLocationContact()
        );*/

        //This needs to be corrected. This doesn't dynamically adjust which Client/Project is selected.
        //setContactTable(eC.getLocationContactList(1));

        newContactMenuItem.addEventHandler(ActionEvent.ACTION,
                event -> openNewStageWindow(loadScreen("locationContact", "newLocationContact.fxml")));

        newInvoiceMenuItem.addEventHandler(ActionEvent.ACTION,
                event -> openNewStageWindow(loadScreen("invoiceScreen", "newInvoice.fxml")));

        customersEdit.addEventHandler(ActionEvent.ACTION,
                event -> openNewStageWindow(loadScreen("loginScreen", "loginScreen.fxml")));

        /*cPNotesBox.addEventHandler(MouseEvent.MOUSE_CLICKED,
                event -> cPNotesBox.addEventHandler(MouseEvent.MOUSE_EXITED, event2 -> saveNotesBox()));*/

        //Saves the current notes upon gaining focus & saves the notes again upon losing focus.
        //Might want to improve so that notes are not saved upon gaining focus.
        cPNotesBox.focusedProperty().addListener((observable, oldValue, newValue) -> saveNotesBox());
    }

    /**
     * Created By: Ethan
     * Created On: 9/25/2016
     * Last Modified: 10/2/2016
     */
    private void saveNotesBox()
    {
        TreeItem<String> newItem = customerTreeView.getSelectionModel().getSelectedItem();

        if(newItem.isLeaf() && !(newItem.getParent().getValue() == "Customers"))
            eC.setProjectNotes(newItem.getValue(), cPNotesBox.getText());
        else
            eC.setClientNotes(newItem.getValue(), cPNotesBox.getText());

        System.out.println("Notes Saved for: " + newItem.getValue());
    }

    /**
     * Created By: Ethan
     * Created On: 10/12/2016
     * Last Modified: 10/15/2016
     */
    private void setNotes(Client singleClient, Project singleProject)
    {
        if(singleClient != null)
            cPNotesBox.setText(singleClient.getClientNotes());
        else if(singleProject != null)
            cPNotesBox.setText(singleProject.getProjectNotes());
        else
            cPNotesBox.setText("No Notes Available");
    }

    /**
     * Created By: Ethan
     * Created On: 9/25/2016
     * Last Modified: 10/10/2016
     *
     */
    private void setClientList(ArrayList<Client> clientList)
    {
        loadInitialTreeItems("Customers", clientList);

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
                    clientNode += projectList.size() + 1;
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
    private void setContactTable(ArrayList<Contact> contactList)
    {
        contactTableName.setCellValueFactory(new PropertyValueFactory<Contact,String>("fullName"));
        contactTablePhone.setCellValueFactory(new PropertyValueFactory<Contact,String>("formattedPhoneNumber"));
        contactTableEmail.setCellValueFactory(new PropertyValueFactory<Contact,String>("email"));
        contactTableEmail.setMinWidth(175);
        customerContactTable.setItems(getContactTableData(contactList));
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
    private void setInvoiceTable(ArrayList<Invoice> invoiceList)
    {
        invoiceTableProject.setCellValueFactory(new PropertyValueFactory<Invoice,String>("projectName"));
        invoiceTableAmount.setCellValueFactory(new PropertyValueFactory<Invoice,String>("invoiceAmount"));
        invoiceTableDaysOutstanding.setCellValueFactory(new PropertyValueFactory<Invoice,String>("daysOutstanding"));
        customerInvoiceTable.setItems(getInvoiceTableData(invoiceList));
    }

    /**
     * Created By: Ethan
     * Created On: 9/25/2016
     * Last Modified: 10/10/2016
     *
     */
    private void setClientStatus(ArrayList<String> customerStatusList)
    {
        for(String item : customerStatusList)
            customerStatus.getItems().addAll(item);
        customerStatus.setValue(customerStatus.getItems().get(0));
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
        customerTreeView.getSelectionModel().selectedItemProperty().addListener((observableValue, oldItem, newItem) ->
        {
            if (newItem.isLeaf() && !(newItem.getParent().getValue() == "Customers"))
            {
                int projectID = eC.getSelectedID(newItem.getValue(), "Project");
                contractorNameLabel.setText(newItem.getParent().getValue());
                projectNameLabel.setText(newItem.getValue());
                setContactTable(eC.getContactList(projectID, "Project"));
                setInvoiceTable(eC.getInvoiceList(projectID, "Project"));
                setNotes(null, eC.getProjectNotes(newItem.getValue()));
            }
            else
            {
                int clientID = eC.getSelectedID(newItem.getValue(), "Client");
                contractorNameLabel.setText(newItem.getValue());
                projectNameLabel.setText("No Project Selected");
                setContactTable(eC.getContactList(clientID, "Client"));
                setInvoiceTable(eC.getInvoiceList(clientID, "Client"));
                setNotes(eC.getClientNotes(newItem.getValue()), null);
            }
        });
    }

    /**
     * Created By: Ethan
     * Created On: 9/25/2016
     * Last Modified: 10/10/2016
     */
    @FXML
    private void updateClientStatus()
    {
        customerStatus.addEventHandler(ActionEvent.ACTION,
                event -> setClientList(eC.getClientList(customerStatus.getValue())));
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
     * @param rootBranch
     * @param customerList
     */
    private void loadInitialTreeItems(String rootBranch, ArrayList<Client> customerList)
    {
        TreeItem<String> treeBranch = new TreeItem<>(rootBranch);
        treeBranch.setExpanded(true);

        for (Client clientName : customerList)
            makeNewTreeItem(clientName.getCompanyName(), treeBranch);

        customerTreeView.setRoot(treeBranch);
        customerTreeView.setShowRoot(false);
    }
}