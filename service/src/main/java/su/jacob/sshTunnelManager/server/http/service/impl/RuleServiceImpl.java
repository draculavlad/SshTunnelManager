package su.jacob.sshTunnelManager.server.http.service.impl;

import su.jacob.sshTunnelManager.common.dao.RuleDao;
import su.jacob.sshTunnelManager.common.dao.impl.RuleDaoImpl;
import su.jacob.sshTunnelManager.common.entity.Rule;
import su.jacob.sshTunnelManager.server.http.service.RuleService;
import org.apache.commons.net.util.SubnetUtils;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Jacob.Su
 * @mail: 450416583@qq.com
 */
public class RuleServiceImpl implements RuleService {

    private RuleDao ruleDao;

    public RuleServiceImpl() {
        ruleDao = new RuleDaoImpl();
    }

    @Override
    public Rule getRuleById(int id) {
        Rule rule = ruleDao.getById(id);
        return rule;
    }

    @Override
    public List<Rule> getAllRules() {
        List<Rule> ruleList = ruleDao.getAll();
        return ruleList;
    }

    @Override
    public void addRule(Rule rule) {
        rule.setValid(true);
        ruleDao.add(rule);
    }

    @Override
    public void updateRule(Rule rule) {
        ruleDao.update(rule);
    }

    @Override
    public void deleteRule(int id) {
        ruleDao.delete(id);
    }

    @Override
    public Rule findMatchedRule(String sourceIp, String sourceLocation,
                                String destinationIp, String destinationLocation) {
        List<Rule> ruleList = ruleDao.getAll();
        Stream<Rule> ruleStream = ruleList.stream();
        Rule ruleMatched = null;
        if (isPrivateIP(destinationIp)) {
            List<Rule> ruleForInternalUUTDestination = ruleStream.filter(rule -> rule.getRequireSocksProxy() == false).collect(Collectors.toList());
            for (Rule rule : ruleForInternalUUTDestination){
                if (rule.getSourceLocation().equals(sourceLocation)
                        && isInRange(sourceIp, rule.getSourceNetwork())
                        && rule.getDestinationLocation().equals(destinationLocation)
                        && isInRange(destinationIp, rule.getDestinationNetwork())
                ) {
                    ruleMatched = rule;
                    break;
                }
            }
        } else {
            List<Rule> ruleForExternalDestination = ruleStream.filter(rule -> rule.getRequireSocksProxy()).collect(Collectors.toList());
            for (Rule rule : ruleForExternalDestination) {
                if (rule.getSourceLocation().equals(sourceLocation)
                        && isInRange(rule.getSourceNetwork(), sourceIp)) {
                    ruleMatched = rule;
                    break;
                }
            }
        }
        return ruleMatched;
    }




    private static boolean isInRange(String ipAddress, String network) {
        SubnetUtils subnetUtils = new SubnetUtils(network);
        return subnetUtils.getInfo().isInRange(ipAddress);
    }

    private static boolean isPrivateIP(String ipAddress) {
        boolean isValid = false;

        if (ipAddress != null && !ipAddress.isEmpty()) {
            String[] ip = ipAddress.split("\\.");
            short[] ipNumber = new short[] {
                    Short.parseShort(ip[0]),
                    Short.parseShort(ip[1]),
                    Short.parseShort(ip[2]),
                    Short.parseShort(ip[3])
            };

            if (ipNumber[0] == 10) { // Class A
                isValid = true;
            } else if (ipNumber[0] == 172 && (ipNumber[1] >= 16 && ipNumber[1] <= 31)) { // Class B
                isValid = true;
            } else if (ipNumber[0] == 192 && ipNumber[1] == 168) { // Class C
                isValid = true;
            }
        }

        return isValid;
    }
}
