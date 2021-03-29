import javafx.animation.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

import java.io.IOException;

public class Controller {

    @FXML
    private StackPane parent_StackPane;

    @FXML
    private AnchorPane root_AnchorPane;

    @FXML
    private Button btn_load;

    public void initialize() {
        System.out.println("initialized");

        btn_load.setOpacity(0.2f);
    }

    @FXML
    void handleLoad(ActionEvent event) throws IOException {
        System.out.println("click, load...");

        // setup next scene and current scene
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("scene1.fxml"));
        Parent nextSceneRoot = fxmlLoader.load();
        Scene thisScene = btn_load.getScene();

        nextSceneRoot.translateYProperty().set(thisScene.getHeight());
        parent_StackPane.getChildren().add(nextSceneRoot);

        // timeline and key-value, key-frame
        Timeline transitionTimeline = new Timeline();
        KeyValue keyValue = new KeyValue(nextSceneRoot.translateYProperty(), 0, Interpolator.EASE_IN);
        KeyFrame keyFrame = new KeyFrame(Duration.seconds(0.6), keyValue);
        transitionTimeline.getKeyFrames().add(keyFrame);
        transitionTimeline.setOnFinished(e -> {
            parent_StackPane.getChildren().remove(root_AnchorPane);
        });
        transitionTimeline.play();
    }

    @FXML
    void handleMouseEnter(MouseEvent event) {
        System.out.println("mouse enter");

        ScaleTransition scaleT = new ScaleTransition(Duration.seconds(0.2), btn_load);
        scaleT.setFromX(1f);
        scaleT.setFromY(1f);
        scaleT.setToX(1.2f);
        scaleT.setToY(1.2f);
        scaleT.setInterpolator(Interpolator.EASE_IN);

        FadeTransition fadeT = new FadeTransition(Duration.seconds(0.2), btn_load);
        fadeT.setFromValue(0.2);
        fadeT.setToValue(1);

        ParallelTransition allTransition = new ParallelTransition(btn_load, scaleT, fadeT);
        allTransition.play();
    }

    @FXML
    void handleMouseExit(MouseEvent event) {
        System.out.println("mouse exit");

        ScaleTransition scaleT = new ScaleTransition(Duration.seconds(0.2), btn_load);
        scaleT.setFromX(1.2f);
        scaleT.setFromY(1.2f);
        scaleT.setToX(1f);
        scaleT.setToY(1f);
        scaleT.setInterpolator(Interpolator.EASE_IN);

        FadeTransition fadeT = new FadeTransition(Duration.seconds(0.2), btn_load);
        fadeT.setFromValue(1);
        fadeT.setToValue(0.2);

        ParallelTransition allTransition = new ParallelTransition(btn_load, scaleT, fadeT);
        allTransition.play();
    }

}