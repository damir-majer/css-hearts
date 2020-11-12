package ch.css.coaching.hearts.domain;

import java.util.ArrayList;
import java.util.List;

public class GameOfHeartsDeck implements Deck {

    List<Card> cards = new ArrayList<>();

    public GameOfHeartsDeck() {
        reset();
    }

    @Override
    public void reset() {
        cards.clear();
        for (int suit = Card.NO_OF_SUITS - 1; suit >= 0; suit--) {
            for (int rank = Card.NO_OF_RANKS - 1; rank >= 0 ; rank--) {
                cards.add(new Card(rank, suit));
            }
        }
    }

    @Override
    public void shuffle() {
    }

    @Override
    public List<Card> dealNumberOfCards(int number) throws NoCardsAvailableException {
        if (number > cards.size()) {
            throw new NoCardsAvailableException("Not that many cards available in deck.");
        }
        List<Card> result = new ArrayList<>();
        while (result.size() < number) {
            result.add(cards.remove(0));
        }
        return result;
    }
}
