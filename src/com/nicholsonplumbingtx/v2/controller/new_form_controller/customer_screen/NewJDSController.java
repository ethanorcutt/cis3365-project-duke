package com.nicholsonplumbingtx.v2.controller.new_form_controller.customer_screen;

import com.nicholsonplumbingtx.v2.database_connector.CustomerDBConnection;
import com.nicholsonplumbingtx.v2.model.client.Client;
import com.nicholsonplumbingtx.v2.model.job_data_sheet.Bank;
import com.nicholsonplumbingtx.v2.model.job_data_sheet.BondCompany;
import com.nicholsonplumbingtx.v2.model.job_data_sheet.JobDataSheet;
import com.nicholsonplumbingtx.v2.model.project.Project;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Created by Ethan Orcutt on 11/17/2016.
 * Project D.U.K.E.
 */
public class NewJDSController implements Initializable
{
    /* Object that handles all the connection information to the database */
    private CustomerDBConnection eC;

    private JobDataSheet createdJDS;

    @FXML private Button createButton;
    @FXML private Button cancelButton;
    @FXML private TextField estimatedMaterialsField;
    @FXML private ComboBox<Client> customerComboBox;
    @FXML private ComboBox<Project> projectComboBox;
    @FXML private ComboBox<Bank> bankComboBox;
    @FXML private ComboBox<BondCompany> bondCompanyComboBox;
    @FXML private CheckBox publicWorksCB;
    @FXML private TextField parcelField;
    @FXML private TextField bondField;
    @FXML private CheckBox residentialCB;
    @FXML private CheckBox commercialCB;
    @FXML private CheckBox taxExemptCB;
    @FXML private TextField jobField;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        establishConnection();
        loadCustomerList(eC.getClientList());
        loadBankList(eC.getBankList());
        loadBondCompanyList(eC.getBondCompanyList());
        loadEventHandlers();
    }

    private void loadEventHandlers()
    {
        customerComboBox.addEventHandler(ActionEvent.ACTION, event -> loadProjectList(eC.getClientProjectList(customerComboBox.getSelectionModel().getSelectedItem().getCompanyName())));
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

    private void loadCustomerList(ArrayList<Client> clients)
    {
        Task taskOne = new Task<Void>()
        {
            public Void call()
            {
                customerComboBox.getItems().addAll(clients);
                return null;
            }
        };
        new Thread(taskOne).start();
    }

    private void loadProjectList(ArrayList<Project> projects)
    {
        Task taskOne = new Task<Void>()
        {
            public Void call()
            {
                projectComboBox.getItems().addAll(projects);
                return null;
            }
        };
        new Thread(taskOne).start();
    }

    private void loadBankList(ArrayList<Bank> banks)
    {
        Task taskOne = new Task<Void>()
        {
            public Void call()
            {
                bankComboBox.getItems().addAll(banks);
                return null;
            }
        };
        new Thread(taskOne).start();
    }

    private void loadBondCompanyList(ArrayList<BondCompany> bondCompanies)
    {
        Task taskOne = new Task<Void>()
        {
            public Void call()
            {
                bondCompanyComboBox.getItems().addAll(bondCompanies);
                return null;
            }
        };
        new Thread(taskOne).start();
    }

    @FXML
    void cancelButtonAction()
    {

    }

    @FXML
    void createButtonAction()
    {

    }
}
