module es.ieslavereda.tortuga {
    requires javafx.controls;
    requires javafx.fxml;

    opens es.ieslavereda.tortuga to javafx.fxml;
    exports es.ieslavereda.tortuga;
}