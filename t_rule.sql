CREATE TABLE `t_rule` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `source_network` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `source_location` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `destination_network` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `destination_location` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `require_ssh_tunnel` tinyint(4) NOT NULL,
  `require_remote_forwarding` tinyint(4) NOT NULL,
  `tunnel_bridge_host` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `tunnel_bridge_uid` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `tunnel_bridge_pwd` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `tunnel_bridge_ssh_port` int(11) DEFAULT NULL,
  `require_socks_proxy` tinyint(4) NOT NULL,
  `proxy_host` varchar(15) DEFAULT NULL,
  `proxy_port` int(11) DEFAULT NULL,
  `valid` tinyint(4) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
