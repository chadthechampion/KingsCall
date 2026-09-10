package kingscall.model;

/**
 * The four card suits.
 *
 * <p>Each constant knows its single-letter code (as used throughout the original
 * game logic and the card-image file names) and the path to its icon image. The
 * declaration order {@code HEARTS, DIAMONDS, CLUBS, SPADES} matches the order in
 * which suits were historically offered to the king, so {@code values()} can be
 * used directly as the "available suits" list.
 */
public enum Suit {
    HEARTS('H', "hearts"),
    DIAMONDS('D', "diamonds"),
    CLUBS('C', "clubs"),
    SPADES('S', "spades");

    private final char code;
    private final String iconName;

    Suit(char code, String iconName) {
        this.code = code;
        this.iconName = iconName;
    }

    /** The single uppercase letter identifying this suit ({@code H, D, C, S}). */
    public char code() {
        return code;
    }

    /** File-name stem of the small suit icon, e.g. {@code "hearts"}. */
    public String iconName() {
        return iconName;
    }

    /** Resolves a suit from its letter, accepting either case. Returns {@code null} for anything else. */
    public static Suit fromCode(char letter) {
        return switch (Character.toUpperCase(letter)) {
            case 'H' -> HEARTS;
            case 'D' -> DIAMONDS;
            case 'C' -> CLUBS;
            case 'S' -> SPADES;
            default -> null;
        };
    }

    /** Prints the bare letter so debug output and on-screen lists read {@code [H, S, C]}. */
    @Override
    public String toString() {
        return String.valueOf(code);
    }
}
