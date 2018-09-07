package com.nicholsonplumbingtx.v2.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Ethan Orcutt on 10/15/2016.
 */
public class HomeController implements Initializable
{
    @FXML private Button exitButton;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {

    }

    /**
     * Created By: Ethan
     * Created On: 10/28/2016
     * Last Modified: 10/28/2016
     *
     */
    @FXML
    private void setExitButton(ActionEvent event)
    {
        event.consume();
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void setCustomerButton(ActionEvent event)
    {
        event.consume();
        MainController.switchTab(1);
    }

    @FXML
    private void setEmployeeButton(ActionEvent event)
    {
        event.consume();
        MainController.switchTab(2);
    }

    @FXML
    private void setFormButton(ActionEvent event)
    {
        event.consume();
        MainController.switchTab(3);
    }

    @FXML
    private void setPSBButton(ActionEvent event)
    {
        event.consume();
        MainController.switchTab(7);
    }

    @FXML
    private void setReportButton(ActionEvent event)
    {
        event.consume();
        MainController.switchTab(6);
    }
}
