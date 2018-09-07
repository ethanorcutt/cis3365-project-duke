package com.nicholsonplumbingtx.v2.controller.main_form_controller;

import com.nicholsonplumbingtx.v2.controller.common_controller.ScreenLoader;
import com.nicholsonplumbingtx.v2.controller.edit_form_controller.employee_screen.EditEPAController;
import com.nicholsonplumbingtx.v2.controller.edit_form_controller.employee_screen.EditEVAController;
import com.nicholsonplumbingtx.v2.database_connector.EmployeeDBConnection;
import com.nicholsonplumbingtx.v2.model.common.Status;
import com.nicholsonplumbingtx.v2.model.common.Type;
import com.nicholsonplumbingtx.v2.model.employee.Employee;
import com.nicholsonplumbingtx.v2.model.employee.EmployeeHistory;
import com.nicholsonplumbingtx.v2.model.project.Project;
import com.nicholsonplumbingtx.v2.model.vehicle.Vehicle;
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
import java.util.ResourceBundle;

/**
 * Created by Ethan Orcutt on 10/15/2016.
 * Project D.U.K.E.
 */
public class EmployeeController extends ScreenLoader implements Initializable
{
    /* Object that handles all the connection information to the database */
    private EmployeeDBConnection eC;

    @FXML private Label jobTitleLabel;
    @FXML private Label employeeNameLabel;

    /* Employees Tab - Assigned Vehicles Table */
    @FXML private TableView<Vehicle> employeeVehicleTable;
    @FXML private TableColumn<Vehicle, String> cTVehicleMake;
    @FXML private TableColumn<Vehicle, String> cTVehicleModel;
    @FXML private TableColumn<Vehicle, String> cTLicensePlate;

    /* Employees Tab - Assigned Projects Table */
    @FXML private TableView<Project> employeeProjectTable;
    @FXML private TableColumn<Project, String> cTProjectName;
    @FXML private TableColumn<Project, String> cTProjectStatus;
    @FXML private TableColumn<Project, String> cTProjectType;

    @FXML private MenuItem newEmployeeMenuItem;
    @FXML private MenuItem newVehicleAssignmentMenuItem;
    @FXML private MenuItem newProjectAssignmentMenuItem;
    @FXML private Button employeeEdit;

    @FXML private TableView<EmployeeHistory> employeeHistoryLog;
    @FXML private TableColumn<EmployeeHistory, String> historyDate;
    @FXML private TableColumn<EmployeeHistory, String> historyDescription;
    @FXML private TableColumn<EmployeeHistory, String> historySalary;

    @FXML private VBox employeeVBox;
    @FXML private ComboBox<Status> employeeStatus;
    @FXML private TreeView<String> employeeTreeView;

    @FXML private Label phoneNumberLabel;
    @FXML private Label emailLabel;
    @FXML private Label addLineOneLabel;
    @FXML private Label addLineTwoLabel;
    @FXML private Label addressLineThree;

    public EmployeeController()
    {

        jobTitleLabel = new Label();
        employeeNameLabel = new Label();
        newEmployeeMenuItem = new MenuItem();
        employeeStatus = new ComboBox<>();
        employeeTreeView = new TreeView<>();
    }

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
        jobTitleLabel.setVisible(false);
        employeeNameLabel.setVisible(false);
        phoneNumberLabel.setVisible(false);
        emailLabel.setVisible(false);
        addLineOneLabel.setVisible(false);
        addLineTwoLabel.setVisible(false);
        addressLineThree.setVisible(false);
        loadEmployeeStatus(eC.getEmployeeStatusList());
        loadEmployeeList(eC.getEmployeeTypeList());
        loadEmployeeVBox();
        updateLabels();
        updateEmployeeStatus();
        establishEventHandlers();
    }

    private void establishEventHandlers()
    {
        newEmployeeMenuItem.setOnAction(event -> openNewStageWindow(loadScreen("new_forms/newEmployee.fxml")));
        newProjectAssignmentMenuItem.setOnAction(event -> openNewStageWindow(loadScreen("new_forms/newEmployeeProjectAssignment.fxml")));
        newVehicleAssignmentMenuItem.setOnAction(event -> openNewStageWindow(loadScreen("new_forms/newEmployeeVehicleAssignment.fxml")));

        employeeEdit.setOnAction(event ->
        {
            Project selectedProject = employeeProjectTable.getSelectionModel().getSelectedItem();
            Vehicle selectedVehicle = employeeVehicleTable.getSelectionModel().getSelectedItem();
            TreeItem<String> selectedItem = employeeTreeView.getSelectionModel().getSelectedItem();

            if(selectedProject != null)
            {
                openNewStageWindow(loadScreen("edit_forms/editEmployeeProjectAssignment.fxml", selectedProject));
                employeeProjectTable.getSelectionModel().clearSelection();
            }
            else if(selectedVehicle != null)
            {
                openNewStageWindow(loadScreen("edit_forms/editEmployeeVehicleAssignment.fxml", selectedVehicle));
                employeeVehicleTable.getSelectionModel().clearSelection();
            }
            else if(selectedItem != null)
            {
                if(checkIfParent(selectedItem))
                {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Warning Dialog");
                    alert.setHeaderText("");
                    alert.setContentText("Not a valid selection.");
                    alert.showAndWait();
                }
                else
                    openNewStageWindow(loadScreen("edit_forms/editEmployee.fxml", selectedItem.getValue()));
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
    }

    private void loadEmployeeVBox()
    {
        employeeVBox = new VBox();
        employeeVBox.setVisible(true);
    }

    private void loadEmployeeList(ArrayList<Type> employeeTypeList)
    {
        loadInitialTreeItems(employeeTypeList);

        Task taskOne = new Task<Void>()
        {
            public Void call()
            {
                ArrayList<Employee> employeeList;
                String currentStatusSelection = employeeStatus.getSelectionModel().getSelectedItem().getStatusTitle();

                int typeNode = 0;
                for(Type typeName : employeeTypeList)
                {
                    employeeList = eC.getEmployeeList(currentStatusSelection, typeName.getTypeTitle());
                    for(Employee eName : employeeList)
                        makeNewTreeItem(eName.getFullName(), employeeTreeView.getTreeItem(typeNode));
                    typeNode++;
                }
                return null;
            }
        };
        new Thread(taskOne).start();
    }

    private void loadEmployeeStatus(ArrayList<Status> employeeStatusList)
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
        new Thread(taskOne).start();
    }

    private void loadAssignedProjectsTable(ArrayList<Project> projects)
    {
        Task taskOne = new Task<Void>()
        {
            public Void call()
            {
                cTProjectName.setCellValueFactory(new PropertyValueFactory<>("projectName"));
                cTProjectStatus.setCellValueFactory(new PropertyValueFactory<>("projectStatus"));
                cTProjectType.setCellValueFactory(new PropertyValueFactory<>("projectType"));
                employeeProjectTable.setItems(getProjectTableData(projects));
                return null;
            }
        };
        new Thread(taskOne).start();
    }

    private void loadAssignedVehiclesTable(ArrayList<Vehicle> vehicles)
    {
        Task taskOne = new Task<Void>()
        {
            public Void call()
            {
                cTVehicleMake.setCellValueFactory(new PropertyValueFactory<>("vehicleMake"));
                cTVehicleModel.setCellValueFactory(new PropertyValueFactory<>("vehicleModel"));
                cTLicensePlate.setCellValueFactory(new PropertyValueFactory<>("licensePlate"));
                employeeVehicleTable.setItems(getVehicleTableData(vehicles));
                return null;
            }
        };
        new Thread(taskOne).start();
    }

    private void loadEmployeeHistory(ArrayList<EmployeeHistory> historyLog)
    {
        Task taskOne = new Task<Void>()
        {
            public Void call()
            {
                historyDate.setCellValueFactory(new PropertyValueFactory<>("entry_date"));
                historyDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
                historySalary.setCellValueFactory(new PropertyValueFactory<>("salary"));
                employeeHistoryLog.setItems(getHistoryTableData(historyLog));
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
     * Gathers the Data from the database and then creates the records.
     * All records are then returned to the setupContactsTable() for display.
     *
     * TO-DO:
     *    - Transition to ContactBuilder
     */
    private ObservableList<Project> getProjectTableData(ArrayList<Project> projects)
    {
        final ObservableList<Project> data = FXCollections.observableArrayList();
        data.addAll(projects);
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
    private ObservableList<Vehicle> getVehicleTableData(ArrayList<Vehicle> vehicles)
    {
        final ObservableList<Vehicle> data = FXCollections.observableArrayList();
        data.addAll(vehicles);
        return data;
    }

    private ObservableList<EmployeeHistory> getHistoryTableData(ArrayList<EmployeeHistory> historyLog)
    {
        final ObservableList<EmployeeHistory> data = FXCollections.observableArrayList();
        data.addAll(historyLog);
        return data;
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
                    Employee selectedEmp;
                    TreeItem<String> newItem = employeeTreeView.getSelectionModel().getSelectedItem();

                    //noinspection StatementWithEmptyBody
                    if (newItem == null) {}
                    else if (newItem.isLeaf() && !(newItem.getParent().getValue().equals("Employees")))
                    {
                        selectedEmp = eC.getEmployee(newItem.getValue());

                        employeeNameLabel.setText(selectedEmp.getFullName());
                        jobTitleLabel.setText(newItem.getParent().getValue());

                        jobTitleLabel.setVisible(true);
                        employeeNameLabel.setVisible(true);

                        loadAssignedProjectsTable(eC.getAssignedProjects(selectedEmp.getEmployeeID()));
                        loadAssignedVehiclesTable(eC.getAssignedVehicles(selectedEmp.getEmployeeID()));
                        loadEmployeeHistory(eC.getEmployeeHistoryLog(selectedEmp.getEmployeeID()));

                        phoneNumberLabel.setText(selectedEmp.getFormattedPhoneNumber());
                        emailLabel.setText(selectedEmp.getEmail());
                        addLineOneLabel.setText(selectedEmp.getAddressLineOne());

                        if(selectedEmp.getAddressLineTwo() != null)
                            addLineTwoLabel.setText(selectedEmp.getAddressLineTwo());
                        else
                            addLineTwoLabel.setText("N/A");

                        addressLineThree.setText(selectedEmp.getCity() + ", " + eC.getRegionName(selectedEmp.getRegionID()).getRegionCode() + " " + selectedEmp.getZipCode());

                        phoneNumberLabel.setVisible(true);
                        emailLabel.setVisible(true);
                        addLineOneLabel.setVisible(true);
                        addLineTwoLabel.setVisible(true);
                        addressLineThree.setVisible(true);
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

    private boolean checkIfParent(TreeItem<String> newItem)
    {
        boolean isParent = false;

        //noinspection StatementWithEmptyBody
        if (newItem == null) {}
        else isParent = !(newItem.isLeaf() && !(newItem.getParent().getValue().equals("Employees")));

        return isParent;
    }

    private void makeNewTreeItem(String title, TreeItem<String> parent)
    {
        TreeItem<String> item = new TreeItem<>(title);
        item.setExpanded(false);
        parent.getChildren().add(item);
    }

    private void loadInitialTreeItems(ArrayList<Type> employeeTypeList)
    {
        TreeItem<String> treeBranch = new TreeItem<>("Employees");
        treeBranch.setExpanded(true);

        for (Type typeName : employeeTypeList)
            makeNewTreeItem(typeName.getTypeTitle(), treeBranch);

        employeeTreeView.setRoot(treeBranch);
        employeeTreeView.setShowRoot(false);
    }

    protected Parent loadScreen(String resource, Project selectedProject)
    {
        try
        {
            FXMLLoader myLoader = new FXMLLoader(getClass().getClassLoader().getResource("resources/" + resource));
            Parent loadScreen = myLoader.load();
            EditEPAController masterController = myLoader.getController();
            masterController.loadInformation(eC.getEmployee(
                    employeeTreeView.getSelectionModel().getSelectedItem().getValue()), selectedProject);
            return loadScreen;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }

        return null;
    }

    protected Parent loadScreen(String resource, Vehicle selectedVehicle)
    {
        try
        {
            FXMLLoader myLoader = new FXMLLoader(getClass().getClassLoader().getResource("resources/" + resource));
            Parent loadScreen = myLoader.load();
            EditEVAController masterController = myLoader.getController();
            masterController.loadInformation(eC.getEmployee(
                    employeeTreeView.getSelectionModel().getSelectedItem().getValue()), selectedVehicle);
            return loadScreen;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }

        return null;
    }
}
