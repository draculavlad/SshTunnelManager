# SshTunnelManager

## how to build
```
mvn clean package -DskipTests
```

# API

# RULE

## create example 1(local forwarding)
api:
```
curl -v -X PUT http://localhost/rule/create -H "Content-Type: application/json" --data '{"sourceNetwork":"x.x.x.x/24","sourceLocation":"A","destinationNetwork":"x.x.x.x/20","destinationLocation":"B","requireSshTunnel":"true","requireRemoteForwarding":"false","tunnelBridgeHost":"x.x.x.x","tunnelBridgeUid":"xxx","tunnelBridgePwd":"xxx","tunnelBridgeSshPort":"xx","requireSocksProxy":"false","proxyHost":null,"proxyPort":null}'
```
req body:
```
{"sourceNetwork":"x.x.x.x/24","sourceLocation":"B","destinationNetwork":"x.x.x.x/24","destinationLocation":"B","requireSshTunnel":"true","requireRemoteForwarding":"false","tunnelBridgeHost":"x.x.x.x","tunnelBridgeUid":"xxx","tunnelBridgePwd":"xxx","tunnelBridgeSshPort":"xx","requireSocksProxy":"false","proxyHost":null,"proxyPort":null}
```

## create example 2 (Remote forwarding)
api:
```
curl -v -X PUT http://localhost/rule/create -H "Content-Type: application/json" --data '{"destinationNetwork":"x.x.x.x/24","destinationLocation":"A","sourceNetwork":"x.x.x.x/24","sourceLocation":"A","requireSshTunnel":"true","requireRemoteForwarding":"true","tunnelBridgeHost":"x.x.x.x","tunnelBridgeUid":"xxx","tunnelBridgePwd":"xxx","tunnelBridgeSshPort":"xx","requireSocksProxy":"true","proxyHost":"x.x.x.x","proxyPort":"xxxxx"}'
```
req body:
```
{"destinationNetwork":"x.x.x.x/24","destinationLocation":"A","sourceNetwork":"x.x.x.x/24","sourceLocation":"B","requireSshTunnel":"true","requireRemoteForwarding":"true","tunnelBridgeHost":"x.x.x.x","tunnelBridgeUid":"xxx","tunnelBridgePwd":"xxx","tunnelBridgeSshPort":"xx","requireSocksProxy":"true","proxyHost":"x.x.x.x","proxyPort":"xxxx"}
```


## delete example
api:
```
curl -v -XDELETE http://localhost/rule/delete?id=2
```
req params:
```
id=2
```

## list example
api:
```
curl -v http://localhost/rule/list
```
response;
```
[{"destinationLocation":"A","destinationNetwork":"x.x.x.x/24","id":1,"requireRemoteForwarding":false,"requireSocksProxy":false,"requireSshTunnel":true,"sourceLocation":"B","sourceNetwork":"x.x.x.x/22","tunnelBridgeHost":"x.x.x.x","tunnelBridgePwd":"xxx","tunnelBridgeSshPort":xx,"tunnelBridgeUid":"xxx","valid":true}]
```

## get example
api:
```
curl -v http://localhost/rule/get?id=1
```
req params:
```
id=1
```
resp:
```
{"destinationLocation":"A","destinationNetwork":"x.x.x.x/24","id":1,"requireRemoteForwarding":false,"requireSocksProxy":false,"requireSshTunnel":true,"sourceLocation":"B","sourceNetwork":"x.x.x.x/22","tunnelBridgeHost":"x.x.x.x","tunnelBridgePwd":"xxx","tunnelBridgeSshPort":xx,"tunnelBridgeUid":"xxx","valid":true}
```

## find exapmle
api:
```curl
curl http://localhost/rule/find?sourceIp=x.x.x.x&sourceLocation=A&destinationIp=x.x.x.x&destinationLocation=B
```
req params:
```
sourceIp=x.x.x.x&sourceLocation=A&destinationIp=x.x.x.x&destinationLocation=B
```
resp:
```
{"destinationLocation":"A","destinationNetwork":"x.x.x.x/24","id":1,"requireRemoteForwarding":false,"requireSocksProxy":false,"requireSshTunnel":true,"sourceLocation":"B","sourceNetwork":"x.x.x.x/22","tunnelBridgeHost":"x.x.x.x","tunnelBridgePwd":"xxx","tunnelBridgeSshPort":xx,"tunnelBridgeUid":"xxx","valid":true}
```

# Tunnel

## create local forwarding example
api:
```
curl -X PUT　http://localhost/tunnel/forwarding/local/create?ruleId=x&destinationIp=x.x.x.x&destinationPort=xx
```

## create remote forwarding example
```
curl -X PUT http://localhost/tunnel/forwarding/remote/create?ruleId=x&servicePort=xxxx
```

## list example
api:
```
curl http://localhost/tunnel/list
```
resp:
```
[
    {
        "hasError": false,
        "running": true,
        "sshTunnel": {
            "accessPort": xxxxx,
            "bridgeHostIp": "x.x.x.x",
            "bridgeHostSshPort": xx,
            "bridgeHostSshPwd": "xxxx",
            "bridgeHostSshUid": "xxxx",
            "destinationIp": "x.x.x.x",
            "destinationPort": xx,
            "id": "1adaf4e0-9f6f-4539-b7b4-d9b0ad7aa630",
            "ruleId": x
        }
    }
]
```

## delete example
api:
```
curl -X DELETE http://localhost/tunnel/kill?tunnelId=1adaf4e0-9f6f-4539-b7b4-d9b0ad7aa630
```
