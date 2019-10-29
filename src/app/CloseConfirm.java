package app;

import animatefx.animation.*;
import javafx.event.EventTarget;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * CloseConfirm.java - a SettingsGenerator class to handle the close-confirm.fxml file
 */
public class CloseConfirm {

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
     */
    private Mouse mouse;
    private Offset offset;

    // The body of the confirmation dialog
    @FXML
    VBox confirm;
    // The ex image holder
    @FXML
    ImageView ex;
    // The check image holder
    @FXML
    ImageView check;
    // The confirmation label "ARE YOU SURE?"
    @FXML
    Label confirmationText;

    /**
     * Called after all FXML components have been initialized
     */
    @FXML
    public void initialize() {
        // Initialize mouse and offset instance variables
        mouse = new Mouse();
        offset = new Offset();

        // Set the confirm and confirmationText colors
        confirm.setStyle("-fx-background-color: " + Data.Theme.CONFIRM + ";");
        confirmationText.setTextFill(Color.web(Data.Theme.CONFIRM_TEXT));

        // Assigns images for ex and check image holders with recoloring
        Image exImage = new Image(getClass().getResource("resources/ex.png").toExternalForm());
        Image checkImage = new Image(getClass().getResource("resources/check.png").toExternalForm());
        ex.setImage(new RecoloredImage(exImage, Color.web("#9F9F9F"),
                Color.web(Data.Theme.CONFIRM_EX)).getImage());
        check.setImage(new RecoloredImage(checkImage, Color.web("#9F9F9F"),
                Color.web(Data.Theme.CONFIRM_CHECK)).getImage());

        // Initiates an animation for confirmation box appearance
        AnimationFX fx = new BounceIn(confirm);
        fx.setSpeed(1.25D);
        fx.play();
    }

    /**
     * Stores mouse and offset data upon cursor press on the confirmation dialog box
     * @param mouseEvent - a MouseEvent that holds the properties of the calling event
     */
    @FXML
    public void onConfirmPressed(MouseEvent mouseEvent) {
        // Updates mouse data
        mouse.x = mouseEvent.getScreenX();
        mouse.y = mouseEvent.getScreenY();

        // Updates offset data
        offset.x = confirm.getScene().getWindow().getX() - mouse.x;
        offset.y = confirm.getScene().getWindow().getY() - mouse.y;
    }

    /**
     * Moves the confirmation dialog box upon its drag
     * @param mouseEvent - a MouseEvent that holds the properties of the calling event
     */
    @FXML
    public void onConfirmDrag(MouseEvent mouseEvent) {
        // Checks if cursor was not aimed at ex or check
        EventTarget target = mouseEvent.getTarget();
        if (!(target == ex || target == check)) {

            // Updates mouse data
            mouse.x = mouseEvent.getScreenX();
            mouse.y = mouseEvent.getScreenY();

            // Updates confirmation dialog box location based on mouse and offset data
            confirm.getScene().getWindow().setX(mouse.x + offset.x);
            confirm.getScene().getWindow().setY(mouse.y + offset.y);
        }
    }

    /**
     * Handles cancellation of program exit call
     * @param mouseEvent - a MouseEvent that holds the properties of the calling event
     */
    @FXML
    public void onExClicked(MouseEvent mouseEvent) {
        // Animates the closing of the confirmation dialog box
        AnimationFX fx = new BounceOut(confirm);
        fx.setSpeed(1.25D);
        fx.setOnFinished(actionEvent -> ((Stage) confirm.getScene().getWindow()).close());
        fx.play();
    }

    /**
     * Handles confirmation of program exit call
     * @param mouseEvent - a MouseEvent that holds the properties of the calling event
     */
    @FXML
    public void onCheckClicked(MouseEvent mouseEvent) {
        // Stores boolean data on confirmation dialog box and app animation completion
        AtomicBoolean fxDone = new AtomicBoolean(false);
        AtomicBoolean parentFxDone = new AtomicBoolean(false);

        // Animates the closing of the confirmation dialog box and app
        AnimationFX parentFx = new ZoomOut(((Stage) confirm.getScene().getWindow()).getOwner().getScene().getRoot());
        AnimationFX fx = new BounceOut(confirm);
        parentFx.setSpeed(0.75D);
        fx.setSpeed(1.25D);

        // Uses stored boolean data to determine when to call program exit
        parentFx.setOnFinished(actionEvent -> {
            if(fxDone.get()) System.exit(0);
            parentFxDone.set(true);
        });
        fx.setOnFinished(actionEvent -> {
            if(parentFxDone.get()) System.exit(0);
            fxDone.set(true);
        });

        // Starts animations
        parentFx.play();
        fx.play();
    }

}
