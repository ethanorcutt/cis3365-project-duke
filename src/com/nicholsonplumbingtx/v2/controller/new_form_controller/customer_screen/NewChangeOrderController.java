package com.nicholsonplumbingtx.v2.controller.new_form_controller.customer_screen;

import com.nicholsonplumbingtx.v2.controller.common_controller.Controller;
import com.nicholsonplumbingtx.v2.database_connector.InvoiceDBConnection;
import com.nicholsonplumbingtx.v2.model.change_order.ChangeOrder;
import com.nicholsonplumbingtx.v2.model.change_order.ChangeOrderBuilder;
import com.nicholsonplumbingtx.v2.model.client.Client;
import com.nicholsonplumbingtx.v2.model.common.Status;
import com.nicholsonplumbingtx.v2.model.invoice.Invoice;
import com.nicholsonplumbingtx.v2.model.project.Project;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

/**
 * Created by Ethan Orcutt on 11/1/2016.
 * Project D.U.K.E.
 */
public class NewChangeOrderController extends Controller implements Initializable
{
    /* Object that handles all the connection information to the database */
    private InvoiceDBConnection eC;

    private ChangeOrder createdChangeOrder;

    private double subtotalOne;
    private double subtotaltwo;
    private double subTotalThree;
    Date date = new Date();

    @FXML private ComboBox<Invoice> invoiceComboBox;
    @FXML private Button createButton;
    @FXML private Button cancelButton;
    @FXML private ComboBox<Status> statusComboBox;
    @FXML private TextField notesfield;
    @FXML private TextField exclusionsfield;
    @FXML private TextField materialsfield;
    @FXML private TextField salestaxfield;
    @FXML private TextField directlaborfield;
    @FXML private TextField indirectcostfield;
    @FXML private TextField equipmenttoolsfield;
    @FXML private TextField subtotalfield;
    @FXML private TextField overheadfield;
    @FXML private TextField subcontractorfield;
    @FXML private TextField overheadpercent;
    @FXML private TextField oversubtotal;
    @FXML private TextField profitpercntsubtotal;
    @FXML private TextField profitpercnt;
    @FXML private TextField bondPercnt;
    @FXML private TextField serviceprcnt;
    @FXML private TextField totalcostfield;
    @FXML private CheckBox addcheckbox;
    @FXML private CheckBox deductcheckbox;
    @FXML private CheckBox validdayscheck;
    @FXML private CheckBox extensioncheck;
    @FXML private TextField validdaysfield;
    @FXML private TextField extensionfield;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        establishConnection();
        prepFields();
        loadEventHandlers();
        loadStatusList(eC.getChangeOrderStatusList());
    }

    private void prepFields()
    {
        /* Start Section One */
        notesfield.setText("");
        exclusionsfield.setText("");
        materialsfield.setText("0.00");
        salestaxfield.setText("0.00");
        directlaborfield.setText("0.00");
        indirectcostfield.setText("0.00");
        equipmenttoolsfield.setText("0.00");
        subtotalfield.setText("0.00");
        /* End Section One */

        /* Start Section Two */
        overheadfield.setText("0.00");
        subcontractorfield.setText("0.00");
        overheadpercent.setText("0.00");
        oversubtotal.setText("0.00");
        /* End Section Two */

        /* Start Section Three */
        profitpercnt.setText("0.00");
        profitpercntsubtotal.setText("0.00");
        /* End Section Three */

        /* Start Section Four */
        bondPercnt.setText("0.00");
        serviceprcnt.setText("0.00");
        totalcostfield.setText("0.00");
        validdaysfield.setText("0");
        extensionfield.setText("0");
        /* End Section Four */
        //TODO: Overhead % and Overhead costs need their names updated and add the % symbols to the GUI.
    }

    private void loadEventHandlers()
    {
        firstSectionEventHandlers();
        secondSectionEventHandlers();
        thirdSectionEventHandlers();
        fourthSectionEventHandlers();
        //TODO: Error's are thrown if you delete what was in a box. Need to make it always have a default value.
    }

    private void firstSectionEventHandlers()
    {
        materialsfield.focusedProperty().addListener(observable ->
                sumTotalOne(Double.parseDouble(materialsfield.getText()),
                        Double.parseDouble(salestaxfield.getText()),
                        Double.parseDouble(directlaborfield.getText()),
                        Double.parseDouble(indirectcostfield.getText()),
                        Double.parseDouble(equipmenttoolsfield.getText())));

        salestaxfield.focusedProperty().addListener(observable ->
                sumTotalOne(Double.parseDouble(materialsfield.getText()),
                        Double.parseDouble(salestaxfield.getText()),
                        Double.parseDouble(directlaborfield.getText()),
                        Double.parseDouble(indirectcostfield.getText()),
                        Double.parseDouble(equipmenttoolsfield.getText())));

        directlaborfield.focusedProperty().addListener(observable ->
                sumTotalOne(Double.parseDouble(materialsfield.getText()),
                        Double.parseDouble(salestaxfield.getText()),
                        Double.parseDouble(directlaborfield.getText()),
                        Double.parseDouble(indirectcostfield.getText()),
                        Double.parseDouble(equipmenttoolsfield.getText())));

        indirectcostfield.focusedProperty().addListener(observable ->
                sumTotalOne(Double.parseDouble(materialsfield.getText()),
                        Double.parseDouble(salestaxfield.getText()),
                        Double.parseDouble(directlaborfield.getText()),
                        Double.parseDouble(indirectcostfield.getText()),
                        Double.parseDouble(equipmenttoolsfield.getText())));

        equipmenttoolsfield.focusedProperty().addListener(observable ->
                sumTotalOne(Double.parseDouble(materialsfield.getText()),
                        Double.parseDouble(salestaxfield.getText()),
                        Double.parseDouble(directlaborfield.getText()),
                        Double.parseDouble(indirectcostfield.getText()),
                        Double.parseDouble(equipmenttoolsfield.getText())));
    }

    private void secondSectionEventHandlers()
    {
        overheadfield.focusedProperty().addListener(observable ->
                sumTotalTwo(Double.parseDouble(overheadfield.getText()) / 100,
                            Double.parseDouble(subcontractorfield.getText()),
                            Double.parseDouble(overheadpercent.getText()) / 100,
                            subtotalOne));

        subcontractorfield.focusedProperty().addListener(observable ->
                sumTotalTwo(Double.parseDouble(overheadfield.getText()) / 100,
                        Double.parseDouble(subcontractorfield.getText()),
                        Double.parseDouble(overheadpercent.getText()) / 100,
                        subtotalOne));

        overheadpercent.focusedProperty().addListener(observable ->
                sumTotalTwo(Double.parseDouble(overheadfield.getText()) / 100,
                        Double.parseDouble(subcontractorfield.getText()),
                        Double.parseDouble(overheadpercent.getText()) / 100,
                        subtotalOne));
    }

    private void thirdSectionEventHandlers()
    {
        profitpercnt.focusedProperty().addListener(observable ->
                sumTotalThree(subtotaltwo, Double.parseDouble(profitpercnt.getText()) / 100));
    }

    private void fourthSectionEventHandlers()
    {
        bondPercnt.focusedProperty().addListener(observable ->
                sumGrandTotal(subTotalThree,
                        Double.parseDouble(bondPercnt.getText()) / 100,
                        Double.parseDouble(serviceprcnt.getText()) / 100));

        serviceprcnt.focusedProperty().addListener(observable ->
                sumGrandTotal(subTotalThree,
                        Double.parseDouble(bondPercnt.getText()) / 100,
                        Double.parseDouble(serviceprcnt.getText()) / 100));
    }

    private void sumTotalOne(double... values)
    {
        double sumValue = 0;
        for(double tmp : values)
            sumValue += tmp;
        subtotalOne = sumValue;
        subtotalfield.setText(subtotalOne + "");
    }

    private void sumTotalTwo(double valueOne, double valueTwo, double valueThree, double valueFour)
    {
        if(valueThree == 0)
            subtotaltwo = valueFour + (valueFour * valueOne);
        else
            subtotaltwo = valueFour + (valueFour * valueOne) + valueTwo + (valueTwo * valueThree);

        oversubtotal.setText(subtotaltwo + "");
    }

    private void sumTotalThree(double valueOne, double valueTwo)
    {
        subTotalThree = valueOne + (valueOne * valueTwo);
        profitpercntsubtotal.setText(subTotalThree + "");
    }

    private void sumGrandTotal(double valueOne, double valueTwo, double valueThree)
    {
        double grandTotal;
        if(valueThree == 0)
            grandTotal = valueOne + (valueOne * valueTwo);
        else
            grandTotal = valueOne + (valueOne * valueTwo) + valueOne + (valueOne * valueThree);

        totalcostfield.setText(grandTotal + "");
    }

    private void establishConnection()
    {
        try
        {
            eC = new InvoiceDBConnection();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    private void loadStatusList(ArrayList<Status> statusList)
    {
        Task taskOne = new Task<Void>()
        {
            public Void call()
            {
                statusComboBox.getItems().addAll(statusList);
                return null;
            }
        };
        new Thread(taskOne).start();
    }

    private void loadInvoiceList(ArrayList<Invoice> invoices)
    {
        Task taskOne = new Task<Void>()
        {
            public Void call()
            {
                invoiceComboBox.getItems().addAll(invoices);
                return null;
            }
        };
        new Thread(taskOne).start();
    }

    @Override
    protected void loadInformation(int vehicleID)
    {

    }

    @Override
    public void loadInformation(String selectedName) {}

    @Override
    protected void loadInformation(Client selectedClient) {}

    @Override
    public void loadInformation(Client selectedClient, Project selectedProject)
    {
        /*Client selectedClient1 = selectedClient;
        Project selectedProject1 = selectedProject;*/
        loadInvoiceList(eC.getInvoiceList(selectedProject.getProjectID()));
    }

    @Override
    protected void collectInformation()
    {
        createdChangeOrder = new ChangeOrderBuilder()
                .setChangeOrderStatusID(statusComboBox.getSelectionModel().getSelectedItem().getStatusID())
                .setInvoiceID(invoiceComboBox.getSelectionModel().getSelectedItem().getInvoiceID())
                .setMaterial(Double.parseDouble(materialsfield.getText()))
                .setSalesTax(Double.parseDouble(salestaxfield.getText()))
                .setDirectLabor(Double.parseDouble(directlaborfield.getText()))
                .setIndirectCosts(Double.parseDouble(indirectcostfield.getText()))
                .setEquipmentToolsCosts(Double.parseDouble(equipmenttoolsfield.getText()))
                .setOverheadCosts(Double.parseDouble(overheadfield.getText()))
                .setOverheadPercentage(Double.parseDouble(overheadpercent.getText()))
                .setProfitPercentage(Double.parseDouble(profitpercnt.getText()))
                .setBondPremiumPercentage(Double.parseDouble(bondPercnt.getText()))
                .setServicePercentage(Double.parseDouble(serviceprcnt.getText()))
                .setFinalTotal(Double.parseDouble(totalcostfield.getText()))
                .setExclusions(exclusionsfield.getText())
                .setSubContracts(Double.parseDouble(subcontractorfield.getText()))
                .setDaysValid(Integer.parseInt(validdaysfield.getText()))
                .setContractExtension(Integer.parseInt(extensionfield.getText()))
                .setNotes(notesfield.getText())
                .setDateCreated(convertJavaDateToSqlDate(date))
                .setSubtotalF(Double.parseDouble(subtotalfield.getText()))
                .setSubtotalJ(Double.parseDouble(oversubtotal.getText()))
                .setSubtotalL(Double.parseDouble(profitpercntsubtotal.getText()))
                .setAdd(addcheckbox.isSelected())
                .setDeduct(deductcheckbox.isSelected())
                .createChangeOrder();
    }

    @FXML
    private void createButtonAction()
    {
        Stage stage = (Stage) createButton.getScene().getWindow();
        collectInformation();
        eC.createChangeOrder(createdChangeOrder);
        stage.close();
    }

    @FXML
    private void cancelButtonAction()
    {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }
}