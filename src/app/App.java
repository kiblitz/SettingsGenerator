package app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * App.java - a class which when initialized and run creates a generator for a settings file
 */
public class App extends Application {

    /**
     * Initializing method for the application
     * @param primaryStage - a Stage which is where the app will take place
     * @throws Exception - in the event that the appropriate fxml file is not found
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        // Loads the fxml file
        Parent root = FXMLLoader.load(getClass().getResource("app.fxml"));

        // Beautifies the program with a transparent background
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        Scene scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT);

        // Finishes stage setup and shows the application
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * Runs the program
     * @param args - a string array which represents terminal arguments
     */
    public void run(String[] args) {
        launch(args);
    }


}
