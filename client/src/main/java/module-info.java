module utfpr.cc66c.client {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens utfpr.cc66c.client to javafx.fxml;
    exports utfpr.cc66c.client;
}