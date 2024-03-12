package adrian.roszkowski.verkefni5goldrush.vidmot;

import javafx.fxml.FXMLLoader;
import javafx.scene.shape.Rectangle;

import java.io.IOException;

public class Grafari extends Rectangle {

    public Grafari(double x, double y) {
        super(x, y, 50, 50);
        FXMLLoader fxmlLoader = new FXMLLoader(Leikbord.class.getResource("grafari-view.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public Grafari() {
        FXMLLoader fxmlLoader = new FXMLLoader(Leikbord.class.getResource("grafari-view.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
