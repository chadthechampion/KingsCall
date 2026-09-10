package kingscall;

import kingscall.audio.AudioHelper;
import kingscall.audio.MusicPlayer;
import kingscall.game.Deck;
import kingscall.game.Dealer;
import kingscall.game.RoleSelector;
import kingscall.game.RoundEngine;
import kingscall.game.ScoreBoard;
import kingscall.game.TrickResolver;
import kingscall.game.TrumpCaller;
import kingscall.model.Card;
import kingscall.model.Player;
import kingscall.model.Seat;
import kingscall.model.Suit;
import kingscall.ui.Assets;
import kingscall.ui.IntroSequence;
import kingscall.ui.TableRenderer;
import kingscall.ui.TutorialSequence;
import nl.saxion.app.SaxionApp;
import nl.saxion.app.interaction.GameLoop;
import nl.saxion.app.interaction.KeyboardEvent;
import nl.saxion.app.interaction.MouseEvent;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Entry point and top-level flow for King's Call: intro &rarr; menu &rarr; deal
 * &rarr; trump calls &rarr; tricks &rarr; scoring, repeating until one team wins
 * seven sets.
 *
 * <p>The structure (including {@link #playHand} calling itself when a set ends)
 * is deliberately preserved from the original; only the body has been split into
 * readable phases and delegated to the {@code game} / {@code ui} packages.
 */
public class KingsCall implements GameLoop {

    private static final int SCREEN_W = 1920;
    private static final int SCREEN_H = 1024;
    /** Cards shown per hand while trumps are being called. */
    private static final int PREVIEW = 5;

    private final ScoreBoard score = new ScoreBoard();
    private String king = RoleSelector.chooseKing();
    private String minister = RoleSelector.chooseMinister(king);
    private MusicPlayer music;

    public static void main(String[] args) {
        SaxionApp.startGameLoop(new KingsCall(), SCREEN_W, SCREEN_H, 5);
    }

    @Override
    public void init() {
        music = new MusicPlayer(Assets.MUSIC_INTRO);
        music.play();

        IntroSequence.play();

        char choice = SaxionApp.readChar();
        SaxionApp.clear();
        if (choice == '1') {
            SaxionApp.clear();
            IntroSequence.deliverLetter();
            SaxionApp.clear();
            playHand(king, minister);
        } else if (choice == '3') {
            SaxionApp.quit();
        } else if (choice == '2') {
            TutorialSequence.play();
            SaxionApp.clear();
            IntroSequence.showMenu();
            choice = SaxionApp.readChar();
            if (choice == '1') {
                IntroSequence.deliverLetter();
                playHand(king, minister);
            } else if (choice == '2') {
                SaxionApp.quit();
            }
        }

        playHand(king, minister);
    }

    @Override
    public void loop() {
    }

    /**
     * Plays out a full set from a fresh deal. When a team reaches seven set
     * points this recurses to start the next set (mirroring the original), so it
     * only truly returns once the match is over.
     */
    public void playHand(String king, String minister) {
        music.changeSong(Assets.MUSIC_GAME);

        List<Card> deck = Deck.create();
        List<Player> players = List.of(
            new Player(Seat.SOUTH), new Player(Seat.EAST),
            new Player(Seat.NORTH), new Player(Seat.WEST));
        Dealer.deal(deck, players);

        List<Suit> availableSuits = new ArrayList<>(Arrays.asList(Suit.values()));
        List<Suit> trumps = new ArrayList<>();
        List<String> trumpIcons = new ArrayList<>();

        announceRoles(king, minister);

        // Deal shown, trumps not yet called.
        clearToMarkedTable(king, minister);
        drawPreviewHands(players);
        callKing(king, players, availableSuits, trumps, trumpIcons);
        drawPreviewHands(players);
        SaxionApp.sleep(2);

        clearToMarkedTable(king, minister);
        drawPreviewHands(players);
        callMinister(minister, players, availableSuits, trumps, trumpIcons);

        clearToMarkedTable(king, minister);
        drawPlayHands(players);

        List<Card> trick = RoundEngine.playTrick(players, king, minister, trumps, trumpIcons);
        String trickWinner = TrickResolver.winner(trick, trumps, minister);
        score.awardTrick(trickWinner);

        while (!score.humanWonMatch() && !score.opponentWonMatch()) {
            clearToMarkedTable(king, minister);
            score.render();
            drawPlayHands(players);

            if (score.humanWonSet()) {
                showSetResult(Assets.HUMAN_WON_SET, Assets.SFX_WIN);
                king = passKingToHumanTeam(king);
                minister = RoleSelector.chooseMinister(king);
                score.awardTrick(trickWinner);
                score.resetPoints();
                playHand(king, minister);
            } else if (score.opponentWonSet()) {
                showSetResult(Assets.OPPONENT_WON_SET, Assets.SFX_FAIL);
                king = passKingToOpponentTeam(king);
                minister = RoleSelector.chooseMinister(king);
                score.awardTrick(trickWinner);
                score.resetPoints();
                playHand(king, minister);
            }

            while (!score.humanWonSet() && !score.opponentWonSet()) {
                SaxionApp.sleep(3);
                clearToMarkedTable(king, minister);
                score.render();
                drawPlayHands(players);
                trick = RoundEngine.playTrick(players, trickWinner, minister, trumps, trumpIcons);
                trickWinner = TrickResolver.winner(trick, trumps, minister);
                score.awardTrick(trickWinner);
            }
            SaxionApp.drawImage(score.humanWonSet() ? Assets.GAME_WON : Assets.GAME_LOST,
                0, 0, SCREEN_W, SCREEN_H);
        }
    }

    // ----- phases --------------------------------------------------------------

    private void announceRoles(String king, String minister) {
        SaxionApp.setFill(Color.orange);
        SaxionApp.setBorderColor(Color.orange);
        TableRenderer.drawBackground();
        score.render();
        SaxionApp.setFill(Color.ORANGE);
        SaxionApp.setBorderColor(Color.ORANGE);
        SaxionApp.drawBorderedText(roleLine(king, "king"), 700, 500, 50);
        SaxionApp.sleep(3);

        SaxionApp.clear();
        TableRenderer.drawBackground();
        score.render();
        score.render();
        SaxionApp.drawBorderedText(roleLine(minister, "minister"), 700, 500, 50);
        SaxionApp.sleep(3);
        SaxionApp.clear();
    }

    private void callKing(String king, List<Player> players, List<Suit> availableSuits,
                          List<Suit> trumps, List<String> trumpIcons) {
        Suit called = king.equals(Seat.SOUTH.id())
            ? TrumpCaller.callAsKingHuman(players, availableSuits)
            : TrumpCaller.callAsKingAi(players.get(Seat.fromId(king).ordinal()), availableSuits);
        record(called, trumps, trumpIcons);
    }

    private void callMinister(String minister, List<Player> players, List<Suit> availableSuits,
                              List<Suit> trumps, List<String> trumpIcons) {
        Suit called = minister.equals(Seat.SOUTH.id())
            ? TrumpCaller.callAsMinisterHuman(players, trumps.get(0), availableSuits)
            : TrumpCaller.callAsMinisterAi(players, availableSuits);
        record(called, trumps, trumpIcons);
    }

    private static void record(Suit called, List<Suit> trumps, List<String> trumpIcons) {
        trumps.add(called);
        trumpIcons.add(Assets.suitIcon(called));
    }

    private void showSetResult(String screen, String sound) {
        SaxionApp.sleep(1);
        SaxionApp.drawImage(screen, 0, 0, SCREEN_W, SCREEN_H);
        AudioHelper.play(sound, false);
        SaxionApp.sleep(3);
    }

    // ----- small helpers -----------------------------------------------------

    private void clearToMarkedTable(String king, String minister) {
        SaxionApp.clear();
        TableRenderer.drawBackground();
        TableRenderer.drawRoleMarkers(Seat.fromId(king), Seat.fromId(minister));
    }

    private static void drawPreviewHands(List<Player> players) {
        TableRenderer.drawAllHands(
            players.get(0).hand, players.get(1).hand, players.get(2).hand, players.get(3).hand, PREVIEW);
    }

    /**
     * The four hands as shown once play begins: the human's sorted and face up,
     * the others face down. Faithful to the original, every hand is truncated to
     * the east player's card count.
     */
    private static void drawPlayHands(List<Player> players) {
        Player south = players.get(0);
        Player east = players.get(1);
        Player north = players.get(2);
        Player west = players.get(3);
        int count = east.hand.size();
        TableRenderer.drawSouthHand(south.sortedHand(), kingscall.ui.TableLayout.hand(Seat.SOUTH), count);
        TableRenderer.drawSideHand(east.hand, kingscall.ui.TableLayout.hand(Seat.EAST), count);
        TableRenderer.drawNorthHand(north.hand, kingscall.ui.TableLayout.hand(Seat.NORTH), north.hand.size());
        TableRenderer.drawSideHand(west.hand, kingscall.ui.TableLayout.hand(Seat.WEST), west.hand.size());
    }

    private static String roleLine(String who, String role) {
        return who.equals(Seat.SOUTH.id()) ? "You are the " + role + "!" : who + " is the " + role + "!";
    }

    /** King moves to the human side after they win a set (only seats 2 and 4 hand it over). */
    private static String passKingToHumanTeam(String king) {
        if (king.equals("player 2")) {
            return "player 3";
        }
        if (king.equals("player 4")) {
            return "player 1";
        }
        return king;
    }

    /** King moves to the opponent side after they win a set. */
    private static String passKingToOpponentTeam(String king) {
        if (king.equals("player 1")) {
            return "player 2";
        }
        if (king.equals("player 3")) {
            return "player 4";
        }
        return king;
    }

    @Override
    public void keyboardEvent(KeyboardEvent keyboardEvent) {
    }

    @Override
    public void mouseEvent(MouseEvent mouseEvent) {
    }
}
