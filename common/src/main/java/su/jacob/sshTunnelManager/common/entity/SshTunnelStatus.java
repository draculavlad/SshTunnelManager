package su.jacob.sshTunnelManager.common.entity;

/**
 * @author Jacob.Su
 * @mail: 450416583@qq.com
 */
public class SshTunnelStatus {
    private SshTunnel sshTunnel;
    private Boolean hasError;
    private Boolean isRunning;
    private Throwable error;

    public SshTunnelStatus(SshTunnel sshTunnel, Boolean hasError, Boolean isRunning, Throwable throwable) {
        this.sshTunnel = sshTunnel;
        this.hasError = hasError;
        this.isRunning = isRunning;
        this.error = throwable;
    }

    public SshTunnel getSshTunnel() {
        return sshTunnel;
    }

    public void setSshTunnel(SshTunnel sshTunnel) {
        this.sshTunnel = sshTunnel;
    }

    public Boolean getHasError() {
        return hasError;
    }

    public void setHasError(Boolean hasError) {
        this.hasError = hasError;
    }

    public Boolean getRunning() {
        return isRunning;
    }

    public void setRunning(Boolean running) {
        isRunning = running;
    }

    public Throwable getError() {
        return error;
    }

    public void setError(Throwable error) {
        this.error = error;
    }
}
