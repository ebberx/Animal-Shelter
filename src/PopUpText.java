import javafx.animation.PauseTransition;
import javafx.scene.control.Label;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.util.Duration;

public class PopUpText {

    public PopUpText() {

    }

    public void popText(String text, String color, String size, Stage stage) {
        try {
            Popup popup = new Popup();
            popup.setAutoHide(true);
            popup.setAutoFix(true);
            Label popupLabel = new Label(text);
            popupLabel.setStyle("-fx-background-color:white;"
                    + " -fx-text-fill: " + color + ";"
                    + " -fx-font-size: " + size + ";"
                    + " -fx-padding: 10px;"
                    + " -fx-background-radius: 6;");
            popup.getContent().add(popupLabel);

            PauseTransition delay = new PauseTransition(Duration.seconds(1));
            delay.setOnFinished(e -> popup.hide());

            popup.show(stage);
            delay.play();

        } catch (Exception e) {

        }
    }
}
