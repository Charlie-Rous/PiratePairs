public class PiratePairs {
    static Player[] players = new Player[2];
    static Deck deck = new Deck();
    static Player p1 = new Player("Dave", deck, 50);
    static Player p2 = new Player("Mark", deck, 30);
    static int maxTurns = 0;
    static boolean playing = true;
    
    static int minimum = 10;
    
    public static void main(String[] args){
        players[0] = p1;
        players[1] = p2;
        Start();

        while (playing) {
            for (Player player : players) {
                TakeTurn(player);
            }
            printPlayerStatus();
            printLine();
            checkEndConditions();
                
        }
        
    }

    public static void Start() {
        deck.populateDeck();
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
        if (player.wantsCard(cardsOnTable()) || !player.hasCards()) {
            player.takeCard(deck.dealCard());
        } else {
            player.takeMininimum(cardsOnTable(), minimumPlayer());
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

    static void checkEndConditions() {
        if (players.length == 1) {
            playing = false;
        }
        if (maxTurns >= 20) {
            playing = false;
        } else {
            maxTurns++;
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
        Player minimumPlayer = players[0];
        for (Player player : players) {
            for (int i = 0; i < player.getHand().length; i++) {
                if (player.getHand()[i] <= minimum) {
                    minimumPlayer = player;
                }
            }
        }
        return minimumPlayer;
    }
}

