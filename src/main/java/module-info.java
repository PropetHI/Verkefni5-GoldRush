module adrian.roszkowski.verkefni5goldrush {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.base;

    opens adrian.roszkowski.verkefni5goldrush.vidmot to javafx.fxml;
    exports adrian.roszkowski.verkefni5goldrush.vidmot;

    opens adrian.roszkowski.verkefni5goldrush.vinnsla to javafx.fxml;
    exports adrian.roszkowski.verkefni5goldrush.vinnsla;
}