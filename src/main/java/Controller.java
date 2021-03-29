import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
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
    void handleMouseOver(MouseEvent event) {
        System.out.println("mouse enter");
//        btn_check.setStyle("-fx-background-color: white");
    }

}