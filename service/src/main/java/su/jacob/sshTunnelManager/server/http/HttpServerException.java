package su.jacob.sshTunnelManager.server.http;

/**
 * @author Jacob.Su
 * @mail: 450416583@qq.com
 */
public class HttpServerException extends Exception {
    public HttpServerException() {
    }

    public HttpServerException(String message) {
        super(message);
    }

    public HttpServerException(String message, Throwable cause) {
        super(message, cause);
    }

    public HttpServerException(Throwable cause) {
        super(cause);
    }

    public HttpServerException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
