package ch.css.coaching.hearts;

import ch.css.coaching.hearts.presentation.Messages;

import javax.websocket.Endpoint;
import javax.websocket.EndpointConfig;
import javax.websocket.Session;

public class WebsocketTestClient extends Endpoint {

    private boolean gameStarted;
    private Session session;

    @Override
    public void onOpen(Session session, EndpointConfig config) {
        this.session = session;
        session.addMessageHandler(Messages.Message.class, message -> checkGameStarted(message));
    }

    public boolean isOpen() {
        return session.isOpen();
    }

    public boolean gameStarted() {
        return gameStarted;
    }

    private void checkGameStarted(Messages.Message message) {
        if (message instanceof Messages.GameStarted) {
            gameStarted = true;
        }
    }
}
