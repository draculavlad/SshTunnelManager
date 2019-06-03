package su.jacob.sshTunnelManager.common.entity;

/**
 * @author Jacob.Su
 * @mail: 450416583@qq.com
 */
public class RemoteForwardingSshtTunnel extends SshTunnel {
    private String localIp;
    private Integer localPort;
    private Integer bridgeHostLocalServicePort;
    private Integer bridgeHostPublicServicePort;

    public RemoteForwardingSshtTunnel() {
    }

    public RemoteForwardingSshtTunnel(String localIp,
                                      Integer localPort,
                                      Integer bridgeHostLocalServicePort,
                                      Integer bridgeHostPublicServicePort,
                                      String bridgeHostIp,
                                      Integer bridgeHostSshPort,
                                      String bridgeHostSshUid,
                                      String bridgeHostSshPwd
    ) {
        this.localIp = localIp;
        this.localPort = localPort;
        this.bridgeHostLocalServicePort = bridgeHostLocalServicePort;
        this.bridgeHostPublicServicePort = bridgeHostPublicServicePort;;
        this.bridgeHostIp = bridgeHostIp;
        this.bridgeHostSshPort = bridgeHostSshPort;
        this.bridgeHostSshUid = bridgeHostSshUid;
        this.bridgeHostSshPwd = bridgeHostSshPwd;
    }

    public String getLocalIp() {
        return localIp;
    }

    public void setLocalIp(String localIp) {
        this.localIp = localIp;
    }

    public Integer getLocalPort() {
        return localPort;
    }

    public void setLocalPort(Integer localPort) {
        this.localPort = localPort;
    }

    public Integer getBridgeHostLocalServicePort() {
        return bridgeHostLocalServicePort;
    }

    public void setBridgeHostLocalServicePort(Integer bridgeHostLocalServicePort) {
        this.bridgeHostLocalServicePort = bridgeHostLocalServicePort;
    }

    public Integer getBridgeHostPublicServicePort() {
        return bridgeHostPublicServicePort;
    }

    public void setBridgeHostPublicServicePort(Integer bridgeHostPublicServicePort) {
        this.bridgeHostPublicServicePort = bridgeHostPublicServicePort;
    }
}
