import javafx.scene.Scene;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Objects;

public class Application extends javafx.application.Application {

    Overview overview = new Overview();
    public static BorderPane borderPane;
    public static Stage stage;
    public static Scene applicationScene;

    public double minWidth = 1150;
    public double minHeight = 600;

    @Override
    public void start(Stage stage) throws Exception {

        // Starting procedures and Set the Scene
        Application.stage = stage;
        VBox root = new VBox();
        applicationScene = new Scene(root, minWidth, minHeight);

        // Create a borderPane
        borderPane = new BorderPane();
        root.getChildren().add(borderPane);

        // Enables resizing
        VBox.setVgrow(borderPane, Priority.ALWAYS);

        // Fetch Overview
        borderPane.setCenter(overview.getOverview());

        // Initialize Window
        stage.setMinWidth(minWidth);
        stage.setMinHeight(minHeight);
        stage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/icon.png"))));
        stage.setScene(applicationScene);
        stage.setTitle("EASV Animal Shelter");
        stage.show();
    }
}

