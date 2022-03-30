import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Application extends javafx.application.Application {

    TopBar topBar = new TopBar();
    Overview overview = new Overview();
    public static Stage stage;
    public static Scene applicationScene;

    public double minWidth = 1150;
    public double minHeight = 600;


    @Override
    public void start(Stage stage) throws Exception {
        // Starting procedures and Set the Scene
        this.stage = stage;
        VBox root = new VBox();
        applicationScene = new Scene(root, minWidth, minHeight);

        // Add MenuBar to top of application
        root.getChildren().add(topBar.getMenus());

        // Create a borderPane
        BorderPane borderPane = new BorderPane();
        root.getChildren().add(borderPane);

        // Enables resizing
        VBox.setVgrow(borderPane, Priority.ALWAYS);

        // Fetch Overview
        borderPane.setCenter(overview.getOverview());

        // Rules
        stage.setMinWidth(minWidth);
        stage.setMinHeight(minHeight);

        // Initialize Window
        //stage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/icon.png"))));
        stage.setScene(applicationScene);
        stage.setTitle("Animal Shelter Portal");
        stage.show();
    }
}

