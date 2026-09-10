package kingscall.game;

import kingscall.audio.AudioHelper;
import kingscall.game.ai.OpponentStrategy;
import kingscall.model.Card;
import kingscall.model.Player;
import kingscall.model.Seat;
import kingscall.model.Suit;
import kingscall.ui.Assets;
import kingscall.ui.TableLayout;
import kingscall.ui.TableRenderer;
import nl.saxion.app.SaxionApp;

import java.util.ArrayList;
import java.util.List;

/**
 * Plays a single trick: the leader plays one card (the human chooses, an AI
 * leader plays a random card), then the other three follow in clockwise order.
 * The list of cards played, in order, is returned for {@link TrickResolver}.
 *
 * <p>The original had one 50-line {@code switch} arm per possible leader, all
 * but identical; this keeps the two behaviours that actually differ (human vs AI
 * lead, human vs AI follow) and drives everyone else off {@link Seat} order.
 */
public final class RoundEngine {

    private RoundEngine() {
    }

    /**
     * @param players    seated in order SOUTH, EAST, NORTH, WEST
     * @param leaderId   id of the player leading this trick
     * @param ministerId id of the current minister
     * @param trumps      [0] king's trump, [1] minister's trump
     * @param trumpIcons  icon paths matching {@code trumps}
     */
    public static List<Card> playTrick(List<Player> players, String leaderId, String ministerId,
                                       List<Suit> trumps, List<String> trumpIcons) {
        drawCallHeader(trumpIcons);

        Seat leaderSeat = Seat.fromId(leaderId);
        Player leader = players.get(leaderSeat.ordinal());
        List<Card> trick = new ArrayList<>();
        Suit ledSuit;

        if (leaderSeat.isHuman()) {
            ledSuit = humanLead(players, trumpIcons, trick);
        } else {
            ledSuit = aiLead(leader, leaderSeat, trick);
        }

        for (Seat seat : leaderSeat.othersInPlayOrder()) {
            Player follower = players.get(seat.ordinal());
            if (seat.isHuman()) {
                humanFollow(follower, ledSuit, trick);
            } else {
                OpponentStrategy.playFollow(trick, ledSuit, trumps, follower, ministerId, TableLayout.play(seat));
                AudioHelper.play(Assets.SFX_THROW, false);
                SaxionApp.sleep(1);
            }
        }
        return trick;
    }

    // ----- leading -----------------------------------------------------------

    private static Suit humanLead(List<Player> players, List<String> trumpIcons, List<Card> trick) {
        Player human = players.get(Seat.SOUTH.ordinal());
        SaxionApp.printLine("Choose a card");

        int index = SaxionApp.readInt() - 1;
        while (index >= human.hand.size() || index < 0) {
            SaxionApp.printLine("card number does not exists.");
            index = SaxionApp.readInt() - 1;
        }

        TableRenderer.clearToTable();
        drawCallHeader(trumpIcons);

        Card chosen = human.sortedHand().get(index);
        drawInMiddle(chosen.image, TableLayout.play(Seat.SOUTH));
        AudioHelper.play(Assets.SFX_THROW, false);
        trick.add(chosen);
        human.hand.remove(chosen);

        // The human lead is the only point the whole table is redrawn mid-trick.
        redrawHandsDuringTrick(players);
        return chosen.suit;
    }

    private static Suit aiLead(Player leader, Seat leaderSeat, List<Card> trick) {
        int index = SaxionApp.getRandomValueBetween(0, leader.hand.size());
        Card chosen = leader.hand.get(index);

        drawInMiddle(chosen.image, TableLayout.play(leaderSeat));
        AudioHelper.play(Assets.SFX_THROW, false);
        SaxionApp.sleep(1);
        if (leaderSeat == Seat.NORTH || leaderSeat == Seat.WEST) {
            SaxionApp.sleep(1); // the original lingered a beat longer for these two seats
        }

        trick.add(chosen);
        leader.hand.remove(index);
        return chosen.suit;
    }

    // ----- following -------------------------------------------------------

    private static void humanFollow(Player human, Suit ledSuit, List<Card> trick) {
        int index = SaxionApp.readInt() - 1;
        boolean mustFollowSuit = human.hand.stream().anyMatch(c -> c.suit == ledSuit);

        while ((index >= human.hand.size() || index < 0)
            || (mustFollowSuit && human.sortedHand().get(index).suit != ledSuit)) {
            if (index >= human.hand.size() || index < 0) {
                SaxionApp.printLine("card number does not exists.");
            } else {
                SaxionApp.printLine("you have to play the played suit");
            }
            index = SaxionApp.readInt() - 1;
        }

        Card chosen = human.sortedHand().get(index);
        drawInMiddle(chosen.image, TableLayout.play(Seat.SOUTH));
        AudioHelper.play(Assets.SFX_THROW, false);
        SaxionApp.sleep(1);
        trick.add(chosen);
        human.hand.remove(chosen);
    }

    // ----- shared drawing --------------------------------------------------

    private static void drawCallHeader(List<String> trumpIcons) {
        SaxionApp.drawBorderedText("kings call: ", 10, 10, 25);
        SaxionApp.drawImage(trumpIcons.get(0), 130, 10, 25, 25);
        SaxionApp.drawBorderedText("ministers call: ", 10, 40, 25);
        SaxionApp.drawImage(trumpIcons.get(1), 170, 40, 25, 25);
    }

    private static void drawInMiddle(String image, TableLayout.Pos at) {
        SaxionApp.drawImage(image, at.x(), at.y(), TableLayout.CARD_W, TableLayout.CARD_H);
    }

    private static void redrawHandsDuringTrick(List<Player> players) {
        Player s = players.get(Seat.SOUTH.ordinal());
        Player e = players.get(Seat.EAST.ordinal());
        Player n = players.get(Seat.NORTH.ordinal());
        Player w = players.get(Seat.WEST.ordinal());
        TableRenderer.drawSouthHand(s.sortedHand(), TableLayout.handDuringTrick(Seat.SOUTH), s.hand.size());
        TableRenderer.drawSideHand(e.hand, TableLayout.handDuringTrick(Seat.EAST), e.hand.size());
        TableRenderer.drawNorthHand(n.hand, TableLayout.handDuringTrick(Seat.NORTH), n.hand.size());
        TableRenderer.drawSideHand(w.hand, TableLayout.handDuringTrick(Seat.WEST), w.hand.size());
    }
}
