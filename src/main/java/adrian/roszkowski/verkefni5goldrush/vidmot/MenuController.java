package adrian.roszkowski.verkefni5goldrush.vidmot;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class MenuController extends MenuBar {


    @FXML
    private RadioMenuItem difficulty_1;
    @FXML
    private RadioMenuItem difficulty_2;
    @FXML
    private RadioMenuItem difficulty_3;


    @FXML
    GoldController goldController;

    public void initialize() {
    }

    @FXML
    protected void OnStart(ActionEvent event) {
        goldController.beginGame();
    }

    @FXML
    protected void OnClose(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.showAndWait()
                .filter(response -> response == ButtonType.OK)
                .ifPresent(response -> Platform.exit());
    }

    /**
     * Sets the difficulty based on the players choice.
     * @param event The difficulty chosen
     */
    @FXML
    protected void OnDifficulty(ActionEvent event) {
        if (event.getSource().equals(difficulty_1)) {
            goldController.setDifficulty(1);
        } else if (event.getSource().equals(difficulty_2)) {
            goldController.setDifficulty(2);
        } else if (event.getSource().equals(difficulty_3)) {
            goldController.setDifficulty(3);
        }
    }

    /**
     * Creates a dialog with basic information about the game.
     * @param event is not used
     */
    @FXML
    protected void OnAbout(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("This is a small Gold Collecting game made with Javafx for the class HBV201G.\n" +
                "Made by Adrian Roszkowski in 2024/03.");
        alert.showAndWait()
                .filter(response -> response == ButtonType.OK)
                .ifPresent(response -> alert.close());
    }


    public void setGoldController(GoldController goldController) {
        this.goldController = goldController;
    }
}
