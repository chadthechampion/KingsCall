import nl.saxion.app.SaxionApp;


public class Choose {
    public String chooseAPlayer() {
        String chosenPlayer = "";
        int playerNumber = SaxionApp.getRandomValueBetween(1, 5);
        if (playerNumber == 1) {
            chosenPlayer = "player 1";
        } else if (playerNumber == 2) {
            chosenPlayer = "player 2";
        } else if (playerNumber == 3) {
            chosenPlayer = "player 3";
        } else {
            chosenPlayer = "player 4";
        }
        return chosenPlayer;
    }


    public String chooseTheKing() {

        return chooseAPlayer();
    }


    public String chooseTheMinister(String king) {
        String minister = chooseAPlayer();
        while (minister.equals(king)) {
            minister = chooseAPlayer();
        }
        return minister;
    }
    public void showKingAndMinister (String king, String minister){
        switch (king) {
            case "player 1" -> SaxionApp.drawImage("resources/c1.png", 830, 950, 45, 45);
            case "player 2" -> SaxionApp.drawImage("resources/c2.png", 1700, 350, 45, 45);
            case "player 3" -> SaxionApp.drawImage("resources/c3.png", 820, 0, 45, 45);
            default -> SaxionApp.drawImage("resources/c4.png", 85, 350, 45, 45);
        }
        switch (minister) {
            case "player 1" -> SaxionApp.drawImage("resources/m1.png", 830, 950, 50, 50);
            case "player 2" -> SaxionApp.drawImage("resources/m2.png", 1700, 350, 50, 50);
            case "player 3" -> SaxionApp.drawImage("resources/m3.png", 820, 0, 50, 50);
            default -> SaxionApp.drawImage("resources/m4.png", 85, 350, 50, 50);
        }
    }
}
