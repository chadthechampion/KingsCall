package kingscall.ui;

import kingscall.model.Seat;

/**
 * Fixed pixel coordinates for the 1920x1024 table. The original code sprinkled
 * these numbers through every class; they are collected here so the layout can
 * be read (and tweaked) in one place.
 */
public final class TableLayout {

    private TableLayout() {
    }

    /** A top-left screen position. */
    public record Pos(int x, int y) {
    }

    // Card dimensions
    public static final int CARD_W = 150;
    public static final int CARD_H = 200;
    /** Side hands are drawn rotated, so width and height swap. */
    public static final int CARD_W_ROTATED = 200;
    public static final int CARD_H_ROTATED = 150;

    /** Horizontal fan step between successive cards in a hand. */
    public static final int FAN_STEP = 30;

    // Hand anchor points, indexed by Seat.ordinal(): SOUTH, EAST, NORTH, WEST
    private static final Pos[] HAND = {
        new Pos(680, 750), new Pos(1500, 230), new Pos(680, 50), new Pos(140, 230)
    };

    /** Slightly shifted hand anchors used while a trick is in progress. */
    private static final Pos[] HAND_DURING_TRICK = {
        new Pos(670, 750), new Pos(1500, 380), new Pos(830, 50), new Pos(140, 380)
    };

    /** Where each seat's played card lands in the middle of the table. */
    private static final Pos[] PLAY = {
        new Pos(800, 500), new Pos(1000, 350), new Pos(800, 260), new Pos(600, 350)
    };

    public static Pos hand(Seat seat) {
        return HAND[seat.ordinal()];
    }

    public static Pos handDuringTrick(Seat seat) {
        return HAND_DURING_TRICK[seat.ordinal()];
    }

    public static Pos play(Seat seat) {
        return PLAY[seat.ordinal()];
    }

    // Role markers
    private static final Pos[] MARKER = {
        new Pos(830, 950), new Pos(1700, 350), new Pos(820, 0), new Pos(85, 350)
    };
    public static final int KING_MARKER_SIZE = 45;
    public static final int MINISTER_MARKER_SIZE = 50;

    public static Pos marker(Seat seat) {
        return MARKER[seat.ordinal()];
    }
}
