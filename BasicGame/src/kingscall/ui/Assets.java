package kingscall.ui;

import kingscall.model.Suit;

/**
 * Every image and sound path the game loads, resolved in one place.
 *
 * <p>Paths are built under {@link #HOME}{@code /resources/...}. {@code HOME}
 * defaults to {@code "."} (so running from the project root just works), and a
 * packaged build overrides it with {@code -Dkingscall.home=<app dir>} so the
 * bundled {@code resources/} folder is found wherever the app is installed.
 */
public final class Assets {

    private static final String HOME = System.getProperty("kingscall.home", ".");

    private Assets() {
    }

    /** Resolves {@code relative} under the bundled {@code resources/} directory. */
    public static String res(String relative) {
        return HOME + "/resources/" + relative;
    }

    /** A card or suit-icon image by file key, e.g. {@code "10H"} or {@code "hearts"}. */
    public static String card(String key) {
        return res("cardimages/" + key + ".png");
    }

    public static String suitIcon(Suit suit) {
        return card(suit.iconName());
    }

    private static String story(String file) {
        return res("STORYLINEPHOTOS/" + file);
    }

    // Table
    public static final String TABLE_BACKGROUND = res("file.png");

    // End-of-set / end-of-game screens
    public static final String HUMAN_WON_SET = res("yourteam.png");
    public static final String OPPONENT_WON_SET = res("opponentWin.png");
    public static final String GAME_WON = res("win.png");
    public static final String GAME_LOST = res("loose.png");

    // Role markers drawn next to each seat: [0]=South .. [3]=West
    public static final String[] KING_MARKER = {
        res("c1.png"), res("c2.png"), res("c3.png"), res("c4.png")
    };
    public static final String[] MINISTER_MARKER = {
        res("m1.png"), res("m2.png"), res("m3.png"), res("m4.png")
    };

    // Card backs
    public static final String CARD_BACK = card("gray_back");
    public static final String CARD_BACK_ROTATED = card("gray_back_kaj");

    // Music & sound effects
    public static final String MUSIC_INTRO = res("music/g_intro.wav");
    public static final String MUSIC_GAME = res("music/g_music.wav");
    public static final String SFX_THROW = res("music/throwing.wav");
    public static final String SFX_WIN = res("music/win.wav");
    public static final String SFX_FAIL = res("music/fail.wav");

    // Intro fade-in frames (dark -> full)
    public static final String[] INTRO_FADE_IN = {
        story("black.png"), story("start20.png"), story("start40.png"),
        story("start60.png"), story("start80.png"), story("start.png")
    };
    public static final String INTRO_SHIRAZ = story("shiraz100.png");
    public static final String INTRO_SHIRAZ_DARK = story("shirazdark100.png");
    public static final String INTRO_KING = story("king100.png");
    public static final String INTRO_MINISTER = story("minister100.png");
    public static final String INTRO_CITIZEN = story("citizen100.png");
    public static final String MENU = story("menu.png");
    public static final String BIRD = story("birdd.png");
    public static final String LETTER = story("letter.png");
    public static final String GAME_STARTS = story("gamestarts.png");

    /** Tutorial slides t1..t9. */
    public static final String[] TUTORIAL_SLIDES = {
        story("t1.png"), story("t2.png"), story("t3.png"), story("t4.png"), story("t5.png"),
        story("t6.png"), story("t7.png"), story("t8.png"), story("t9.png")
    };
}
