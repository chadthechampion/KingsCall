import nl.saxion.app.SaxionApp;

import java.util.ArrayList;

public class BetterMove {
    public void checkForBetterMove(ArrayList<Card> playedCards, ArrayList<Player> players, String playedSuit, ArrayList<String> trumps, Player player, ArrayList<Card> playerCards, Player player1, Player player2, Player player3, Player player4, String minister, int x, int y) {
        ArrayList<Card> nonTrumps = new ArrayList<>();
        ArrayList<Card> kingsTrump = new ArrayList<>();
        ArrayList<Card> ministerTrump = new ArrayList<>();
        ArrayList<Card> sameSuit = new ArrayList<>();
        Card bestMove = playerCards.getFirst();
        Card biggest = playedCards.getFirst();
        boolean betterMove = false;
        String winnerByNow;
        for (Card card : playedCards) {
            if (card.suit.equals(trumps.getFirst())) {
                kingsTrump.add(card);
            } else if (card.suit.equals(trumps.get(1)) && card.player.equals(minister)) {
                ministerTrump.add(card);
            } else if (card.suit.equals(playedCards.getFirst().suit)) {
                nonTrumps.add(card);
            }
        }
        if (!kingsTrump.isEmpty()) {
            biggest = kingsTrump.getFirst();
            for (Card card : kingsTrump) {
                if (card.value > biggest.value) {
                    biggest = card;
                }
            }
            winnerByNow = biggest.player;
        } else if (!ministerTrump.isEmpty()) {
            if (!(playedCards.getFirst().suit.equals(trumps.get(1)))) {
                winnerByNow = minister;
            } else {
                biggest = nonTrumps.getFirst();
                for (Card card : nonTrumps) {
                    if (card.value > biggest.value) {
                        biggest = card;
                    }
                }
                winnerByNow = biggest.player;
            }

        } else {
            biggest = nonTrumps.getFirst();
            for (Card card : nonTrumps) {
                if (card.value > biggest.value) {
                    biggest = card;
                }
            }
            winnerByNow = biggest.player;

        }
        if (player.equals(player3)) {
            if (winnerByNow.equals("player 1")) {
                if (biggest.suit.equals(trumps.getFirst())) {
                    for (Card card : playerCards) {
                        if ((!card.suit.equals(trumps.getFirst())) && card.value < bestMove.value && card.suit.equals(playedSuit)) {
                            bestMove = card;
                            betterMove = true;
                            SaxionApp.drawImage(bestMove.picture, x, y, 150, 200);
                            playedCards.add(card);
                            playerCards.remove(bestMove);
                            SaxionApp.sleep(1);
                            break;
                        }
                        {

                        }
                    }
                } else {
                    for (Card card : playerCards) {
                        if (card.value < biggest.value && card.suit.equals(playedSuit)) {
                            bestMove = card;
                            betterMove = true;
                            SaxionApp.drawImage(bestMove.picture, x, y, 150, 200);
                            playedCards.add(card);
                            SaxionApp.sleep(1);
                            playerCards.remove(bestMove);
                            break;
                        }
                    }
                }


            } else {
                for (Card card : playerCards) {
                    if (card.suit.equals(playedSuit)) {
                        sameSuit.add(card);
                    }
                }
                if (sameSuit.isEmpty()) {
                    if (biggest.suit.equals(trumps.getFirst())) {
                        for (Card card : playerCards) {
                            if (card.suit.equals(trumps.getFirst()) && card.value > biggest.value) {
                                bestMove = card;
                                SaxionApp.drawImage(bestMove.picture, x, y, 150, 200);
                                playerCards.remove(bestMove);
                                playedCards.add(card);
                                SaxionApp.sleep(1);
                                betterMove = true;
                                break;
                            }
                        }

                    }
                } else {
                    if (!biggest.suit.equals(trumps.getFirst())) {
                        for (Card card : playerCards) {
                            if (card.suit.equals(playedSuit) && card.value > biggest.value) {
                                bestMove = card;
                                SaxionApp.drawImage(bestMove.picture, x, y, 150, 200);
                                playedCards.add(card);
                                playerCards.remove(bestMove);
                                betterMove = true;
                                break;
                            }
                        }

                    }
                }
            }
        }
        if (player.equals(player2)) {
            if (winnerByNow.equals("player 4")) {
                if (biggest.suit.equals(trumps.getFirst())) {
                    for (Card card : playerCards) {
                        if ((!card.suit.equals(trumps.getFirst())) && card.value < bestMove.value && card.suit.equals(playedSuit)) {
                            bestMove = card;
                            SaxionApp.drawImage(bestMove.picture, x, y, 150, 200);
                            playerCards.remove(bestMove);
                            playedCards.add(card);
                            SaxionApp.sleep(1);
                            betterMove = true;
                            break;
                        }
                    }
                } else {
                    for (Card card : playerCards) {
                        if (card.value < biggest.value && card.suit.equals(playedSuit)) {
                            bestMove = card;
                            betterMove = true;
                            SaxionApp.drawImage(bestMove.picture, x, y, 150, 200);
                            playedCards.add(card);
                            SaxionApp.sleep(1);
                            playerCards.remove(bestMove);
                            break;
                        }
                    }
                }


            } else {
                for (Card card : playerCards) {
                    if (card.suit.equals(playedSuit)) {
                        sameSuit.add(card);
                    }
                }
                if (sameSuit.isEmpty()) {
                    if (biggest.suit.equals(trumps.getFirst())) {
                        for (Card card : playerCards) {
                            if (card.suit.equals(trumps.getFirst()) && card.value > biggest.value) {
                                bestMove = card;
                                SaxionApp.drawImage(bestMove.picture, x, y, 150, 200);
                                playerCards.remove(bestMove);
                                playedCards.add(card);
                                SaxionApp.sleep(1);
                                betterMove = true;
                                break;
                            }
                        }

                    }
                } else {
                    if (!biggest.suit.equals(trumps.getFirst())) {
                        for (Card card : playerCards) {
                            if (card.suit.equals(playedSuit) && card.value > biggest.value) {
                                bestMove = card;
                                SaxionApp.drawImage(bestMove.picture, x, y, 150, 200);
                                playerCards.remove(bestMove);
                                playedCards.add(card);
                                betterMove = true;
                                break;
                            }
                        }

                    }
                }
            }
        }
        if (player.equals(player4)) {
            if (winnerByNow.equals("player 2")) {
                if (biggest.suit.equals(trumps.getFirst())) {
                    for (Card card : playerCards) {
                        if ((!card.suit.equals(trumps.getFirst())) && card.value < bestMove.value && card.suit.equals(playedSuit)) {
                            bestMove = card;
                            SaxionApp.drawImage(bestMove.picture, x, y, 150, 200);
                            playerCards.remove(bestMove);
                            playedCards.add(card);
                            SaxionApp.sleep(1);
                            betterMove = true;
                            break;
                        }
                    }
                } else {
                    for (Card card : playerCards) {
                        if (card.value < biggest.value && card.suit.equals(playedSuit)) {
                            bestMove = card;
                            betterMove = true;
                            SaxionApp.drawImage(bestMove.picture, x, y, 150, 200);
                            playedCards.add(card);
                            SaxionApp.sleep(1);
                            playerCards.remove(bestMove);
                            break;
                        }
                    }
                }


            } else {
                for (Card card : playerCards) {
                    if (card.suit.equals(playedSuit)) {
                        sameSuit.add(card);
                    }
                }
                if (sameSuit.isEmpty()) {
                    if (biggest.suit.equals(trumps.getFirst())) {
                        for (Card card : playerCards) {
                            if (card.suit.equals(trumps.getFirst()) && card.value > biggest.value) {
                                bestMove = card;
                                SaxionApp.drawImage(bestMove.picture, x, y, 150, 200);
                                playedCards.add(card);
                                ////
                                playerCards.remove(bestMove);
                                SaxionApp.sleep(1);
                                betterMove = true;
                                break;
                            }
                        }

                    }
                } else {
                    if (!biggest.suit.equals(trumps.getFirst())) {
                        for (Card card : playerCards) {
                            if (card.suit.equals(playedSuit) && card.value > biggest.value) {
                                bestMove = card;
                                SaxionApp.drawImage(bestMove.picture, x, y, 150, 200);
                                playedCards.add(card);
                                ////
                                playerCards.remove(bestMove);
                                betterMove = true;
                                break;
                            }
                        }

                    }
                }
            }
        }

        if (!betterMove) {
            int test = 0;
            for (Card card : player.playerCards) {
                if (card.suit.equals(playedSuit)) {
                    SaxionApp.drawImage(card.picture, x, y, 150, 200);
                    SaxionApp.sleep(1);
                    playedCards.add(card);
                    player.playerCards.remove(card);
                    test++;
                    bestMove = card;
                    break;
                }
            }
            if (test == 0) {
                if (player.name.equals(minister)) {
                    for (Card card : player.playerCards) {
                        if (card.suit.equals(trumps.get(1))) {
                            SaxionApp.drawImage(card.picture, x, y, 150, 200);
                            SaxionApp.sleep(1);
                            playedCards.add(card);
                            player.playerCards.remove(card);
                            test++;
                            bestMove = card;
                            break;
                        }

                    }
                    if (test == 0) {
                        for (Card card : player.playerCards) {
                            if (card.suit.equals(trumps.getFirst())) {
                                SaxionApp.drawImage(card.picture, x, y, 150, 200);
                                SaxionApp.sleep(1);
                                playedCards.add(card);
                                player.playerCards.remove(card);
                                test++;
                                bestMove = card;
                                break;
                            }
                        }
                        if (test == 0) {
                            int random = SaxionApp.getRandomValueBetween(0, player.playerCards.size());
                            SaxionApp.drawImage(player.playerCards.get(random).picture, x, y, 150, 200);
                            SaxionApp.sleep(1);
                            playedCards.add(player.playerCards.get(random));
                            player.playerCards.remove(player.playerCards.get(random));
                            bestMove = player.playerCards.get(random);

                        }
                    }
                } else {
                    for (Card card : player.playerCards) {
                        if (card.suit.equals(trumps.getFirst())) {
                            SaxionApp.drawImage(card.picture, x, y, 150, 200);
                            SaxionApp.sleep(1);
                            playedCards.add(card);
                            player.playerCards.remove(card);
                            test++;
                            bestMove = card;
                            break;
                        }
                    }
                    if (test == 0) {
                        for (Card card : player.playerCards) {
                            int random = SaxionApp.getRandomValueBetween(0, player.playerCards.size());
                            SaxionApp.drawImage(player.playerCards.get(random).picture, x, y, 150, 200);
                            SaxionApp.sleep(1);
                            playedCards.add(card);
                            player.playerCards.remove(card);
                            bestMove = card;
                            break;
                        }
                    }


                }

            }
        }

    }
}
