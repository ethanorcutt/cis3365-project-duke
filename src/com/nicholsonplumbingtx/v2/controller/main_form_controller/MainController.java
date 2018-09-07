package com.nicholsonplumbingtx.v2.controller.main_form_controller;

import com.nicholsonplumbingtx.v2.controller.common_controller.ScreenLoader;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController extends ScreenLoader implements Initializable
{
    @FXML private static TabPane mainTabPane;
    @FXML private Tab homeTab;
    @FXML private Tab customerTab;
    @FXML private Tab employeeTab;
    @FXML private Tab vehicleTab;
    @FXML private Tab supplierTab;
    @FXML private Tab vendorTab;
    @FXML private Tab reportTab;
    @FXML private Tab PSBTab;

    public MainController()
    {
        homeTab = new Tab();
        customerTab = new Tab();
        employeeTab = new Tab();
        vehicleTab = new Tab();
        supplierTab = new Tab();
        vendorTab = new Tab();
        reportTab = new Tab();
        PSBTab = new Tab();
    }

    /**
     * Created By: Ethan
     * Created On: 10/15/2016
     * Last Modified: 10/15/2016
     *
     * @param url
     * @param rbd
     */
    public void initialize(URL url, ResourceBundle rbd)
    {
        setMainTabPane();
    }

    public void setMainTabPane()
    {
        mainTabPane = new TabPane();
        mainTabPane.getTabs().add(homeTab);
        mainTabPane.getTabs().add(customerTab);
        mainTabPane.getTabs().add(employeeTab);
        mainTabPane.getTabs().add(vehicleTab);
        mainTabPane.getTabs().add(supplierTab);
        mainTabPane.getTabs().add(vendorTab);
        mainTabPane.getTabs().add(reportTab);
        mainTabPane.getTabs().add(PSBTab);
    }

    /*
    * Program the Menu Bar on the MainController.
    * DON'T FORGET TO PROGRAM THE MENU BAR
    * */

    public static void switchTab(int selectedTabIndex)
    {
        /*SingleSelectionModel<Tab> selectionModel = mainTabPane.getSelectionModel();
        selectionModel.select(selectedTabIndex); //select by index starting with 0
        selectionModel.clearSelection(); //clear your selection*/

        mainTabPane.getSelectionModel().select(selectedTabIndex);
    }
}