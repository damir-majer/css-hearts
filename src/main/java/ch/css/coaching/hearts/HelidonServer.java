package ch.css.coaching.hearts;

import ch.css.coaching.hearts.presentation.GameEndpoint;
import io.helidon.config.Config;
import io.helidon.health.HealthSupport;
import io.helidon.health.checks.HealthChecks;
import io.helidon.media.jsonp.JsonpSupport;
import io.helidon.metrics.MetricsSupport;
import io.helidon.webserver.Routing;
import io.helidon.webserver.StaticContentSupport;
import io.helidon.webserver.WebServer;
import io.helidon.webserver.tyrus.TyrusSupport;

import javax.websocket.server.ServerEndpointConfig;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class HelidonServer {

    public static final MainModule mainModule = new MainModule();

    private static final Logger LOGGER = Logger.getLogger(HelidonServer.class.getName());

    private HelidonServer() {
    }


    /**
     * Start the server.
     *
     * @return the created {@link WebServer} instance
     * @throws IOException if there are problems reading logging properties
     */
    static WebServer start() throws IOException {
        setupLogging();

        // By default this will pick up application.yaml from the classpath
        Config config = Config.create();

        WebServer server = WebServer.builder(createRouting())
                .config(config.get("server"))
                .addMediaSupport(JsonpSupport.create())
                .build();

        server.start()
                .thenAccept(ws -> {
                    LOGGER.info("Web server is up! http://localhost:" + ws.port());
                    ws.whenShutdown().thenRun(()
                            -> LOGGER.info("Web server is DOWN. Good bye!"));
                })
                .exceptionally(t -> {
                    LOGGER.log(Level.SEVERE, "Startup failed: ", t);
                    return null;
                });

        // Server threads are not daemon. No need to block. Just react.

        return server;
    }

    /**
     * Creates new {@link Routing}.
     * @return routing configured with JSON support, a health check, and a service
     */
    private static Routing createRouting() {

        MetricsSupport metrics = MetricsSupport.create();
        HealthSupport health = HealthSupport.builder()
                .addLiveness(HealthChecks.deadlockCheck(), HealthChecks.heapMemoryCheck())
                .build();

        return Routing.builder()
                .register(health)  // Health at "/health"
                .register(metrics) // Metrics at "/metrics"
                .register("/", TyrusSupport.builder().register(
                        ServerEndpointConfig.Builder.create(GameEndpoint.class, "/{playerName}")
                                .build())
                        .build())
                .register("/", StaticContentSupport.builder("/assets")
                        .welcomeFileName("index.html"))
                .build();
    }

    /**
     * Configure logging from logging.properties file.
     */
    private static void setupLogging() throws IOException {
        try (InputStream is = Main.class.getResourceAsStream("/logging.properties")) {
            LogManager.getLogManager().readConfiguration(is);
        }
    }
}
