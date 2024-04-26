package utfpr.cc66c.client.controllers.views;

import javafx.stage.Stage;
import utfpr.cc66c.client.controllers.ClientConnectionController;
import utfpr.cc66c.client.views.IpViewFactory;
import utfpr.cc66c.client.views.LoginViewFactory;

public class ApplicationViewController {
    private static Stage stage;

    public ApplicationViewController(Stage stage) {
        ApplicationViewController.stage = stage;

        stage.setTitle("Client");
        stage.setResizable(false);
        stage.show();

        stage.setScene(IpViewFactory.getScene());
    }

    public static void toLogin(String addr) {
        ClientConnectionController.start(addr);
        var loginScene = LoginViewFactory.getInstance().getScene();
        stage.setScene(loginScene);
    }

    public void shutdown() {
        ClientConnectionController.shutdown();
    }

}

