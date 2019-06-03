package su.jacob.sshTunnelManager.server.socks;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Jacob.Su
 * @mail: 450416583@qq.com
 */
public class ProxyServer implements Runnable {

    private static final Logger logger = LoggerFactory.getLogger(ProxyServer.class);

    private int port;

    public ProxyServer(int port) {
        this.port = port;
    }

    @Override
    public void run() {
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .handler(new LoggingHandler(LogLevel.DEBUG))
                    .childHandler(new SocksServerInitializer());
            b.bind(this.port).sync().channel().closeFuture().sync();
        } catch (InterruptedException e) {
            logger.info("HTTP Service Error: ", e.getMessage());
            logger.debug("HTTP Service Error: ", e);
            throw new RuntimeException(e);
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
