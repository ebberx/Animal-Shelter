import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.print.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.transform.Scale;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.util.Objects;

public class tabBookings {

    public tabBookings() {

    }

    /**
     * This is the default view of the Employee Tab. It consists of a BorderPane wrapped in a Vbox.
     */
    public VBox getTabBookings(Tab tab) throws SQLException {

        VBox vBox = new VBox();
        tab.setContent(vBox);

        // Creating a BorderPane for organizing content
        BorderPane borderPane = new BorderPane();
        vBox.getChildren().add(borderPane);
        VBox.setVgrow(borderPane, Priority.ALWAYS);

        // Fetch content from methods
        borderPane.setCenter(getTableView());

        borderPane.setBottom(getToolBar());

        return vBox;
    }

    /**
     * TableView of 6 columns for viewing information on employees.
     */
    private TableView getTableView() throws SQLException {


        TableView tableView = new TableView();

        TableColumn<String, String> column1 = new TableColumn<>("Spots");
        column1.setMinWidth(100);

        TableColumn<String, String> column2 = new TableColumn<>("Name of Customer");
        column2.setMinWidth(200);

        TableColumn<String, String> column3 = new TableColumn<>("Name of Pet");
        column3.setMinWidth(200);

        TableColumn<String, String> column4 = new TableColumn<>("Date of Arrival");
        column4.setMinWidth(200);

        TableColumn<String, String> column5 = new TableColumn<>("Date of Depature");
        column5.setMinWidth(200);

        tableView.getColumns().addAll(column1, column2, column3, column4, column5);

        ObservableList<String> bookings = DB.getAllBookings();

        for (int i = 0; i < bookings.size(); i++) {

            tableView.getItems().add(bookings.get(i));

        }

        return tableView;

    }

    /**
     * ToolBar consisting of three buttons for manipulating a TableView.
     */
    private ToolBar getToolBar() {

        ToolBar toolBar = new ToolBar();

        // Button for creating a new employee
        Button buttonNewBooking = new Button("New");
        toolBar.getItems().add(buttonNewBooking);

        // On button click, call this method
        buttonNewBooking.setOnAction(event -> {
            createBooking();
        });

        // Button for editing an existing employee
        Button buttonEdit = new Button("Edit");
        toolBar.getItems().add(buttonEdit);

        // Button for deleting an existing employee
        Button buttonDelete = new Button("Delete");
        toolBar.getItems().add(buttonDelete);

        return toolBar;
    }

    /**
     * Event for when user presses buttonNewEmployee from ToolBar. Opens a Dialog.
     */
    private void createBooking() {

        final double dialogWidth = 450;
        final double dialogHeight = 400;

        // Starting procedures and Set the Scene
        final Stage dialog = new Stage();
        dialog.setResizable(false);
        dialog.setTitle("New booking");
        VBox vBoxDialog = new VBox(5);
        //dialog.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/icon.png"))));
        Scene dialogScene = new Scene(vBoxDialog, dialogWidth, dialogHeight);

        // Disables the option to use main window when dialog is active
        dialog.initModality(Modality.APPLICATION_MODAL);

        // Add BorderPane
        BorderPane borderPane = new BorderPane();
        VBox.setVgrow(borderPane, Priority.ALWAYS);
        vBoxDialog.getChildren().add(borderPane);

        // Add GridPane to Center
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(0,0,0,10));  // padding 10 left side
        borderPane.setCenter(gridPane);
        HBox.setHgrow(gridPane, Priority.ALWAYS);
        VBox.setMargin(gridPane, new Insets(5, 5, 5, 5));

        // Dimensions of GridPane
        final int numCols = 2 ;
        final int numRows = 6 ;

        // Sets the width of the first column
        ColumnConstraints colConst0 = new ColumnConstraints(150);
        gridPane.getColumnConstraints().add(colConst0);

        // Sets the width of the second column
        ColumnConstraints colConst1 = new ColumnConstraints(250);
        gridPane.getColumnConstraints().add(colConst1);

        // Sets the height of all rows
        for (int i = 0; i < numRows-2; i++) {
            RowConstraints rowConst = new RowConstraints(40);
            gridPane.getRowConstraints().add(rowConst);
        }
        for (int i = numRows-2; i < numRows; i++) {
            RowConstraints rowConst = new RowConstraints(80);
            gridPane.getRowConstraints().add(rowConst);
        }

        // Creating Labels for GridPane
        Label labelName = new Label("Name of Customer:");
        Label labelPetName = new Label("Name of Pet:");
        Label labelArea = new Label("Area:");
        Label labelDoA = new Label("Date of Arrival:");
        Label labelDoD = new Label("Date of Depature:");
        Label labelNotes = new Label("Notes:");

        // Adding Labels to Gridpane
        gridPane.add(labelName, 0, 0);
        gridPane.add(labelPetName, 0, 1);
        gridPane.add(labelArea, 0, 2);
        gridPane.add(labelDoA, 0, 3);
        gridPane.add(labelDoD, 0, 4);
        gridPane.add(labelNotes, 0, 5);

        // Adding Selection Options
        TextField name = new TextField();
        name.setMaxWidth(Double.MAX_VALUE);
        gridPane.add(name, 1, 0);

        TextField lastName = new TextField();
        lastName.setMaxWidth(Double.MAX_VALUE);
        gridPane.add(lastName, 1, 1);

        ChoiceBox<String> department = new ChoiceBox<>();
        department.setMaxWidth(Double.MAX_VALUE);
        gridPane.add(department, 1, 2);

        ChoiceBox<String> function = new ChoiceBox<>();
        function.setMaxWidth(Double.MAX_VALUE);
        gridPane.add(function, 1, 3);

        ChoiceBox<String> contract = new ChoiceBox<>();
        contract.setMaxWidth(Double.MAX_VALUE);
        gridPane.add(contract, 1, 4);

        TextArea comments = new TextArea();
        comments.setMaxWidth(Double.MAX_VALUE);
        gridPane.add(comments, 1, 5);

        // Add ToolBar to Bottom of Dialog
        ToolBar toolBar = new ToolBar();
        borderPane.setBottom(toolBar);

        // Create HBox for alignment of Buttons
        HBox hBoxSaveAndExit = new HBox();
        toolBar.getItems().add(hBoxSaveAndExit);
        hBoxSaveAndExit.setAlignment(Pos.CENTER);
        hBoxSaveAndExit.setSpacing(8);

        // Add Save Button (saves to DB)
        Button buttonSaveAndExit = new Button("Save and exit!");
        hBoxSaveAndExit.getChildren().add(buttonSaveAndExit);

        // On button click, call this method
        buttonSaveAndExit.setOnAction(event -> {
            // TODO SOME COOL DB STUFF
            PopUpText popUp = new PopUpText();
            dialog.close();
            popUp.popText("Booking saved!", "black", "18", Application.stage);
            //saveNewEmployee();
        });

        // Add cancel button
        Button buttonExit = new Button("Annuller");
        hBoxSaveAndExit.getChildren().add(buttonExit);
        buttonExit.setOnAction(event -> {
            dialog.close();
        });

        // Initialize Window
        dialog.setScene(dialogScene);
        dialog.show();
    }

    public void saveNewEmployee() {
        // TODO THIS IS WHERE DB STUFF HAPPENS
    }
}
