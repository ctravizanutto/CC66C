package utfpr.cc66c.client.controllers.connection;

public class SessionController {
    private static final int port = 21234;
    private static final String tokenKey = "DISTRIBUIDOS";
    private static String host = "localhost";
    private static String token;

    public static int getPort() {
        return port;
    }

    public static String getHost() {
        return host;
    }

    public static void setHost(String host) {
        SessionController.host = host;
    }

    public static String getTokenKey() {
        return tokenKey;
    }

    public static String getToken() {
        return token;
    }

    public static void setToken(String token) {
        SessionController.token = token;
    }
}
