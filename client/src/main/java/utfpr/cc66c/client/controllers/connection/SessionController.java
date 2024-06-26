package utfpr.cc66c.client.controllers.connection;

public class SessionController {
    private static final int port = 21234;
    private static String token;

    public static int getPort() {
        return port;
    }

    public static String getToken() {
        return token;
    }

    public static void setToken(String token) {
        SessionController.token = token;
    }
}
