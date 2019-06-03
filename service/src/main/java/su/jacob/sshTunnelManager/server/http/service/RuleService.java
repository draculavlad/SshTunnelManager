package su.jacob.sshTunnelManager.server.http.service;


import su.jacob.sshTunnelManager.common.entity.Rule;

import java.util.List;

/**
 * @author Jacob.Su
 * @mail: 450416583@qq.com
 */
public interface RuleService {

    Rule getRuleById(int id);

    List<Rule> getAllRules();

    void addRule(Rule rule);

    void updateRule(Rule rule);

    void deleteRule(int id);

    Rule findMatchedRule(String sourceIp, String sourceLocation,
                         String destinationIp, String destinationLocation);
}
