module com.pinson.gridgames {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;
    requires com.almasb.fxgl.all;

    opens com.pinson.gridgames to javafx.fxml;
    exports com.pinson.gridgames;
}