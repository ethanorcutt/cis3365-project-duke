package simple;

import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javax.swing.*;

public class Main extends Application
{
    @Override
    public void start(Stage primaryStage)
    {
        ScreenLoader temp = new ScreenLoader()
        {
            @Override
            protected Parent loadScreen(String name, String resource)
            {
                return super.loadScreen(name, resource);
            }
        };

        Group root = new Group(temp.loadScreen("mainApplication", "mainApplication.fxml"));
        Scene scene = new Scene(root);

        primaryStage.setTitle("Nicholson Plumbing");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.sizeToScene();
        primaryStage.show();
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. IntelliJ ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        try
        {
            launch(args);
        }
        catch (Throwable t)
        {
            JOptionPane.showMessageDialog(null, t.getClass().getSimpleName() + ": " + t.getMessage());
            throw t; // don't suppress Throwable
        }

        /*try
        {
            new Data_Importer();
        } catch (Exception e)
        {
            System.out.println("Broke at main" + "\n" + e);
        }*/
    }
}