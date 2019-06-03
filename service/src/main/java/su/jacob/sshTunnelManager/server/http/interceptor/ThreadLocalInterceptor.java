package su.jacob.sshTunnelManager.server.http.interceptor;

import su.jacob.sshTunnelManager.common.core.ThreadLocalToolkit;
import io.netty.handler.codec.http.FullHttpRequest;
import org.leo.web.rest.HttpResponse;
import org.leo.web.rest.interceptor.Interceptor;

/**
 * @author Jacob.Su
 * @mail: 450416583@qq.com
 */
public class ThreadLocalInterceptor implements Interceptor {
    @Override
    public boolean preHandle(FullHttpRequest request, HttpResponse response) throws Exception {
        ThreadLocalToolkit.init();
        return true;
    }

    @Override
    public void postHandle(FullHttpRequest request, HttpResponse response) throws Exception {
        ThreadLocalToolkit.getLocalSession().commit();
        ThreadLocalToolkit.getLocalSession().close();
    }

    @Override
    public void afterCompletion(FullHttpRequest request, HttpResponse response) {

    }
}
