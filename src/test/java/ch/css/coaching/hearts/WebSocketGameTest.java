package ch.css.coaching.hearts;

import org.junit.jupiter.api.Test;

import javax.websocket.RemoteEndpoint;
import javax.websocket.Session;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class WebSocketGameTest {

    @Test
    void initializeGame_NoPlayerConnected_LobbyIsNotFull() {
        WebSocketGame sut = new WebSocketGame();
        assertThat(sut.isLobbyFull()).isFalse();
    }

    @Test
    void initializeGame_OnePlayerConnected_LobbyIsNotFull() {
        WebSocketGame sut = new WebSocketGame();

        sut.addPlayerSession(mock(Session.class));

        assertThat(sut.isLobbyFull()).isFalse();
    }

    @Test
    void initializeGame_FourPlayersConnected_LobbyIsFull() {
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