package ru.athena.logging.util;

import org.slf4j.Logger;
import org.springframework.core.env.Environment;

import java.net.InetAddress;
import java.net.UnknownHostException;

import static java.util.Objects.nonNull;

/**
 * Logging utility class.
 */
public final class LoggingUtil {

    private LoggingUtil() {
        throw new UnsupportedOperationException("Operation not supported");
    }

    /**
     * Initial spring application start log.
     *
     * @param env spring environment.
     * @param log logger.
     */
    public static void logApplicationStartup(Environment env, Logger log) {
        String applicationName = env.getProperty("spring.application.name");
        String protocol = nonNull(env.getProperty("server.ssl.key-store")) ? "https" : "http";
        String serverPort = env.getProperty("server.port", "unknown");
        String contextPath = env.getProperty("server.servlet.context-path", "/");
        if (contextPath.isBlank()) {
            contextPath = "/";
        }
        String hostAddress = "localhost";
        try {
            hostAddress = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            log.warn("The host name could not be determined, using `localhost` as fallback");
        }

        log.info("\n----------------------------------------------------------\n\t" +
                        "Application '{}' is running! Access URLs:\n\t" +
                        "Local: \t\t{}://localhost:{}{}\n\t" +
                        "External: \t{}://{}:{}{}\n\t" +
                        "Profile(s): \t{}\n----------------------------------------------------------",
                applicationName,
                protocol,
                serverPort,
                contextPath,
                protocol,
                hostAddress,
                serverPort,
                contextPath,
                env.getActiveProfiles());

        String configServerStatus = env.getProperty("configserver.status", "Not found or not setup for this application");
        log.info("\n----------------------------------------------------------\n\t" +
                "Config Server: \t{}\n----------------------------------------------------------", configServerStatus);
    }
}