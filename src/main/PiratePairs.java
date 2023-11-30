public class PiratePairs {
    static Player[] players = new Player[4];
    static Deck deck = new Deck();
    static Player p1 = new Player("Dave", deck);
    static Player p2 = new Player("Mark", deck);
    static Player p3 = new Player("Henrey", deck);
    static Player p4 = new Player("Chris", deck);
    static int maxTurns = 0;
    static boolean playing = true;
    static int maxScore = 21;
    static Player losingPlayer;
    
    public static void main(String[] args){
        players[0] = p1;
        players[1] = p2;
        players[2] = p3;
        players[3] = p4;
        Start();

        while (playing) {
            for (Player player : players) {
                if (playing) {
                    TakeTurn(player);
                } else {
                    break;
                }
            }
            printPlayerStatus();
            printLine();
            
        }
        System.out.println(losingPlayer.getName() + " loses");
    }

    public static void Start() {
        deck.shuffle();
        System.out.println("Welcome");
        printLine();

        for (Player player : players) {
            player.takeCard(deck.dealCard());
        }
        printPlayerStatus();
        printLine();
    }

    public static void TakeTurn(Player player) {
        if (player.hasCards() ) {
            
            
            if (player.wantsCard(cardsOnTable())) {
                player.takeCard(deck.dealCard());
            } else {
                
            player.takeMininimum(cardsOnTable(), minimumPlayer());
            }
            
        } else {
            player.takeCard(deck.dealCard());
        }
    }

    public static void printLine() {
        System.out.println("-----------------------");
    }

    public static void printPlayerStatus() {
        for (int i = 0; i < players.length; i++) {
            System.out.println(players[i].getName() + " Hand: " + players[i].handToString() + " Score: " + players[i].getScore());
        }
    }

    static int[] cardsOnTable() {
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

    static Player minimumPlayer() {
        
        for (Player player : players) {
            for (int i = 0; i < player.getHand().length; i++) {
                if (player.getHand()[i] == minimumCard(cardsOnTable())) {
                    return player;
                }
            }
        }
        
        return null;
    }
    
    static int minimumCard(int[] cards) {
        int minimum = cards[0];
        for (int i = 0; i < cards.length; i++) {
            if (cards[i] < minimum) {
                minimum = cards[i];
            }
        }
        return minimum;
    }

    static void removePlayer(Player player) {
        losingPlayer = player;
        playing = false;
        
    }

    public static int getMaxScore() {
        return maxScore;
    }
}

