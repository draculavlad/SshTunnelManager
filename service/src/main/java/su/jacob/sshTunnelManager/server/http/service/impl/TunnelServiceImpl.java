package su.jacob.sshTunnelManager.server.http.service.impl;

import su.jacob.sshTunnelManager.common.core.SshTunnelExcpetion;
import su.jacob.sshTunnelManager.common.core.SshTunnelManager;
import su.jacob.sshTunnelManager.common.entity.LocalForwardingSshTunnel;
import su.jacob.sshTunnelManager.common.entity.RemoteForwardingSshtTunnel;
import su.jacob.sshTunnelManager.common.entity.Rule;
import su.jacob.sshTunnelManager.common.entity.SshTunnelStatus;
import su.jacob.sshTunnelManager.server.http.service.TunnelService;

import java.util.List;

/**
 * @author Jacob.Su
 * @mail: 450416583@qq.com
 */
public class TunnelServiceImpl implements TunnelService {

    public TunnelServiceImpl() {

    }

    @Override
    public SshTunnelStatus checkStatusByTunnelId(String tunnelId) {
        return SshTunnelManager.getSshTunnel(tunnelId);
    }

    @Override
    public List<SshTunnelStatus> getAllTunnels() {
        return SshTunnelManager.getAllTunnels();
    }

    @Override
    public void deleteTunnelByTunnelId(String tunnelId) {
        SshTunnelManager.removeSshTunnel(tunnelId);
    }

    @Override
    public void createLocalForwardingTunnel(Rule rule, String targetHostIp, int targetHostPort) {
        String bridgeHostIp = rule.getTunnelBridgeHost();
        int bridgeHostSshPort = rule.getTunnelBridgeSshPort();
        String bridgeHostUid = rule.getTunnelBridgeUid();
        String bridgeHostPwd = rule.getTunnelBridgePwd();

        LocalForwardingSshTunnel localForwardingSshTunnel = new LocalForwardingSshTunnel();

        localForwardingSshTunnel.setDestinationIp(targetHostIp);
        localForwardingSshTunnel.setDestinationPort(targetHostPort);
        localForwardingSshTunnel.setBridgeHostIp(bridgeHostIp);
        localForwardingSshTunnel.setBridgeHostSshPort(bridgeHostSshPort);
        localForwardingSshTunnel.setBridgeHostSshUid(bridgeHostUid);
        localForwardingSshTunnel.setBridgeHostSshPwd(bridgeHostPwd);

        localForwardingSshTunnel.setRuleId(rule.getId());
        try {
            SshTunnelManager.addSshTunnel(localForwardingSshTunnel);
        } catch (SshTunnelExcpetion sshTunnelExcpetion) {
            throw new RuntimeException(sshTunnelExcpetion);
        }
    }

    @Override
    public void createRemoteForwardingTunnel(Rule rule, int localPort) {
        String bridgeHostIp = rule.getTunnelBridgeHost();
        int bridgeHostSshPort = rule.getTunnelBridgeSshPort();
        String bridgeHostUid = rule.getTunnelBridgeUid();
        String bridgeHostPwd = rule.getTunnelBridgePwd();

        RemoteForwardingSshtTunnel remoteForwardingSshtTunnel = new RemoteForwardingSshtTunnel();
        remoteForwardingSshtTunnel.setLocalPort(localPort);
        remoteForwardingSshtTunnel.setBridgeHostIp(bridgeHostIp);
        remoteForwardingSshtTunnel.setBridgeHostSshPort(bridgeHostSshPort);
        remoteForwardingSshtTunnel.setBridgeHostSshUid(bridgeHostUid);
        remoteForwardingSshtTunnel.setBridgeHostSshPwd(bridgeHostPwd);

        remoteForwardingSshtTunnel.setRuleId(rule.getId());
        try {
            SshTunnelManager.addSshTunnel(remoteForwardingSshtTunnel);
        } catch (SshTunnelExcpetion sshTunnelExcpetion) {
            throw new RuntimeException(sshTunnelExcpetion);
        }
    }

}
