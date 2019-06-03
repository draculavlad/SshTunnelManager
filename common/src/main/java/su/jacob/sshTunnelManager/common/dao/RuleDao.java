package su.jacob.sshTunnelManager.common.dao;

import su.jacob.sshTunnelManager.common.entity.Rule;

import java.util.List;

/**
 * @author Jacob.Su
 * @mail: 450416583@qq.com
 */
public interface RuleDao {
    void add(Rule rule);

    void update(Rule rule);

    void delete(int id);

    Rule getById(int id);

    List<Rule> getAll();
}
