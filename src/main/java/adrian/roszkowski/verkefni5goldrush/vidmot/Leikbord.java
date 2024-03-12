package adrian.roszkowski.verkefni5goldrush.vidmot;

import adrian.roszkowski.verkefni5goldrush.vinnsla.Leikur;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Bounds;

import java.io.IOException;
import java.util.Random;

public class Leikbord extends javafx.scene.layout.Pane {

    private Leikur leikur;

    ObservableList<Gull> gullList = FXCollections.observableArrayList();
    int gullCount = 0;

    @FXML
    private Grafari grafarifx_ID;

    public Leikbord(){
        FXMLLoader fxmlLoader = new FXMLLoader(Leikbord.class.getResource("leikbord-view.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    /**
     * Initializes the game state and recreates the player character.
     */
    public void start(){
        getChildren().remove(grafarifx_ID);
        grafarifx_ID = null;
        grafarifx_ID = new Grafari( getBoundsInParent().getCenterX() - getLayoutX(),getBoundsInParent().getCenterY() - getLayoutY());
        getChildren().add(grafarifx_ID);
        leikur = new Leikur();
    }

    public Leikur getLeikur() {
        return leikur;
    }

    public void meiraGull(){
        framleidaGull();
    }

    /**
     * Creates the gold randomly on the game board up to 12.
     */
    private void framleidaGull(){
        if (gullCount == 12) return;
        Bounds bound = this.getBoundsInParent();

        Random random = new Random();

        Gull gull = new Gull(random.nextInt((int) getLayoutX(), (int) bound.getWidth()),
                random.nextInt((int) getLayoutY(), (int) bound.getHeight()));

        getChildren().add(gull);
        gullList.add(gull);
        gullCount++;
    }

    /**
     * Handles the movement and collision calling as the game progresses.
     * @param stefna direction the player is moving.
     */
    void afram(Stefna stefna)
    {
        Bounds bound = this.getBoundsInParent();
        Bounds grafarifBound = grafarifx_ID.getBoundsInParent();
        movement(stefna, bound, grafarifBound);


        //TESTINGMOVEMENT(bound, grafarifBound);


        checkGullCollision();
    }

    /**
     * Handles moving the player and preventing moving outside of the map.
     * @param stefna Direction to move.
     * @param bound The bounds of the map.
     * @param grafarifBound The bounds of the player.
     */
    private void movement(Stefna stefna, Bounds bound, Bounds grafarifBound) {
        switch (stefna) {
            case Left:
                if ((bound.getCenterX() - bound.getWidth() / 2) + grafarifBound.getWidth() / 2
                        >= (grafarifBound.getCenterX() + getLayoutX())) break;
                grafarifx_ID.setX(grafarifx_ID.getX() - 10);
                break;
            case Right:
                if ((bound.getCenterX() + bound.getWidth() / 2) - grafarifBound.getWidth() / 2
                        <= (grafarifBound.getCenterX() + getLayoutX())) break;
                grafarifx_ID.setX(grafarifx_ID.getX() + 10);
                break;
            case Down:
                if ((bound.getCenterY() + bound.getHeight() / 2) - grafarifBound.getHeight() / 2
                        <= (grafarifBound.getCenterY() + getLayoutY())) break;
                grafarifx_ID.setY(grafarifx_ID.getY() + 10);
                break;
            case Up:
                if ((bound.getCenterY() - bound.getHeight() / 2) + grafarifBound.getHeight() / 2
                        >= (grafarifBound.getCenterY() + getLayoutY())) break;
                grafarifx_ID.setY(grafarifx_ID.getY() - 10);
                break;
            default:
                break;
        }
    }

    /**
     * Used for testing purposes to verify collision with the boundaries of the map.
     * @param bound The bounds of the map.
     * @param grafarifBound The bounds of the player.
     */
    private void TESTINGMOVEMENT(Bounds bound, Bounds grafarifBound) {
        System.out.println("Layout x : " + getLayoutX() + " | " + "Max x : " + bound.getMaxX() + " | " +
                "Layout y : " + getLayoutX() + " | " + "Max y : " + bound.getMaxY() + " | " +
                "Width : " + bound.getWidth() + " | " + "Height : " + bound.getHeight() + " | " +
                "Center x : " + bound.getCenterX() + " | " + "Center y : " + bound.getCenterY() + " | " +
                "Grafarif x : " + grafarifBound.getCenterX() + " | " + "Grafarif y : " + grafarifBound.getCenterY() + "\n | " +
                "Grafarif Width : " + grafarifBound.getWidth() + " | " + "Grafarif Height : " + grafarifBound.getHeight() + " | " +
                "Grafari Layout X : " + grafarifx_ID.getLayoutX() + " | " + "Grafari Layout Y : " + grafarifx_ID.getLayoutY()
        );

        System.out.println(
                (getBoundsInParent().getCenterY() + getBoundsInParent().getHeight() / 2) - grafarifx_ID.getBoundsInParent().getHeight() / 2 + " VALE \n"
                + grafarifx_ID.getBoundsInParent().getCenterY() + " VALE \n"
        );
    }

    /**
     * Handles collision with the gold.
     */
    void checkGullCollision(){
        for(Gull gull : gullList)
        {
            if (gull.getBoundsInParent().intersects(grafarifx_ID.getBoundsInParent())){
                gullList.remove(gull);
                getChildren().remove(gull);
                leikur.addScore();
                return;
            }
        }
    }

    /**
     * Removes all remaining gold on the map.
     */
    void removeAllGulls(){
        for(Gull gull : gullList)
        {
            getChildren().remove(gull);
        }
        gullList.clear();
    }



}
