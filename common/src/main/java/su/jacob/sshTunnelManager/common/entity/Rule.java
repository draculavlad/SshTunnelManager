package su.jacob.sshTunnelManager.common.entity;

/**
 * @author Jacob.Su
 * @mail: 450416583@qq.com
 *
 * Source: connection launch point
 * Destination: target point
 * Bridge: sshd service provider
 *
 */
public class Rule {
    /**
     * db index auto-gen
     */
    private Integer id;
    /**
     * network range of source
     */
    private String sourceNetwork;
    /**
     * location of source
     */
    private String sourceLocation;
    /**
     * network range of destination
     */
    private String destinationNetwork;
    /**
     * location of destination
     */
    private String destinationLocation;
    /**
     * is this rule for ssh tunnel
     */
    private Boolean requireSshTunnel;
    /**
     * is this rule for ssh tunnel of remote forwarding
     */
    private Boolean requireRemoteForwarding;
    /**
     * bridgee ip
     */
    private String tunnelBridgeHost;
    /**
     * bridge user identity
     */
    private String tunnelBridgeUid;
    /**
     * bridge user password
     */
    private String tunnelBridgePwd;
    /**
     * bridge sshd port
     */
    private Integer tunnelBridgeSshPort;
    /**
     * is this rule for socks proxy
     */
    private Boolean requireSocksProxy;
    /**
     * socks proxy ip
     */
    private String proxyHost;
    /**
     * socks proxy port
     */
    private Integer proxyPort;
    /**
     * is valid
     */
    private Boolean valid;

    public Rule() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSourceNetwork() {
        return sourceNetwork;
    }

    public void setSourceNetwork(String sourceNetwork) {
        this.sourceNetwork = sourceNetwork;
    }

    public String getSourceLocation() {
        return sourceLocation;
    }

    public void setSourceLocation(String sourceLocation) {
        this.sourceLocation = sourceLocation;
    }

    public String getDestinationNetwork() {
        return destinationNetwork;
    }

    public void setDestinationNetwork(String destinationNetwork) {
        this.destinationNetwork = destinationNetwork;
    }

    public String getDestinationLocation() {
        return destinationLocation;
    }

    public void setDestinationLocation(String destinationLocation) {
        this.destinationLocation = destinationLocation;
    }

    public Boolean getRequireSshTunnel() {
        return requireSshTunnel;
    }

    public void setRequireSshTunnel(Boolean requireSshTunnel) {
        this.requireSshTunnel = requireSshTunnel;
    }

    public Boolean getRequireRemoteForwarding() {
        return requireRemoteForwarding;
    }

    public void setRequireRemoteForwarding(Boolean requireRemoteForwarding) {
        this.requireRemoteForwarding = requireRemoteForwarding;
    }

    public Boolean getRequireSocksProxy() {
        return requireSocksProxy;
    }

    public void setRequireSocksProxy(Boolean requireSocksProxy) {
        this.requireSocksProxy = requireSocksProxy;
    }

    public String getTunnelBridgeHost() {
        return tunnelBridgeHost;
    }

    public void setTunnelBridgeHost(String tunnelBridgeHost) {
        this.tunnelBridgeHost = tunnelBridgeHost;
    }

    public String getTunnelBridgeUid() {
        return tunnelBridgeUid;
    }

    public void setTunnelBridgeUid(String tunnelBridgeUid) {
        this.tunnelBridgeUid = tunnelBridgeUid;
    }

    public String getTunnelBridgePwd() {
        return tunnelBridgePwd;
    }

    public void setTunnelBridgePwd(String tunnelBridgePwd) {
        this.tunnelBridgePwd = tunnelBridgePwd;
    }

    public Integer getTunnelBridgeSshPort() {
        return tunnelBridgeSshPort;
    }

    public void setTunnelBridgeSshPort(Integer tunnelBridgeSshPort) {
        this.tunnelBridgeSshPort = tunnelBridgeSshPort;
    }

    public String getProxyHost() {
        return proxyHost;
    }

    public void setProxyHost(String proxyHost) {
        this.proxyHost = proxyHost;
    }

    public Integer getProxyPort() {
        return proxyPort;
    }

    public void setProxyPort(Integer proxyPort) {
        this.proxyPort = proxyPort;
    }

    public Boolean getValid() {
        return valid;
    }

    public void setValid(Boolean valid) {
        this.valid = valid;
    }

    @Override
    public String toString() {
        return "Rule{" +
                "id=" + id +
                ", sourceNetwork='" + sourceNetwork + '\'' +
                ", sourceLocation='" + sourceLocation + '\'' +
                ", destinationNetwork='" + destinationNetwork + '\'' +
                ", destinationLocation='" + destinationLocation + '\'' +
                ", requireSshTunnel=" + requireSshTunnel +
                ", requireRemoteForwarding=" + requireRemoteForwarding +
                ", tunnelBridgeHost='" + tunnelBridgeHost + '\'' +
                ", tunnelBridgeUid='" + tunnelBridgeUid + '\'' +
                ", tunnelBridgePwd='" + tunnelBridgePwd + '\'' +
                ", tunnelBridgeSshPort=" + tunnelBridgeSshPort +
                ", requireSocksProxy=" + requireSocksProxy +
                ", ProxyHost='" + proxyHost + '\'' +
                ", ProxyPort='" + proxyPort + '\'' +
                ", valid='" + valid + '\'' +
                '}';
    }
}