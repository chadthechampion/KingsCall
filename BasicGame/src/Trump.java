import nl.saxion.app.SaxionApp;

import java.awt.*;
import java.util.ArrayList;

public class Trump {
    public void kingsCall(Player player1,Player player2,Player player3,Player player4, ArrayList<Character> suits, ArrayList<String> trumps, ArrayList<String> trumpsImage) {
        SaxionApp.setFill(Color.orange);
        SaxionApp.setBorderColor(Color.orange);
        SaxionApp.drawBorderedText("king!choose your trump!(h,s,c,d) ", 600, 500, 50);
        char trump = SaxionApp.readChar();
        if (trump == 'h' || trump == 'H') {
            suits.removeFirst();
        } else if (trump == 'd' || trump == 'D') {
            suits.remove(1);
        } else if (trump == 'c' || trump == 'C') {
            suits.remove(2);
        } else {
            suits.remove(3);
        }
        SaxionApp.clear();
        SaxionApp.drawImage("resources/file.png", 0, 0, 1920, 1024);
        player1.showMyCards(player1.playerCards,680, 750, 5, 0);
        player2.showCardsEven(player2.playerCards,1500, 230, 5, 0);
        player3.showCardsOdd(player3.playerCards, 680, 50, 5, 0);
        player4.showCardsEven(player4.playerCards, 140, 230, 5, 0);

        String trumpImage = "";
        while (!(trump == 's' || trump == 'd' || trump == 'h' || trump == 'c')) {
            SaxionApp.setFill(Color.red);
            SaxionApp.setBorderColor(Color.red);
            SaxionApp.drawBorderedText("wrong suit!", 600, 500, 50);
            SaxionApp.sleep(2);
            SaxionApp.clear();
            SaxionApp.drawImage("resources/file.png", 0, 0, 1920, 1024);
            player1.showMyCards(player1.playerCards,680, 750, 5, 0);
            player2.showCardsEven(player2.playerCards,1500, 230, 5, 0);
            player3.showCardsOdd(player3.playerCards, 680, 50, 5, 0);
            player4.showCardsEven(player4.playerCards, 140, 230, 5, 0);
            SaxionApp.setFill(Color.orange);
            SaxionApp.setBorderColor(Color.orange);
            SaxionApp.drawBorderedText("king!choose your trump!(h,s,c,d) ", 600, 500, 50);
            trump = SaxionApp.readChar();
            SaxionApp.clear();
            SaxionApp.drawImage("resources/file.png", 0, 0, 1920, 1024);
            player1.showMyCards(player1.playerCards,680, 750, 5, 0);
            player2.showCardsEven(player2.playerCards,1500, 230, 5, 0);
            player3.showCardsOdd(player3.playerCards, 680, 50, 5, 0);
            player4.showCardsEven(player4.playerCards, 140, 230, 5, 0);
        }
        String kingsTrump;
        if (trump == 's') {
            trumpImage = "resources/cardimages/spades.png";
            kingsTrump = "S";
        } else if (trump == 'h') {
            trumpImage = "resources/cardimages/hearts.png";
            kingsTrump = "H";
        } else if (trump == 'c') {
            trumpImage = "resources/cardimages/clubs.png";
            kingsTrump = "C";
        } else {
            trumpImage = "resources/cardimages/diamonds.png";
            kingsTrump = "D";
        }
        trumps.add(kingsTrump);
        trumpsImage.add(trumpImage);
        SaxionApp.setFill(Color.orange);
        SaxionApp.setBorderColor(Color.orange);
        SaxionApp.drawBorderedText("the king calls ", 700, 500, 50);
        SaxionApp.drawImage(trumpImage, 1000, 500, 50, 50);

    }

    public void ministersCall(Player player1, Player player2, Player player3, Player player4, ArrayList<Character> suits, ArrayList<String> trumps, ArrayList<String> trumpsImage) {
        SaxionApp.setFill(Color.orange);
        SaxionApp.setBorderColor(Color.orange);
        SaxionApp.drawBorderedText("minister!choose your trump![H,S,C,D] ", 600, 500, 50);
        char trump = SaxionApp.readChar();
        trump = Character.toUpperCase(trump);
        while (trumps.contains(Character.toString(trump))) {
            SaxionApp.clear();
            SaxionApp.drawImage("resources/file.png", 0, 0, 1920, 1024);
            player1.showMyCards(player1.playerCards,680, 750, 5, 0);
            player2.showCardsEven(player2.playerCards,1500, 230, 5, 0);
            player3.showCardsOdd(player3.playerCards, 680, 50, 5, 0);
            player4.showCardsEven(player4.playerCards, 140, 230, 5, 0);
            SaxionApp.setFill(Color.red);
            SaxionApp.setBorderColor(Color.red);
            SaxionApp.drawBorderedText("That is kings call !", 600, 500, 50);
            SaxionApp.sleep(2);
            SaxionApp.clear();
            SaxionApp.drawImage("resources/file.png", 0, 0, 1920, 1024);
            player1.showMyCards(player1.playerCards,680, 750, 5, 0);
            player2.showCardsEven(player2.playerCards,1500, 230, 5, 0);
            player3.showCardsOdd(player3.playerCards, 680, 50, 5, 0);
            player4.showCardsEven(player4.playerCards, 140, 230, 5, 0);
            SaxionApp.setFill(Color.orange);
            SaxionApp.setBorderColor(Color.orange);
            SaxionApp.drawBorderedText("minister!choose your trump! " + suits, 600, 500, 50);
            trump = SaxionApp.readChar();
            trump = Character.toUpperCase(trump);
            SaxionApp.clear();
            SaxionApp.drawImage("resources/file.png", 0, 0, 1920, 1024);
            player1.showMyCards(player1.playerCards,680, 750, 5, 0);
            player2.showCardsEven(player2.playerCards,1500, 230, 5, 0);
            player3.showCardsOdd(player3.playerCards, 680, 50, 5, 0);
            player4.showCardsEven(player4.playerCards, 140, 230, 5, 0);
        }

        SaxionApp.clear();
        SaxionApp.drawImage("resources/file.png", 0, 0, 1920, 1024);
        player1.showMyCards(player1.playerCards,680, 750, 5, 0);
        player2.showCardsEven(player2.playerCards,1500, 230, 5, 0);
        player3.showCardsOdd(player3.playerCards, 680, 50, 5, 0);
        player4.showCardsEven(player4.playerCards, 140, 230, 5, 0);

        String trumpImage = "";
        while (!(trump == 'S' || trump == 'D' || trump == 'H' || trump == 'C')) {
            SaxionApp.setFill(Color.red);
            SaxionApp.setBorderColor(Color.red);
            SaxionApp.drawBorderedText("wrong suit!", 600, 500, 50);
            SaxionApp.sleep(2);
            SaxionApp.clear();
            SaxionApp.drawImage("resources/file.png", 0, 0, 1920, 1024);
            player1.showMyCards(player1.playerCards,680, 750, 5, 0);
            player2.showCardsEven(player2.playerCards,1500, 230, 5, 0);
            player3.showCardsOdd(player3.playerCards, 680, 50, 5, 0);
            player4.showCardsEven(player4.playerCards, 140, 230, 5, 0);
            SaxionApp.setFill(Color.orange);
            SaxionApp.setBorderColor(Color.orange);
            SaxionApp.drawBorderedText("minister!choose your trump! " + suits, 600, 500, 50);
            trump = SaxionApp.readChar();
            trump = Character.toUpperCase(trump);
            SaxionApp.clear();
            SaxionApp.drawImage("resources/file.png", 0, 0, 1920, 1024);
            player1.showMyCards(player1.playerCards,680, 750, 5, 0);
            player2.showCardsEven(player2.playerCards,1500, 230, 5, 0);
            player3.showCardsOdd(player3.playerCards, 680, 50, 5, 0);
            player4.showCardsEven(player4.playerCards, 140, 230, 5, 0);
        }
        String ministersTrump;
        if (trump == 'S') {
            trumpImage = "resources/cardimages/spades.png";
            ministersTrump = "S";
        } else if (trump == 'H') {
            trumpImage = "resources/cardimages/hearts.png";
            ministersTrump = "H";
        } else if (trump == 'C') {
            trumpImage = "resources/cardimages/clubs.png";
            ministersTrump = "C";

        } else {
            trumpImage = "resources/cardimages/diamonds.png";
            ministersTrump = "D";
        }
        trumps.add(ministersTrump);
        trumpsImage.add(trumpImage);
        SaxionApp.setFill(Color.orange);
        SaxionApp.setBorderColor(Color.orange);
        SaxionApp.drawBorderedText("the minister calls ", 700, 500, 50);
        SaxionApp.drawImage(trumpImage, 1100, 500, 50, 50);
        SaxionApp.sleep(2);
        SaxionApp.clear();
        SaxionApp.drawImage("resources/file.png", 0, 0, 1920, 1024);
        player1.showMyCards(player1.playerCards,680, 750, 5, 0);
        player2.showCardsEven(player2.playerCards,1500, 230, 5, 0);
        player3.showCardsOdd(player3.playerCards, 680, 50, 5, 0);
        player4.showCardsEven(player4.playerCards, 140, 230, 5, 0);
    }


    public void otherKingsCall(Player player, ArrayList<Character> allSuits, ArrayList<String> trump, ArrayList<String> trumpsImage) {
        int counterH = 0;
        int counterD = 0;
        int counterC = 0;
        int counterS = 0;

        ArrayList<Integer> suitNumbers = new ArrayList<>();
        String suitImage = "";

        for (int i = 0; i < 5; i++) {
            switch (player.playerCards.get(i).suit) {
                case "H" -> counterH++;
                case "D" -> counterD++;
                case "C" -> counterC++;
                default -> counterS++;
            }
        }
        suitNumbers.add(counterH);
        suitNumbers.add(counterD);
        suitNumbers.add(counterC);
        suitNumbers.add(counterS);
        String otherKingsTrump;
        int highestSuit = suitNumbers.getFirst();
        for (int amount : suitNumbers) {
            if (amount > highestSuit) {
                highestSuit = amount;

            }
        }

        if (highestSuit == suitNumbers.getFirst()) {
            suitImage = "resources/cardimages/hearts.png";
            otherKingsTrump = "H";
            allSuits.removeFirst();
        } else if (highestSuit == suitNumbers.get(1)) {
            suitImage = "resources/cardimages/diamonds.png";
            otherKingsTrump = "D";
            allSuits.remove(1);
        } else if (highestSuit == suitNumbers.get(2)) {
            suitImage = "resources/cardimages/clubs.png";
            otherKingsTrump = "C";
            allSuits.remove(2);
        } else {
            suitImage = "resources/cardimages/spades.png";
            otherKingsTrump = "S";
            allSuits.remove(3);
        }
        trump.add(otherKingsTrump);
        trumpsImage.add(suitImage);
        SaxionApp.drawBorderedText("the king calls ", 700, 500, 50);
        SaxionApp.drawImage(suitImage, 1000, 500, 50, 50);
        SaxionApp.sleep(2);
    }


    public void otherMinistersCall(Player player1, Player player2, Player player3, Player player4, ArrayList<Character> allSuits, ArrayList<String> trumps, ArrayList<String> trumpsImage) {
        String suitImage = "";
        String ministersTrump;
        int chosenSuit = SaxionApp.getRandomValueBetween(0, allSuits.size());
        if (allSuits.get(chosenSuit) == 'H') {
            suitImage = "resources/cardimages/hearts.png";
            ministersTrump = "H";
        } else if (allSuits.get(chosenSuit) == 'D') {
            suitImage = "resources/cardimages/diamonds.png";
            ministersTrump = "D";
        } else if (allSuits.get(chosenSuit) == 'C') {
            suitImage = "resources/cardimages/clubs.png";
            ministersTrump = "C";
        } else {
            suitImage = "resources/cardimages/spades.png";
            ministersTrump = "S";
        }
        trumps.add(ministersTrump);
        trumpsImage.add(suitImage);
        SaxionApp.drawBorderedText("the minister calls  ", 700, 500, 50);
        SaxionApp.drawImage(suitImage, 1100, 500, 50, 50);
        SaxionApp.sleep(2);
        SaxionApp.clear();
        SaxionApp.drawImage("resources/file.png", 0, 0, 1920, 1024);
        player1.showMyCards(player1.playerCards,680, 750, 5, 0);
        player2.showCardsEven(player2.playerCards,1500, 230, 5, 0);
        player3.showCardsOdd(player3.playerCards, 680, 50, 5, 0);
        player4.showCardsEven(player4.playerCards, 140, 230, 5, 0);
    }
}
