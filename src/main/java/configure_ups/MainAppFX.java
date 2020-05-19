package configure_ups;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainAppFX extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(MainAppFX.class.getResource("/first_screen.fxml"));

    Parent root = loader.load();

    primaryStage.setTitle("configure_ups");
    primaryStage.setScene(new Scene(root, 1055, 750));
    primaryStage.show();


    Controller controller = loader.getController();
    controller.setMain(this);
}


    public static void main(String[] args) {
        launch(args);
    }
    }

