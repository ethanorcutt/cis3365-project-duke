package com.nicholsonplumbingtx.v2.controller;

import com.nicholsonplumbingtx.v2.common.ScreenLoader;
import com.nicholsonplumbingtx.v2.database_connector.EmployeeDBConnection;
import com.nicholsonplumbingtx.v2.model.Employee;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Created by Ethan Orcutt on 10/15/2016.
 */
public class EmployeeController extends ScreenLoader implements Initializable
{
    /* Object that handles all the connection information to the database */
    private EmployeeDBConnection eC;

    @FXML private Label jobTitleLabel;
    @FXML private Label employeeNameLabel;
    @FXML private TableView<?> employeeVehicleTable;
    @FXML private TableColumn<?, ?> invoiceTableProject1;
    @FXML private TableColumn<?, ?> invoiceTableAmount1;
    @FXML private TableColumn<?, ?> invoiceTableDaysOutstanding1;
    @FXML private TableView<?> employeeProjectTable;
    @FXML private TableColumn<?, ?> contactTableName1;
    @FXML private TableColumn<?, ?> contactTablePhone1;
    @FXML private TableColumn<?, ?> contactTableEmail11;
    @FXML private MenuItem newEmployeeMenuItem;
    @FXML private MenuItem newVehicleMenuItem;
    @FXML private MenuItem newVehicleAssignmentMenuItem;
    @FXML private MenuItem newProjectAssignmentMenuItem;
    @FXML private Button employeeEdit;
    @FXML private TableView<?> employeeHistoryLog;
    @FXML private TableColumn<?, ?> contactTableName11;
    @FXML private TableColumn<?, ?> contactTableEmail111;
    @FXML private VBox employeeVBox;
    @FXML private ComboBox<String> employeeStatus;
    @FXML private TreeView<String> employeeTreeView;

    @Override
    public void initialize(URL url, ResourceBundle rbd)
    {
        establishConnection();
        mainEmployeeScreen();
    }

    private void establishConnection()
    {
        try
        {
            eC = new EmployeeDBConnection();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    private void mainEmployeeScreen()
    {
        loadEmployeeStatus(eC.getEmployeeStatusList());
        loadEmployeeList(eC.getEmployeeTypeList());
        loadEmployeeVBox();
        updateLabels();
        updateEmployeeStatus();
        establishEventHandlers();
    }

    private void loadEmployeeVBox()
    {
        employeeVBox = new VBox();
        employeeVBox.setVisible(true);
    }

    private void loadEmployeeList(ArrayList<String> employeeTypeList)
    {
        loadInitialTreeItems(employeeTypeList);

        Task taskOne = new Task<Void>()
        {
            public Void call()
            {
                ArrayList<Employee> employeeList;
                String currentStatusSelection = employeeStatus.getSelectionModel().getSelectedItem();

                int typeNode = 0;
                for(String typeName : employeeTypeList)
                {
                    employeeList = eC.getEmployeeList(currentStatusSelection, typeName);
                    for(Employee eName : employeeList)
                        makeNewTreeItem(eName.getFullName(), employeeTreeView.getTreeItem(typeNode));
                    typeNode++;
                }
                return null;
            }
        };
        new Thread(taskOne).run();
    }

    private void loadEmployeeStatus(ArrayList<String> employeeStatusList)
    {
        Task taskOne = new Task<Void>()
        {
            public Void call()
            {
                employeeStatus.getItems().addAll(employeeStatusList);
                employeeStatus.setValue(employeeStatus.getItems().get(0));
                return null;
            }
        };
        new Thread(taskOne).run();
    }

    private void updateLabels()
    {
        Task taskOne = new Task<Void>()
        {
            public Void call()
            {
                //For now, this event is limited to only responding when a mouse is clicked.
                //Maybe improve in a future revision.
                //KeyEvent's don't work.
                employeeTreeView.addEventHandler(MouseEvent.MOUSE_CLICKED, event ->
                {
                    TreeItem<String> newItem = employeeTreeView.getSelectionModel().getSelectedItem();

                    if (newItem == null) {}
                    else if (newItem.isLeaf() && !(newItem.getParent().getValue().equals("Employees")))
                    {
                        int employeeID = eC.getEmployeeID(newItem.getValue());
                        employeeNameLabel.setText(eC.getEmployeeList(employeeID).getFullName());
                        jobTitleLabel.setText(newItem.getParent().getValue());
                    }
                });
                return null;
            }
        };
        new Thread(taskOne).start();
    }

    private void updateEmployeeStatus()
    {
        employeeStatus.setOnAction(event -> loadEmployeeList(eC.getEmployeeTypeList()));
    }

    private void establishEventHandlers()
    {

    }

    private TreeItem<String> makeNewTreeItem(String title, TreeItem<String> parent)
    {
        TreeItem<String> item = new TreeItem<>(title);
        item.setExpanded(false);
        parent.getChildren().add(item);
        return item;
    }

    private void loadInitialTreeItems(ArrayList<String> employeeTypeList)
    {
        TreeItem<String> treeBranch = new TreeItem<>("Employees");
        treeBranch.setExpanded(true);

        for (String typeName : employeeTypeList)
            makeNewTreeItem(typeName, treeBranch);

        employeeTreeView.setRoot(treeBranch);
        employeeTreeView.setShowRoot(false);
    }
}
