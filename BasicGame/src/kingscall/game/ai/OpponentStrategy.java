package kingscall.game.ai;

import kingscall.audio.AudioHelper;
import kingscall.model.Card;
import kingscall.model.Player;
import kingscall.model.Suit;
import kingscall.ui.Assets;
import kingscall.ui.TableLayout;
import kingscall.ui.TableLayout.Pos;
import kingscall.ui.TableRenderer;
import nl.saxion.app.SaxionApp;

import java.util.ArrayList;
import java.util.List;

/**
 * How a computer opponent chooses the card it plays when following a trick.
 *
 * <p>The three original per-opponent blocks were byte-for-byte identical apart
 * from the partner's name, so they are unified here into one routine
 * parameterised by {@code player.seat().partner()}. The decision itself is
 * unchanged:
 * <ol>
 *   <li>If our partner is currently winning the trick, dump our lowest card.</li>
 *   <li>Otherwise try to beat the current best card (over-trump, or play higher
 *       in the led suit).</li>
 *   <li>Fallback: follow suit if we can, else (as minister) play a trump, else
 *       play a good-enough / random card.</li>
 * </ol>
 */
public final class OpponentStrategy {

    private OpponentStrategy() {
    }

    public static void playFollow(List<Card> trick, Suit ledSuit, List<Suit> trumps,
                                  Player player, String ministerId, Pos at) {
        Suit kingsTrump = trumps.get(0);
        Suit ministersTrump = trumps.get(1);
        Suit trickLedSuit = trick.get(0).suit;

        List<Card> kingsTrumpCards = new ArrayList<>();
        List<Card> ministersTrumpCards = new ArrayList<>();
        List<Card> followingLed = new ArrayList<>();
        for (Card card : trick) {
            if (card.suit == kingsTrump) {
                kingsTrumpCards.add(card);
            } else if (card.suit == ministersTrump && card.owner.equals(ministerId)) {
                ministersTrumpCards.add(card);
            } else if (card.suit == trickLedSuit) {
                followingLed.add(card);
            }
        }

        Card bestMove = player.hand.get(0);
        Card leader = trick.get(0);
        String currentWinner;
        if (!kingsTrumpCards.isEmpty()) {
            leader = highest(kingsTrumpCards);
            currentWinner = leader.owner;
        } else if (!ministersTrumpCards.isEmpty()) {
            if (trickLedSuit != ministersTrump) {
                currentWinner = ministerId;
            } else {
                leader = highest(followingLed);
                currentWinner = leader.owner;
            }
        } else {
            leader = highest(followingLed);
            currentWinner = leader.owner;
        }

        boolean played = false;
        if (!player.seat.isHuman()) {
            String partner = player.seat.partner().id();
            if (currentWinner.equals(partner)) {
                played = dumpLowest(trick, ledSuit, kingsTrump, player, at, bestMove, leader);
            } else {
                played = tryToBeat(trick, ledSuit, kingsTrump, player, at, leader);
            }
        }

        if (!played) {
            playFallback(trick, ledSuit, trumps, player, ministerId, at);
        }
    }

    /** Partner is winning: unload our smallest card in the led suit (a low trump if forced). */
    private static boolean dumpLowest(List<Card> trick, Suit ledSuit, Suit kingsTrump,
                                      Player player, Pos at, Card bestMove, Card leader) {
        if (leader.suit == kingsTrump) {
            for (Card card : player.hand) {
                if (card.suit != kingsTrump && card.value < bestMove.value && card.suit == ledSuit) {
                    return commit(card, player, trick, at);
                }
            }
        } else {
            for (Card card : player.hand) {
                if (card.value < leader.value && card.suit == ledSuit) {
                    return commit(card, player, trick, at);
                }
            }
        }
        return false;
    }

    /** Opponent is winning: play something that beats the current best card. */
    private static boolean tryToBeat(List<Card> trick, Suit ledSuit, Suit kingsTrump,
                                     Player player, Pos at, Card leader) {
        boolean canFollowSuit = player.hand.stream().anyMatch(c -> c.suit == ledSuit);
        if (!canFollowSuit) {
            if (leader.suit == kingsTrump) {
                for (Card card : player.hand) {
                    if (card.suit == kingsTrump && card.value > leader.value) {
                        return commit(card, player, trick, at);
                    }
                }
            }
        } else if (leader.suit != kingsTrump) {
            for (Card card : player.hand) {
                if (card.suit == ledSuit && card.value > leader.value) {
                    return commit(card, player, trick, at);
                }
            }
        }
        return false;
    }

    /**
     * Last resort, reached when neither reactive rule produced a card: follow
     * suit, else (as minister) play the minister's trump then the king's trump,
     * else play a card. Faithfully preserves the quirks of the original fallback.
     */
    private static void playFallback(List<Card> trick, Suit ledSuit, List<Suit> trumps,
                                     Player player, String ministerId, Pos at) {
        if (commitFirstOfSuit(player, trick, at, ledSuit)) {
            return;
        }
        if (player.name.equals(ministerId)) {
            if (commitFirstOfSuit(player, trick, at, trumps.get(1))) {
                return;
            }
            if (commitFirstOfSuit(player, trick, at, trumps.get(0))) {
                return;
            }
            int random = SaxionApp.getRandomValueBetween(0, player.hand.size());
            draw(player.hand.get(random).image, at);
            SaxionApp.sleep(1);
            trick.add(player.hand.get(random));
            player.hand.remove(player.hand.get(random));
            // NOTE: original reads get(random) again after the removal; kept as-is.
        } else {
            if (commitFirstOfSuit(player, trick, at, trumps.get(0))) {
                return;
            }
            for (Card card : player.hand) {
                int random = SaxionApp.getRandomValueBetween(0, player.hand.size());
                draw(player.hand.get(random).image, at);
                SaxionApp.sleep(1);
                trick.add(card);
                player.hand.remove(card);
                break;
            }
        }
    }

    private static boolean commitFirstOfSuit(Player player, List<Card> trick, Pos at, Suit suit) {
        for (Card card : player.hand) {
            if (card.suit == suit) {
                draw(card.image, at);
                SaxionApp.sleep(1);
                trick.add(card);
                player.hand.remove(card);
                return true;
            }
        }
        return false;
    }

    private static boolean commit(Card card, Player player, List<Card> trick, Pos at) {
        draw(card.image, at);
        trick.add(card);
        player.hand.remove(card);
        SaxionApp.sleep(1);
        return true;
    }

    private static void draw(String image, Pos at) {
        SaxionApp.drawImage(image, at.x(), at.y(), TableLayout.CARD_W, TableLayout.CARD_H);
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

    /** Plays the throw sound; kept here so callers stay one-liners. */
    public static void throwSound() {
        AudioHelper.play(Assets.SFX_THROW, false);
    }
}
