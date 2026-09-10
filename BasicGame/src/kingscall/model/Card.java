package kingscall.model;

/**
 * A single playing card.
 *
 * <p>Identity (suit, rank and artwork) is immutable; only {@link #owner} changes,
 * and only once, when the card is dealt to a player.
 */
public class Card {

    /** Rank value: 2..10 for pip cards, 11 = Jack, 12 = Queen, 13 = King, 14 = Ace. */
    public final int value;
    public final Suit suit;

    /** Face-up artwork. */
    public final String image;
    /** Face-down artwork, upright and rotated 90 degrees for the side players. */
    public final String backImage;
    public final String rotatedBackImage;

    /** Id of the player holding this card ({@code "player 1"} .. {@code "player 4"}); {@code null} until dealt. */
    public String owner;

    public Card(int value, Suit suit, String image, String backImage, String rotatedBackImage) {
        this.value = value;
        this.suit = suit;
        this.image = image;
        this.backImage = backImage;
        this.rotatedBackImage = rotatedBackImage;
    }

    @Override
    public String toString() {
        return value + String.valueOf(suit.code());
    }
}
