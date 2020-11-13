package ch.css.coaching.hearts.domain;

import java.util.List;

public class Game {

    private final Deck deck;
    private final List<Player> playerList;

    public Game(Deck deck, List<Player> playerList) {
        this.deck = deck;
        this.playerList = playerList;
    }

    public GameState start() {
        return new GameState(null);
    }

    public GameState playMove(Player player, Card card) {
        return new GameState(null);
    }
}
