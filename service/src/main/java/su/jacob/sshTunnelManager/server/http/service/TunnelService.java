package su.jacob.sshTunnelManager.server.http.service;

import su.jacob.sshTunnelManager.common.entity.Rule;
import su.jacob.sshTunnelManager.common.entity.SshTunnelStatus;

import java.util.List;

/**
 * @author Jacob.Su
 * @mail: 450416583@qq.com
 */
public interface TunnelService {

    SshTunnelStatus checkStatusByTunnelId(String tunnelId);

    List<SshTunnelStatus> getAllTunnels();

    void createLocalForwardingTunnel(Rule rule, String targetHostIp, int targetHostPort);

    void createRemoteForwardingTunnel(Rule rule, int localPort);

    void deleteTunnelByTunnelId(String tunnelId);

}
