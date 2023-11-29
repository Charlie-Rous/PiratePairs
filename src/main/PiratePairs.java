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

        while (playing) {
            //game loop
        }
        
    }

    public static void Start() {
        deck.populateDeck();
        deck.shuffle();
        for (Player player : players) {
            player.addToHand(deck.dealCard());
        }
    }

    // public static void TakeTurn(Player player) {
    //     if (player.wantsCard()) {
    //         player.addToHand(deck.dealCard());
    //     }
    // }
}