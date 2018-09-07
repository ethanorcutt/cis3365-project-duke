package com.nicholsonplumbingtx.v2.controller.main_form_controller;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by Ethan Orcutt on 11/8/2016.
 * Project D.U.K.E.
 */
public class ReportViewerController implements Initializable
{
    @FXML private TableView<ObservableList<String>> reportTableView;

    @Override
    public void initialize(URL location, ResourceBundle resources) {}

    private void addColumns(List<String> cNames)
    {
        for (int i = 0; i < cNames.size(); i++)
        {
            final int finalIdx = i;
            TableColumn<ObservableList<String>, String> column = new TableColumn<>(cNames.get(i));
            column.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue().get(finalIdx)));
            reportTableView.getColumns().add(column);
        }
    }

    private void loadReportTable(List<String[]> rowData)
    {
        ObservableList<ObservableList<String>> masterData = FXCollections.observableArrayList();
        ObservableList<String> row;

        for (String[] item : rowData)
        {
            row = FXCollections.observableArrayList();
            for(String t : item)
                Collections.addAll(row, t);
            masterData.add(row);
        }
        reportTableView.setItems(masterData);
    }

    public void loadInformation(List<String> cNames, List<String[]> data)
    {
        addColumns(cNames);
        loadReportTable(data);
    }
}