package kingscall.model;

import java.util.ArrayList;
import java.util.List;

/**
 * A player and the cards currently in their hand.
 *
 * <p>Dealing lives in {@link kingscall.game.Dealer} and rendering in
 * {@link kingscall.ui.TableRenderer}; this type is just the state those
 * collaborators act on.
 */
public class Player {

    public final Seat seat;
    /** Kept for the AI, which still compares {@code name} against the minister's id. */
    public final String name;
    public final List<Card> hand = new ArrayList<>();

    public Player(Seat seat) {
        this.seat = seat;
        this.name = seat.id();
    }

    /** This hand grouped by suit in the fixed display order H, S, D, C. */
    public List<Card> sortedHand() {
        return sortedBySuit(hand);
    }

    /** Returns {@code cards} reordered into the fixed display suit order H, S, D, C. */
    public static List<Card> sortedBySuit(List<Card> cards) {
        List<Card> sorted = new ArrayList<>();
        for (Suit suit : new Suit[] {Suit.HEARTS, Suit.SPADES, Suit.DIAMONDS, Suit.CLUBS}) {
            for (Card card : cards) {
                if (card.suit == suit) {
                    sorted.add(card);
                }
            }
        }
        return sorted;
    }
}
