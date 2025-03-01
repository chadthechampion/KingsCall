import nl.saxion.app.SaxionApp;

import java.util.ArrayList;

public class Round {
    public ArrayList<Card> round(Player player1, Player player2, Player player3, Player player4, String winner, String minister, ArrayList<String> trumps, ArrayList<String> trumpsImage) {
        SaxionApp.drawBorderedText("kings call: ", 10, 10, 25);
        SaxionApp.drawImage(trumpsImage.getFirst(), 130, 10, 25, 25);
        SaxionApp.drawBorderedText("ministers call: ", 10, 40, 25);
        SaxionApp.drawImage(trumpsImage.get(1), 170, 40, 25, 25);
        BetterMove betterMove = new BetterMove();
        ArrayList<Card> playedCards = new ArrayList<>();
        ArrayList<Player> players = new ArrayList<>();
        int cardNumber;
        String playedSuit;
        switch (winner) {
            case "player 1" -> {
                SaxionApp.printLine("Choose a card");
                players.add(player2);
                players.add(player3);
                players.add(player4);
                cardNumber = SaxionApp.readInt() - 1;
                while (cardNumber >= player1.sortCards(player1.playerCards).size() || cardNumber < 0) {
                    SaxionApp.printLine("card number does not exists.");
                    cardNumber = SaxionApp.readInt() - 1;
                }
                SaxionApp.clear();
                SaxionApp.drawImage("resources/file.png", 0, 0, 1920, 1024);
                SaxionApp.drawBorderedText("kings call: ", 10, 10, 25);
                SaxionApp.drawImage(trumpsImage.getFirst(), 130, 10, 25, 25);
                SaxionApp.drawBorderedText("ministers call: ", 10, 40, 25);
                SaxionApp.drawImage(trumpsImage.get(1), 170, 40, 25, 25);
                SaxionApp.drawImage(player1.sortCards(player1.playerCards).get(cardNumber).picture, 800, 500, 150, 200);
                AudioHelper.play("resources/music/throwing.wav", false);
                playedSuit = player1.sortCards(player1.playerCards).get(cardNumber).suit;
                playedCards.add(player1.sortCards(player1.playerCards).get(cardNumber));
                player1.playerCards.remove(player1.sortCards(player1.playerCards).get(cardNumber));

                player1.showMyCards(player1.sortCards(player1.playerCards), 670, 750, player1.playerCards.size(), 0);
                player2.showCardsEven(player2.playerCards, 1500, 380, player2.playerCards.size(), 0);
                player3.showCardsOdd(player3.playerCards, 830, 50, player3.playerCards.size(), 0);
                player4.showCardsEven(player4.playerCards, 140, 380, player4.playerCards.size(), 0);


                for (Player player : players) {
                    if (player.equals(player2)) {
                        betterMove.checkForBetterMove(playedCards, players, playedSuit, trumps, player, player.playerCards, player1, player2, player3, player4, minister, 1000, 350);
                        AudioHelper.play("resources/music/throwing.wav", false);
                        SaxionApp.sleep(1);
                    } else if (player.equals(player3)) {
                        betterMove.checkForBetterMove(playedCards, players, playedSuit, trumps, player, player.playerCards, player1, player2, player3, player4, minister, 800, 260);
                        AudioHelper.play("resources/music/throwing.wav", false);
                        SaxionApp.sleep(1);
                    }else {
                        betterMove.checkForBetterMove(playedCards, players, playedSuit, trumps, player, player.playerCards, player1, player2, player3, player4, minister, 600, 350);
                        AudioHelper.play("resources/music/throwing.wav", false);
                        SaxionApp.sleep(1);

                    }
                }
            }

            case "player 2" -> {
                players.add(player3);
                players.add(player4);
                players.add(player1);
                cardNumber = SaxionApp.getRandomValueBetween(0, player2.playerCards.size());
                playedSuit = player2.playerCards.get(cardNumber).suit;
                SaxionApp.drawImage(player2.playerCards.get(cardNumber).picture, 1000, 350, 150, 200);
                AudioHelper.play("resources/music/throwing.wav", false);
                SaxionApp.sleep(1);
                playedCards.add(player2.playerCards.get(cardNumber));
                player2.playerCards.remove(cardNumber);
                for (Player player : players) {
                    if (player.equals(player3)) {
                        betterMove.checkForBetterMove(playedCards, players, playedSuit, trumps, player, player.playerCards, player1, player2, player3, player4, minister, 800, 260);
                        AudioHelper.play("resources/music/throwing.wav", false);
                        SaxionApp.sleep(1);
                    } else if (player.equals(player4)) {
                        betterMove.checkForBetterMove(playedCards, players, playedSuit, trumps, player, player.playerCards, player1, player2, player3, player4, minister, 600, 350);
                        AudioHelper.play("resources/music/throwing.wav", false);
                        SaxionApp.sleep(1);
                    } else {
                        cardNumber = SaxionApp.readInt() - 1;
                        String suitExists = "";
                        for (Card card : player.sortCards(player1.playerCards)) {
                            if (card.suit.equals(playedSuit)) {
                                suitExists = playedSuit;
                                break;
                            }
                        }
                        while ((cardNumber >= player1.playerCards.size() || cardNumber < 0) || (!player.sortCards(player1.playerCards).get(cardNumber).suit.equals(playedSuit) && !suitExists.isEmpty())) {
                            if (cardNumber >= player1.playerCards.size() || cardNumber < 0) {
                                SaxionApp.printLine("card number does not exists.");
                            } else {
                                SaxionApp.printLine("you have to play the played suit");
                            }
                            cardNumber = SaxionApp.readInt() - 1;
                        }
                        SaxionApp.drawImage(player.sortCards(player1.playerCards).get(cardNumber).picture, 800, 500, 150, 200);
                        AudioHelper.play("resources/music/throwing.wav", false);
                        SaxionApp.sleep(1);
                        playedCards.add(player.sortCards(player1.playerCards).get(cardNumber));
                        player1.playerCards.remove(player1.sortCards(player1.playerCards).get(cardNumber));
                        break;

                    }

                }
            }
            case "player 3" -> {
                players.add(player4);
                players.add(player1);
                players.add(player2);

                cardNumber = SaxionApp.getRandomValueBetween(0, player3.playerCards.size());

                playedSuit = player3.playerCards.get(cardNumber).suit;
                SaxionApp.drawImage(player3.playerCards.get(cardNumber).picture, 800, 260, 150, 200);
                AudioHelper.play("resources/music/throwing.wav", false);
                SaxionApp.sleep(1);
                SaxionApp.sleep(1);
                playedCards.add(player3.playerCards.get(cardNumber));

                player3.playerCards.remove(cardNumber);


                for (Player player : players) {
                    if (player.equals(player4)) {
                        betterMove.checkForBetterMove(playedCards, players, playedSuit, trumps, player, player.playerCards, player1, player2, player3, player4, minister, 600, 350);
                        AudioHelper.play("resources/music/throwing.wav", false);
                        SaxionApp.sleep(1);
                    } else if (player.equals(player1)) {
                        cardNumber = SaxionApp.readInt() - 1;
                        String suitExists = "";
                        for (Card card : player.sortCards(player1.playerCards)) {
                            if (card.suit.equals(playedSuit)) {
                                suitExists = playedSuit;
                                break;
                            }
                        }
                        while ((cardNumber >= player1.playerCards.size() || cardNumber < 0) || (!player.sortCards(player1.playerCards).get(cardNumber).suit.equals(playedSuit) && !suitExists.isEmpty())) {
                            if (cardNumber >= player1.playerCards.size() || cardNumber < 0) {
                                SaxionApp.printLine("card number does not exists.");
                            } else {
                                SaxionApp.printLine("you have to play the played suit");
                            }
                            cardNumber = SaxionApp.readInt() - 1;
                        }
                        SaxionApp.drawImage(player1.sortCards(player1.playerCards).get(cardNumber).picture, 800, 500, 150, 200);
                        AudioHelper.play("resources/music/throwing.wav", false);
                        SaxionApp.sleep(1);
                        playedCards.add(player1.sortCards(player1.playerCards).get(cardNumber));
                        player1.playerCards.remove(player1.sortCards(player1.playerCards).get(cardNumber));


                    } else {
                        betterMove.checkForBetterMove(playedCards, players, playedSuit, trumps, player, player.playerCards, player1, player2, player3, player4, minister, 1000, 350);
                        AudioHelper.play("resources/music/throwing.wav", false);
                        SaxionApp.sleep(1);
                    }
                }
            }
            case "player 4" -> {
                players.add(player1);
                players.add(player2);
                players.add(player3);

                cardNumber = SaxionApp.getRandomValueBetween(0, player4.playerCards.size());
                playedSuit = player4.playerCards.get(cardNumber).suit;
                SaxionApp.drawImage(player4.playerCards.get(cardNumber).picture, 600, 350, 150, 200);
                AudioHelper.play("resources/music/throwing.wav", false);
                SaxionApp.sleep(1);
                SaxionApp.sleep(1);
                playedCards.add(player4.playerCards.get(cardNumber));
                player4.playerCards.remove(cardNumber);

                for (Player player : players) {
                    if (player.equals(player3)) {
                        betterMove.checkForBetterMove(playedCards, players, playedSuit, trumps, player, player.playerCards, player1, player2, player3, player4, minister, 800, 260);
                        AudioHelper.play("resources/music/throwing.wav", false);
                        SaxionApp.sleep(1);
                    } else if (player.equals(player2)) {
                        betterMove.checkForBetterMove(playedCards, players, playedSuit, trumps, player, player.playerCards, player1, player2, player3, player4, minister, 1000, 350);
                        AudioHelper.play("resources/music/throwing.wav", false);
                        SaxionApp.sleep(1);

                    } else {
                        cardNumber = SaxionApp.readInt() - 1;
                        String suitExists = "";
                        for (Card card : player.playerCards) {
                            if (card.suit.equals(playedSuit)) {
                                suitExists = playedSuit;
                                break;
                            }
                        }
                        while ((cardNumber >= player1.playerCards.size() || cardNumber < 0) || (!player.sortCards(player1.playerCards).get(cardNumber).suit.equals(playedSuit) && !suitExists.isEmpty())) {
                            if (cardNumber >= player1.playerCards.size() || cardNumber < 0) {
                                SaxionApp.printLine("card number does not exists.");
                            } else {
                                SaxionApp.printLine("you have to play the played suit");
                            }
                            cardNumber = SaxionApp.readInt() - 1;
                        }
                        SaxionApp.drawImage(player1.sortCards(player1.playerCards).get(cardNumber).picture, 800, 500, 150, 200);
                        AudioHelper.play("resources/music/throwing.wav", false);
                        SaxionApp.sleep(1);
                        playedCards.add(player1.sortCards(player1.playerCards).get(cardNumber));
                        player1.playerCards.remove(player1.sortCards(player1.playerCards).get(cardNumber));
                    }
                }
            }

        }
        return playedCards;
    }
}
