package ch.css.coaching.hearts.domain;

import java.util.List;

public class Game {

    private final Deck deck;
    private final List<Player> playerList;
    private GameState gameState;

    public Game(Deck deck, List<Player> playerList) {
        this.deck = deck;
        this.playerList = playerList;
        this.gameState = new GameState(new Table());
    }

    public GameState getCurrentState() {
        return gameState;
    }

    public GameState playMove(Player player, Card card) {

        return new GameState(null);
    }
}
