package su.jacob.sshTunnelManager.common.core;

/**
 * @author Jacob.Su
 * @mail: 450416583@qq.com
 */
public class SshTunnelExcpetion extends Exception {

    public SshTunnelExcpetion() {
        super();
    }

    public SshTunnelExcpetion(String message) {
        super(message);
    }

    public SshTunnelExcpetion(String message, Throwable cause) {
        super(message, cause);
    }

    public SshTunnelExcpetion(Throwable cause) {
        super(cause);
    }

    protected SshTunnelExcpetion(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
