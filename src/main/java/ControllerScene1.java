import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

import java.io.IOException;

public class ControllerScene1 {

    @FXML
    private AnchorPane root_AnchorPane;

    @FXML
    private Button btn_load;

    @FXML
    void handleLoad(ActionEvent event) throws IOException {
        System.out.println("click, load...");

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("scene2.fxml"));
        Parent nextSceneRoot = fxmlLoader.load();
        Scene thisScene = btn_load.getScene();

        nextSceneRoot.translateXProperty().set(thisScene.getWidth());
        StackPane thisSceneParent = (StackPane) thisScene.getRoot();
        thisSceneParent.getChildren().add(nextSceneRoot);

        Timeline transitionTimeline = new Timeline();
        KeyValue kv = new KeyValue(nextSceneRoot.translateXProperty(), 0, Interpolator.EASE_BOTH);
        KeyFrame kf = new KeyFrame(Duration.seconds(0.6), kv);
        transitionTimeline.getKeyFrames().add(kf);
        transitionTimeline.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                thisSceneParent.getChildren().remove(root_AnchorPane);
            }
        });
        transitionTimeline.play();
    }
}
