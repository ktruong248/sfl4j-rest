package api.common;

import java.net.InetAddress;
import java.net.UnknownHostException;

public final class Utils {
    private Utils() {
    }

    public static String localAddress() {
        try {
            return InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            return null;
        }
    }
}
