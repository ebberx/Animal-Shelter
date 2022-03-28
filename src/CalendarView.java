import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.*;

public class CalendarView {

    GridPane gridPane;

    public CalendarView() {

    }

    public VBox getCalendarView (String month) {

        // VBox
        VBox vBox = new VBox();
        vBox.setAlignment(Pos.CENTER);

        // BorderPane
        BorderPane borderPane = new BorderPane();
        VBox.setVgrow(borderPane, Priority.ALWAYS);
        vBox.getChildren().add(borderPane);

        // Top of BorderPane is a label
        Label labelMonth = new Label(month);
        borderPane.setTop(labelMonth);

        // Center of BorderPane is a GridPane
        borderPane.setCenter(getGridPane());

        return vBox;
    }

    public GridPane getGridPane() {
        gridPane = new GridPane();
        gridPane.setGridLinesVisible(true);
        gridPane.setPadding(new Insets(10, 10, 10, 10));
        VBox.setMargin(gridPane, new Insets(5, 5, 5, 5));

        final int numCols = 7;
        final int numRows = 4;

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
}
