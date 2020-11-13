package ch.css.coaching.hearts.presentation;

import ch.css.coaching.hearts.HelidonServer;
import ch.css.coaching.hearts.WebSocketGame;

import javax.websocket.Endpoint;
import javax.websocket.EndpointConfig;
import javax.websocket.Session;

public class GameEndpoint extends Endpoint {

    private final static WebSocketGame webSocketGame = HelidonServer.mainModule.webSocketGame;

    @Override
    public void onOpen(Session session, EndpointConfig config) {
        webSocketGame.addPlayerSession(session);
    }


}
