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
    private boolean confirmCloseOpen = false;

    public Mouse getMouse() {
        return mouse;
    }

    @FXML
    public void initialize() {
        mouse = new Mouse();
        offset = new Offset();
        titleButtons = new TitleButtons();
        Image closeImage = new Image(getClass().getResource("resources/close.png").toExternalForm());
        Image minimizeImage = new Image(getClass().getResource("resources/minimize.png").toExternalForm());
        Image uploadImage = new Image(getClass().getResource("resources/download.png").toExternalForm());
        Image forwardImage = new Image(getClass().getResource("resources/right.png").toExternalForm());
        Image backImage = new Image(getClass().getResource("resources/left.png").toExternalForm());
        close.setImage(new RecoloredImaged(closeImage, Color.web("#000000"), Color.web("#9f9f9f")).getImage());
        minimize.setImage(new RecoloredImaged(minimizeImage, Color.web("#000000"), Color.web("#9f9f9f")).getImage());
        upload.setImage(new RecoloredImaged(uploadImage, Color.web("#000000"), Color.web("#9f9f9f")).getImage());
        forward.setImage(new RecoloredImaged(forwardImage, Color.web("#000000"), Color.web("#9f9f9f")).getImage());
        back.setImage(new RecoloredImaged(backImage, Color.web("#000000"), Color.web("#9f9f9f")).getImage());
        AnimationFX fx = new FadeIn(background);
        fx.play();
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
        Stage stage = (Stage) background.getScene().getWindow();
        stage.setIconified(true);
    }

    @FXML
    public void onCloseClicked(MouseEvent mouseEvent) {
        if(!confirmCloseOpen)
            try {
                Stage primaryStage = (Stage) background.getScene().getWindow();
                Parent root = FXMLLoader.load(getClass().getResource("close-confirm.fxml"));
                Stage stage = new Stage();
                stage.initStyle(StageStyle.TRANSPARENT);
                Scene scene = new Scene(root);
                scene.setFill(Color.TRANSPARENT);
                stage.setScene(scene);
                stage.initOwner(primaryStage);
                stage.setOnHidden(eventHandler -> confirmCloseOpen = false);
                confirmCloseOpen = true;
                stage.show();
            } catch(Exception e) {
                e.printStackTrace();
                System.exit(0);
            }
    }

}

