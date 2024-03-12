package adrian.roszkowski.verkefni5goldrush.vinnsla;

import javafx.beans.property.SimpleIntegerProperty;

public class Klukka {

    private SimpleIntegerProperty timinn = new SimpleIntegerProperty(0);
    public void tic(){
        setTiminn(getTiminn() - 1);
    }

    public void setTiminn(int timinn) {
        this.timinn.set(timinn);
    }

    public int getTiminn() {
        return timinn.get();
    }

    public SimpleIntegerProperty getTiminnProperty() {
        return timinn;
    }
}
