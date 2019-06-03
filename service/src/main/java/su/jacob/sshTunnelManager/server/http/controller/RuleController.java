package su.jacob.sshTunnelManager.server.http.controller;

import su.jacob.sshTunnelManager.common.entity.Rule;
import su.jacob.sshTunnelManager.server.http.service.RuleService;
import su.jacob.sshTunnelManager.server.http.service.impl.RuleServiceImpl;
import org.leo.web.annotation.*;
import org.leo.web.rest.HttpStatus;
import org.leo.web.rest.ResponseEntity;

import java.util.List;

/**
 * @author Jacob.Su
 * @mail: 450416583@qq.com
 */
@RestController
@RequestMapping("/rule")
public class RuleController {

    private RuleService ruleService;

    public RuleController() {
        ruleService = new RuleServiceImpl();
    }

    @GetMapping("/get")
    @JsonResponse
    public ResponseEntity<Rule> getRule(@RequestParam("id") int id) {
        Rule rule = ruleService.getRuleById(id);
        return ResponseEntity.ok().build(rule);
    }

    @GetMapping("/find")
    @JsonResponse
    public ResponseEntity<Rule> findRule(@RequestParam("sourceIp") String sourceIp,
                                         @RequestParam("sourceLocation") String sourceLocation,
                                         @RequestParam("destinationIp") String destinationIp,
                                         @RequestParam("destinationLocation") String destinationLocation) {
        Rule rule = ruleService.findMatchedRule(sourceIp,
                sourceLocation,
                destinationIp,
                destinationLocation);
        return ResponseEntity.ok().build(rule);
    }

    @GetMapping("/list")
    @JsonResponse
    public ResponseEntity<List<Rule>> listRule() {
        List<Rule> ruleList = ruleService.getAllRules();
        return ResponseEntity.ok().build(ruleList);
    }

    @DeleteMapping("/deleteById")
    public ResponseEntity<?> deleteRule(@RequestParam("id") int id) {
        ruleService.deleteRule(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PutMapping("/create")
    public ResponseEntity<Rule> createRule(@RequestBody Rule rule) {
        ruleService.addRule(rule);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
