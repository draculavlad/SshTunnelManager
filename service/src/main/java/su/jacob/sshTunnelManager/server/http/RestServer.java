package su.jacob.sshTunnelManager.server.http;

import su.jacob.sshTunnelManager.server.exception.ServerException;
import su.jacob.sshTunnelManager.server.http.handler.HttpExceptionHandler;
import su.jacob.sshTunnelManager.server.http.interceptor.CorsInterceptor;
import su.jacob.sshTunnelManager.server.http.interceptor.ThreadLocalInterceptor;
import org.leo.web.core.WebServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Jacob.Su
 * @mail: 450416583@qq.com
 */
public class RestServer implements Runnable {

    private static final Logger logger = LoggerFactory.getLogger(RestServer.class);

    private int port;

    public RestServer(int port) {
        this.port = port;
    }

    @Override
    public void run() {

        // 全局异常处理
        WebServer.setExceptionHandler(new HttpExceptionHandler());

        // 设置监听端口号
        WebServer server = new WebServer(this.port);

        // 设置Http最大内容长度（默认 为1GB）
        server.setMaxContentLength(1024 * 1024 * 1024);

        // 设置Controller所在包
        //C:\Users\suj11\IdeaProjects\ProxyService\mo-service\src\main\java\su\jacob\sshTunnelManager\server\http\controller
        server.setControllerBasePackage("su.jacob.sshTunnelManager.server.http.controller");

        // 添加拦截器，按照添加的顺序执行。
        // 跨域拦截器
        server.addInterceptor(new CorsInterceptor());
        server.addInterceptor(new ThreadLocalInterceptor());

        try {
            server.start();
        } catch (InterruptedException e) {
            logger.info("HTTP Service Error: ", e.getMessage());
            logger.debug("HTTP Service Error: ", e);
            throw new ServerException(e);
        }
    }
}
