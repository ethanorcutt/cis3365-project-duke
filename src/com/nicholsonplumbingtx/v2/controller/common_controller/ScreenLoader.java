package com.nicholsonplumbingtx.v2.controller.common_controller;

import com.nicholsonplumbingtx.v2.model.client.Client;
import com.nicholsonplumbingtx.v2.model.project.Project;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Created by Ethan Orcutt on 10/15/2016.
 * Project D.U.K.E.
 */
public abstract class ScreenLoader
{
    /**
     * Created By: Ethan
     * Created On: 10/15/2016
     * Last Modified: 10/15/2016
     *
     * @param value
     */
    public void openNewStageWindow(Parent value)
    {
        Stage myDialog = new Stage();

        Group root = new Group(value);
        Scene scene = new Scene(root);

        myDialog.setScene(scene);
        myDialog.sizeToScene();
        myDialog.show();
    }

    /**
     * Created By: Ethan
     * Created On: 10/15/2016
     * Last Modified: 10/15/2016
     *
     * @param resource
     * @return
     */
    public Parent loadScreen(String resource)
    {
        try
        {
            FXMLLoader myLoader = new FXMLLoader(getClass().getClassLoader().getResource("resources/" + resource));
            return myLoader.load();
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        return null;
    }

    protected Parent loadScreen(String resource, int vehicleID)
    {
        try
        {
            FXMLLoader myLoader = new FXMLLoader(getClass().getClassLoader().getResource("resources/" + resource));
            Parent loadScreen = myLoader.load();
            Controller masterController = myLoader.getController();
            masterController.loadInformation(vehicleID);
            return loadScreen;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }

        return null;
    }

    protected Parent loadScreen(String resource, String customerName)
    {
        try
        {
            FXMLLoader myLoader = new FXMLLoader(getClass().getClassLoader().getResource("resources/" + resource));
            Parent loadScreen = myLoader.load();
            Controller masterController = myLoader.getController();
            masterController.loadInformation(customerName);
            return loadScreen;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }

        return null;
    }

    protected Parent loadScreen(String resource, Client client)
    {
        try
        {
            FXMLLoader myLoader = new FXMLLoader(getClass().getClassLoader().getResource("resources/" + resource));
            Parent loadScreen = myLoader.load();
            Controller masterController = myLoader.getController();
            masterController.loadInformation(client);
            return loadScreen;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }

        return null;
    }

    protected Parent loadScreen(String resource, Client client, Project project)
    {
        try
        {
            FXMLLoader myLoader = new FXMLLoader(getClass().getClassLoader().getResource("resources/" + resource));
            Parent loadScreen = myLoader.load();
            Controller masterController = myLoader.getController();
            masterController.loadInformation(client, project);
            return loadScreen;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }

        return null;
    }
}