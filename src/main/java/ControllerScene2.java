import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

import java.io.IOException;

public class ControllerScene2 {

    @FXML
    private AnchorPane root_AnchorPane;

    @FXML
    private Button btn_load;

    @FXML
    void handleLoad(ActionEvent event) throws IOException {
        System.out.println("click, load...");

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("scene3.fxml"));
        Parent nextScene = fxmlLoader.load();
        Scene currentScene = btn_load.getScene();

        StackPane currentParent = (StackPane) currentScene.getRoot();
        currentParent.getChildren().add(nextScene);

        FadeTransition ft = new FadeTransition(Duration.seconds(1), nextScene);
        ft.setFromValue(0);
        ft.setToValue(1);
        ft.setOnFinished(e -> {
            currentParent.getChildren().remove(root_AnchorPane);
        });
        ft.play();

    }
}
