package com.nicholsonplumbingtx.v2.controller;

import com.nicholsonplumbingtx.v2.common.ScreenLoader;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Ethan Orcutt on 10/18/2016.
 */
public class ContactSBController extends ScreenLoader implements Initializable
{
    @FXML private ComboBox<String> sbComboBox;
    @FXML private Button sbSubmitButton;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        loadContactTypeList();
    }

    private void loadContactTypeList()
    {
        String[] items = {"Customer", "Project", "Supplier", "Vendor"};
        sbComboBox.getItems().addAll(items);
    }

    @FXML
    public void submitButtonAction()
    {
        Stage stage = (Stage) sbSubmitButton.getScene().getWindow();

        switch(sbComboBox.getSelectionModel().getSelectedItem())
        {
            case "Customer":
                openNewStageWindow(loadScreen("/new_forms/newClientContact.fxml"));
                break;
            case "Project":
                openNewStageWindow(loadScreen("/new_forms/newProjectContact.fxml"));
                break;
            case "Supplier":
                openNewStageWindow(loadScreen("/new_forms/newSupplierContact.fxml"));
                break;
            case "Vendor":
                openNewStageWindow(loadScreen("/new_forms/newVendorContact.fxml"));
                break;
            default:
                break;
        }
        stage.close();
    }
}