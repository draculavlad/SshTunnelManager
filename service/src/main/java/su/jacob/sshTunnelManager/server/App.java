package su.jacob.sshTunnelManager.server;

import su.jacob.sshTunnelManager.server.http.RestServer;
import org.apache.commons.cli.*;
import su.jacob.sshTunnelManager.server.socks.ProxyServer;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * @author Jacob.Su
 * @mail: 450416583@qq.com
 */
public class App {

    private static final Executor executor = Executors.newFixedThreadPool(2);

    public static void main(String... args) {

        Options options = new Options();
        Option input = new Option("f", "config", true, "configuration file path");
        input.setRequired(true);
        options.addOption(input);

        CommandLineParser cliParser = new DefaultParser();
        HelpFormatter formatter = new HelpFormatter();
        CommandLine cmd = null;

        try {
            cmd = cliParser.parse(options, args);
        } catch (ParseException e) {
            System.out.println(e.getMessage());
            formatter.printHelp("proxyservice", options);
            System.exit(1);
        }

        String configFilePath = cmd.getOptionValue("config");
        System.out.println("config file path: " + configFilePath);

        Properties globalProps = null;
        try {
            globalProps = readConfig(configFilePath);
        } catch (IOException e) {
            System.out.println(configFilePath + " has error:");
            System.out.println(e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }

        int restPort = Integer.valueOf(globalProps.getProperty("service.rest.port", "80"));
        int socksPort = Integer.valueOf(globalProps.getProperty("service.socks.port", "1080"));

        ProxyServer proxyServer = new ProxyServer(socksPort);
        executor.execute(proxyServer);
        RestServer restServer = new RestServer(restPort);
        executor.execute(restServer);


    }
    private static Properties readConfig(String configFilePath) throws IOException {
        Properties properties = new Properties();
        InputStream is = new FileInputStream(new File(configFilePath));
        properties.load(is);
        return properties;
    }


}
