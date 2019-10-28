package app;

import javafx.beans.binding.Bindings;
import javafx.event.EventTarget;
import javafx.fxml.FXML;
import javafx.scene.effect.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

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
        ((Stage) confirm.getScene().getWindow()).close();
    }

    @FXML
    public void onCheckClicked(MouseEvent mouseEvent) {
        System.exit(0);
    }

}
