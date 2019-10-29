package app;

import animatefx.animation.*;
import javafx.event.EventTarget;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.concurrent.atomic.AtomicBoolean;

public class CloseConfirm {

    public class Mouse {
        double x, y;
        boolean primaryDown, secondaryDown;
        Mouse() {
            primaryDown = false;
            secondaryDown = false;
        }
    }

    public class Offset {
        double x, y;
    }

    private Mouse mouse;
    private Offset offset;

    @FXML
    VBox confirm;
    @FXML
    ImageView ex;
    @FXML
    ImageView check;
    @FXML
    public void initialize() {
        mouse = new Mouse();
        offset = new Offset();
        Image exImage = new Image(getClass().getResource("resources/ex.png").toExternalForm());
        Image checkImage = new Image(getClass().getResource("resources/check.png").toExternalForm());
        ex.setImage(new RecoloredImaged(exImage, Color.web("#9F9F9F"), Color.web("#808080")).getImage());
        check.setImage(new RecoloredImaged(checkImage, Color.web("#9F9F9F"), Color.web("#808080")).getImage());
        AnimationFX fx = new BounceIn(confirm);
        fx.setSpeed(1.25D);
        fx.play();
    }

    @FXML
    public void onConfirmPressed(MouseEvent mouseEvent) {
        mouse.x = mouseEvent.getScreenX();
        mouse.y = mouseEvent.getScreenY();
        offset.x = confirm.getScene().getWindow().getX() - mouse.x;
        offset.y = confirm.getScene().getWindow().getY() - mouse.y;
    }

    @FXML
    public void onConfirmDrag(MouseEvent mouseEvent) {
        EventTarget target = mouseEvent.getTarget();
        if (!(target == ex || target == check)) {
            mouse.x = mouseEvent.getScreenX();
            mouse.y = mouseEvent.getScreenY();
            confirm.getScene().getWindow().setX(mouse.x + offset.x);
            confirm.getScene().getWindow().setY(mouse.y + offset.y);
        }
    }

    @FXML
    public void onExClicked(MouseEvent mouseEvent) {
        AnimationFX fx = new BounceOut(confirm);
        fx.setSpeed(1.25D);
        fx.setOnFinished(actionEvent -> ((Stage) confirm.getScene().getWindow()).close());
        fx.play();
    }

    @FXML
    public void onCheckClicked(MouseEvent mouseEvent) {
        AtomicBoolean fxDone = new AtomicBoolean(false);
        AtomicBoolean parentFxDone = new AtomicBoolean(false);
        AnimationFX parentFx = new ZoomOut(((Stage) confirm.getScene().getWindow()).getOwner().getScene().getRoot());
        AnimationFX fx = new BounceOut(confirm);
        parentFx.setSpeed(0.75D);
        fx.setSpeed(1.25D);
        parentFx.setOnFinished(actionEvent -> {
            if(fxDone.get()) System.exit(0);
            parentFxDone.set(true);
        });
        fx.setOnFinished(actionEvent -> {
            if(parentFxDone.get()) System.exit(0);
            fxDone.set(true);
        });
        parentFx.play();
        fx.play();
    }

}
