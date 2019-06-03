package su.jacob.sshTunnelManager.server.http.service;

/**
 * @author Jacob.Su
 * @mail: 450416583@qq.com
 */
public class CommonServiceException extends RuntimeException {
    public CommonServiceException() {
    }

    public CommonServiceException(String message) {
        super(message);
    }

    public CommonServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public CommonServiceException(Throwable cause) {
        super(cause);
    }

    public CommonServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
