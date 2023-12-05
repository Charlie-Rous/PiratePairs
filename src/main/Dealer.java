public class Dealer {
    private Deck deck;
    public Player[] players = new Player[0];
    public static int maxScore = 21;

    public Dealer(Deck deck) {
        this.deck = deck;
    }

    public void StartGame() {
        PiratePairs.setPlaying(true);
        deck.shuffle();
        System.out.println("Welcome");
        printLine();

        for (Player player : players) {
            player.takeCard(deck.dealCard());
        }
        printPlayerStatus();
        printLine();
    }

    public void playOneRound() {
        for (Player player : players) {
            if (PiratePairs.getPlaying()) {
                TakeTurn(player);
                checkScore(player);
            } else {
                break;
            }
        }
        if (PiratePairs.playing) {
            printPlayerStatus();
        }

        printLine();

    }

    private void TakeTurn(Player player) {
        if (player.hasCards()) {
            if (player.wantsCard(cardsOnTable())) {
                player.takeCard(deck.dealCard());
            } else {

                player.takeMininimum(cardsOnTable(), minimumPlayer());
            }

        } else {
            player.takeCard(deck.dealCard());
        }
    }

    public int[] cardsOnTable() {
        int length = 0;

        for (Player player : players) {
            length += player.getHand().length;
        }
        int[] cardsOnTable = new int[length];
        int index = 0;
        for (Player player : players) {
            for (int i = 0; i < player.getHand().length; i++) {
                cardsOnTable[index] = player.getHand()[i];
                index++;

            }

        }
        return cardsOnTable;
    }

    public Player minimumPlayer() {

        for (Player player : players) {
            for (int i = 0; i < player.getHand().length; i++) {
                if (player.getHand()[i] == getMinimumCard(cardsOnTable())) {
                    return player;
                }
            }
        }

        return null;
    }

    private int getMinimumCard(int[] cards) {
        int minimum = cards[0];
        for (int i = 0; i < cards.length; i++) {
            if (cards[i] < minimum) {
                minimum = cards[i];
            }
        }
        return minimum;
    }

    private void printLine() {
        System.out.println("-----------------------");
    }

    private void printPlayerStatus() {
        for (int i = 0; i < players.length; i++) {
            System.out.println(
                    players[i].getName() + "'s Hand: " + players[i].handToString() + " Score: "
                            + players[i].getScore());
        }
    }

    private void checkScore(Player player) {
        if (player.getScore() >= maxScore) {
            playerLoses(player);
        }
    }

    private void playerLoses(Player player) {
        PiratePairs.setPlaying(false);
        System.out.println(player.getName() + " Loses!");

    }

    public void addPlayer(Player player) {
        Player[] tempPlayers = new Player[players.length + 1];

        for (int i = 0; i < players.length; i++) {
            tempPlayers[i] = players[i];
        }
        tempPlayers[players.length] = player;

        players = tempPlayers;

    }
}
