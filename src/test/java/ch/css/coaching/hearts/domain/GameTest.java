package ch.css.coaching.hearts.domain;

import org.junit.jupiter.api.Test;

import java.util.List;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class GameTest {

    @Test
    void acceptance_TwoCardsOnly_FirstPlayerWins() throws NoCardsAvailableException {
        Player player1 = new Player();
        Player player2 = new Player();
        Player player3 = new Player();
        Player player4 = new Player();
        List<Player> playerList = asList(player1, player2, player3, player4);
        Deck deckMock = mock(Deck.class);
        when(deckMock.dealNumberOfCards(anyInt()))
                .thenReturn(List.of(new Card(Card.ACE, Card.CLUBS), new Card(Card.ACE, Card.HEARTS)))
                .thenReturn(List.of(new Card(Card.KING, Card.CLUBS), new Card(Card.KING, Card.HEARTS)))
                .thenReturn(List.of(new Card(Card.QUEEN, Card.CLUBS), new Card(Card.QUEEN, Card.HEARTS)))
                .thenReturn(List.of(new Card(Card.JACK, Card.CLUBS), new Card(Card.JACK, Card.HEARTS)));

        Game sut = new Game(deckMock, playerList);
        sut.start();
        // round one
        sut.playMove(player1, new Card(Card.ACE, Card.CLUBS));
        sut.playMove(player2, new Card(Card.KING, Card.CLUBS));
        sut.playMove(player3, new Card(Card.QUEEN, Card.CLUBS));
        sut.playMove(player4, new Card(Card.JACK, Card.CLUBS));
        // round two
        sut.playMove(player1, new Card(Card.ACE, Card.HEARTS));
        sut.playMove(player2, new Card(Card.KING, Card.HEARTS));
        sut.playMove(player3, new Card(Card.QUEEN, Card.HEARTS));
        GameState lastGameState = sut.playMove(player4, new Card(Card.JACK, Card.HEARTS));

        assertThat(lastGameState.getWinner()).contains(player1);
    }

    @Test
    void gameStart_GameInitialized_InitialStateReturned() {
        Game sut = new Game(new Deck(), List.of(new Player(), new Player(), new Player(), new Player()));

        assertThat(sut.start()).isEqualToComparingFieldByField(new GameState(new Table()));
    }

}