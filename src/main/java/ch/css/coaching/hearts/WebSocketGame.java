package ch.css.coaching.hearts;

import ch.css.coaching.hearts.presentation.Messages;

import javax.websocket.Session;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WebSocketGame {
    private List<Session> playerSessions = new ArrayList<>();

    public boolean isLobbyFull() {
        return playerSessions.size() >= 4;
    }

    public void addPlayerSession(Session playerSession) {
        playerSessions.add(playerSession);
        if(isLobbyFull()) {
            triggerGameStart();
        }
    }

    private void triggerGameStart() {
        playerSessions.forEach(playerSession -> {
            try {
                playerSession.getBasicRemote().sendText(new Messages.GameStarted().toJson());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
