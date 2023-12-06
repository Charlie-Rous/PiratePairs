public class Dealer {
    private Deck deck;
    public Player[] players = new Player[0];
    public static int maxScore = 60;
    private int[] strategyLosses = new int[Player.NUM_STRATEGIES];

    public Dealer(Deck deck) {
        this.deck = deck;
    }

    public void StartGame() {
        // set score player has to have to lose;

        maxScore = (maxScore / players.length) + 1;

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
                // if player doest want card from deck they take lowest card on the table
                player.takeMininimum(cardsOnTable(), minimumPlayer());
            }

        } else {
            // player always takes a card when their hand is empty
            player.takeCard(deck.dealCard());
        }
    }

    public int[] cardsOnTable() {
        // gets cards in all players hands
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
        // finds player with lowest card in their hand
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
        // returns minimum card from a set of
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
        if (player.getStrategy() == -1) {
            strategyLosses[Player.NUM_STRATEGIES - 1] += 1;
            System.out.println("They used the default strategy");
        } else {
            strategyLosses[player.getStrategy()] += 1;
            System.out.println("They used strategy " + player.getStrategy());
        }

    }

    public void addPlayer(Player player) {
        Player[] tempPlayers = new Player[players.length + 1];
        for (int i = 0; i < players.length; i++) {
            tempPlayers[i] = players[i];
        }
        tempPlayers[players.length] = player;

        players = tempPlayers;

    }

    public void resetGame() {
        Names.resetNames();
        deck = new Deck();
        players = new Player[0];
        PiratePairs.populateGame(PiratePairs.numPlayers);
        StartGame();
    }

    public void printStrategies() {
        System.out.println("Strategy losses:");
        for (int i = 0; i < Player.NUM_STRATEGIES; i++) {
            if (i != strategyLosses.length - 1) {
                System.out.println("Strategy " + i + ": " + strategyLosses[i]);
            } else {
                System.out.println("Default Strategy: " + strategyLosses[i]);
            }
            
        }
    }
}
