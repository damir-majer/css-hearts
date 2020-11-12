package ch.css.coaching.hearts.domain;

import java.util.List;

public class Game {

    private final Deck deck;
    private final List<Player> playerList;

    public Game(Deck deck, List<Player> playerList) {
        this.deck = deck;
        this.playerList = playerList;
    }

    public ch.css.coaching.hearts.domain.Round createNewRound(Table table) {
    }
}
