module utfpr.cc66c.server {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens utfpr.cc66c.server to javafx.fxml;
    exports utfpr.cc66c.server;
}