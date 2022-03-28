import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.sql.SQLException;

public class Main extends Application {

    public static Stage window;
    public static Scene mainScene;
    double minWidth = 1150;
    double minHeight = 600;
    PopUpText popUp = new PopUpText();
    private GridPane gridPane;
    static int chosenWeek = 12;


    @Override
    public void start(Stage stage) throws Exception {

        // Starting procedures and Set the Scene
        window = stage;
        VBox root = new VBox();
        root.setAlignment(Pos.CENTER);
        mainScene = new Scene(root, minWidth, minHeight);

        // BorderPane
        BorderPane borderPane = new BorderPane();
        VBox.setVgrow(borderPane, Priority.ALWAYS);
        root.getChildren().add(borderPane);

        // Add GridPane to Center
        borderPane.setCenter(getGridPane());

        // Add Content to GridPane
        addContent(gridPane);

        // Add toolbar to Bottom of BorderPane
        borderPane.setBottom(getToolBar());

        // Initialize Window
        stage.setScene(mainScene);
        stage.setTitle("Animal Shelter Portal");
        stage.show();

    }

    public void addContent(GridPane gridPane) {
        CalendarView cw = new CalendarView();

        gridPane.add(cw.getCalendarView("January"), 0, 0);
        gridPane.add(cw.getCalendarView("February"), 1, 0);
        gridPane.add(cw.getCalendarView("March"), 2, 0);
        gridPane.add(cw.getCalendarView("April"), 3, 0);
        gridPane.add(cw.getCalendarView("May"), 0, 1);
        gridPane.add(cw.getCalendarView("June"), 1, 1);
        gridPane.add(cw.getCalendarView("July"), 2, 1);
        gridPane.add(cw.getCalendarView("August"), 3, 1);
        gridPane.add(cw.getCalendarView("September"), 0, 2);
        gridPane.add(cw.getCalendarView("October"), 1, 2);
        gridPane.add(cw.getCalendarView("November"), 2, 2);
        gridPane.add(cw.getCalendarView("December"), 3, 2);

    }

    public GridPane getGridPane() throws SQLException {
        gridPane = new GridPane();
        gridPane.setPadding(new Insets(10, 10, 10, 10));
        VBox.setMargin(gridPane, new Insets(5, 5, 5, 5));

        final int numCols = 4;
        final int numRows = 3;

        for (int i = 0; i < numCols; i++) {
            ColumnConstraints colConst = new ColumnConstraints();
            colConst.setPercentWidth(100.0 / numCols);
            gridPane.getColumnConstraints().add(colConst);
        }
        for (int i = 0; i < numRows; i++) {
            RowConstraints rowConst = new RowConstraints();
            rowConst.setPercentHeight(100.0 / numRows);
            gridPane.getRowConstraints().add(rowConst);
        }

        return gridPane;
    }

    private ToolBar getToolBar() {

        ToolBar toolBar = new ToolBar();

        // Button for creating a booking
        Button buttonBook = new Button("Book");
        toolBar.getItems().add(buttonBook);

        // Enabling changing scene
         Booking bk = new Booking();
        Scene sceneBooking = new Scene(bk.getBookingView(), minWidth, minHeight);

        // On button click, call this method
        buttonBook.setOnAction(event -> {
            window.setScene(sceneBooking);

        });

        // Button for editing an existing employee
        Button buttonView = new Button("View bookings");
        toolBar.getItems().add(buttonView);
        buttonView.setOnAction(event -> {
            popUp.popText("View bookings", "black", "18", window);
        });

        // Button for deleting an existing employee
        Button buttonProfile = new Button("My animal");
        toolBar.getItems().add(buttonProfile);
        buttonProfile.setOnAction(event -> {
            popUp.popText("My animal", "black", "18", window);
        });

        return toolBar;
    }

}
