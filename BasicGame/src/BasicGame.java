import nl.saxion.app.SaxionApp;

import nl.saxion.app.interaction.GameLoop;
import nl.saxion.app.interaction.KeyboardEvent;
import nl.saxion.app.interaction.MouseEvent;

import java.awt.*;
import java.util.ArrayList;

public class BasicGame implements GameLoop {

    public static void main(String[] args) {
        SaxionApp.startGameLoop(new BasicGame(), 1920, 1024, 5);
    }

    CreateCards create = new CreateCards();
    Intro intro = new Intro();
    Choose choose = new Choose();
    int[] team1 = new int[2];
    int[] team2 = new int[2];
    String king = choose.chooseTheKing();
    String minister = choose.chooseTheMinister(king);
    MPlayer mPlayer;


    @Override
    public void init() {

        mPlayer = new MPlayer("resources/music/g_intro.wav");
        mPlayer.play();

        intro.FadeIn("resources/STORYLINEPHOTOS/black.png",
                "resources/STORYLINEPHOTOS/start20.png",
                "resources/STORYLINEPHOTOS/start40.png",
                "resources/STORYLINEPHOTOS/start60.png",
                "resources/STORYLINEPHOTOS/start80.png",
                "resources/STORYLINEPHOTOS/start.png");

        intro.fadeRight("resources/STORYLINEPHOTOS/shiraz100.png");
        intro.fadeLeft("resources/STORYLINEPHOTOS/shirazdark100.png");
        intro.fadeRight("resources/STORYLINEPHOTOS/king100.png");
        intro.fadeLeft("resources/STORYLINEPHOTOS/minister100.png");
        intro.fadeRight("resources/STORYLINEPHOTOS/citizen100.png");
        intro.fadeDown("resources/STORYLINEPHOTOS/menu.png");


        char userChoice = SaxionApp.readChar();
        SaxionApp.clear();
        if (userChoice == '1') {
            SaxionApp.clear();
            intro.birdCome("resources/STORYLINEPHOTOS/birdd.png", "resources/STORYLINEPHOTOS/letter.png", "resources/STORYLINEPHOTOS/gamestarts.png");
            SaxionApp.clear();
            start(king, minister);

        } else if (userChoice == '3') {
            SaxionApp.quit();

        } else if (userChoice == '2') {
            Tutorial.tutorial("resources/STORYLINEPHOTOS/t1.png", "resources/STORYLINEPHOTOS/t2.png", "resources/STORYLINEPHOTOS/t3.png", "resources/STORYLINEPHOTOS/t4.png", "resources/STORYLINEPHOTOS/t5.png", "resources/STORYLINEPHOTOS/t6.png", "resources/STORYLINEPHOTOS/t7.png", "resources/STORYLINEPHOTOS/t8.png", "resources/STORYLINEPHOTOS/t9.png");
            SaxionApp.clear();
            intro.fadeDown("resources/STORYLINEPHOTOS/menu.png");
            userChoice = SaxionApp.readChar();
            if (userChoice == '1') {
                intro.birdCome("resources/STORYLINEPHOTOS/birdd.png", "resources/STORYLINEPHOTOS/letter.png", "resources/STORYLINEPHOTOS/gamestarts.png");
                start(king, minister);
            } else if (userChoice == '2') {

                SaxionApp.quit();
            }
        }

        start(king, minister);


    }

    @Override
    public void loop() {


    }

    public void start(String king, String minister) {
        mPlayer.changeSong("resources/music/g_music.wav");

        ArrayList<Card> cards = create.createCards();

        Player player1 = new Player();
        Player player2 = new Player();
        Player player3 = new Player();
        Player player4 = new Player();


        ArrayList<Card> player1Cards = player1.createDeckOfCards("player 1", cards);
        ArrayList<Card> player2Cards = player2.createDeckOfCards("player 2", cards);
        ArrayList<Card> player3Cards = player3.createDeckOfCards("player 3", cards);
        ArrayList<Card> player4Cards = player4.createDeckOfCards("player 4", cards);

        player1.name = "player 1";
        player2.name = "player 2";
        player3.name = "player 3";
        player4.name = "player 4";

        ArrayList<String> trumps = new ArrayList<>();
        ArrayList<String> trumpImages = new ArrayList<>();

        ArrayList<Character> allSuits = new ArrayList<>();
        allSuits.add('H');
        allSuits.add('D');
        allSuits.add('C');
        allSuits.add('S');

        Score scores = new Score();
        SaxionApp.setFill(Color.orange);
        SaxionApp.setBorderColor(Color.orange);
        SaxionApp.drawImage("resources/file.png", 0, 0, 1920, 1024);
        scores.printScore(team1[1], team2[1], team1[0], team2[0]);
        SaxionApp.setFill(Color.ORANGE);
        SaxionApp.setBorderColor(Color.ORANGE);
        if (king.equals("player 1")) {
            SaxionApp.drawBorderedText("You are the king!", 700, 500, 50);
        } else {
            SaxionApp.drawBorderedText(king + " is the king!", 700, 500, 50);
        }
        SaxionApp.sleep(3);
        SaxionApp.clear();
        SaxionApp.drawImage("resources/file.png", 0, 0, 1920, 1024);
        scores.printScore(team1[1], team2[1], team1[0], team2[0]);
        scores.printScore(team1[1], team2[1], team1[0], team2[0]);


        if (minister.equals("player 1")) {
            SaxionApp.drawBorderedText("You are the minister!", 700, 500, 50);
        } else {
            SaxionApp.drawBorderedText(minister + " is the minister!", 700, 500, 50);
        }
        SaxionApp.sleep(3);
        SaxionApp.clear();
        SaxionApp.drawImage("resources/file.png", 0, 0, 1920, 1024);
        choose.showKingAndMinister(king, minister);


        player1.showMyCards(player1Cards, 680, 750, 5, 0);
        player2.showCardsEven(player2Cards, 1500, 230, 5, 0);
        player3.showCardsOdd(player3Cards, 680, 50, 5, 0);
        player4.showCardsEven(player4Cards, 140, 230, 5, 0);

        Trump trumpCalls = new Trump();



        switch (king) {
            case "player 1" -> trumpCalls.kingsCall(player1, player2, player3, player4, allSuits, trumps, trumpImages);
            case "player 2" -> trumpCalls.otherKingsCall(player2, allSuits, trumps, trumpImages);

            case "player 3" -> trumpCalls.otherKingsCall(player3, allSuits, trumps, trumpImages);
            default -> trumpCalls.otherKingsCall(player4, allSuits, trumps, trumpImages);
        }
        player1.showMyCards(player1Cards, 680, 750, 5, 0);
        player2.showCardsEven(player2Cards, 1500, 230, 5, 0);
        player3.showCardsOdd(player3Cards, 680, 50, 5, 0);
        player4.showCardsEven(player4Cards, 140, 230, 5, 0);

        SaxionApp.sleep(2);
        SaxionApp.clear();
        SaxionApp.drawImage("resources/file.png", 0, 0, 1920, 1024);
        choose.showKingAndMinister(king, minister);

        player1.showMyCards(player1Cards, 680, 750, 5, 0);
        player2.showCardsEven(player2Cards, 1500, 230, 5, 0);
        player3.showCardsOdd(player3Cards, 680, 50, 5, 0);
        player4.showCardsEven(player4Cards, 140, 230, 5, 0);

        if (minister.equals("player 1")) {
            trumpCalls.ministersCall(player1, player2, player3, player4, allSuits, trumps, trumpImages);
        } else {
            trumpCalls.otherMinistersCall(player1, player2, player3, player4, allSuits, trumps, trumpImages);
        }
        SaxionApp.clear();
        SaxionApp.drawImage("resources/file.png", 0, 0, 1920, 1024);
        choose.showKingAndMinister(king, minister);
        player1.showMyCards(player1.sortCards(player1Cards), 680, 750, player2.playerCards.size(), 0);
        player2.showCardsEven(player2.playerCards, 1500, 230, player2.playerCards.size(), 0);
        player3.showCardsOdd(player3.playerCards, 680, 50, player3.playerCards.size(), 0);
        player4.showCardsEven(player4.playerCards, 140, 230, player4.playerCards.size(), 0);


        Round round = new Round();
        ArrayList<Card> roundCards = round.round(player1, player2, player3, player4, king, minister, trumps, trumpImages);
        Winner winner = new Winner();
        String roundWinner = winner.roundWinner(roundCards, trumps, minister);
        scores.roundScore(roundWinner, team1, team2, team1[0], team1[1], team2[0], team2[1]);

        while (team1[1] != 7 && team2[1] != 7) {
            SaxionApp.clear();
            SaxionApp.drawImage("resources/file.png", 0, 0, 1920, 1024);
            choose.showKingAndMinister(king, minister);
            scores.printScore(team1[1], team2[1], team1[0], team2[0]);
            player1.showMyCards(player1.sortCards(player1Cards), 680, 750, player2.playerCards.size(), 0);
            player2.showCardsEven(player2.playerCards, 1500, 230, player2.playerCards.size(), 0);
            player3.showCardsOdd(player3.playerCards, 680, 50, player3.playerCards.size(), 0);
            player4.showCardsEven(player4.playerCards, 140, 230, player4.playerCards.size(), 0);

            if (team1[0] == 7) {
                SaxionApp.sleep(1);
                SaxionApp.drawImage("resources/yourteam.png", 0, 0, 1920, 1024);
                AudioHelper.play("resources/music/win.wav",false);
                SaxionApp.sleep(3);
                if (king.equals("player 2")) {
                    king = "player 3";
                } else if (king.equals("player 4")) {
                    king = "player 1";
                }
                minister = choose.chooseTheMinister(king);
                scores.roundScore(roundWinner, team1, team2, team1[0], team1[1], team2[0], team2[1]);
                team1[0] = 0;
                team2[0] = 0;
                start(king, minister);

            } else if (team2[0] == 7) {
                SaxionApp.sleep(1);
                SaxionApp.drawImage("resources/opponentWin.png", 0, 0, 1920, 1024);
                AudioHelper.play("resources/music/fail.wav",false);
                SaxionApp.sleep(3);
                if (king.equals("player 1")) {
                    king = "player 2";
                } else if (king.equals("player 3")) {
                    king = "player 4";
                }
                minister = choose.chooseTheMinister(king);
                scores.roundScore(roundWinner, team1, team2, team1[0], team1[1], team2[0], team2[1]);
                team1[0] = 0;
                team2[0] = 0;
                start(king, minister);

            }
            while ((team1[0] != 7 && (team2[0] != 7))) {
                SaxionApp.sleep(3);
                SaxionApp.clear();
                SaxionApp.drawImage("resources/file.png", 0, 0, 1920, 1024);
                choose.showKingAndMinister(king, minister);
                scores.printScore(team1[1], team2[1], team1[0], team2[0]);
                player1.showMyCards(player1.sortCards(player1Cards), 680, 750, player2.playerCards.size(), 0);
                player2.showCardsEven(player2.playerCards, 1500, 230, player2.playerCards.size(), 0);
                player3.showCardsOdd(player3.playerCards, 680, 50, player3.playerCards.size(), 0);
                player4.showCardsEven(player4.playerCards, 140, 230, player4.playerCards.size(), 0);
                roundCards = round.round(player1, player2, player3, player4, roundWinner, minister, trumps, trumpImages);
                roundWinner = winner.roundWinner(roundCards, trumps,minister);
                scores.roundScore(roundWinner, team1, team2, team1[0], team1[1], team2[0], team2[1]);
            }
            if (team1[0] == 7){
                SaxionApp.drawImage("resources/win.png", 0, 0, 1920, 1024);
            }else {
                SaxionApp.drawImage("resources/loose.png", 0, 0, 1920, 1024);
            }


        }


    }


    @Override
    public void keyboardEvent(KeyboardEvent keyboardEvent) {

    }

    @Override
    public void mouseEvent(MouseEvent mouseEvent) {

    }


}






