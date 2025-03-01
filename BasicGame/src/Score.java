import nl.saxion.app.SaxionApp;

import java.util.ArrayList;

public class Score {
    public void roundScore(String winner, int[] team1, int[] team2, int pointTeam1, int scoreTeam1, int pointTeam2, int scoreTeam2) {

        if (winner.equals("player 1") || winner.equals("player 3")) {
            if (pointTeam1 == 7) {
                scoreTeam1 += 1;
                team1[1] = scoreTeam1;
            } else {
                pointTeam1 += 1;
                team1[0] = pointTeam1;
            }

        }
        if (winner.equals("player 2") || winner.equals("player 4")) {
            if (pointTeam2 == 7) {
                scoreTeam2 += 1;
                team2 [1] = scoreTeam2;
            } else {
                pointTeam2 += 1;
                team2 [0] = pointTeam2;
            }
        }


    }

    public void printScore(int team1Score, int team2Score,int team1Point, int team2Point ){
        SaxionApp.drawBorderedText("SCORE", 1639, 10, 25);
        SaxionApp.drawBorderedText("you:", 1550, 40, 25);
        SaxionApp.drawBorderedText(team1Score + "", 1610, 40, 25);
        SaxionApp.drawBorderedText("opponent:", 1700, 40, 25);
        SaxionApp.drawBorderedText(team2Score + "", 1830, 40, 25);
        SaxionApp.drawBorderedText("POINTS", 1639, 73, 25);
        SaxionApp.drawBorderedText("you:", 1550, 100, 25);
        SaxionApp.drawBorderedText(team1Point+ "", 1610, 100, 25);
        SaxionApp.drawBorderedText("opponent:", 1700, 100, 25);
        SaxionApp.drawBorderedText(team2Point + "", 1830, 100, 25);
    }
}
