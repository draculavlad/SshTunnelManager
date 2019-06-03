package su.jacob.sshTunnelManager.common.core;

import org.apache.ibatis.session.SqlSession;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Jacob.Su
 * @mail: 450416583@qq.com
 */
public class ThreadLocalToolkit {

    private static final ThreadLocal<Map<String, String>> threadLocalDictionary = new ThreadLocal<>();
    private static ThreadLocal<SqlSession> sqlSessionThreadLocal = new ThreadLocal<>();

    public static void init() {
        Map<String, String> map = new HashMap<>();
        threadLocalDictionary.set(map);
        SqlSession sqlSession = MybatisToolkit.getSqlSessionFactory().openSession();
        sqlSessionThreadLocal.set(sqlSession);
    }

    public static void saveToLocal(String key, String value) {
        threadLocalDictionary.get().put(key, value);
    }

    public static String getFromLocal(String key) {
        return threadLocalDictionary.get().get(key);
    }

    public static SqlSession getLocalSession() {
        return sqlSessionThreadLocal.get();
    }

}
