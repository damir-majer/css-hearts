package ch.css.coaching.hearts.domain;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.List;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class GameTest {

    @Test
    void acceptanceTest() {
        Player player1 = new Player();
        Player player2 = new Player();
        Player player3 = new Player();
        Player player4 = new Player();
        List<Player> playerList = asList(player1, player2, player3, player4);
        Deck deckMock = mock(Deck.class);

        when(deckMock.dealNumberOfCards(any()))
                .thenReturn(List.of(new Card(Card.ACE, Card.CLUBS), new Card(Card.ACE, Card.HEARTS)))
                .thenReturn(List.of(new Card(Card.KING, Card.CLUBS), new Card(Card.KING, Card.HEARTS)))
                .thenReturn(List.of(new Card(Card.QUEEN, Card.CLUBS), new Card(Card.QUEEN, Card.HEARTS)))
                .thenReturn(List.of(new Card(Card.JACK, Card.CLUBS), new Card(Card.JACK, Card.HEARTS)));

        Game sut = new Game(deckMock, playerList);
        GameState initialGameState = sut.start();
        sut.playMove()


        assertThat(lastGameState.getWinner()).contains(player3);


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