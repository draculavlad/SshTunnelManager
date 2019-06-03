package su.jacob.sshTunnelManager.server.http.controller;

import su.jacob.sshTunnelManager.common.entity.Rule;
import su.jacob.sshTunnelManager.common.entity.SshTunnelStatus;
import su.jacob.sshTunnelManager.server.http.service.RuleService;
import su.jacob.sshTunnelManager.server.http.service.TunnelService;
import su.jacob.sshTunnelManager.server.http.service.impl.RuleServiceImpl;
import su.jacob.sshTunnelManager.server.http.service.impl.TunnelServiceImpl;
import org.leo.web.annotation.*;
import org.leo.web.rest.HttpStatus;
import org.leo.web.rest.ResponseEntity;

import java.util.List;

/**
 * @author Jacob.Su
 * @mail: 450416583@qq.com
 */
@RestController
@RequestMapping("/tunnel")
public class TunnelController {

    private TunnelService tunnelService;
    private RuleService ruleService;

    public TunnelController() {
        this.tunnelService = new TunnelServiceImpl();
        this.ruleService = new RuleServiceImpl();
    }

    @GetMapping("/status")
    @JsonResponse
    public ResponseEntity<SshTunnelStatus> checkStatus(@RequestParam("tunnelId") String tunnelId) {
        SshTunnelStatus sshTunnelStatus = tunnelService.checkStatusByTunnelId(tunnelId);
        return ResponseEntity.ok().build(sshTunnelStatus);
    }

    @GetMapping("/list")
    @JsonResponse
    public ResponseEntity<List<SshTunnelStatus>> listTunnel() {
        List<SshTunnelStatus> tunnelList = tunnelService.getAllTunnels();
        return ResponseEntity.ok().build(tunnelList);
    }

    @DeleteMapping("/kill")
    public ResponseEntity<?> killTunnel(@RequestParam("tunnelId") String tunnelId) {
        tunnelService.deleteTunnelByTunnelId(tunnelId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PutMapping("/forwarding/remote/create")
    public ResponseEntity<?> createRemoteForwardingTunnel(@RequestParam("ruleId") int ruleId,
                                                          @RequestParam("servicePort") int servicePort) {
        Rule rule = ruleService.getRuleById(ruleId);
        tunnelService.createRemoteForwardingTunnel(rule, servicePort);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/forwarding/local/create")
    public ResponseEntity<?> createLocalForwardingTunnel(@RequestParam("ruleId") int ruleId,
                                                         @RequestParam("destinationIp") String destinationIp,
                                                         @RequestParam("destinationPort") int destinationPort) {
        Rule rule = ruleService.getRuleById(ruleId);
        tunnelService.createLocalForwardingTunnel(rule, destinationIp, destinationPort);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
