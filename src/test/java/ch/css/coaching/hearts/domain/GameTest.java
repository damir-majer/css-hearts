package ch.css.coaching.hearts.domain;

import org.junit.jupiter.api.Test;

import java.util.List;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

class GameTest {

    @Test
    void acceptanceTest() {
        List<Player> playerList = asList(new Player(), new Player(), new Player(), new Player());
        Game sut = new Game(new Deck(), playerList);
        Player nexPlayer = sut.getNextPlayer();
        GameState gameState = sut.playMove(nextPlayer, card);




        Round round = sut.createNewRound(new Table());

        assertThat(round.getNextPlayer()).equalTo()
        //Create new Deck
        Player nextPlayer = roundState.getNextPlayer();
        assertThat(playerList).contains(nextPlayer);

        roundState = round.playMove(Player player, Card card);
        //for number players dealCards
        //determine first player
        //for number of cards in users hands
        // for number of players
        //   select Move for player -->
        //   validateMove for choosen card
        //   add card to table
        //
        // calculateRoundWinner
        //calculateGameWinner
    }

}