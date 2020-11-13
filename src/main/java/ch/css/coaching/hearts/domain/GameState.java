package ch.css.coaching.hearts.domain;

import java.util.Optional;

public class GameState {

    private final Table table;
    private Player winner;

    public GameState(Table table) {
        this(table, null);
    }

    public GameState(Table table, Player winner) {
        this.table = table;
        this.winner = winner;
    }

    public Optional<Player> getWinner() {
        return Optional.ofNullable(winner);
    }
}
