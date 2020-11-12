package ch.css.coaching.hearts.domain;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class GameOfHeartsDeckTest {

    @Test
    void dealNumberOfCards_Deal13Cards_Received13Cards() throws NoCardsAvailableException {
        Deck cut = new GameOfHeartsDeck();
        List<Card> cards = cut.dealNumberOfCards(13);

        assertThat(cards).hasSize(13);
    }

    @Test
    void reset() throws NoCardsAvailableException {
        Deck cut = new GameOfHeartsDeck();
        cut.dealNumberOfCards(42);
        cut.reset();
        assertThat(cut.dealNumberOfCards(52)).hasSize(52);
    }
}