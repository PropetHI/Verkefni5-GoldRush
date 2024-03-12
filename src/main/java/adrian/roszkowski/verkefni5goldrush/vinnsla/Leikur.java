package adrian.roszkowski.verkefni5goldrush.vinnsla;

import javafx.beans.property.SimpleIntegerProperty;

public class Leikur {

    private SimpleIntegerProperty score;

    public Leikur() {
        score = new SimpleIntegerProperty(0);
    }

    public SimpleIntegerProperty getScoreProperty() {
        return score;
    }

    public void addScore() {
        score.set(score.get() + 1);
    }

}
