package su.jacob.sshTunnelManager.common.core;

/**
 * @author Jacob.Su
 * @mail: 450416583@qq.com
 */
public class DBConnectionException extends RuntimeException {
    public DBConnectionException() {
    }

    public DBConnectionException(String message) {
        super(message);
    }

    public DBConnectionException(String message, Throwable cause) {
        super(message, cause);
    }

    public DBConnectionException(Throwable cause) {
        super(cause);
    }

    public DBConnectionException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
