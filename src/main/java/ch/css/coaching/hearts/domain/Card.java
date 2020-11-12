package ch.css.coaching.hearts.domain;

public class Card implements Comparable<Card> {
    /**
     * The number of ranks in a deck.
     */
    public static final int NO_OF_RANKS = 13;

    /**
     * The number of suits in a deck.
     */
    public static final int NO_OF_SUITS = 4;

    // The ranks.
    public static final int ACE = 12;
    public static final int KING = 11;
    public static final int QUEEN = 10;
    public static final int JACK = 9;
    public static final int TEN = 8;
    public static final int NINE = 7;
    public static final int EIGHT = 6;
    public static final int SEVEN = 5;
    public static final int SIX = 4;
    public static final int FIVE = 3;
    public static final int FOUR = 2;
    public static final int THREE = 1;
    public static final int DEUCE = 0;

    // The suits.
    public static final int SPADES = 3;
    public static final int HEARTS = 2;
    public static final int CLUBS = 1;
    public static final int DIAMONDS = 0;

    /**
     * The rank.
     */
    private final int rank;

    /**
     * The suit.
     */
    private final int suit;

    /**
     * Constructor based on rank and suit.
     *
     * @param rank The rank.
     * @param suit The suit.
     * @throws IllegalArgumentException If the rank or suit is invalid.
     */
    public Card(int rank, int suit) {
        if (rank < 0 || rank > NO_OF_RANKS - 1) {
            throw new IllegalArgumentException("Invalid rank");
        }
        if (suit < 0 || suit > NO_OF_SUITS - 1) {
            throw new IllegalArgumentException("Invalid suit");
        }
        this.rank = rank;
        this.suit = suit;
    }

    /**
     * Returns the suit.
     *
     * @return The suit.
     */
    public int getSuit() {
        return suit;
    }

    /**
     * Returns the rank.
     *
     * @return The rank.
     */
    public int getRank() {
        return rank;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return (rank * NO_OF_SUITS + suit);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object obj) {
        return obj instanceof Card && obj.hashCode() == hashCode();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int compareTo(Card card) {
        return Integer.compare(hashCode(), card.hashCode());
    }

    public int compareToWithinSuit(Card card) {
        if (this.getSuit() != card.getSuit()) {
            throw new IllegalArgumentException("Comparation with cards of different suits is not allowed.");
        }
        return this.compareTo(card);
    }
}