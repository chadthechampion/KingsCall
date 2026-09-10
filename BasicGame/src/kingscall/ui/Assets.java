package kingscall.ui;

/**
 * Every image and sound path the game loads, in one place. Paths are relative to
 * the working directory, so the game must be launched from the project root.
 */
public final class Assets {

    private Assets() {
    }

    // Table
    public static final String TABLE_BACKGROUND = "resources/file.png";

    // End-of-round / end-of-game screens
    public static final String HUMAN_WON_SET = "resources/yourteam.png";
    public static final String OPPONENT_WON_SET = "resources/opponentWin.png";
    public static final String GAME_WON = "resources/win.png";
    public static final String GAME_LOST = "resources/loose.png";

    // Role markers drawn next to each seat: [0]=South .. [3]=West
    public static final String[] KING_MARKER = {
        "resources/c1.png", "resources/c2.png", "resources/c3.png", "resources/c4.png"
    };
    public static final String[] MINISTER_MARKER = {
        "resources/m1.png", "resources/m2.png", "resources/m3.png", "resources/m4.png"
    };

    // Card backs
    public static final String CARD_BACK = "resources/cardimages/gray_back.png";
    public static final String CARD_BACK_ROTATED = "resources/cardimages/gray_back_kaj.png";

    // Music & sound effects
    public static final String MUSIC_INTRO = "resources/music/g_intro.wav";
    public static final String MUSIC_GAME = "resources/music/g_music.wav";
    public static final String SFX_THROW = "resources/music/throwing.wav";
    public static final String SFX_WIN = "resources/music/win.wav";
    public static final String SFX_FAIL = "resources/music/fail.wav";

    // Storyline
    public static final String STORY = "resources/STORYLINEPHOTOS/";

    // Intro fade-in frames (dark -> full)
    public static final String[] INTRO_FADE_IN = {
        STORY + "black.png", STORY + "start20.png", STORY + "start40.png",
        STORY + "start60.png", STORY + "start80.png", STORY + "start.png"
    };
    public static final String INTRO_SHIRAZ = STORY + "shiraz100.png";
    public static final String INTRO_SHIRAZ_DARK = STORY + "shirazdark100.png";
    public static final String INTRO_KING = STORY + "king100.png";
    public static final String INTRO_MINISTER = STORY + "minister100.png";
    public static final String INTRO_CITIZEN = STORY + "citizen100.png";
    public static final String MENU = STORY + "menu.png";
    public static final String BIRD = STORY + "birdd.png";
    public static final String LETTER = STORY + "letter.png";
    public static final String GAME_STARTS = STORY + "gamestarts.png";

    /** Tutorial slides t1..t9. */
    public static final String[] TUTORIAL_SLIDES = {
        STORY + "t1.png", STORY + "t2.png", STORY + "t3.png", STORY + "t4.png", STORY + "t5.png",
        STORY + "t6.png", STORY + "t7.png", STORY + "t8.png", STORY + "t9.png"
    };
}
