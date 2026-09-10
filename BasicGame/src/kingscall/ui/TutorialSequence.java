package kingscall.ui;

import kingscall.audio.AudioHelper;
import nl.saxion.app.SaxionApp;

import java.awt.Color;

/**
 * Interactive tutorial: a handful of explanation slides interleaved with two
 * "pick the right card" exercises. Slides come from {@link Assets#TUTORIAL_SLIDES}
 * (t1..t9).
 */
public final class TutorialSequence {

    private static final int SCREEN_W = 1920;
    private static final int SCREEN_H = 1024;

    private TutorialSequence() {
    }

    public static void play() {
        String[] slide = Assets.TUTORIAL_SLIDES;

        showSlideAndWait(slide[0]);
        showSlideAndWait(slide[1]);
        showSlideAndWait(slide[2]);

        SaxionApp.clear();
        SaxionApp.setBorderColor(Color.orange);
        SaxionApp.setFill(Color.orange);
        SaxionApp.drawBorderedText("let's try it out!", 600, 500, 40);
        SaxionApp.sleep(2);

        trumpChoiceExercise();

        showSlideAndWait(slide[3]);
        showSlideAndWait(slide[4]);
        showSlideAndWait(slide[5]);
        showSlideAndWait(slide[6]);
        showSlideAndWait(slide[7]);
        SaxionApp.drawImage(slide[8], 0, 0, SCREEN_W, SCREEN_H);

        SaxionApp.clear();
        SaxionApp.drawBorderedText("let's go for a try!", 600, 500, 40);
        SaxionApp.sleep(2);

        winningCardExercise();
        supportingTeammateExercise();

        AudioHelper.stop();
    }

    private static void showSlideAndWait(String image) {
        SaxionApp.drawImage(image, 0, 0, SCREEN_W, SCREEN_H);
        SaxionApp.pause();
    }

    private static void trumpChoiceExercise() {
        SaxionApp.clear();
        SaxionApp.drawImage(Assets.TABLE_BACKGROUND, 0, 0, SCREEN_W, SCREEN_H);
        SaxionApp.drawBorderedText("which suit do you think is better to choose?", 600, 500, 40);
        SaxionApp.drawBorderedText("s", 700, 550, 40);
        SaxionApp.drawImage("resources/cardimages/spades.png", 740, 550, 50, 50);
        SaxionApp.drawBorderedText("d", 840, 550, 40);
        SaxionApp.drawImage("resources/cardimages/diamonds.png", 880, 550, 50, 50);
        SaxionApp.drawBorderedText("c", 980, 550, 40);
        SaxionApp.drawImage("resources/cardimages/clubs.png", 1020, 550, 50, 50);
        SaxionApp.drawBorderedText("h", 1120, 550, 40);
        SaxionApp.drawImage("resources/cardimages/hearts.png", 1160, 550, 50, 50);
        SaxionApp.drawImage("resources/cardimages/6H.png", 680, 750, 150, 200);
        SaxionApp.drawImage("resources/cardimages/3S.png", 710, 750, 150, 200);
        SaxionApp.drawImage("resources/cardimages/JD.png", 740, 750, 150, 200);
        SaxionApp.drawImage("resources/cardimages/8D.png", 770, 750, 150, 200);
        SaxionApp.drawImage("resources/cardimages/QC.png", 800, 750, 150, 200);

        char choice = SaxionApp.readChar();
        while (choice != 'd') {
            if (choice == 'h' || choice == 's') {
                SaxionApp.printLine("I think there are better options.");
            } else {
                SaxionApp.printLine("good choice but queen is powerful alone, it does not need to be a trump");
            }
            choice = SaxionApp.readChar();
        }
        SaxionApp.printLine("good choice!");
        SaxionApp.sleep(2);
    }

    private static void winningCardExercise() {
        SaxionApp.clear();
        SaxionApp.drawImage(Assets.TABLE_BACKGROUND, 0, 0, SCREEN_W, SCREEN_H);
        SaxionApp.drawBorderedText("what do we do here? choose a card", 600, 650, 40);
        SaxionApp.drawBorderedText("kings call: ", 10, 30, 25);
        SaxionApp.drawImage("resources/cardimages/diamonds.png", 130, 30, 25, 25);
        SaxionApp.drawBorderedText(" 1   2   3   4   5  6  7  8", 610, 710, 25);

        SaxionApp.drawImage("resources/cardimages/JC.png", 1000, 350, 150, 200);
        SaxionApp.drawImage("resources/cardimages/4C.png", 800, 260, 150, 200);
        SaxionApp.drawImage("resources/cardimages/8C.png", 600, 350, 150, 200);

        drawTutorialHand();

        char choice = SaxionApp.readChar();
        while (choice != '7' && choice != '8') {
            SaxionApp.printLine("but that way we would loose!");
            choice = SaxionApp.readChar();
        }
        SaxionApp.printLine("good choice!");
        SaxionApp.sleep(2);
    }

    private static void supportingTeammateExercise() {
        SaxionApp.clear();
        SaxionApp.drawImage(Assets.TABLE_BACKGROUND, 0, 0, SCREEN_W, SCREEN_H);
        SaxionApp.drawBorderedText("how about now? choose a card", 600, 650, 40);
        SaxionApp.drawBorderedText("kings call: ", 10, 30, 25);
        SaxionApp.drawImage("resources/cardimages/diamonds.png", 130, 30, 25, 25);

        SaxionApp.drawImage("resources/cardimages/JC.png", 1000, 350, 150, 200);
        SaxionApp.drawImage("resources/cardimages/AC.png", 800, 260, 150, 200);
        SaxionApp.drawImage("resources/cardimages/8C.png", 600, 350, 150, 200);
        SaxionApp.drawBorderedText(" 1   2   3   4   5  6  7  8", 610, 710, 25);

        drawTutorialHand();

        char choice = SaxionApp.readChar();
        while (choice == '7' || choice == '8') {
            SaxionApp.print("but our teammate has the upper hand!");
            choice = SaxionApp.readChar();
        }
        SaxionApp.printLine("great job!");
        SaxionApp.sleep(2);
    }

    private static void drawTutorialHand() {
        String[] hand = {"7S", "3S", "5S", "KH", "QH", "6H", "8D", "JD"};
        int x = 620;
        for (String card : hand) {
            SaxionApp.drawImage("resources/cardimages/" + card + ".png", x, 750, 150, 200);
            x += 30;
        }
    }
}
