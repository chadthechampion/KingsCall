import java.util.ArrayList;

public class CreateCards {
    public ArrayList<Card> createCards() {
        ArrayList<Card> cards = new ArrayList<>();
        String[] suits = new String[4];
        suits[0] = "C";
        suits[1] = "H";
        suits[2] = "S";
        suits[3] = "D";
        char[] values = new char[4];
        values[0] = 'J';
        values[1] = 'Q';
        values[2] = 'K';
        values[3] = 'A';
        for (String suit : suits) {
            for (int i = 2; i <= 10; i++) {
                Card card = new Card();
                card.suit = suit;
                card.value = i;
                card.picture = "resources/cardimages/" + i + suit + ".png";
                cards.add(card);
            }
        }
        for (String suit : suits) {
            for (char value : values) {
                Card card = new Card();
                if (value == 'J') {
                    card.suit = suit;
                    card.value = 11;
                    card.picture = "resources/cardimages/" + value + suit + ".png";
                    cards.add(card);
                }
                if (value == 'Q') {
                    card.suit = suit;
                    card.value = 12;
                    card.picture = "resources/cardimages/" + value + suit + ".png";
                    cards.add(card);
                }
                if (value == 'K') {
                    card.suit = suit;
                    card.value = 13;
                    card.picture = "resources/cardimages/" + value + suit + ".png";
                    cards.add(card);
                }
                if (value == 'A') {
                    card.suit = suit;
                    card.value = 14;
                    card.picture = "resources/cardimages/" + value + suit + ".png";
                    cards.add(card);
                }
            }
        }
        for (Card card : cards) {
            card.back = "resources/cardimages/gray_back.png";
            card.backRotated = "resources/cardimages/gray_back_kaj.png";
        }
        return cards;
    }
}
