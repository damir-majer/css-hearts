package ch.css.coaching.hearts;

import io.helidon.common.http.MediaType;
import io.helidon.media.jsonp.JsonpSupport;
import io.helidon.webclient.WebClient;
import io.helidon.webserver.WebServer;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;

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
    void healthAndMetricsUp() throws Exception {
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
    void rootReturnsIndexHtml() throws Exception {
        webClient.get()
                .path("/")
                .request()
                .thenAccept(response -> assertThat(response.headers().contentType().get()).isEqualTo(MediaType.TEXT_HTML))
                .toCompletableFuture()
                .get();
    }
}
