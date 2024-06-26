module utfpr.cc66c.server {
    requires javafx.controls;
    requires javafx.fxml;

    requires javafx.graphics;

    requires com.fasterxml.jackson.databind;
    requires utfpr.cc66c.core;

    opens utfpr.cc66c.server to javafx.fxml;
    exports utfpr.cc66c.server;
    opens utfpr.cc66c.server.controllers to javafx.fxml;
    exports utfpr.cc66c.server.controllers;
    exports utfpr.cc66c.server.services;
    opens utfpr.cc66c.server.services to javafx.fxml;
    exports utfpr.cc66c.server.controllers.views;
    exports utfpr.cc66c.server.services.db;
    opens utfpr.cc66c.server.services.db to javafx.fxml;
    exports utfpr.cc66c.server.controllers.candidate.auth;
    opens utfpr.cc66c.server.controllers.candidate.auth to javafx.fxml;
    exports utfpr.cc66c.server.services.candidate.auth;
    opens utfpr.cc66c.server.services.candidate.auth to javafx.fxml;
    exports utfpr.cc66c.server.services.candidate.profile;
    opens utfpr.cc66c.server.services.candidate.profile to javafx.fxml;
    exports utfpr.cc66c.server.controllers.candidate;
    opens utfpr.cc66c.server.controllers.candidate to javafx.fxml;

    requires java.sql;
}