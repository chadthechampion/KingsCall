import nl.saxion.app.SaxionApp;

import java.util.ArrayList;

public class Winner {
    public String roundWinner(ArrayList<Card> playedCards, ArrayList<String> trumps, String minister) {

        ArrayList<Card> nonTrumps = new ArrayList<>();
        ArrayList<Card> kingsTrump = new ArrayList<>();
        ArrayList<Card> ministerTrump = new ArrayList<>();

        for (Card card : playedCards) {
            if (card.suit.equals(trumps.getFirst())) {
                kingsTrump.add(card);
            } else if (card.suit.equals(trumps.get(1)) && card.player.equals(minister)) {
                ministerTrump.add(card);
            } else if (card.suit.equals(playedCards.getFirst().suit)){
                nonTrumps.add(card);
            }

        }


        if (!kingsTrump.isEmpty()) {
            Card biggest = kingsTrump.getFirst();
            for (Card card : kingsTrump) {
                if (card.value > biggest.value) {
                    biggest = card;
                }
            }
            return biggest.player;
        } else if (!ministerTrump.isEmpty()) {
            if (!(playedCards.getFirst().suit.equals(trumps.get(1)))){
                return minister;
            }
            else {
                Card biggest = nonTrumps.getFirst();
                for (Card card: nonTrumps){
                    if (card.value > biggest.value){
                        biggest = card;
                    }
                }
                return biggest.player;
            }

        } else {
            Card biggest = nonTrumps.getFirst();
            for (Card card : nonTrumps) {
                if (card.value > biggest.value) {
                    biggest = card;
                }
            }
            if(biggest.player.equals(minister) && biggest.suit.equals(trumps.get(1))){
                return minister;
            }else {
                return biggest.player;
            }

        }
    }

}
