package su.jacob.sshTunnelManager.common.core;


import com.jcraft.jsch.JSchException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import su.jacob.sshTunnelManager.common.entity.SshTunnel;
import su.jacob.sshTunnelManager.common.entity.LocalForwardingSshTunnel;
import su.jacob.sshTunnelManager.common.entity.RemoteForwardingSshtTunnel;
import su.jacob.sshTunnelManager.common.entity.SshTunnelStatus;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Jacob.Su
 * @mail: 450416583@qq.com
 */
public class SshTunnelManager {

    private static final Logger logger = LoggerFactory.getLogger(SshTunnelManager.class);

    private static final Map<String, SshTunnelThread> SSH_TUNNEL_NAME_MAP = new ConcurrentHashMap<>();

    private static final ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    public static void addSshTunnel(SshTunnel sshTunnel) throws SshTunnelExcpetion {
        SshTunnelThread sshTunnelThread;
        try {
            sshTunnelThread = new SshTunnelThread(sshTunnel);
        } catch (IOException e) {
            throw new SshTunnelExcpetion(e);
        } catch (JSchException e) {
            throw new SshTunnelExcpetion(e);
        }

        executor.submit(sshTunnelThread);

        SSH_TUNNEL_NAME_MAP.put(sshTunnelThread.getName(), sshTunnelThread);
    }

    public static void removeSshTunnel(String tunnelId) {
        SshTunnelThread sshTunnelThread = SSH_TUNNEL_NAME_MAP.get(tunnelId);
        sshTunnelThread.interrupt();
        SSH_TUNNEL_NAME_MAP.remove(tunnelId);
        SshTunnel sshTunnel = sshTunnelThread.getSshTunnel();
        if (sshTunnel instanceof LocalForwardingSshTunnel) {
            LocalForwardingSshTunnel localForwardingSshTunnel = (LocalForwardingSshTunnel)sshTunnel;
            SshTunnelToolkit.retireLocalPort(localForwardingSshTunnel.getAccessPort());
        } else {
            RemoteForwardingSshtTunnel remoteForwardingSshtTunnel = (RemoteForwardingSshtTunnel)sshTunnel;
            SshTunnelToolkit.retireRemotePort(remoteForwardingSshtTunnel.getBridgeHostIp(), remoteForwardingSshtTunnel.getBridgeHostLocalServicePort());
            SshTunnelToolkit.retireRemotePort(remoteForwardingSshtTunnel.getBridgeHostIp(), remoteForwardingSshtTunnel.getBridgeHostPublicServicePort());
        }

    }

    public static SshTunnelStatus getSshTunnel(String tunnelId) {
        SshTunnelThread sshTunnelThread = SSH_TUNNEL_NAME_MAP.get(tunnelId);
        SshTunnelStatus sshTunnelStatus = buildStatus(sshTunnelThread);
        return sshTunnelStatus;
    }

    private static SshTunnelStatus buildStatus(SshTunnelThread sshTunnelThread) {
        SshTunnel sshTunnel = sshTunnelThread.getSshTunnel();
        boolean isRunning = sshTunnelThread.getRunning();
        boolean hasError = sshTunnelThread.getHasError();
        Throwable error = sshTunnelThread.getCause();
        SshTunnelStatus sshTunnelStatus = new SshTunnelStatus(sshTunnel, hasError, isRunning, error);
        return sshTunnelStatus;
    }
    public static List<SshTunnelStatus> getAllTunnels() {
        List<SshTunnelStatus> sshTunnelStatusList = new ArrayList<>();
        for (SshTunnelThread sshTunnelThread : SSH_TUNNEL_NAME_MAP.values()) {
            SshTunnelStatus sshTunnelStatus = buildStatus(sshTunnelThread);
            sshTunnelStatusList.add(sshTunnelStatus);
        }
        return sshTunnelStatusList;
    }
}
