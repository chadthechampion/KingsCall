import nl.saxion.app.SaxionApp;

import java.awt.*;
import java.util.ArrayList;

public class Intro {

    public void FadeIn(String filename1, String filename2, String filename3, String filename4, String filename5, String filename6) {

        SaxionApp.drawImage(filename1, 0, 0, 1920, 1024);
        SaxionApp.sleep(0.00005);
        SaxionApp.drawImage(filename2, 0, 0, 1920, 1024);
        SaxionApp.sleep(0.00005);
        SaxionApp.drawImage(filename3, 0, 0, 1920, 1024);
        SaxionApp.sleep(0.00005);
        SaxionApp.drawImage(filename4, 0, 0, 1920, 1024);
        SaxionApp.sleep(0.00005);
        SaxionApp.drawImage(filename5, 0, 0, 1920, 1024);
        SaxionApp.sleep(0.00005);
        SaxionApp.drawImage(filename6, 0, 0, 1920, 1024);
        SaxionApp.sleep(2);
    }

    public void birdCome(String filename1, String filename2, String filename3) {

        int x = -500; // Start from the top-left corner
        int y = -500;
        int wantedX = 1920 / 10;
        int wantedY = 1024 / 16;

        SaxionApp.setBackgroundColor(Color.black);

        while (x < wantedX && y < wantedY) {
            SaxionApp.clear(); // Clear the screen
            SaxionApp.drawImage(filename1, x, y, 500, 500); // Draw the image at (x, y)
            SaxionApp.sleep(0.008); // Pause to create an animation effect
            x += 4;
            y += 4;
        }

        SaxionApp.clear();
        SaxionApp.drawImage(filename2, 650, 300, 350, 300);
        SaxionApp.sleep(1);
        SaxionApp.clear();
        SaxionApp.drawImage(filename3, 650, 300, 500, 500);

        SaxionApp.sleep(1);
    }


    public void fadeRight(String filename) {

        int x = 2000; // Start from the top-left corner
        int y = 0;
        int wantedX = 0;
        int wantedY = 1024;
        SaxionApp.setBackgroundColor(Color.black);
        while (x > wantedX) {
            SaxionApp.clear(); // Clear the screen
            SaxionApp.drawImage(filename, x, y, 1920, 1024); // Draw the image at (x, y)
            SaxionApp.sleep(0.001); // Pause to create an animation effect
            x -= 5;

            if (x == 0) {
                SaxionApp.pause();

            }
        }
    }


    public void fadeLeft(String filename) {
        int x = -2000; // Start from the top-left corner
        int y = 0;
        int wantedX = 0;
        int wantedY = 1024;

        SaxionApp.setBackgroundColor(Color.black);

        while (x < wantedX) {
            SaxionApp.clear(); // Clear the screen
            SaxionApp.drawImage(filename, x, y, 1920, 1024); // Draw the image at (x, y)
            SaxionApp.sleep(0.001); // Pause to create an animation effect
            x += 5;
            if (x == 0) {
                SaxionApp.pause();


            }
            ;

        }

    }

    public void fadeDown(String filename) {
        int x = 0; // Start from the top-left corner
        int y = -1500;
        int wantedX = 0;
        int wantedY = 0;

        SaxionApp.setBackgroundColor(Color.black);

        while (y < wantedY) {
            SaxionApp.clear(); // Clear the screen
            SaxionApp.drawImage(filename, x, y, 1920, 1024); // Draw the image at (x, y)
            SaxionApp.sleep(0.001); // Pause to create an animation effect
            y += 5;


        }

    }



}
