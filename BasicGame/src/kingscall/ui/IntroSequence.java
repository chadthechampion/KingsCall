package kingscall.ui;

import nl.saxion.app.SaxionApp;

import java.awt.Color;

/**
 * The opening storyline: a fade-in title, characters sliding in from alternating
 * sides, and the messenger bird that delivers the king's letter. Pure animation,
 * driven by short {@code sleep} calls.
 */
public final class IntroSequence {

    private static final int SCREEN_W = 1920;
    private static final int SCREEN_H = 1024;

    private IntroSequence() {
    }

    /** Plays the whole intro up to the menu. */
    public static void play() {
        fadeInTitle();
        slideInFromRight(Assets.INTRO_SHIRAZ);
        slideInFromLeft(Assets.INTRO_SHIRAZ_DARK);
        slideInFromRight(Assets.INTRO_KING);
        slideInFromLeft(Assets.INTRO_MINISTER);
        slideInFromRight(Assets.INTRO_CITIZEN);
        dropInMenu();
    }

    /** Re-shows just the menu (used after the tutorial). */
    public static void showMenu() {
        dropInMenu();
    }

    /** The messenger bird flies in, drops the letter, and the game-start card appears. */
    public static void deliverLetter() {
        int x = -500;
        int y = -500;
        int targetX = SCREEN_W / 10;
        int targetY = SCREEN_H / 16;

        SaxionApp.setBackgroundColor(Color.black);
        while (x < targetX && y < targetY) {
            SaxionApp.clear();
            SaxionApp.drawImage(Assets.BIRD, x, y, 500, 500);
            SaxionApp.sleep(0.008);
            x += 4;
            y += 4;
        }

        SaxionApp.clear();
        SaxionApp.drawImage(Assets.LETTER, 650, 300, 350, 300);
        SaxionApp.sleep(1);
        SaxionApp.clear();
        SaxionApp.drawImage(Assets.GAME_STARTS, 650, 300, 500, 500);
        SaxionApp.sleep(1);
    }

    private static void fadeInTitle() {
        for (String frame : Assets.INTRO_FADE_IN) {
            SaxionApp.drawImage(frame, 0, 0, SCREEN_W, SCREEN_H);
            SaxionApp.sleep(0.00005);
        }
        SaxionApp.sleep(2);
    }

    private static void slideInFromRight(String image) {
        SaxionApp.setBackgroundColor(Color.black);
        for (int x = 2000; x > 0; x -= 5) {
            SaxionApp.clear();
            SaxionApp.drawImage(image, x, 0, SCREEN_W, SCREEN_H);
            SaxionApp.sleep(0.001);
            if (x - 5 == 0) {
                SaxionApp.pause();
            }
        }
    }

    private static void slideInFromLeft(String image) {
        SaxionApp.setBackgroundColor(Color.black);
        for (int x = -2000; x < 0; x += 5) {
            SaxionApp.clear();
            SaxionApp.drawImage(image, x, 0, SCREEN_W, SCREEN_H);
            SaxionApp.sleep(0.001);
            if (x + 5 == 0) {
                SaxionApp.pause();
            }
        }
    }

    private static void dropInMenu() {
        SaxionApp.setBackgroundColor(Color.black);
        for (int y = -1500; y < 0; y += 5) {
            SaxionApp.clear();
            SaxionApp.drawImage(Assets.MENU, 0, y, SCREEN_W, SCREEN_H);
            SaxionApp.sleep(0.001);
        }
    }
}
