module utfpr.cc66c.server {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    requires com.fasterxml.jackson.databind;
    requires utfpr.cc66c.core;

    opens utfpr.cc66c.server to javafx.fxml;
    exports utfpr.cc66c.server;
    opens utfpr.cc66c.server.controllers to javafx.fxml;
    exports utfpr.cc66c.server.controllers;
    exports utfpr.cc66c.server.services;
    opens utfpr.cc66c.server.services to javafx.fxml;
    exports utfpr.cc66c.server.views;
    opens utfpr.cc66c.server.views to javafx.fxml;

}