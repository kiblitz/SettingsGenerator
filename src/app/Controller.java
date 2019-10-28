package app;

import animatefx.animation.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventTarget;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Controller {

    public class Mouse {
        double x, y;
        boolean primaryDown, secondaryDown;
        Mouse() {
            primaryDown = false;
            secondaryDown = false;
        }
    }

    public class TitleButtons {
        boolean back, forward, upload, minimize, close;
        TitleButtons() {
            back = false;
            forward = false;
            upload = false;
            minimize = false;
            close = false;
        }
        public boolean anyPressed() {
            return back && forward && upload && minimize && close;
        }
    }

    public class Offset {
        double x, y;
    }

    private Mouse mouse;
    private Offset offset;
    private TitleButtons titleButtons;

    public Mouse getMouse() {
        return mouse;
    }

    @FXML
    public void initialize() {
        mouse = new Mouse();
        offset = new Offset();
        titleButtons = new TitleButtons();
    }

    @FXML
    VBox background;
    @FXML
    HBox titleBar;
    @FXML
    Region gap;
    @FXML
    AnchorPane content;

    @FXML
    ImageView back;
    @FXML
    ImageView forward;
    @FXML
    ImageView upload;
    @FXML
    ImageView minimize;
    @FXML
    ImageView close;

    @FXML
    public void onTitlePressed(MouseEvent mouseEvent) {
        mouse.x = mouseEvent.getScreenX();
        mouse.y = mouseEvent.getScreenY();
        offset.x = background.getScene().getWindow().getX() - mouse.x;
        offset.y = background.getScene().getWindow().getY() - mouse.y;
    }

    @FXML
    public void onTitleDrag(MouseEvent mouseEvent) {
        EventTarget target = mouseEvent.getTarget();
        if (!(target == back || target == forward || target == upload || target == minimize || target == close)) {
            mouse.x = mouseEvent.getScreenX();
            mouse.y = mouseEvent.getScreenY();
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
    public void onUploadClicked(MouseEvent mouseEvent) {

    }

    @FXML
    public void onMinimizeClicked(MouseEvent mouseEvent) {
        ((Stage) background.getScene().getWindow()).setIconified(true);
    }

    @FXML
    public void onCloseClicked(MouseEvent mouseEvent) {
        System.exit(0);
    }

}

