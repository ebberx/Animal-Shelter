import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.*;

public class Booking {

    GridPane gridPane;
    PopUpText popUp = new PopUpText();

    public Booking() {

    }

    public VBox getBookingView () {

        // VBox
        VBox vBox = new VBox();
        vBox.setAlignment(Pos.CENTER);
        
        // Set a label as title
        Label labelMonth = new Label("Booking");
        labelMonth.setStyle("-fx-font-size: 22");
        vBox.getChildren().add(labelMonth);

        // BorderPane
        BorderPane borderPane = new BorderPane();
        VBox.setVgrow(borderPane, Priority.ALWAYS);
        vBox.getChildren().add(borderPane);

        // Center of BorderPane is a GridPane
        borderPane.setCenter(getGridPane());

        // Bottom of Borderpane is a Toolbar
        borderPane.setBottom(getToolBar());

        return vBox;
    }

    public GridPane getGridPane() {
        gridPane = new GridPane();
        gridPane.setGridLinesVisible(true);
        gridPane.setPadding(new Insets(10, 10, 10, 10));
        VBox.setMargin(gridPane, new Insets(5, 5, 5, 5));

        final int numCols = 2;
        final int numRows = 6;

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

        // Button for going back to Main scene
        Button buttonBack = new Button("Back");
        toolBar.getItems().add(buttonBack);
        buttonBack.setOnAction(event -> {
            Main.window.setScene(Main.mainScene);
        });

        return toolBar;
    }
}
