package kingscall.game;

import kingscall.model.Seat;
import kingscall.model.Team;
import nl.saxion.app.SaxionApp;

/**
 * Tracks and draws the score.
 *
 * <p>Each team has two counters: <b>points</b> are tricks won in the current set
 * (0..7) and <b>sets</b> are completed sets won. Winning a trick while already on
 * 7 points converts into a set instead. First team to 7 sets wins the match.
 */
public final class ScoreBoard {

    private static final int SET_TARGET = 7;

    private int humanPoints;
    private int humanSets;
    private int opponentPoints;
    private int opponentSets;

    public int humanPoints() {
        return humanPoints;
    }

    public int opponentPoints() {
        return opponentPoints;
    }

    public int humanSets() {
        return humanSets;
    }

    public int opponentSets() {
        return opponentSets;
    }

    public boolean humanWonSet() {
        return humanPoints == SET_TARGET;
    }

    public boolean opponentWonSet() {
        return opponentPoints == SET_TARGET;
    }

    public boolean humanWonMatch() {
        return humanSets == SET_TARGET;
    }

    public boolean opponentWonMatch() {
        return opponentSets == SET_TARGET;
    }

    /** Zeroes both point counters at the start of a new set. */
    public void resetPoints() {
        humanPoints = 0;
        opponentPoints = 0;
    }

    /** Credits the trick to the winner's team: a point, or a set if already at 7. */
    public void awardTrick(String winnerId) {
        Team team = Seat.fromId(winnerId).team();
        if (team == Team.HUMAN) {
            if (humanPoints == SET_TARGET) {
                humanSets++;
            } else {
                humanPoints++;
            }
        } else {
            if (opponentPoints == SET_TARGET) {
                opponentSets++;
            } else {
                opponentPoints++;
            }
        }
    }

    /** Draws the score/points panel in the top-right corner. */
    public void render() {
        SaxionApp.drawBorderedText("SCORE", 1639, 10, 25);
        SaxionApp.drawBorderedText("you:", 1550, 40, 25);
        SaxionApp.drawBorderedText(humanSets + "", 1610, 40, 25);
        SaxionApp.drawBorderedText("opponent:", 1700, 40, 25);
        SaxionApp.drawBorderedText(opponentSets + "", 1830, 40, 25);
        SaxionApp.drawBorderedText("POINTS", 1639, 73, 25);
        SaxionApp.drawBorderedText("you:", 1550, 100, 25);
        SaxionApp.drawBorderedText(humanPoints + "", 1610, 100, 25);
        SaxionApp.drawBorderedText("opponent:", 1700, 100, 25);
        SaxionApp.drawBorderedText(opponentPoints + "", 1830, 100, 25);
    }
}
