package ch.css.coaching.hearts.decoder;

import ch.css.coaching.hearts.presentation.Messages;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.websocket.DecodeException;

public class GameStartedDecoder extends MessageDecoder<Messages.GameStarted> {

    @Override
    public Messages.GameStarted decode(String s) throws DecodeException {
        try {
            return new ObjectMapper().readValue(s, Messages.GameStarted.class);
        } catch (JsonProcessingException e) {
            throw new DecodeException(s, "failed to parse json", e);
        }
    }

    @Override
    public boolean willDecode(String s) {
        return s.contains(Messages.GameStarted.class.getSimpleName());
    }
}
