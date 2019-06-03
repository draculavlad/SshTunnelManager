package su.jacob.sshTunnelManager.common.core;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author Jacob.Su
 * @mail: 450416583@qq.com
 */
public class MybatisToolkit {
    private static final Logger logger = LoggerFactory.getLogger(MybatisToolkit.class);
    private static SqlSessionFactory sqlSessionFactory;

    static {
        String resource = "mybatis-config.xml";
        InputStream inputStream;
        try {
            inputStream = Resources.getResourceAsStream(resource);
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        } catch (IOException e) {
            logger.error("DB connection error: {}", e.getMessage());
            logger.debug("DB error: {}", e);
            throw new DBConnectionException(e);
        }
    }

    public static SqlSessionFactory getSqlSessionFactory() {
        return sqlSessionFactory;
    }
}
