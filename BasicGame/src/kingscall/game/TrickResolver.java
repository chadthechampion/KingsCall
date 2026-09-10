package kingscall.game;

import kingscall.model.Card;
import kingscall.model.Suit;

import java.util.ArrayList;
import java.util.List;

/**
 * Decides who wins a completed trick.
 *
 * <p>Priority: the king's trump beats everything; failing that the minister's
 * trump (played by the minister) beats everything except when the trick was led
 * with the minister's trump suit; otherwise the highest card following the led
 * suit wins.
 */
public final class TrickResolver {

    private TrickResolver() {
    }

    /**
     * @param trick     cards in play order (index 0 led the trick)
     * @param trumps     [0] = king's trump suit, [1] = minister's trump suit
     * @param ministerId id of the minister ({@code "player 1"} .. {@code "player 4"})
     * @return the winning card's owner id
     */
    public static String winner(List<Card> trick, List<Suit> trumps, String ministerId) {
        Suit kingsTrump = trumps.get(0);
        Suit ministersTrump = trumps.get(1);
        Suit ledSuit = trick.get(0).suit;

        List<Card> kingsTrumpCards = new ArrayList<>();
        List<Card> ministersTrumpCards = new ArrayList<>();
        List<Card> followingLed = new ArrayList<>();
        for (Card card : trick) {
            if (card.suit == kingsTrump) {
                kingsTrumpCards.add(card);
            } else if (card.suit == ministersTrump && card.owner.equals(ministerId)) {
                ministersTrumpCards.add(card);
            } else if (card.suit == ledSuit) {
                followingLed.add(card);
            }
        }

        if (!kingsTrumpCards.isEmpty()) {
            return highest(kingsTrumpCards).owner;
        }
        if (!ministersTrumpCards.isEmpty()) {
            if (ledSuit != ministersTrump) {
                return ministerId;
            }
            return highest(followingLed).owner;
        }

        Card best = highest(followingLed);
        if (best.owner.equals(ministerId) && best.suit == ministersTrump) {
            return ministerId;
        }
        return best.owner;
    }

    private static Card highest(List<Card> cards) {
        Card best = cards.get(0);
        for (Card card : cards) {
            if (card.value > best.value) {
                best = card;
            }
        }
        return best;
    }
}
