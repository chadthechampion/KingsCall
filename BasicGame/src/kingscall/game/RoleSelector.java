package kingscall.game;

import kingscall.model.Seat;
import nl.saxion.app.SaxionApp;

/**
 * Picks who starts as king and, each hand, who plays the minister. Both are
 * chosen uniformly at random; the minister is simply re-rolled until it differs
 * from the king.
 */
public final class RoleSelector {

    private RoleSelector() {
    }

    /** A random seat id, {@code "player 1"} .. {@code "player 4"}. */
    public static String randomPlayer() {
        return "player " + SaxionApp.getRandomValueBetween(1, 5);
    }

    public static String chooseKing() {
        return randomPlayer();
    }

    public static String chooseMinister(String king) {
        String minister = randomPlayer();
        while (minister.equals(king)) {
            minister = randomPlayer();
        }
        return minister;
    }

    /** Convenience overload when the caller already has a {@link Seat}. */
    public static String chooseMinister(Seat king) {
        return chooseMinister(king.id());
    }
}
