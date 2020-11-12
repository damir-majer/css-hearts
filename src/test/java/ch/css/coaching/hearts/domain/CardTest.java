package ch.css.coaching.hearts.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class CardTest {

    @Test
    void equals_SameCardRankAndSuit_AreEqual() {
        Card aceCard = new Card(Card.ACE, Card.CLUBS);
        Card aceCard2 = new Card(Card.ACE, Card.CLUBS);

        assertThat(aceCard).isEqualTo(aceCard2);
    }

    @Test
    void equals_DifferentCardRankAndSameSuit_AreNotEqual() {
        Card aceCard = new Card(Card.ACE, Card.CLUBS);
        Card kingCard = new Card(Card.KING, Card.CLUBS);

        assertThat(aceCard).isNotEqualTo(kingCard);
    }

    @Test
    void equals_SameCardRankAndDifferentSuit_AreNotEqual() {
        Card aceCardClubs = new Card(Card.ACE, Card.CLUBS);
        Card aceCardHearts = new Card(Card.ACE, Card.HEARTS);

        assertThat(aceCardClubs).isNotEqualTo(aceCardHearts);
    }

    @Test
    void compareToWithinSuit_WithinSameSuit_CompareSuccessfull() {
        Card aceCard = new Card(Card.ACE, Card.CLUBS);
        Card nineCard = new Card(Card.NINE, Card.CLUBS);

        assertThat(aceCard.compareToWithinSuit(nineCard)).isEqualTo(1);
        assertThat(nineCard.compareToWithinSuit(aceCard)).isEqualTo(-1);
        assertThat(aceCard.compareToWithinSuit(aceCard)).isZero();
    }

    @Test
    void compareToWithinSuit_ThrowsException_WhenCardsInDifferentSuit() {
        Card aceCard = new Card(Card.ACE, Card.HEARTS);
        Card nineCard = new Card(Card.NINE, Card.CLUBS);

        assertThrows(IllegalArgumentException.class, () -> aceCard.compareToWithinSuit(nineCard));
    }
}