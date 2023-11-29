public class PiratePairs {
    static Player[] players = new Player[2];
    static Deck deck = new Deck();
    static Player p1 = new Player("Dave", deck, 50);
    static Player p2 = new Player("Mark", deck, 60);
    
    
    public static void main(String[] args){
        boolean playing = true;
        players[0] = p1;
        players[1] = p2;
        Start();
        p1.wantsCard();
        p2.wantsCard();

        // while (playing) {
        //     for (Player player : players) {
        //         TakeTurn(player);
        //     }
        // }
        
    }

    public static void Start() {
        deck.populateDeck();
        deck.shuffle();
        System.out.println("Welcome");
        printLine();

        for (Player player : players) {
            player.takeCard(deck.dealCard());
        }
        printPlayerHands();
    }

    public static void TakeTurn(Player player) {
        if (player.wantsCard()) {
            player.takeCard(deck.dealCard());
        }
    }

    public static void printLine() {
        System.out.println("-----------------------");
    }


    public static void printPlayerHands() {
        for (int i = 0; i < players.length; i++) {
            System.out.println(players[i].getName() + ": " + players[i].handToString());
        }
    }
}
