package ch.css.coaching.hearts.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    @Test
    void acceptanceTest() {
        Game sut = new Game(new Player(), new Player(), new Player(), new Player());
        Round round = sut.createNewRound(new Deck(), new Table());
        //Create new Deck
        RoundState roundState = round.getCurrentState();
        Player nextPlayer = roundState.getNextPlayer();
        roundState = round.playNextRound();
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