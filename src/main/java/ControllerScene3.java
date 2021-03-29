import javafx.animation.ScaleTransition;
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

public class ControllerScene3 {

    @FXML
    private AnchorPane root_AnchorPane;

    @FXML
    private Button btn_load;

    @FXML
    void handleLoad(ActionEvent event) throws IOException {
        System.out.println("click, load...");

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("sample.fxml"));
        Parent nextScene = fxmlLoader.load();
        Scene currentScene = btn_load.getScene();

        StackPane currentParent = (StackPane) currentScene.getRoot();
        currentParent.getChildren().add(nextScene);

        ScaleTransition st = new ScaleTransition(Duration.seconds(1), nextScene);
        st.setFromX(0f);
        st.setFromY(0f);
        st.setToX(1f);
        st.setToY(1f);
        st.setOnFinished(e -> {
            currentParent.getChildren().remove(root_AnchorPane);
        });
        st.play();

    }
}
