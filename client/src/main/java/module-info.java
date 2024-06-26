module utfpr.cc66c.client {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    requires utfpr.cc66c.core;

    opens utfpr.cc66c.client to javafx.fxml;
    exports utfpr.cc66c.client;
    exports utfpr.cc66c.client.controllers.views;
    opens utfpr.cc66c.client.controllers.views to javafx.fxml;
    exports utfpr.cc66c.client.controllers.connection;
    opens utfpr.cc66c.client.controllers.connection to javafx.fxml;
    exports utfpr.cc66c.client.controllers.views.candidate;
    opens utfpr.cc66c.client.controllers.views.candidate to javafx.fxml;
    exports utfpr.cc66c.client.controllers.views.recruiter;
    opens utfpr.cc66c.client.controllers.views.recruiter to javafx.fxml;

    requires com.fasterxml.jackson.databind;
    requires java.naming;
    requires net.bytebuddy;
}