import nl.saxion.app.SaxionApp;

import java.util.ArrayList;

public class Player {
    ArrayList<Card> playerCards = new ArrayList<>();
    String name;


    public ArrayList<Card> createDeckOfCards(String playerNumber, ArrayList<Card> cards) {

        for (int i = 0; i < 13; i++) {
            int random = SaxionApp.getRandomValueBetween(0, cards.size());
            cards.get(random).player = playerNumber;
            playerCards.add(cards.get(random));
            cards.remove(random);
        }
        return playerCards;

    }

    public void showMyCards(ArrayList<Card> player, int x, int y, int number, int first) {
        for (first = first; first < number; first++) {
            SaxionApp.drawImage(player.get(first).picture, x, y, 150, 200);
            SaxionApp.drawBorderedText(first + 1 + "", x + 6, y - 15, 14);
            x += 30;
        }
    }


    public void showCardsOdd(ArrayList<Card> player, int x, int y, int number, int first) {
        for (first = first; first < number; first++) {
            SaxionApp.drawImage(player.get(first).back, x, y, 150, 200);
            x += 30;
        }

    }


    public void showCardsEven(ArrayList<Card> player, int x, int y, int number, int first) {
        for (first = first; first < number; first++) {
            SaxionApp.drawImage(player.get(first).backRotated, x, y, 200, 150);
            SaxionApp.printLine();
            y += 30;
        }
    }
    public ArrayList<Card> sortCards (ArrayList<Card> playerCards){
        ArrayList<Card> sortedCards = new ArrayList<>();
        ArrayList<String> suits = new ArrayList<>();
        suits.add("H");
        suits.add("S");
        suits.add("D");
        suits.add("C");
        for (String suit : suits) {
            for (Card card : playerCards) {
                if (card.suit.equals(suit)) {
                    sortedCards.add(card);
                }
            }
        }return sortedCards;
    }




}
