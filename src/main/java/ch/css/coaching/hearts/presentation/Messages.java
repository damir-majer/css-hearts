package ch.css.coaching.hearts.presentation;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Messages {

    private Messages() {
    }

    public interface Message {
        default String toJson() {
            try {
                return new ObjectMapper().writeValueAsString(this);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static class GameStarted implements Message {
        public final String type = GameStarted.class.getSimpleName();
    }
}
