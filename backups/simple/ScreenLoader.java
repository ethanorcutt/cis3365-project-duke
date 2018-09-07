package simple;

import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Created by Ethan Orcutt on 10/15/2016.
 */
abstract class ScreenLoader
{
    /**
     * Created By: Ethan
     * Created On: 10/15/2016
     * Last Modified: 10/15/2016
     *
     * @param value
     */
    protected void openNewStageWindow(Parent value)
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
     * @param name
     * @param resource
     * @return
     */
    protected Parent loadScreen(String name, String resource)
    {
        try
        {
            FXMLLoader myLoader = new FXMLLoader(getClass().getClassLoader().getResource("resources/" + resource));
            Parent loadScreen = myLoader.load();
            return loadScreen;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }

        return null;
    }
}
