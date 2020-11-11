package ch.css.coaching.hearts.decoder;

import ch.css.coaching.hearts.presentation.Messages;

import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;

public abstract class MessageDecoder<T extends Messages.Message> implements Decoder.Text<T> {

    @Override
    public void init(EndpointConfig config) {

    }

    @Override
    public void destroy() {

    }
}
