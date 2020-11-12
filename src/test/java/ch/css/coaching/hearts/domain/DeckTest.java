package ch.css.coaching.hearts.domain;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class DeckTest {

    @Test
    void dealNumberOfCards_Deal13Cards_Received13Cards() throws NoCardsAvailableException {
        Deck cut = new Deck();

        List<Card> cards = cut.dealNumberOfCards(13);

        assertThat(cards).hasSize(13);
    }

    @Test
    void dealNumberOfCards_DealMoreCardsThanAvailable_ThrowsException() throws NoCardsAvailableException {
        Deck cut = new Deck();

        cut.dealNumberOfCards(42);

        assertThatThrownBy(() -> cut.dealNumberOfCards(52)).isInstanceOf(NoCardsAvailableException.class);
    }
}