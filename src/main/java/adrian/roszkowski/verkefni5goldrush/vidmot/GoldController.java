package adrian.roszkowski.verkefni5goldrush.vidmot;

import adrian.roszkowski.verkefni5goldrush.vinnsla.Difficulty;
import adrian.roszkowski.verkefni5goldrush.vinnsla.Klukka;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.DoubleBinding;
import javafx.beans.binding.StringBinding;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.util.Duration;

import java.util.HashMap;

public class GoldController {
    @FXML
    private MenuController menuStyringController;
    @FXML
    private Leikbord LeikbordView_ID;
    @FXML
    private Label Timefx_ID;
    @FXML
    private Label Scorefx_ID;

    private Difficulty difficulty = Difficulty.MEDIUM;
    Klukka klukka = new Klukka();
    private final HashMap<KeyCode, Stefna> keyMap = new HashMap<>();

    Timeline[] timelines = new Timeline[2];

    boolean isGameRunning = false;

    /**
     * Handles key presses for movement
     */
    EventHandler<KeyEvent> keyEventPressed_Movement = event -> {
        try{
            LeikbordView_ID.afram(keyMap.get(event.getCode()));
        }
        catch (Exception e){
            return;
        }
    };

    public void initialize() {
        menuStyringController.setGoldController(this);
    }

    /**
     * Sets the difficulty based on the user's choice.
     * @param _difficulty The integer representing difficulty levels, higher is harder.
     */
    public void setDifficulty(int _difficulty) {
        switch (_difficulty) {
            case 1:
                System.out.println("Easy");
                difficulty = Difficulty.EASY;
                break;
            case 2:
                System.out.println("Medium");
                difficulty = Difficulty.MEDIUM;
                break;
            case 3:
                System.out.println("Hard");
                difficulty = Difficulty.HARD;
                break;
        }
    }


    /**
     * Establishes the key mapping for movement and adds an event filter for them.
     */
    void orvatakkar(){
        keyMap.put(KeyCode.LEFT, Stefna.Left);
        keyMap.put(KeyCode.RIGHT, Stefna.Right);
        keyMap.put(KeyCode.DOWN, Stefna.Down);
        keyMap.put(KeyCode.UP, Stefna.Up);

        LeikbordView_ID.getScene().addEventFilter(KeyEvent.ANY, keyEventPressed_Movement);
    }

    /**
     * Calls all the methods responsible for establishing the beginning state of the game.
     */
    public void beginGame(){
        gameOver();

        orvatakkar();
        raesaKlukku();
        LeikbordView_ID.start();
        goldSpawning();
        ScoreUpdate();
    }

    /**
     * Sets up the score update and adds an event filter for the winning condition.
     */
    private void ScoreUpdate() {
        Scorefx_ID.setText(" | Score: 0");
        LeikbordView_ID.getLeikur().getScoreProperty().addListener((observable, oldValue, newValue) -> {
            Scorefx_ID.setText(" | Score: " + newValue);
            if (newValue.equals(12)) {
                gameOver();
                gameVictoryDialog();
            }
        });
    }

    /**
     * Establishes a timeline that spawns gold every 2.5 seconds.
     */
    private void goldSpawning() {
        KeyFrame keyFrame_Gull = new KeyFrame(Duration.seconds(2.5), event -> {
            LeikbordView_ID.meiraGull();
        });

        Timeline timeline_Gull = new Timeline(keyFrame_Gull);
        timeline_Gull.setCycleCount(difficulty.getTime() + 1);
        timeline_Gull.play();
        timelines[0] = timeline_Gull;
        LeikbordView_ID.meiraGull();
    }

    /**
     * Removes the movement event filter and calls the gold removing method.
     * Also stops any still running timelines.
     */
    public void gameOver(){
        LeikbordView_ID.getScene().removeEventFilter(KeyEvent.ANY, keyEventPressed_Movement);
        LeikbordView_ID.removeAllGulls();

        for (Timeline timeline : timelines) {
            if(timeline != null){
                timeline.stop();
            }
        }
    }


    /**
     * Establishes the clock and begins running it down to 0.
     */
    void raesaKlukku(){
        klukka = new Klukka();
        klukka.setTiminn(difficulty.getTime());
        KeyFrame keyFrame = new KeyFrame(Duration.seconds(1), event -> {

            if(klukka.getTiminn() == 0){
                gameOver();

                gameOverDialog();
                return;
            }
            klukka.tic();
        });

        klukka.getTiminnProperty().addListener((observable, oldValue, newValue) -> Timefx_ID.setText("Time: " + newValue));
        Timeline timeline = new Timeline(keyFrame);
        timeline.setCycleCount(difficulty.getTime() + 1);

        timeline.play();
        timelines[1] = timeline;
    }

    /**
     * Dialog to inform the player of the game ending.
     */
    private void gameOverDialog() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("The game is over. Your final score was "
                + LeikbordView_ID.getLeikur().getScoreProperty().get());
        alert.show();
    }

    /**
     * Dialog informing the player they won by getting all possible gold.
     */
    private void gameVictoryDialog() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("You won! Your final score was "
                + LeikbordView_ID.getLeikur().getScoreProperty().get());
        alert.show();
    }

}