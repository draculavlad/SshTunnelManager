package su.jacob.sshTunnelManager.common.entity;

/**
 * @author Jacob.Su
 * @mail: 450416583@qq.com
 */
public abstract class SshTunnel {
    protected String id;
    protected Integer ruleId;
    protected String bridgeHostIp;
    protected Integer bridgeHostSshPort;
    protected String bridgeHostSshUid;
    protected String bridgeHostSshPwd;

    public SshTunnel() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getRuleId() {
        return ruleId;
    }

    public void setRuleId(Integer ruleId) {
        this.ruleId = ruleId;
    }

    public String getBridgeHostIp() {
        return bridgeHostIp;
    }

    public void setBridgeHostIp(String bridgeHostIp) {
        this.bridgeHostIp = bridgeHostIp;
    }

    public Integer getBridgeHostSshPort() {
        return bridgeHostSshPort;
    }

    public void setBridgeHostSshPort(Integer bridgeHostSshPort) {
        this.bridgeHostSshPort = bridgeHostSshPort;
    }

    public String getBridgeHostSshUid() {
        return bridgeHostSshUid;
    }

    public void setBridgeHostSshUid(String bridgeHostSshUid) {
        this.bridgeHostSshUid = bridgeHostSshUid;
    }

    public String getBridgeHostSshPwd() {
        return bridgeHostSshPwd;
    }

    public void setBridgeHostSshPwd(String bridgeHostSshPwd) {
        this.bridgeHostSshPwd = bridgeHostSshPwd;
    }

}
