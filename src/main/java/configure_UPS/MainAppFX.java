package configure_UPS;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainAppFX extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(MainAppFX.class.getResource("/first screen.fxml"));

    Parent root = loader.load();

<<<<<<< HEAD:src/main/java/configure_UPS/MainAppFX.java
    primaryStage.setTitle("configure_UPS");
    primaryStage.setScene(new Scene(root, 600, 650));
=======
    primaryStage.setTitle("configure_ups");
    primaryStage.setScene(new Scene(root, 1050, 750));
>>>>>>> 52751f5... first screen with large design:src/main/java/configure_ups/MainAppFX.java
    primaryStage.show();


    Controller controller = loader.getController();
    controller.setMain(this);
}


    public static void main(String[] args) {
        launch(args);
    }
    }

