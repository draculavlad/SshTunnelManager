package su.jacob.sshTunnelManager.common.dao.impl;


import org.apache.ibatis.session.SqlSession;
import su.jacob.sshTunnelManager.common.core.ThreadLocalToolkit;
import su.jacob.sshTunnelManager.common.dao.RuleDao;
import su.jacob.sshTunnelManager.common.dao.mapper.RuleMapper;
import su.jacob.sshTunnelManager.common.entity.Rule;

import java.util.List;

/**
 * @author Jacob.Su
 * @mail: 450416583@qq.com
 */
public class RuleDaoImpl implements RuleDao {

    public RuleDaoImpl() {
    }

    @Override
    public void add(Rule rule) {
        SqlSession sqlSession = ThreadLocalToolkit.getLocalSession();
        RuleMapper ruleMapper = sqlSession.getMapper(RuleMapper.class);
        ruleMapper.add(rule);
    }

    @Override
    public void update(Rule rule) {
        SqlSession sqlSession = ThreadLocalToolkit.getLocalSession();
        RuleMapper ruleMapper = sqlSession.getMapper(RuleMapper.class);
        ruleMapper.update(rule);
    }

    @Override
    public void delete(int id) {
        SqlSession sqlSession = ThreadLocalToolkit.getLocalSession();
        RuleMapper ruleMapper = sqlSession.getMapper(RuleMapper.class);
        ruleMapper.deleteById(id);
    }

    @Override
    public Rule getById(int id) {
        SqlSession sqlSession = ThreadLocalToolkit.getLocalSession();
        RuleMapper ruleMapper = sqlSession.getMapper(RuleMapper.class);
        return ruleMapper.getBydId(id);
    }

    @Override
    public List<Rule> getAll() {
        SqlSession sqlSession = ThreadLocalToolkit.getLocalSession();
        RuleMapper ruleMapper = sqlSession.getMapper(RuleMapper.class);
        return ruleMapper.getALl();
    }

}
