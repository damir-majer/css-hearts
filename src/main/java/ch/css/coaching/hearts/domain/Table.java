package ch.css.coaching.hearts.domain;

import java.util.HashMap;
import java.util.Map;

public class Table {

    private final Map<Player, Card> cardsOnTable;

    public Table() {
        cardsOnTable = new HashMap<>();
    }

}
