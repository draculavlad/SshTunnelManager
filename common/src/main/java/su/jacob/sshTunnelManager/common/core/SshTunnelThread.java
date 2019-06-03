package su.jacob.sshTunnelManager.common.core;


import com.jcraft.jsch.ChannelShell;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import su.jacob.sshTunnelManager.common.entity.SshTunnel;
import su.jacob.sshTunnelManager.common.entity.RemoteForwardingSshtTunnel;
import su.jacob.sshTunnelManager.common.entity.LocalForwardingSshTunnel;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

/**
 * @author Jacob.Su
 * @mail: 450416583@qq.com
 */
public class SshTunnelThread extends Thread {

    private static final Logger logger = LoggerFactory.getLogger(SshTunnelThread.class);

    private static final int SSH_CONNECTION_TIMEOUT = 300000;

    private SshTunnel sshTunnel;
    private Session session;
    private Boolean isRunning;
    private Boolean hasError;
    private Throwable cause;

    private InputStream inputStream;
    private OutputStream outputStream;


    public SshTunnelThread(SshTunnel sshTunnel) throws IOException, JSchException, SshTunnelExcpetion {
        this.setName(UUID.randomUUID().toString());
        sshTunnel.setId(this.getName());
        String bridgeHostIp = sshTunnel.getBridgeHostIp();
        int bridgeHostSshPort = sshTunnel.getBridgeHostSshPort();
        String bridgeHostSshUid = sshTunnel.getBridgeHostSshUid();
        String bridgeHostSshPwd = sshTunnel.getBridgeHostSshPwd();

        JSch jSch = new JSch();
        Session session = jSch.getSession(bridgeHostSshUid, bridgeHostIp, bridgeHostSshPort);
        session.setPassword(bridgeHostSshPwd);
        session.setConfig("StrictHostKeyChecking", "no");

        if (sshTunnel instanceof LocalForwardingSshTunnel) {
            LocalForwardingSshTunnel localForwardingSshTunnel = (LocalForwardingSshTunnel)sshTunnel;
            int accessPort = SshTunnelToolkit.getUnusedPortFromLocal();
            localForwardingSshTunnel.setAccessPort(accessPort);
            String destinationIp = localForwardingSshTunnel.getDestinationIp();
            int destinationPort =localForwardingSshTunnel.getDestinationPort();
            session.setPortForwardingL("0.0.0.0", accessPort, destinationIp, destinationPort);
        } else {
            RemoteForwardingSshtTunnel remoteForwardingSshtTunnel = (RemoteForwardingSshtTunnel)sshTunnel;
            Socket socket = new Socket();
            socket.connect(new InetSocketAddress(bridgeHostIp, bridgeHostSshPort));
            String localIp = socket.getLocalAddress().getHostAddress();
            remoteForwardingSshtTunnel.setLocalIp(localIp);
            int localPort = remoteForwardingSshtTunnel.getLocalPort();
            remoteForwardingSshtTunnel.setLocalPort(localPort);
            int bridgeHostLocalServicePort = SshTunnelToolkit.getUnusedPortFromRemote(bridgeHostIp);
            remoteForwardingSshtTunnel.setBridgeHostLocalServicePort(bridgeHostLocalServicePort);
            int bridgeHostPublicServicePort = SshTunnelToolkit.getUnusedPortFromRemote(bridgeHostIp);
            remoteForwardingSshtTunnel.setBridgeHostPublicServicePort(bridgeHostPublicServicePort);
        }

        this.sshTunnel = sshTunnel;
        this.session = session;
        this.isRunning = false;
        this.hasError = false;
        this.cause = null;
        this.inputStream = null;
        this.outputStream = null;
    }

    @Override
    public void run() {
        this.isRunning = true;
        try {
             session.connect(SSH_CONNECTION_TIMEOUT);
            if (this.sshTunnel instanceof RemoteForwardingSshtTunnel) {
                RemoteForwardingSshtTunnel remoteForwardingSshtTunnel = (RemoteForwardingSshtTunnel) sshTunnel;

                session.setPortForwardingR("0.0.0.0",
                        remoteForwardingSshtTunnel.getBridgeHostLocalServicePort(),
                        remoteForwardingSshtTunnel.getLocalIp(),
                        remoteForwardingSshtTunnel.getLocalPort());
                ChannelShell channelShell = null;
                StringBuilder cmdBuilder = new StringBuilder();
                cmdBuilder.append("ssh -o StrictHostKeyChecking=no -L 0.0.0.0:");
                cmdBuilder.append(remoteForwardingSshtTunnel.getBridgeHostPublicServicePort());
                cmdBuilder.append(":127.0.0.1:");
                cmdBuilder.append(remoteForwardingSshtTunnel.getBridgeHostLocalServicePort());
                cmdBuilder.append(" ");
                cmdBuilder.append("-p ");
                cmdBuilder.append(this.sshTunnel.getBridgeHostSshPort());
                cmdBuilder.append(" ");
                cmdBuilder.append(this.sshTunnel.getBridgeHostSshUid());
                cmdBuilder.append("@127.0.0.1\r");
                String cmd = cmdBuilder.toString();
                try {
                    channelShell = (ChannelShell)session.openChannel("shell");
                    channelShell.setPty(true);
                    PipedInputStream pis = new PipedInputStream();
                    PipedOutputStream pos = new PipedOutputStream();
                    channelShell.setInputStream(new PipedInputStream(pos));
                    channelShell.setOutputStream(new PipedOutputStream(pis));
                    channelShell.connect();
                    verifyResponseContains(pis,null);
                    pos.write(cmd.getBytes(StandardCharsets.UTF_8));
                    pos.flush();
                    verifyResponseContains(pis, this.sshTunnel.getBridgeHostSshUid()+"@127.0.0.1's password:");
                    pos.write((this.sshTunnel.getBridgeHostSshPwd()+"\r").getBytes(StandardCharsets.UTF_8));
                    inputStream = pis;
                    outputStream = pos;
                    loggingTerminalOutput(pis);
                } catch (JSchException e) {
                    logger.error("SshTunnel run Exception: ", e.getMessage());
                    logger.debug("SshTunnel run Exception: ", e);
                } catch (IOException e) {
                    logger.error("SshTunnel run Exception: ", e.getMessage());
                    logger.debug("SshTunnel run Exception: ", e);
                }
            }
        } catch (JSchException e) {
            logger.error("SshTunnel run Exception: ", e.getMessage());
            logger.debug("SshTunnel run Exception: ", e);
            this.isRunning = false;
            this.hasError = true;
            this.cause = e;
        }
    }

    @Override
    public void interrupt() {
        this.releaseConnection();
    }

    private void loggingTerminalOutput(InputStream pis) throws IOException {
        while (true) {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            StringBuilder tmpOutputBuilder = new StringBuilder().append((char) pis.read());
            logger.debug("Terminal Output: ", tmpOutputBuilder.toString());
        }
    }

    private void verifyResponseContains(InputStream pis, String response) throws IOException {
        if (response == null) {
            StringBuilder sb = new StringBuilder();
            try {
                while (true) {
                    sb.append((char) pis.read());
                    String tmpResponse = sb.toString();
                    if (tmpResponse.endsWith("$") || tmpResponse.endsWith("#")) {
                        break;
                    }
                }
            } finally {
                logger.debug("String to check is: " + sb.toString());
            }
        } else {
            StringBuilder sb = new StringBuilder();
            try {
                while (true) {
                    sb.append((char) pis.read());
                    String tmpResponse = sb.toString();
                    if (tmpResponse.contains(response) || tmpResponse.contains("Welcome")) {
                        break;
                    }
                }
            } finally {
                logger.debug("String to check is: " + sb.toString());
            }
        }
    }


    private void releaseConnection() {
        try {
            if (inputStream != null) {
                inputStream.close();
            }
            if (outputStream != null) {
                outputStream.close();
            }
        } catch (IOException e) {
            logger.warn("SshTunnel releaseConnection Exception: ", e.getMessage());
            logger.debug("SshTunnel releaseConnection Exception: ", e);
        }

        if (this.session.isConnected()) {
            this.session.disconnect();
        }

        try {
            super.finalize();
        } catch (Throwable e) {
            logger.warn("SshTunnel releaseConnection Exception: ", e.getMessage());
            logger.debug("SshTunnel releaseConnection Exception: ", e);
        }
    }

    public SshTunnel getSshTunnel() {
        return sshTunnel;
    }

    public void setSshTunnel(SshTunnel sshTunnel) {
        this.sshTunnel = sshTunnel;
    }

    public Boolean getRunning() {
        return isRunning;
    }

    public void setRunning(Boolean running) {
        isRunning = running;
    }

    public Boolean getHasError() {
        return hasError;
    }

    public void setHasError(Boolean hasError) {
        this.hasError = hasError;
    }

    public Throwable getCause() {
        return cause;
    }

    public void setCause(Throwable cause) {
        this.cause = cause;
    }
}
