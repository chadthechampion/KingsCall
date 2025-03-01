import nl.saxion.app.SaxionApp;

import java.awt.*;

public class Tutorial {
    public static void tutorial(String file1, String file2, String file3, String file4, String file5, String file6, String file7, String file8, String file9) {
        SaxionApp.drawImage(file1, 0, 0, 1920, 1024);
        SaxionApp.pause();
        SaxionApp.drawImage(file2, 0, 0, 1920, 1024);
        SaxionApp.pause();
        SaxionApp.drawImage(file3, 0, 0, 1920, 1024);
        SaxionApp.pause();
        SaxionApp.clear();
        SaxionApp.setBorderColor(Color.orange);
        SaxionApp.setFill(Color.orange);
        SaxionApp.drawBorderedText("let's try it out!", 600, 500, 40);
        SaxionApp.sleep(2);
        SaxionApp.clear();
        SaxionApp.drawImage("resources/file.png", 0, 0, 1920, 1024);
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
        char userChoice = SaxionApp.readChar();
        while (userChoice != 'd') {
            if (userChoice == 'h') {
                SaxionApp.printLine("I think there are better options.");
                userChoice = SaxionApp.readChar();
            } else if (userChoice == 's') {
                SaxionApp.printLine("I think there are better options.");
                userChoice = SaxionApp.readChar();
            } else {
                SaxionApp.printLine("good choice but queen is powerful alone, it does not need to be a trump");
                userChoice = SaxionApp.readChar();
            }

        }
        SaxionApp.printLine("good choice!");
        SaxionApp.sleep(2);
        SaxionApp.clear();
        SaxionApp.drawImage(file4, 0, 0, 1920, 1024);
        SaxionApp.pause();
        SaxionApp.drawImage(file5, 0, 0, 1920, 1024);
        SaxionApp.pause();
        SaxionApp.drawImage(file6, 0, 0, 1920, 1024);
        SaxionApp.pause();
        SaxionApp.drawImage(file7, 0, 0, 1920, 1024);
        SaxionApp.pause();
        SaxionApp.drawImage(file8, 0, 0, 1920, 1024);
        SaxionApp.pause();
        SaxionApp.drawImage(file9, 0, 0, 1920, 1024);
        SaxionApp.clear();
        SaxionApp.drawBorderedText("let's go for a try!", 600, 500, 40);
        SaxionApp.sleep(2);
        SaxionApp.clear();
        SaxionApp.drawImage("resources/file.png", 0, 0, 1920, 1024);
        SaxionApp.drawBorderedText("what do we do here? choose a card", 600, 650, 40);
        SaxionApp.drawBorderedText("kings call: ", 10, 30, 25);
        SaxionApp.drawImage("resources/cardimages/diamonds.png", 130, 30, 25, 25);

        SaxionApp.drawBorderedText(" 1   2   3   4   5  6  7  8", 610, 710, 25);

        SaxionApp.drawImage("resources/cardimages/JC.png", 1000, 350, 150, 200);
        SaxionApp.drawImage("resources/cardimages/4C.png", 800, 260, 150, 200);
        SaxionApp.drawImage("resources/cardimages/8C.png", 600, 350, 150, 200);

        SaxionApp.drawImage("resources/cardimages/7S.png", 620, 750, 150, 200);
        SaxionApp.drawImage("resources/cardimages/3S.png", 650, 750, 150, 200);
        SaxionApp.drawImage("resources/cardimages/5S.png", 680, 750, 150, 200);
        SaxionApp.drawImage("resources/cardimages/KH.png", 710, 750, 150, 200);
        SaxionApp.drawImage("resources/cardimages/QH.png", 740, 750, 150, 200);
        SaxionApp.drawImage("resources/cardimages/6H.png", 770, 750, 150, 200);
        SaxionApp.drawImage("resources/cardimages/8D.png", 800, 750, 150, 200);
        SaxionApp.drawImage("resources/cardimages/JD.png", 830, 750, 150, 200);
        char choice = SaxionApp.readChar();
        while (choice != '7' && choice != '8'){
            SaxionApp.printLine("but that way we would loose!");
            choice = SaxionApp.readChar();
        }
        SaxionApp.printLine("good choice!");
        SaxionApp.sleep(2);
        SaxionApp.clear();
        SaxionApp.drawImage("resources/file.png", 0, 0, 1920, 1024);
        SaxionApp.drawBorderedText("how about now? choose a card", 600, 650, 40);
        SaxionApp.drawBorderedText("kings call: ", 10, 30, 25);
        SaxionApp.drawImage("resources/cardimages/diamonds.png", 130, 30, 25, 25);

        SaxionApp.drawImage("resources/cardimages/JC.png", 1000, 350, 150, 200);
        SaxionApp.drawImage("resources/cardimages/AC.png", 800, 260, 150, 200);
        SaxionApp.drawImage("resources/cardimages/8C.png", 600, 350, 150, 200);
        SaxionApp.drawBorderedText(" 1   2   3   4   5  6  7  8", 610, 710, 25);


        SaxionApp.drawImage("resources/cardimages/7S.png", 620, 750, 150, 200);
        SaxionApp.drawImage("resources/cardimages/3S.png", 650, 750, 150, 200);
        SaxionApp.drawImage("resources/cardimages/5S.png", 680, 750, 150, 200);
        SaxionApp.drawImage("resources/cardimages/KH.png", 710, 750, 150, 200);
        SaxionApp.drawImage("resources/cardimages/QH.png", 740, 750, 150, 200);
        SaxionApp.drawImage("resources/cardimages/6H.png", 770, 750, 150, 200);
        SaxionApp.drawImage("resources/cardimages/8D.png", 800, 750, 150, 200);
        SaxionApp.drawImage("resources/cardimages/JD.png", 830, 750, 150, 200);

        choice = SaxionApp.readChar();
        while (choice == '7' || choice == '8'){
            SaxionApp.print("but our teammate has the upper hand!");
            choice = SaxionApp.readChar();
        }
        SaxionApp.printLine("great job!");
        SaxionApp.sleep(2);


        AudioHelper.stop();
    }
}
