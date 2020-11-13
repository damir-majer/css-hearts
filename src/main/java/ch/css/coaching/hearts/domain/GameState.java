package ch.css.coaching.hearts.domain;

import java.util.Optional;

public class GameState {

    private Player winner;

    public GameState(Player winner) {
        this.winner = winner;
    }

    public Optional<Player> getWinner() {
        return Optional.ofNullable(winner);
    }
}
