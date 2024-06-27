package utfpr.cc66c.server.views;

import javafx.application.Platform;
import utfpr.cc66c.server.controllers.views.ApplicationViewController;

public class DashboardViewFactory {
    public static void setIp(String ip) {
        var controller = ApplicationViewController.getDashboardController();
        controller.getIpLabel().setText(ip);
    }

    public static void addLoggedClient(String email, String token) {
        var controller = ApplicationViewController.getDashboardController();
        controller.getConnectedClients().add(email + " " + token);
        Platform.runLater(() -> {
            controller.getListView().getItems().clear();
            controller.getListView().getItems().addAll(controller.getConnectedClients());
        });
    }

    public static void removeLoggedClient(String token) {
        var controller = ApplicationViewController.getDashboardController();
        controller.getConnectedClients().remove(token);
        Platform.runLater(() -> {
            controller.getListView().getItems().clear();
            if (!controller.getConnectedClients().isEmpty()) {
                controller.getListView().getItems().addAll(controller.getConnectedClients());
            }
        });
    }


}
