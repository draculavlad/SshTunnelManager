package su.jacob.sshTunnelManager.common.entity;

/**
 * @author Jacob.Su
 * @mail: 450416583@qq.com
 */
public class LocalForwardingSshTunnel extends SshTunnel {
    private String destinationIp;
    private Integer destinationPort;
    private String accessIp;
    private Integer accessPort;

    public LocalForwardingSshTunnel() {
    }

    public LocalForwardingSshTunnel(String destinationIp,
                                    Integer destinationPort,
                                    String accessIp,
                                    Integer accessPort,
                                    String bridgeHostIp,
                                    Integer bridgeHostSshPort,
                                    String bridgeHostSshUid,
                                    String bridgeHostSshPwd
    ) {
        this.destinationIp = destinationIp;
        this.destinationPort = destinationPort;
        this.accessIp = accessIp;
        this.accessPort = accessPort;
        this.bridgeHostIp = bridgeHostIp;
        this.bridgeHostSshPort = bridgeHostSshPort;
        this.bridgeHostSshUid = bridgeHostSshUid;
        this.bridgeHostSshPwd = bridgeHostSshPwd;

    }

    public String getDestinationIp() {
        return destinationIp;
    }

    public void setDestinationIp(String destinationIp) {
        this.destinationIp = destinationIp;
    }

    public Integer getDestinationPort() {
        return destinationPort;
    }

    public void setDestinationPort(Integer destinationPort) {
        this.destinationPort = destinationPort;
    }

    public String getAccessIp() {
        return accessIp;
    }

    public void setAccessIp(String accessIp) {
        this.accessIp = accessIp;
    }

    public Integer getAccessPort() {
        return accessPort;
    }

    public void setAccessPort(Integer accessPort) {
        this.accessPort = accessPort;
    }
}
