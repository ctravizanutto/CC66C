package utfpr.cc66c.server.views;

import javafx.application.Platform;
import utfpr.cc66c.server.controllers.ServerController;

public class DashboardViewFactory {
    public static void setIp(String ip) {
        var controller = ServerController.getServerDashboardController();
        controller.getIpLabel().setText(ip);
    }

    public static void addClient(String ip, int port) {
        var client = formatClient(ip, port);
        var controller = ServerController.getServerDashboardController();
        controller.getConnectedClients().add(client);
        Platform.runLater(() -> {
            controller.getListView().getItems().clear();
            controller.getListView().getItems().addAll(controller.getConnectedClients());
        });
    }

    public static void removeClient(String ip, int port) {
        var client = formatClient(ip, port);
        var controller = ServerController.getServerDashboardController();
        controller.getConnectedClients().remove(client);
        Platform.runLater(() -> {
            controller.getListView().getItems().clear();
            if (!controller.getConnectedClients().isEmpty()) {
                controller.getListView().getItems().addAll(controller.getConnectedClients());
            }
        });
    }

    private static String formatClient(String ip, int port) {
        return ip + ":" + port;
    }

}
