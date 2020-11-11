package ch.css.coaching.hearts;

import org.junit.jupiter.api.Test;

import javax.websocket.RemoteEndpoint;
import javax.websocket.Session;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class WebSocketGameTest {

    @Test
    void initializeGameIsWaitingForFurtherPlayers() {
        WebSocketGame sut = new WebSocketGame();
        assertThat(sut.isLobbyFull()).isFalse();
    }

    @Test
    void initializeGameWithOnePlayerIsWaitingForFurtherPlayers() {
        WebSocketGame sut = new WebSocketGame();

        sut.addPlayerSession(mock(Session.class));

        assertThat(sut.isLobbyFull()).isFalse();
    }

    @Test
    void initializeGameWithFourPlayersIsReady() {
        WebSocketGame sut = new WebSocketGame();
        Session mock = mock(Session.class);
        when(mock.getBasicRemote()).thenReturn(mock(RemoteEndpoint.Basic.class));

        sut.addPlayerSession(mock);
        sut.addPlayerSession(mock);
        sut.addPlayerSession(mock);
        sut.addPlayerSession(mock);

        assertThat(sut.isLobbyFull()).isTrue();
    }

}