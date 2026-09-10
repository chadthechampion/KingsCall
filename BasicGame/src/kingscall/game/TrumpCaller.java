package kingscall.game;

import kingscall.model.Player;
import kingscall.model.Suit;
import kingscall.ui.Assets;
import kingscall.ui.TableRenderer;
import nl.saxion.app.SaxionApp;

import java.awt.Color;
import java.util.List;

/**
 * Handles the trump-calling phase: first the king names a trump, then the
 * minister names a second one (which may not be the king's).
 *
 * <p>Each method returns the chosen {@link Suit}; the caller records it and its
 * icon. The human variants run an input/validation loop with on-screen prompts;
 * the AI variants decide instantly.
 */
public final class TrumpCaller {

    /** Cards near the top of a hand the AI king inspects when picking a trump. */
    private static final int AI_KING_SAMPLE = 5;
    /** Only the first few cards are shown during this phase, matching the original. */
    private static final int HAND_PREVIEW = 5;

    private TrumpCaller() {
    }

    // ----- King -----------------------------------------------------------------

    /** Human king picks a trump (lowercase h/s/c/d); {@code available} loses the pick. */
    public static Suit callAsKingHuman(List<Player> players, List<Suit> available) {
        orange();
        SaxionApp.drawBorderedText("king!choose your trump!(h,s,c,d) ", 600, 500, 50);
        char trump = SaxionApp.readChar();
        available.remove(letterToSuitLoose(trump));

        redrawPreview(players);
        while (!isLowercaseSuit(trump)) {
            red();
            SaxionApp.drawBorderedText("wrong suit!", 600, 500, 50);
            SaxionApp.sleep(2);
            redrawPreview(players);
            orange();
            SaxionApp.drawBorderedText("king!choose your trump!(h,s,c,d) ", 600, 500, 50);
            trump = SaxionApp.readChar();
            redrawPreview(players);
        }

        Suit called = Suit.fromCode(trump);
        orange();
        SaxionApp.drawBorderedText("the king calls ", 700, 500, 50);
        SaxionApp.drawImage(Assets.suitIcon(called), 1000, 500, 50, 50);
        return called;
    }

    /** AI king calls its longest suit among the first five cards. */
    public static Suit callAsKingAi(Player king, List<Suit> available) {
        int[] counts = new int[Suit.values().length];
        for (int i = 0; i < AI_KING_SAMPLE; i++) {
            counts[king.hand.get(i).suit.ordinal()]++;
        }
        int most = counts[0];
        for (int c : counts) {
            most = Math.max(most, c);
        }

        Suit called = Suit.SPADES;
        for (Suit suit : new Suit[] {Suit.HEARTS, Suit.DIAMONDS, Suit.CLUBS}) {
            if (counts[suit.ordinal()] == most) {
                called = suit;
                break;
            }
        }
        available.remove(called);

        SaxionApp.drawBorderedText("the king calls ", 700, 500, 50);
        SaxionApp.drawImage(Assets.suitIcon(called), 1000, 500, 50, 50);
        SaxionApp.sleep(2);
        return called;
    }

    // ----- Minister -----------------------------------------------------------

    /** Human minister picks a trump (uppercase), which may not equal {@code kingsTrump}. */
    public static Suit callAsMinisterHuman(List<Player> players, Suit kingsTrump, List<Suit> available) {
        orange();
        SaxionApp.drawBorderedText("minister!choose your trump![H,S,C,D] ", 600, 500, 50);
        char trump = Character.toUpperCase(SaxionApp.readChar());

        while (Suit.fromCode(trump) == kingsTrump) {
            redrawPreview(players);
            red();
            SaxionApp.drawBorderedText("That is kings call !", 600, 500, 50);
            SaxionApp.sleep(2);
            redrawPreview(players);
            orange();
            SaxionApp.drawBorderedText("minister!choose your trump! " + available, 600, 500, 50);
            trump = Character.toUpperCase(SaxionApp.readChar());
            redrawPreview(players);
        }

        redrawPreview(players);
        while (!isUppercaseSuit(trump)) {
            red();
            SaxionApp.drawBorderedText("wrong suit!", 600, 500, 50);
            SaxionApp.sleep(2);
            redrawPreview(players);
            orange();
            SaxionApp.drawBorderedText("minister!choose your trump! " + available, 600, 500, 50);
            trump = Character.toUpperCase(SaxionApp.readChar());
            redrawPreview(players);
        }

        Suit called = Suit.fromCode(trump);
        orange();
        SaxionApp.drawBorderedText("the minister calls ", 700, 500, 50);
        SaxionApp.drawImage(Assets.suitIcon(called), 1100, 500, 50, 50);
        SaxionApp.sleep(2);
        redrawPreview(players);
        return called;
    }

    /** AI minister picks a random still-available suit. */
    public static Suit callAsMinisterAi(List<Player> players, List<Suit> available) {
        Suit called = available.get(SaxionApp.getRandomValueBetween(0, available.size()));

        SaxionApp.drawBorderedText("the minister calls  ", 700, 500, 50);
        SaxionApp.drawImage(Assets.suitIcon(called), 1100, 500, 50, 50);
        SaxionApp.sleep(2);
        redrawPreview(players);
        return called;
    }

    // ----- helpers ----------------------------------------------------------

    private static void redrawPreview(List<Player> players) {
        TableRenderer.clearToTable();
        TableRenderer.drawAllHands(
            players.get(0).hand, players.get(1).hand, players.get(2).hand, players.get(3).hand,
            HAND_PREVIEW);
    }

    private static boolean isLowercaseSuit(char c) {
        return c == 's' || c == 'd' || c == 'h' || c == 'c';
    }

    private static boolean isUppercaseSuit(char c) {
        return c == 'S' || c == 'D' || c == 'H' || c == 'C';
    }

    /**
     * Mirrors the original king input mapping: h/d/c map to their suit, and
     * <em>anything else</em> (including 's') maps to spades.
     */
    private static Suit letterToSuitLoose(char c) {
        return switch (Character.toLowerCase(c)) {
            case 'h' -> Suit.HEARTS;
            case 'd' -> Suit.DIAMONDS;
            case 'c' -> Suit.CLUBS;
            default -> Suit.SPADES;
        };
    }

    private static void orange() {
        SaxionApp.setFill(Color.orange);
        SaxionApp.setBorderColor(Color.orange);
    }

    private static void red() {
        SaxionApp.setFill(Color.red);
        SaxionApp.setBorderColor(Color.red);
    }
}
