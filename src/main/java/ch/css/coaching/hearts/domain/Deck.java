package ch.css.coaching.hearts.domain;

import java.util.List;

public interface Deck {

    void reset();

    void shuffle();

    List<Card> dealNumberOfCards(int number) throws NoCardsAvailableException;
}
