package ch.css.coaching.hearts.domain;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Table {

    private final Map<Player, Card> cardsOnTable;

    public Table() {
        cardsOnTable = new HashMap<>();
    }

    public Map<Player, Card> getCardsOnTable() {
        return Collections.unmodifiableMap(cardsOnTable);
    }

    public void putCardOnTable(Player player, Card playedCard) {
        cardsOnTable.put(player, playedCard);
    }
}
