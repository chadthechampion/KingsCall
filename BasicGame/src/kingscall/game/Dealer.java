package kingscall.game;

import kingscall.model.Card;
import kingscall.model.Player;
import nl.saxion.app.SaxionApp;

import java.util.List;

/**
 * Deals a full deck out to the players, 13 cards each, by repeatedly drawing a
 * card from a random position in the remaining deck.
 */
public final class Dealer {

    public static final int HAND_SIZE = 13;

    private Dealer() {
    }

    /** Deals to each player in turn; the deck is emptied in the process. */
    public static void deal(List<Card> deck, List<Player> players) {
        for (Player player : players) {
            for (int i = 0; i < HAND_SIZE; i++) {
                int index = SaxionApp.getRandomValueBetween(0, deck.size());
                Card card = deck.get(index);
                card.owner = player.name;
                player.hand.add(card);
                deck.remove(index);
            }
        }
    }
}
