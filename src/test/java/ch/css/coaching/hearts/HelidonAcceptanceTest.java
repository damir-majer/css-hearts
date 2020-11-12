package ch.css.coaching.hearts;

import ch.css.coaching.hearts.decoder.GameStartedDecoder;
import io.helidon.common.http.MediaType;
import io.helidon.media.jsonp.JsonpSupport;
import io.helidon.webclient.WebClient;
import io.helidon.webserver.WebServer;
import org.glassfish.tyrus.client.ClientManager;
import org.junit.jupiter.api.*;

import javax.websocket.ClientEndpointConfig;
import javax.websocket.DeploymentException;
import java.io.IOException;
import java.net.URI;
import java.time.Duration;
import java.util.Collections;
import java.util.concurrent.TimeUnit;

import static java.util.concurrent.TimeUnit.MINUTES;
import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.await;

class HelidonAcceptanceTest {

    private static WebServer webServer;
    private static WebClient webClient;

    @BeforeAll
    public static void startTheServer() throws Exception {
        webServer = HelidonServer.start();

        long timeout = 2000; // 2 seconds should be enough to start the server
        long now = System.currentTimeMillis();

        while (!webServer.isRunning()) {
            Thread.sleep(100);
            if ((System.currentTimeMillis() - now) > timeout) {
                Assertions.fail("Failed to start webserver");
            }
        }

        webClient = WebClient.builder()
                .baseUri("http://localhost:" + webServer.port())
                .addMediaSupport(JsonpSupport.create())
                .build();
    }

    @AfterAll
    public static void stopServer() throws Exception {
        if (webServer != null) {
            webServer.shutdown()
                    .toCompletableFuture()
                    .get(10, TimeUnit.SECONDS);
        }
    }

    @Test
    void serverEndpoints_ServerStarted_HealthAndMetricsUp() throws Exception {
        webClient.get()
                .path("/health")
                .request()
                .thenAccept(response -> assertThat(response.status().code()).isEqualTo(200))
                .toCompletableFuture()
                .get();

        webClient.get()
                .path("/metrics")
                .request()
                .thenAccept(response -> assertThat(response.status().code()).isEqualTo(200))
                .toCompletableFuture()
                .get();
    }

    @Test
    void serverEndpoints_ServerStarted_RootReturnsIndexHtml() throws Exception {
        webClient.get()
                .path("/")
                .request()
                .thenAccept(response -> assertThat(response.headers().contentType()).contains(MediaType.TEXT_HTML))
                .toCompletableFuture()
                .get();
    }

    @Test
    void clientRegistration_ClientRequestsRegistration_ClientWebSocketOpen() throws IOException, DeploymentException {
        WebsocketTestClient player = new WebsocketTestClient();
        registerClientSocket(player);
        assertThat(player.isOpen()).isTrue();
    }

    @Test
    @Disabled("Await is not working, yet")
    void gameInitialization_FourClientsRequestRegistration_AllClientsReceivedGameStartedMessage() throws IOException, DeploymentException {
        WebsocketTestClient player1Socket = new WebsocketTestClient();
        WebsocketTestClient player2Socket = new WebsocketTestClient();
        WebsocketTestClient player3Socket = new WebsocketTestClient();
        WebsocketTestClient player4Socket = new WebsocketTestClient();

        registerClientSocket(player1Socket);
        registerClientSocket(player2Socket);
        registerClientSocket(player3Socket);
        registerClientSocket(player4Socket);

        await().atMost(1, MINUTES).untilAsserted(() -> assertThat(player1Socket.gameStarted()).isTrue());
        await().atMost(1, MINUTES).untilAsserted(() -> assertThat(player2Socket.gameStarted()).isTrue());
        await().atMost(1, MINUTES).untilAsserted(() -> assertThat(player3Socket.gameStarted()).isTrue());
        await().atMost(1, MINUTES).untilAsserted(() -> assertThat(player4Socket.gameStarted()).isTrue());
    }

    private void registerClientSocket(WebsocketTestClient player1Socket) throws DeploymentException, IOException {
        ClientManager.createClient().connectToServer(player1Socket,
                ClientEndpointConfig.Builder.create()
                        .decoders(Collections.singletonList(GameStartedDecoder.class))
                        .build(), URI.create("ws://localhost:" + webServer.port() + "/")
        );
    }

}
