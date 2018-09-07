package com.nicholsonplumbingtx.v2.controller;

import com.nicholsonplumbingtx.v2.database_connector.SupplierDBConnection;
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
 * Created by Ethan Orcutt on 10/18/2016.
 */
public class NewSupplierContactController implements Initializable
{
    /* Object that handles all the connection information to the database */
    private SupplierDBConnection eC;

    @FXML private Button createButton;
    @FXML private Button cancelButton;
    @FXML private ComboBox<String> supplierComboBox;
    @FXML private TextField fNameField;
    @FXML private TextField lNameField;
    @FXML private TextField phoneNumField;
    @FXML private TextField emailField;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        establishConnection();
        loadSupplierList(eC.getSupplierList());
    }

    /**
     * Created By: Ethan
     * Created On: 10/18/2016
     * Last Modified: 10/18/2016
     *
     */
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

    /**
     * Created By: Ethan
     * Created On: 10/18/2016
     * Last Modified: 10/18/2016
     *
     */
    private void loadSupplierList(ArrayList<String> supplierList)
    {
        Task taskOne = new Task<Void>()
        {
            public Void call()
            {
                supplierComboBox.getItems().addAll(supplierList);
                return null;
            }
        };
        new Thread(taskOne).start();
    }

    /**
     * Created By: Ethan
     * Created On: 10/18/2016
     * Last Modified: 10/18/2016
     *
     */
    @FXML
    private void createButtonAction()
    {
        Stage stage = (Stage) createButton.getScene().getWindow();
        stage.close();
    }

    /**
     * Created By: Ethan
     * Created On: 10/18/2016
     * Last Modified: 10/18/2016
     *
     */
    @FXML
    private void cancelButtonAction()
    {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }
}
