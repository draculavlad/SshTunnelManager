package su.jacob.sshTunnelManager.common.dao.mapper;

import org.apache.ibatis.annotations.*;
import su.jacob.sshTunnelManager.common.entity.Rule;

import java.util.List;

/**
 * @author Jacob.Su
 * @mail: 450416583@qq.com
 */
@Mapper
public interface RuleMapper {

    @Insert("insert into t_rule (id, " +
            "source_network, source_location, " +
            "destination_network, destination_location, " +
            "require_ssh_tunnel, require_remote_forwarding, " +
            "tunnel_bridge_host, tunnel_bridge_uid, tunnel_bridge_pwd, tunnel_bridge_ssh_port," +
            "require_socks_proxy, proxy_host, proxy_port, " +
            "valid" +
            ") values (null, " +
            "#{sourceNetwork}, #{sourceLocation}," +
            "#{destinationNetwork}, #{destinationLocation}, " +
            "#{requireSshTunnel}, #{requireRemoteForwarding}, " +
            "#{tunnelBridgeHost}, #{tunnelBridgeUid}, #{tunnelBridgePwd}, #{tunnelBridgeSshPort}," +
            "#{requireSocksProxy}, #{proxyHost}, #{proxyPort}," +
            "#{valid}" +
            ")")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void add(Rule rule);

    @Update("update t_rule set " +
            "set source_network=#{source_network}, " +
            " source_location = #{sourceLocation}, " +
            " destination_network = #{destinationNetwork}, " +
            " destination_location = #{destinationLocation}, " +
            " require_ssh_tunnel = #{requireSshTunnel}, " +
            " require_remote_forwarding = #{requireRemoteForwarding}, " +
            " tunnel_bridge_host = #{tunnelBridgeHost}, " +
            " tunnel_bridge_uid = #{tunnelBridgeUid}, " +
            " tunnel_bridge_pwd = #{tunnelBridgePwd}, " +
            " tunnel_bridge_ssh_port = #{tunnelBridgeSshPort}, " +
            " require_socks_proxy = #{requireSocksProxy}, " +
            " proxy_host = #{proxyHost}, " +
            " proxy_port = #{proxyPort}, " +
            " valid = #{valid}" +
            "where id=#{id}")
    void update(Rule rule);

    @Delete("deleteById from t_rule where id=#{id}")
    void deleteById(int id);

    @Select("select * from t_rule where id=#{id}")
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "source_network", property = "sourceNetwork"),
            @Result(column = "source_location", property = "sourceLocation"),
            @Result(column = "destination_network", property = "destinationNetwork"),
            @Result(column = "destination_location", property = "destinationLocation"),
            @Result(column = "require_ssh_tunnel", property = "requireSshTunnel"),
            @Result(column = "require_remote_forwarding", property = "requireRemoteForwarding"),
            @Result(column = "tunnel_bridge_host", property = "tunnelBridgeHost"),
            @Result(column = "tunnel_bridge_uid", property = "tunnelBridgeUid"),
            @Result(column = "tunnel_bridge_pwd", property = "tunnelBridgePwd"),
            @Result(column = "tunnel_bridge_ssh_port", property = "tunnelBridgeSshPort"),
            @Result(column = "require_socks_proxy", property = "requireSocksProxy"),
            @Result(column = "proxy_host", property = "proxyHost"),
            @Result(column = "proxy_port", property = "proxyPort"),
            @Result(column = "valid", property = "valid")
    })
    Rule getBydId(int id);

    @Select("select * from t_rule")
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "source_network", property = "sourceNetwork"),
            @Result(column = "source_location", property = "sourceLocation"),
            @Result(column = "destination_network", property = "destinationNetwork"),
            @Result(column = "destination_location", property = "destinationLocation"),
            @Result(column = "require_ssh_tunnel", property = "requireSshTunnel"),
            @Result(column = "require_remote_forwarding", property = "requireRemoteForwarding"),
            @Result(column = "tunnel_bridge_host", property = "tunnelBridgeHost"),
            @Result(column = "tunnel_bridge_uid", property = "tunnelBridgeUid"),
            @Result(column = "tunnel_bridge_pwd", property = "tunnelBridgePwd"),
            @Result(column = "tunnel_bridge_ssh_port", property = "tunnelBridgeSshPort"),
            @Result(column = "require_socks_proxy", property = "requireSocksProxy"),
            @Result(column = "proxy_host", property = "proxyHost"),
            @Result(column = "proxy_port", property = "proxyPort"),
            @Result(column = "valid", property = "valid")
    })
    List<Rule> getALl();
}
