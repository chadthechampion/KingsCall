package kingscall.ui;

import kingscall.model.Card;
import kingscall.model.Seat;
import kingscall.ui.TableLayout.Pos;
import nl.saxion.app.SaxionApp;

import java.util.List;

/**
 * All drawing of the table itself: the felt background, the four hands, the
 * king/minister markers and the cards played into the middle. Replaces the
 * {@code showMyCards} / {@code showCardsOdd} / {@code showCardsEven} trio and
 * the "clear, redraw background, redraw every hand" block that the original
 * repeated a dozen times.
 */
public final class TableRenderer {

    private TableRenderer() {
    }

    /** Draws the felt over the whole screen. */
    public static void drawBackground() {
        SaxionApp.drawImage(Assets.TABLE_BACKGROUND, 0, 0, 1920, 1024);
    }

    /** Clears the screen and repaints the felt. */
    public static void clearToTable() {
        SaxionApp.clear();
        drawBackground();
    }

    /** The human's hand: face up, each card labelled with its 1-based index. */
    public static void drawSouthHand(List<Card> cards, Pos at, int count) {
        int x = at.x();
        for (int i = 0; i < count; i++) {
            SaxionApp.drawImage(cards.get(i).image, x, at.y(), TableLayout.CARD_W, TableLayout.CARD_H);
            SaxionApp.drawBorderedText((i + 1) + "", x + 6, at.y() - 15, 14);
            x += TableLayout.FAN_STEP;
        }
    }

    /** The top opponent's hand: face down, upright. */
    public static void drawNorthHand(List<Card> cards, Pos at, int count) {
        int x = at.x();
        for (int i = 0; i < count; i++) {
            SaxionApp.drawImage(cards.get(i).backImage, x, at.y(), TableLayout.CARD_W, TableLayout.CARD_H);
            x += TableLayout.FAN_STEP;
        }
    }

    /** A side opponent's hand: face down, rotated 90 degrees, fanned vertically. */
    public static void drawSideHand(List<Card> cards, Pos at, int count) {
        int y = at.y();
        for (int i = 0; i < count; i++) {
            SaxionApp.drawImage(cards.get(i).rotatedBackImage, at.x(), y,
                TableLayout.CARD_W_ROTATED, TableLayout.CARD_H_ROTATED);
            SaxionApp.printLine();
            y += TableLayout.FAN_STEP;
        }
    }

    /** Draws every hand at its standard anchor, all truncated to {@code count} cards. */
    public static void drawAllHands(List<Card> south, List<Card> east, List<Card> north, List<Card> west, int count) {
        drawSouthHand(south, TableLayout.hand(Seat.SOUTH), count);
        drawSideHand(east, TableLayout.hand(Seat.EAST), count);
        drawNorthHand(north, TableLayout.hand(Seat.NORTH), count);
        drawSideHand(west, TableLayout.hand(Seat.WEST), count);
    }

    /** Draws a card played into the middle of the table at the given seat's slot. */
    public static void drawPlayedCard(String image, Seat seat) {
        Pos at = TableLayout.play(seat);
        SaxionApp.drawImage(image, at.x(), at.y(), TableLayout.CARD_W, TableLayout.CARD_H);
    }

    /** Draws the small crown/minister badges next to the seats holding those roles. */
    public static void drawRoleMarkers(Seat king, Seat minister) {
        Pos k = TableLayout.marker(king);
        SaxionApp.drawImage(Assets.KING_MARKER[king.ordinal()], k.x(), k.y(),
            TableLayout.KING_MARKER_SIZE, TableLayout.KING_MARKER_SIZE);
        Pos m = TableLayout.marker(minister);
        SaxionApp.drawImage(Assets.MINISTER_MARKER[minister.ordinal()], m.x(), m.y(),
            TableLayout.MINISTER_MARKER_SIZE, TableLayout.MINISTER_MARKER_SIZE);
    }
}
