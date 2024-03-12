package adrian.roszkowski.verkefni5goldrush.vidmot;

import javafx.fxml.FXMLLoader;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

import java.io.IOException;

public class Gull extends Circle {

    public Gull (int x, int y) {
        super(x, y, 20);
        setFill(Paint.valueOf("gold"));
        setStroke(Paint.valueOf("black"));
        FXMLLoader fxmlLoader = new FXMLLoader(Leikbord.class.getResource("gull-view.fxml"));
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
