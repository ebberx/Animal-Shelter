import javafx.geometry.Side;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Application extends javafx.application.Application {

    TopBar topBar = new TopBar();
    tabWeekly tabWeekly = new tabWeekly();
    tabSchedule tabSchedule = new tabSchedule();
    tabBookings tabBookings = new tabBookings();
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

        // Create a tabPane
        TabPane tabPane = new TabPane();
        root.getChildren().add(tabPane);
        tabPane.setSide(Side.TOP);
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);

        // Enables resizing
        VBox.setVgrow(tabPane, Priority.ALWAYS);

        // Creating the tabs
        Tab tab1 = new Tab("Schedule");
        Tab tab2 = new Tab("Bookings");
        Tab tab3 = new Tab("Weekly [PROTOTYPE]");

        // Adding tabs to the tab pane
        tabPane.getTabs().addAll(tab1, tab2, tab3);

        // Fetch Tab for Schedule
        tab1.setContent(tabSchedule.getTabSchedule(tab1));

        // Fetch Tab for Bookings
        tab2.setContent(tabBookings.getTabBookings(tab2));

        // Fetch Tab for Bookings
        tab3.setContent(tabWeekly.getTabWeekly(tab3));

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

