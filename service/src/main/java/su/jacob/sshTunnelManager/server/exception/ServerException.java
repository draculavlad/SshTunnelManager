package su.jacob.sshTunnelManager.server.exception;

/**
 * @author Jacob.Su
 * @mail: 450416583@qq.com
 */
public class ServerException extends RuntimeException {
    public ServerException() {
        super();
    }

    public ServerException(String message) {
        super(message);
    }

    public ServerException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServerException(Throwable cause) {
        super(cause);
    }

    protected ServerException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
