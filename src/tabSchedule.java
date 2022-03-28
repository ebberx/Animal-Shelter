import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.util.*;

public class tabSchedule {

    ArrayList<ComboBox<String>> comboBoxArrayList = new ArrayList<>();
    DateGenerator date = new DateGenerator();

    private GridPane gridPane;
    PopUpText popUp = new PopUpText();
    static int chosenWeek = 13;
    static int chosenYear = 2022;
    static String chosenShelter = "EASV Shelter";
    static Label labelMon, labelTue, labelWed, labelThu, labelFri, labelSat, labelSun, title, subTitle;

    public tabSchedule() {

    }

    public VBox getTabSchedule(Tab tab) throws SQLException {



        VBox vBox = new VBox();
        //vBox.setStyle("-fx-background-color: #f5f5f5");
        vBox.setAlignment(Pos.CENTER);
        tab.setContent(vBox);

        title = new Label(chosenShelter);
        title.setStyle("-fx-font-size: 24");

        subTitle = new Label("Week " + chosenWeek);
        subTitle.setStyle("-fx-font-size: 20");

        vBox.getChildren().addAll(title, subTitle);

        BorderPane borderPane = new BorderPane();
        vBox.getChildren().add(borderPane);
        VBox.setVgrow(borderPane, Priority.ALWAYS);

        // Add GridPane to Center
        borderPane.setCenter(getGridPane());

        // Bottom ToolBar for Functions
        ToolBar toolBar = new ToolBar();
        BorderPane.setAlignment(toolBar, Pos.CENTER);
        borderPane.setBottom(toolBar);

        // Button for Printing
        HBox hBoxLeft = new HBox(5);
        HBox.setHgrow(hBoxLeft, Priority.ALWAYS);
        hBoxLeft.setAlignment(Pos.CENTER_LEFT);

        // Button for Saving
        Button buttonSave = new Button("Save");
        buttonSave.setOnAction(event -> {
            popUp.popText("Saved!", "black", "18", Application.stage);
        });

        hBoxLeft.getChildren().addAll(buttonSave);
        toolBar.getItems().add(hBoxLeft);

        // Right side of toolbar with selection options
        HBox hBoxRight = new HBox(5);
        HBox.setHgrow(hBoxRight, Priority.ALWAYS);
        hBoxRight.setAlignment(Pos.CENTER_RIGHT);


        Label labelWeek = new Label("Week: ");
        TextField textFieldWeek = new TextField();
        textFieldWeek.setMaxWidth(50);
        textFieldWeek.setText("13");
        textFieldWeek.setOnAction(event -> {
            chosenWeek = Integer.parseInt(textFieldWeek.getText());
            updateDate();
        });

        Label labelYear = new Label("Year: ");
        TextField textFieldYear = new TextField();
        textFieldYear.setMaxWidth(50);
        textFieldYear.setText("2022");
        textFieldYear.setOnAction(event -> {
            chosenYear = Integer.parseInt(textFieldYear.getText());
            updateDate();
        });

        hBoxRight.getChildren().addAll(labelWeek, textFieldWeek, labelYear, textFieldYear);
        toolBar.getItems().add(hBoxRight);

        return vBox;
    }

    public GridPane getGridPane() throws SQLException {
        gridPane = new GridPane();
        gridPane.setGridLinesVisible(true);
        gridPane.setPadding(new Insets(10, 10, 10, 10));
        VBox.setMargin(gridPane, new Insets(5, 5, 5, 5));

        final int numCols = 8;
        final int numRows = 12;

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

        addCollectedContent(gridPane);

        return gridPane;
    }

    public void addCollectedContent(GridPane gridPane) throws SQLException {

        addDates(gridPane);
        addDays(gridPane);
        addButtons(gridPane);
        addCategoryLabels(gridPane);
        addSpots(gridPane);

    }

    public void addDates(GridPane gridPane) {

        labelMon = new Label(date.getDate(tabSchedule.chosenWeek, 2, tabSchedule.chosenYear));
        gridPane.add(labelMon, 1, 0);
        GridPane.setHalignment(labelMon, HPos.CENTER);

        labelTue = new Label(date.getDate(tabSchedule.chosenWeek, 3, tabSchedule.chosenYear));
        gridPane.add(labelTue, 2, 0);
        GridPane.setHalignment(labelTue, HPos.CENTER);

        labelWed = new Label(date.getDate(tabSchedule.chosenWeek, 4, tabSchedule.chosenYear));
        gridPane.add(labelWed, 3, 0);
        GridPane.setHalignment(labelWed, HPos.CENTER);

        labelThu = new Label(date.getDate(tabSchedule.chosenWeek, 5, tabSchedule.chosenYear));
        gridPane.add(labelThu, 4, 0);
        GridPane.setHalignment(labelThu, HPos.CENTER);

        labelFri = new Label(date.getDate(tabSchedule.chosenWeek, 6, tabSchedule.chosenYear));
        gridPane.add(labelFri, 5, 0);
        GridPane.setHalignment(labelFri, HPos.CENTER);

        labelSat = new Label(date.getDate(tabSchedule.chosenWeek, 7, tabSchedule.chosenYear));
        gridPane.add(labelSat, 6, 0);
        GridPane.setHalignment(labelSat, HPos.CENTER);

        labelSun = new Label(date.getDate(tabSchedule.chosenWeek, 1, tabSchedule.chosenYear));
        gridPane.add(labelSun, 7, 0);
        GridPane.setHalignment(labelSun, HPos.CENTER);

    }

    public void updateDate() {
        labelMon.setText(date.getDate(tabSchedule.chosenWeek, 2, tabSchedule.chosenYear));
        labelTue.setText(date.getDate(tabSchedule.chosenWeek, 3, tabSchedule.chosenYear));
        labelWed.setText(date.getDate(tabSchedule.chosenWeek, 4, tabSchedule.chosenYear));
        labelThu.setText(date.getDate(tabSchedule.chosenWeek, 5, tabSchedule.chosenYear));
        labelFri.setText(date.getDate(tabSchedule.chosenWeek, 6, tabSchedule.chosenYear));
        labelSat.setText(date.getDate(tabSchedule.chosenWeek, 7, tabSchedule.chosenYear));
        labelSun.setText(date.getDate(tabSchedule.chosenWeek, 1, tabSchedule.chosenYear));

        title.setText(chosenShelter);
        subTitle.setText("Uge " + chosenWeek);

        popUp.popText("Week " + chosenWeek + "\nYear " + chosenYear, "black", "18", Application.stage);
    }

    public void addCategoryLabels(GridPane gridPane) {

        Label labelSpot = new Label("Spot");
        GridPane.setHalignment(labelSpot, HPos.CENTER);

        gridPane.add(labelSpot, 0, 1);

    }

    public void addDays(GridPane gridPane) {

        List<Label> weekdays = new ArrayList<>();
        Collections.addAll(weekdays, new Label("Monday"), new Label("Tuesday"), new Label("Wednesday"), new Label("Thursday"),
                new Label("Friday"), new Label("Saturday"), new Label("Sunday"));

        int weekdaysIndex = 0;
        for (int col = 1; col <= gridPane.getColumnConstraints().size() - 1; col++) {

            weekdays.get(weekdaysIndex).setStyle("-fx-font-weight: 800");
            gridPane.add(weekdays.get(weekdaysIndex), col, 1);
            GridPane.setHalignment(weekdays.get(weekdaysIndex), HPos.CENTER);
            weekdaysIndex++;

        }
    }


    public void addButtons(GridPane gridPane) {
        for (int row = 2; row < gridPane.getRowConstraints().size(); row++) {
            for (int col = 1; col < gridPane.getColumnConstraints().size(); col++) {

                Button buttonBook = new Button("Available");
                buttonBook.setStyle("-fx-background-color: null; -fx-cursor: HAND");

                buttonBook.setMaxWidth(Double.MAX_VALUE);
                buttonBook.setMaxHeight(Double.MAX_VALUE);

                buttonBook.setOnAction(event -> {
                    createBooking();
                });
                gridPane.add(buttonBook, col, row);
            }
        }
    }
    public void addSpots(GridPane gridPane) {

        int no = 1;
        for (int row = 2; row < gridPane.getRowConstraints().size(); row++) {

            Label labelSpot = new Label("Spot #" + no++);
            GridPane.setHalignment(labelSpot, HPos.CENTER);
            gridPane.add(labelSpot, 0, row);

        }
    }

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
}
