package kingscall.game;

import kingscall.model.Card;
import kingscall.model.Suit;
import kingscall.ui.Assets;

import java.util.ArrayList;
import java.util.List;

/**
 * Builds a fresh, ordered 52-card deck. Shuffling happens implicitly in
 * {@link Dealer}, which draws cards at random positions.
 */
public final class Deck {

    private static final Suit[] BUILD_ORDER = {Suit.CLUBS, Suit.HEARTS, Suit.SPADES, Suit.DIAMONDS};

    /** Face cards, in the order the original deck listed them, with their rank values. */
    private enum Face {
        JACK('J', 11), QUEEN('Q', 12), KING('K', 13), ACE('A', 14);

        final char letter;
        final int value;

        Face(char letter, int value) {
            this.letter = letter;
            this.value = value;
        }
    }

    private Deck() {
    }

    public static List<Card> create() {
        List<Card> cards = new ArrayList<>();
        for (Suit suit : BUILD_ORDER) {
            for (int pip = 2; pip <= 10; pip++) {
                cards.add(card(pip, suit, pip + String.valueOf(suit.code())));
            }
        }
        for (Suit suit : BUILD_ORDER) {
            for (Face face : Face.values()) {
                cards.add(card(face.value, suit, face.letter + String.valueOf(suit.code())));
            }
        }
        return cards;
    }

    private static Card card(int value, Suit suit, String imageKey) {
        return new Card(value, suit,
            "resources/cardimages/" + imageKey + ".png",
            Assets.CARD_BACK,
            Assets.CARD_BACK_ROTATED);
    }
}
