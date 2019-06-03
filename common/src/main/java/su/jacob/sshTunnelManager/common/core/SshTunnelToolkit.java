package su.jacob.sshTunnelManager.common.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.*;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Jacob.Su
 * @mail: 450416583@qq.com
 */
public class SshTunnelToolkit {

    private static final Logger logger = LoggerFactory.getLogger(SshTunnelToolkit.class);

    private static final String LOCAL_KEY_STRING = "local";
    private static final Map<String, Set<Integer>> usedPortDictionary = new ConcurrentHashMap<>();

    static {
        usedPortDictionary.put(LOCAL_KEY_STRING, new HashSet<Integer>());
    }
    private static final Object usedPortDictionaryLock = new Object();

    static int getUnusedPortFromLocal() {
        synchronized (usedPortDictionaryLock) {
            Integer port = 0;
            while (true) {
                int tmpPort = new Random().nextInt(30535) + 35001;
                if (usedPortDictionary.get(LOCAL_KEY_STRING).contains(tmpPort)) {
                    continue;
                }
                ServerSocket serverSocket = null;
                try {
                    serverSocket = new ServerSocket(tmpPort);
                    port = tmpPort;
                    break;
                } catch (IOException e) {
                    logger.debug("local port {} not available, try next...", tmpPort);
                } finally {
                    if (serverSocket != null) {
                        try {
                            serverSocket.close();
                        } catch (IOException e) {
                        }
                    }
                }
            }
            int finalPort = port.intValue();
            usedPortDictionary.get(LOCAL_KEY_STRING).add(finalPort);
            return finalPort;
        }
    }

    static int getUnusedPortFromRemote(String remoteIpAddress) throws SshTunnelExcpetion {
        synchronized (usedPortDictionaryLock) {
            Integer port = 0;
            while (true) {
                int tmpPort = new Random().nextInt(30535) + 35001;

                if (usedPortDictionary.get(remoteIpAddress) == null) {
                    usedPortDictionary.put(remoteIpAddress, new HashSet<Integer>());
                }

                if (usedPortDictionary.get(remoteIpAddress).contains(tmpPort)) {
                    continue;
                }

                Socket socket = null;
                try {
                    socket = new Socket();
                    socket.setReuseAddress(true);
                    SocketAddress socketAddress = new InetSocketAddress(remoteIpAddress, tmpPort);
                    socket.connect(socketAddress, 3000);
                } catch (IOException e) {
                    if (e.getMessage().contains("Connection refused")) {
                        logger.debug("port {} on {} is unused.", tmpPort, remoteIpAddress);
                        port = tmpPort;
                        break;
                    }
                    ;
                    if (e instanceof UnknownHostException) {
                        logger.debug("address {} is unresolved", remoteIpAddress);
                        throw new SshTunnelExcpetion(e);
                    }
                    if (e instanceof SocketTimeoutException) {
                        logger.debug("timeout while attempting to reach address {} on port {}", remoteIpAddress, tmpPort);
                        throw new SshTunnelExcpetion(e);
                    }
                } finally {
                    if (socket != null) {
                        if (socket.isConnected()) {
                            logger.debug("Port {} on {} is used!", tmpPort, remoteIpAddress);
                        }
                        try {
                            socket.close();
                        } catch (IOException e) {
                        }
                    }
                }
            }
            int finalValue = port.intValue();
            usedPortDictionary.get(remoteIpAddress).add(finalValue);
            return finalValue;
        }
    }

    static void retireLocalPort(int port) {
        synchronized (usedPortDictionaryLock) {
            usedPortDictionary.get(LOCAL_KEY_STRING).remove(port);
        }
    }

    static void retireRemotePort(String remoteIpAddress, int port) {
        synchronized (usedPortDictionaryLock) {
            usedPortDictionary.get(remoteIpAddress).remove(port);
        }
    }




}
