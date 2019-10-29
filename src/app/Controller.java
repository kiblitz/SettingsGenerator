package app;

import animatefx.animation.*;
import javafx.event.EventTarget;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * Controller.java - a SettingsGenerator class to handle the app.fxml file
 */
public class Controller {

    /**
     * Mouse - a class to track cursor data
     */
    class Mouse {
        /*
         * x, y - doubles that store mouse location
         */
        double x, y;
    }

    /**
     * Offset - a class to track cursor offset from top left of confirm box
     */
    class Offset {
        /*
         * x, y - doubles that store offset location
         */
        double x, y;
    }

    /*
     * mouse - a Mouse for storing cursor data
     * offset - an Offset for storing offset data
     * confirmCloseOpen - a boolean that represents whether the confirm close dialog is open
     */
    private Mouse mouse;
    private Offset offset;
    private boolean confirmCloseOpen;

    // The background of the app
    @FXML
    VBox background;
    // The title bar of the app
    @FXML
    HBox titleBar;
    // The body of the app
    @FXML
    AnchorPane content;

    // The back image holder
    @FXML
    ImageView back;
    // The forward image holder
    @FXML
    ImageView forward;
    // The download image holder
    @FXML
    ImageView download;
    // The minimize image holder
    @FXML
    ImageView minimize;
    // The close image holder
    @FXML
    ImageView close;

    /**
     * Called after all FXML components have been initialized
     */
    @FXML
    public void initialize() {
        // Initialize mouse, offset, and confirmCloseOpen instance variables
        mouse = new Mouse();
        offset = new Offset();
        confirmCloseOpen = false;

        // Set the titleBar and content colors
        titleBar.setStyle("-fx-background-color: " + Data.Theme.TITLE_BAR + ";");
        content.setStyle("-fx-background-color: " + Data.Theme.BODY + ";");

        // Assigns images for title bar image holders with recoloring
        Image closeImage = new Image(getClass().getResource("resources/close.png").toExternalForm());
        Image minimizeImage = new Image(getClass().getResource("resources/minimize.png").toExternalForm());
        Image uploadImage = new Image(getClass().getResource("resources/download.png").toExternalForm());
        Image forwardImage = new Image(getClass().getResource("resources/right.png").toExternalForm());
        Image backImage = new Image(getClass().getResource("resources/left.png").toExternalForm());
        close.setImage(new RecoloredImage(closeImage, Color.web("#000000"), Color.web(Data.Theme.TITLE_BAR_CLOSE)).getImage());
        minimize.setImage(new RecoloredImage(minimizeImage, Color.web("#000000"), Color.web(Data.Theme.TITLE_BAR_MINIMIZE)).getImage());
        download.setImage(new RecoloredImage(uploadImage, Color.web("#000000"), Color.web(Data.Theme.TITLE_BAR_DOWNLOAD)).getImage());
        forward.setImage(new RecoloredImage(forwardImage, Color.web("#000000"), Color.web(Data.Theme.TITLE_BAR_FORWARD)).getImage());
        back.setImage(new RecoloredImage(backImage, Color.web("#000000"), Color.web(Data.Theme.TITLE_BAR_BACK)).getImage());

        // Initiates an animation for app start
        AnimationFX fx = new FadeIn(background);
        fx.play();
    }

    /**
     * Stores mouse and offset data upon cursor press on the title bar
     * @param mouseEvent - a MouseEvent that holds the properties of the calling event
     */
    @FXML
    public void onTitlePressed(MouseEvent mouseEvent) {
        // Updates mouse data
        mouse.x = mouseEvent.getScreenX();
        mouse.y = mouseEvent.getScreenY();

        // Updates offset data
        offset.x = background.getScene().getWindow().getX() - mouse.x;
        offset.y = background.getScene().getWindow().getY() - mouse.y;
    }

    /**
     * Moves the program window upon the title bar's drag
     * @param mouseEvent - a MouseEvent that holds the properties of the calling event
     */
    @FXML
    public void onTitleDrag(MouseEvent mouseEvent) {
        // Checks if cursor was not aimed at a title bar button
        EventTarget target = mouseEvent.getTarget();
        if (!(target == back || target == forward || target == download || target == minimize || target == close)) {

            // Updates mouse data
            mouse.x = mouseEvent.getScreenX();
            mouse.y = mouseEvent.getScreenY();

            // Updates program window location based on mouse and offset data
            background.getScene().getWindow().setX(mouse.x + offset.x);
            background.getScene().getWindow().setY(mouse.y + offset.y);
        }
    }

    @FXML
    public void onBackClicked(MouseEvent mouseEvent) {

    }

    @FXML
    public void onForwardClicked(MouseEvent mouseEvent) {

    }

    @FXML
    public void onDownloadClicked(MouseEvent mouseEvent) {

    }

    /**
     * Minimizes the program window upon the minimize button being clicked
     * @param mouseEvent - a MouseEvent that holds the properties of the calling event
     */
    @FXML
    public void onMinimizeClicked(MouseEvent mouseEvent) {
        Stage stage = (Stage) background.getScene().getWindow();
        stage.setIconified(true);
    }

    /**
     * Calls the close confirmation dialog box to open upon the close button being clicked
     * @param mouseEvent - a MouseEvent that holds the properties of the calling event
     */
    @FXML
    public void onCloseClicked(MouseEvent mouseEvent) {
        //Checks if the close confirmation dialog box is already open
        if(!confirmCloseOpen)
            try {
                // Loads the fxml file
                Stage primaryStage = (Stage) background.getScene().getWindow();
                Parent root = FXMLLoader.load(getClass().getResource("close-confirm.fxml"));
                Stage stage = new Stage();

                // Beautifies the program with a transparent background
                stage.initStyle(StageStyle.TRANSPARENT);
                Scene scene = new Scene(root);
                scene.setFill(Color.TRANSPARENT);

                // Finishes stage setup which sets confirmCloseOpen to true now and to false upon the stage's exit
                stage.setScene(scene);
                stage.initOwner(primaryStage);
                stage.setOnHidden(eventHandler -> confirmCloseOpen = false);
                confirmCloseOpen = true;

                // Shows the confirmation dialog box
                stage.show();
            } catch(Exception e) {
                // Print the stack trace of the exception and exit
                e.printStackTrace();
                System.exit(0);
            }
    }

}

